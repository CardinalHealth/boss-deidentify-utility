/**
 * 
 */
package com.cardinalhealth.ids.boss.deidentify.job.listener;

import org.owasp.esapi.logging.slf4j.Slf4JLogFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import com.cardinalhealth.framework.utils.ESAPILogger;

/**
 * @author mohan.arora
 *
 */
@Component
public class JobListener implements JobExecutionListener {
	
	final ESAPILogger logger = new ESAPILogger(new Slf4JLogFactory().getLogger(JobListener.class));


	@Override
	public void beforeJob(JobExecution jobExecution) {
		// No need to implement
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// No need to implement
	}

}
