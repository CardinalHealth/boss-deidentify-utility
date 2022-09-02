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
class ItemReadFailureLogListenerSpec extends Specification {

	ItemReadFailureLogListener itemReadFailureLogListener = null;
	Exception exception = Mock();

	StepExecution stepExecution = null;
	ExecutionContext executionContext = Mock();
	JobExecution jobExecution = null;
	FileType fileType = Mock();

	def setup() {
		jobExecution = new JobExecution(JobConstant.JOB_PATIENT, TestUtils.defaultJobParameters());
		stepExecution = new StepExecution(JobConstant.STEP_PATIENT);
		itemReadFailureLogListener = new ItemReadFailureLogListener(stepExecution:stepExecution);
	}


	def 'Test beforeRead'() {
		given:
		String status = "Sucess";

		when:
		itemReadFailureLogListener.beforeRead();
		then: "response should be  generated"
	}
	
	def 'Test afterRead'() {
		given:
		String status = "Sucess";

		when:
		itemReadFailureLogListener.afterRead(stepExecution);
		then: "response should be  generated"
	}
	
	
	def 'Test onReadError'() {
		given:
		String status = "Sucess";

		when:
		itemReadFailureLogListener.onReadError(exception);
		
		then: "response should be  generated"
	}
}
