package com.cardinalhealth.ids.boss.deidentify.job.constants;

import lombok.NoArgsConstructor;

public class DeIdentificationJobQueryConstants {

	/**
	 * No-argument Constructor.<br>
	 */
	private DeIdentificationJobQueryConstants() {
		// This is a private constructor to hide implicit public one.<br>
	}

	@NoArgsConstructor
	public static class JobQueryConstants {

		public static final String READ_PATIENT = "SELECT pt.* FROM boss_fusion.ptnt pt limit 10";
		
		public static final String UPDATE_PATIENT = "UPDATE boss_fusion.ptnt\r\n" + 
				" SET ptnt_id =:patientId \r\n" + 
				" WHERE ptnt_num=:patientNum";
		
		public static final String READ_UTILIZATION_DRG = "SELECT ud.* FROM boss_fusion.utlz_drg ud limit 10";
		
		public static final String UPDATE_UTILIZATION_DRG = "UPDATE boss_fusion.utlz_drg \r\n" + 
				" SET ptnt_id =:patientId \r\n" + 
				" WHERE utlz_drg_num =:utilizationDrgNum";
	}
}
