package com.cardinalhealth.ids.boss.deidentify.job.config;

import javax.sql.DataSource;

import org.owasp.esapi.logging.slf4j.Slf4JLogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cardinalhealth.dm.fusion.BossUtilizationDrg;
import com.cardinalhealth.dm.fusion.PatientEntity;
import com.cardinalhealth.dm.invoice.InvoiceHeader;
import com.cardinalhealth.framework.utils.ESAPILogger;
import com.cardinalhealth.ids.boss.deidentify.job.constants.DeIdentificationJobConstants.JobConstant;
import com.cardinalhealth.ids.boss.deidentify.job.constants.DeIdentificationJobQueryConstants;
import com.cardinalhealth.ids.boss.deidentify.job.listener.ItemReadFailureLogListener;
import com.cardinalhealth.ids.boss.deidentify.job.listener.ItemWriteFailureLogListener;
import com.cardinalhealth.ids.boss.deidentify.job.listener.JobListener;
import com.cardinalhealth.ids.boss.deidentify.job.mapper.PatientEntityRowMapper;
import com.cardinalhealth.ids.boss.deidentify.job.mapper.UtilizationDRGRowMapper;

@Configuration
public class DeIdentificationjob {

	final ESAPILogger logger = new ESAPILogger(new Slf4JLogFactory().getLogger(DeIdentificationjob.class));

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private JobListener jobListener;

	@Autowired
	private ItemWriteFailureLogListener<InvoiceHeader> writeFailLogListner;

	@Autowired
	private ItemReadFailureLogListener<InvoiceHeader> readFailLogListner;

	@Bean
	public JdbcCursorItemReader<PatientEntity> patientReader(DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<PatientEntity>().dataSource(dataSource).name("patientReader")
				.sql(DeIdentificationJobQueryConstants.JobQueryConstants.READ_PATIENT)
				.rowMapper(new PatientEntityRowMapper()).build();
	}

	@Bean
	public JdbcBatchItemWriter<PatientEntity> patientWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<PatientEntity>().dataSource(dataSource).beanMapped()
				.sql(DeIdentificationJobQueryConstants.JobQueryConstants.UPDATE_PATIENT).build();
	}

	@Bean
	public JdbcCursorItemReader<BossUtilizationDrg> utilizationReader(DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<BossUtilizationDrg>().dataSource(dataSource).name("utilizationReader")
				.sql(DeIdentificationJobQueryConstants.JobQueryConstants.READ_UTILIZATION_DRG)
				.rowMapper(new UtilizationDRGRowMapper()).build();
	}

	@Bean
	public JdbcBatchItemWriter<BossUtilizationDrg> utilizationWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<BossUtilizationDrg>().dataSource(dataSource).beanMapped()
				.sql(DeIdentificationJobQueryConstants.JobQueryConstants.UPDATE_UTILIZATION_DRG).build();
	}

	@Bean
	public Job patientDeIdentifyJob(@Qualifier("postgresqlDataSource") DataSource dataSource) {
		logger.info("De-Identify Patient Job - started");

		return jobBuilderFactory.get(JobConstant.JOB_PATIENT).incrementer(new RunIdIncrementer()).start(patientStep(dataSource))
				.listener(jobListener).build();
	}
	
	@Bean
	public Job utilizationDRGDeIdentifyJob(@Qualifier("postgresqlDataSource") DataSource dataSource) {
		logger.info("De-Identify UtilizationDRG Job - started");

		return jobBuilderFactory.get(JobConstant.JOB_UTILIZATIONDRG)
				.incrementer(new RunIdIncrementer()).start(utilizationDRGStep(dataSource))
				.listener(jobListener).build();
	}

	@Bean
	public Step patientStep(DataSource dataSource) {
		logger.info("De-Identify job - Step Patient started");
		return stepBuilderFactory.get(JobConstant.STEP_PATIENT).<PatientEntity, PatientEntity>chunk(500)
				.reader(patientReader(dataSource)).listener(readFailLogListner).writer(patientWriter(dataSource))
				.listener(writeFailLogListner).build();
	}

	@Bean
	public Step utilizationDRGStep(DataSource dataSource) {
		logger.info("De-Identify job - Step Utilization DRG started");
		return stepBuilderFactory.get(JobConstant.STEP_UTILIZTION_DRG)
				.<BossUtilizationDrg, BossUtilizationDrg>chunk(500).reader(utilizationReader(dataSource))
				.listener(readFailLogListner).writer(utilizationWriter(dataSource)).listener(writeFailLogListner)
				.build();
	}
}
