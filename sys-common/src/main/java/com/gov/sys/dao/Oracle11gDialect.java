package com.gov.sys.dao;

import org.hibernate.dialect.Oracle10gDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.SQLException;

public class Oracle11gDialect extends Oracle10gDialect {

	private static final Logger log = LoggerFactory.getLogger(Oracle11gDialect.class);

	protected boolean foundNewerOracleTypes = false;
	protected int oracleTypeCursor;

	public Oracle11gDialect() {
		final String oracleTypeClzName = "oracle.jdbc.OracleTypes";
		Class<?> oracleTypesClzFor11g = null;
		try {
			oracleTypesClzFor11g = Class.forName(oracleTypeClzName);
		} catch (ClassNotFoundException e) {
			log.info(
					"Cannot find {}, use the default logic of {} to detect the package path for OracleTypes.",
					oracleTypeClzName, super.getClass().getName());
		}

		if (null != oracleTypesClzFor11g) {
			try {
				Field typeField = oracleTypesClzFor11g.getField("CURSOR");
				oracleTypeCursor = (Integer) typeField.get(null);
				foundNewerOracleTypes = true;
				log.info("Detected newer OracleTypes at: {}.",
						oracleTypeClzName);
			} catch (Exception e) {
				log.warn(
						"Found class {}, but failed to read the field value for [CURSOR]. use the default logic of {} to detect the package path for OracleTypes.",
						oracleTypeClzName, super.getClass().getName(), e);
			}
		}
	}

	/**
	 * Override to fixed the issue of
	 * "org.hibernate.HibernateException: Problem while trying to load or access OracleTypes.CURSOR value"
	 * when calling Oracle procedure with SYS_REFCURSOR as the output parameter.
	 * ("oracle.jdbc.driver.OracleTypes" was not accessible yet, use
	 * "oracle.jdbc.OracleTypes" instead.).
	 */
	@Override
	public int registerResultSetOutParameter(CallableStatement statement,
			final int col) throws SQLException {
		if (foundNewerOracleTypes) {
			statement.registerOutParameter(col, oracleTypeCursor);
			return (col + 1);
		} else {
			return super.registerResultSetOutParameter(statement, col);
		}
	}

}
