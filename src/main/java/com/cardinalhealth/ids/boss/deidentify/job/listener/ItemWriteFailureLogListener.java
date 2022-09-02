package com.cardinalhealth.ids.boss.deidentify.job.listener;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.owasp.esapi.logging.slf4j.Slf4JLogFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cardinalhealth.framework.utils.ESAPILogger;

@Component
@StepScope
public class ItemWriteFailureLogListener<T> implements ItemWriteListener<T> {

	final ESAPILogger logger = new ESAPILogger(new Slf4JLogFactory().getLogger(ItemWriteFailureLogListener.class));

	@Value("#{stepExecution}")
	public StepExecution stepExecution;

	@Override
	public void beforeWrite(List<? extends T> items) {
		// keeping blank - can be used to print count or other pre-setup
	}

	@Override
	public void afterWrite(List<? extends T> items) {
		// keeping blank - can be used to print count or other post-setup
	}

	@Override
	public void onWriteError(Exception exception, List<? extends T> items) {
		logger.error("Batch Job - Batch Job Failed || step name - {} error cause - {}",
				stepExecution.getStepName(), ExceptionUtils.getStackTrace(exception));
	}

}