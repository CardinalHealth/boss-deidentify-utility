package com.cardinalhealth.ids.boss.deidentify.job.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.owasp.esapi.logging.slf4j.Slf4JLogFactory;
import org.springframework.jdbc.core.RowMapper;

import com.cardinalhealth.dm.fusion.PatientEntity;
import com.cardinalhealth.framework.utils.ESAPILogger;
import com.cardinalhealth.ids.boss.deidentify.job.Util.CommonUtil;

public class PatientEntityRowMapper implements RowMapper<PatientEntity> {
	
	final ESAPILogger logger = new ESAPILogger(new Slf4JLogFactory().getLogger(PatientEntityRowMapper.class));

	@Override
	public PatientEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		PatientEntity patientEntity = new PatientEntity();

		patientEntity.setPatientNum(rs.getLong("ptnt_num"));
		String patientId = CommonUtil.numberMasking(rs.getString("ptnt_id"));
		patientEntity.setPatientId(patientId);

		return patientEntity;
	}
}
