package com.cardinalhealth.ids.boss.deidentify.job.service.impl;

import org.owasp.esapi.logging.slf4j.Slf4JLogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cardinalhealth.framework.utils.ESAPILogger;
import com.cardinalhealth.ids.boss.deidentify.job.constants.DeIdentificationJobConstants.JobConstant;
import com.cardinalhealth.ids.boss.deidentify.job.service.IDeIdentifyService;

/**
 * Service class for deIdentifyJob related services.
 */
@Service
public class DeIdentifyService implements IDeIdentifyService {

	final ESAPILogger logger = new ESAPILogger(new Slf4JLogFactory().getLogger(DeIdentifyService.class));

	@Autowired
	public JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("patientDeIdentifyJob")
	private Job patientDeIdentifyJob;
	
	@Autowired
	@Qualifier("utilizationDRGDeIdentifyJob")
	private Job utilizationDRGDeIdentifyJob;

	@Override
	//@Scheduled(cron = "${cron.expression.update.patient}")
	public void deidentifyData() {
		try {
			logger.info("deIdentifyJob is started ");
			JobParameters jobParameters = new JobParametersBuilder().addLong(JobConstant.TIME, System.currentTimeMillis())
					.toJobParameters();
			//Patient Job
			jobLauncher.run(patientDeIdentifyJob, jobParameters);
			
			//Utilization Job
			jobLauncher.run(utilizationDRGDeIdentifyJob, jobParameters);

		} catch (Exception e) {
			logger.error("Batch Job - Batch Job Failed || De-Identify Job ::" + e.getMessage());
		} 

	}
	
}
