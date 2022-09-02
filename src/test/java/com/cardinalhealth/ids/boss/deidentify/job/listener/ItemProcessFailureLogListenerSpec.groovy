package com.cardinalhealth.ids.boss.deidentify.job.listener

import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.StepExecution
import org.springframework.batch.item.ExecutionContext

import com.cardinalhealth.dm.fusion.FileType
import com.cardinalhealth.ids.boss.deidentify.job.constants.DeIdentificationJobConstants.JobConstant
import com.cardinalhealth.ids.boss.deidentify.job.test.utils.TestUtils

import spock.lang.Specification

/**
 * @author mohan.arora
 *
 */
class ItemProcessFailureLogListenerSpec extends Specification {

	ItemProcessFailureLogListener itemProcessFailureLogListener = null;
	Exception exception = Mock();

	StepExecution stepExecution = null;
	ExecutionContext executionContext = Mock();
	JobExecution jobExecution = null;
	FileType fileType = Mock();

	def setup() {
		jobExecution = new JobExecution(JobConstant.JOB_PATIENT, TestUtils.defaultJobParameters());
		stepExecution = new StepExecution(JobConstant.STEP_PATIENT);
		itemProcessFailureLogListener = new ItemProcessFailureLogListener(stepExecution:stepExecution);
	}


	def 'Test afterProcess'() {
		given:
		String status = "Sucess";

		when:
		itemProcessFailureLogListener.afterProcess(stepExecution, null);
		then: "response should be  generated"
	}
	
	def 'Test beforeProcess'() {
		given:
		String status = "Sucess";

		when:
		itemProcessFailureLogListener.beforeProcess(stepExecution);
		then: "response should be  generated"
	}
	
	
	def 'Test onProcessError'() {
		given:
		String status = "Sucess";

		when:
		itemProcessFailureLogListener.onProcessError(stepExecution,exception);
		
		then: "response should be  generated"
	}
}
