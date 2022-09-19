package com.cardinalhealth.ids.boss.deidentify.job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The main application class for <strong>DeIdentify</strong> service.
 * mohan.arora
 */
@ComponentScan("com.*")
@EntityScan("com.*")
@EnableAspectJAutoProxy
@EnableBatchProcessing
@SpringBootApplication
@EnableScheduling
@EnableAsync
@EnableWebMvc
public class BossDeIdentifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BossDeIdentifyApplication.class, args);

	}

}
