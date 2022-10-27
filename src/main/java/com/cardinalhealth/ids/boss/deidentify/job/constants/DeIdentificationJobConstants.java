package com.cardinalhealth.ids.boss.deidentify.job.constants;

import lombok.NoArgsConstructor;

/**
 * This class is to maintain all the <strong>account-job</strong> constants at one place.<br>
 */
@NoArgsConstructor
public class DeIdentificationJobConstants {

	public static final String BUID_KEY = "app.buid";
	

	/**
	 * This class contains all the open api controller tags.<br>
	 */
	@NoArgsConstructor
	public static class OpenApiTags {
		public static final String DEIDENTIFY_CONTROLLER_TAG = "deidentify-controller";
	}

	/**
	 * This class contains all URL mappings used by Api Job.<br>
	 */
	@NoArgsConstructor
	public static class ApiPaths {

		public static final String SERVICE_BASE = "/api/v1/";

		@NoArgsConstructor
		public static class DeIdentifyJobApiPaths {

			public static final String DEIDENTIFY_DESC = "Deidentify data on stage";
			public static final String DEIDENTIFY_PATH = "deidentify/utility";
		}
	}

	/**
	 * This class contains api response codes.<br>
	 */
	@NoArgsConstructor
	public static class ApiResponseMessages {

		public static final String SYSTEM_ERROR = "System Error";

		@NoArgsConstructor
		public static class DeidentifyResponseMessages {
			public static final String DEIDENTIFY_SUCCESS_MSG = "Successfully de-identify data.";
		}
	}

	/**
	 * JOB CONSTANTS
	 */
	@NoArgsConstructor
	public static class JobConstant {
		public static final String READER = "DeIdentificationJob-Reader";

		public static final String STEP_PATIENT = "Step-Patient";
		public static final String STEP_UTILIZTION_DRG = "Step-Utilization-DRG";

		public static final String JOB_PATIENT = "Patient-DeIdentificationJob";
		public static final String JOB_UTILIZATIONDRG = "UtilizationDRG-DeIdentificationJob";
		public static final String TIME = "time";
		
		public static final String FIRST_FLOW = "firstFlow";
		public static final String SECOND_FLOW = "secondFlow";
	}
}