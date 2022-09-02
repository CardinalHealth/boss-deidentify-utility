package com.cardinalhealth.ids.boss.deidentify.job.listener;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.owasp.esapi.logging.slf4j.Slf4JLogFactory;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cardinalhealth.framework.utils.ESAPILogger;

@Component
@StepScope
public class ItemProcessFailureLogListener<T, S> implements ItemProcessListener<T, S> {

	final ESAPILogger logger = new ESAPILogger(new Slf4JLogFactory().getLogger(ItemProcessFailureLogListener.class));

	@Value("#{stepExecution}")
	public StepExecution stepExecution;

	@Override
	public void beforeProcess(T item) {
		// keeping blank - can be used to for pre-setup
	}

	@Override
	public void afterProcess(T item, S result) {
		// keeping blank - can be used for post-setup
	}

	@Override
	public void onProcessError(T item, Exception exception) {
		logger.error("Batch Job - Batch Job Failed || step name - {} error cause - {}",
				stepExecution.getStepName(), ExceptionUtils.getStackTrace(exception));
	}

}
