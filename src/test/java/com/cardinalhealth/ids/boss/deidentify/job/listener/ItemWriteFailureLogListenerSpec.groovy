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
class ItemWriteFailureLogListenerSpec extends Specification {

	ItemWriteFailureLogListener itemWriteFailureLogListener = null;
	Exception exception = Mock();

	StepExecution stepExecution = null;
	ExecutionContext executionContext = Mock();
	JobExecution jobExecution = null;
	FileType fileType = Mock();

	def setup() {
		jobExecution = new JobExecution(JobConstant.JOB_PATIENT, TestUtils.defaultJobParameters());
		stepExecution = new StepExecution(JobConstant.STEP_PATIENT);
		itemWriteFailureLogListener = new ItemWriteFailureLogListener(stepExecution:stepExecution);
	}


	def 'Test beforeWrite'() {
		given:
		String status = "Sucess";

		when:
		itemWriteFailureLogListener.beforeWrite(_);
		then: "response should be  generated"
	}
	
	def 'Test afterWrite'() {
		given:
		String status = "Sucess";

		when:
		itemWriteFailureLogListener.afterWrite(_);
		then: "response should be  generated"
	}
	
	
	def 'Test onWriteError'() {
		given:
		String status = "Sucess";

		when:
		itemWriteFailureLogListener.onWriteError(exception,_);
		
		then: "response should be  generated"
	}
}
