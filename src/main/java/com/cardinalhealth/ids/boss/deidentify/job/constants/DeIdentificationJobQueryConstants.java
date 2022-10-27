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

		public static final String READ_PATIENT = "SELECT pt.* FROM boss_fusion.ptnt pt where pt.deidentified_flg = false order by pt.ptnt_num desc limit 100000";
		
		public static final String UPDATE_PATIENT = "UPDATE boss_fusion.ptnt \r\n" + 
				" SET ptnt_id =:patientId, deidentified_flg=true \r\n" + 
				" WHERE ptnt_num=:patientNum";
		
		public static final String READ_UTILIZATION_DRG = "SELECT ud.utlz_drg_num, ud.ptnt_id, ud.deidentified_flg FROM boss_fusion.utlz_drg ud where ud.deidentified_flg = false order by ud.utlz_drg_num desc limit 100000";
		
		public static final String UPDATE_UTILIZATION_DRG = "UPDATE boss_fusion.utlz_drg  \r\n" + 
				"SET ptnt_id =:patientId, deidentified_flg=true  \r\n" + 
				"WHERE utlz_drg_num =:utilizationDrgNum";
	}
}
