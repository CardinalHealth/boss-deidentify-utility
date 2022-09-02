package com.cardinalhealth.ids.boss.deidentify.job.test.utils

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Utility class for tests.
 */
class TestUtils {

	def static final TIME = "time"
	
	public ObjectMapper mapper = new ObjectMapper()

	public List<?> convertJsonStringToObjectList(String jsonString, String classNameWithPackage){
		//return (new ObjectMapper()).readValue(jsonString, new TypeReference<List<Component>>(){})

		Class<?> clz = Class.forName(classNameWithPackage);
		JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clz);

		return mapper.readValue(jsonString, type)
	}

	def static JobParameters defaultJobParameters() {
		JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
		jobParametersBuilder.addLong(TIME, System.currentTimeMillis());

		return jobParametersBuilder.toJobParameters();
	}
}