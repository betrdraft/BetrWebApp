package com.betr.server.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtils {

	public static void safeClose(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
	}
}
