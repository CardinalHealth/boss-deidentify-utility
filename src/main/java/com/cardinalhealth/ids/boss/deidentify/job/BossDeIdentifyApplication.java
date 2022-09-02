package com.cardinalhealth.ids.boss.deidentify.job;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The main application class for <strong>DeIdentify</strong> service.
 * mohan.arora
 */
@ComponentScan("com.*")
@EntityScan("com.*")
@EnableAspectJAutoProxy
@EnableJpaRepositories(basePackages = { "com.*" })
@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
public class BossDeIdentifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(BossDeIdentifyApplication.class, args);

	}

}
