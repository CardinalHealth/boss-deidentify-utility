package com.cardinalhealth.ids.boss.deidentify.job.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.owasp.esapi.logging.slf4j.Slf4JLogFactory;
import org.springframework.jdbc.core.RowMapper;

import com.cardinalhealth.dm.fusion.BossUtilizationDrg;
import com.cardinalhealth.framework.utils.ESAPILogger;
import com.cardinalhealth.ids.boss.deidentify.job.Util.CommonUtil;

public class UtilizationDRGRowMapper implements RowMapper<BossUtilizationDrg> {
	
	final ESAPILogger logger = new ESAPILogger(new Slf4JLogFactory().getLogger(UtilizationDRGRowMapper.class));

	@Override
	public BossUtilizationDrg mapRow(ResultSet rs, int rowNum) throws SQLException {
		BossUtilizationDrg bossUtilizationDrg = new BossUtilizationDrg();

		bossUtilizationDrg.setUtilizationDrgNum(rs.getLong("utlz_drg_num"));
		
		String patientId = CommonUtil.numberMasking(rs.getString("ptnt_id"));
		bossUtilizationDrg.setPatientId(patientId);

		return bossUtilizationDrg;
	}
}
