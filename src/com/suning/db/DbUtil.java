package com.suning.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbUtil {
	

	public Statement st;

	public DbUtil(Map<String, String> map) throws IOException, ClassNotFoundException, SQLException {
	
		Class.forName(map.get("driverClassName"));
		Connection conn = DriverManager.getConnection(map.get("JdbcURL"),
				map.get("username"), map.get("password"));
		st = conn.createStatement();
	}
	

	// 读取数据库表信息
	public List<String> getTableNames(String selTableSql) throws SQLException {

		List<String> tableList = new ArrayList<String>(); // 存储表名
	
			ResultSet tabRs = st.executeQuery(selTableSql);
			// 保存表名
			while (tabRs.next()) {
				tableList.add(tabRs.getString(1));
			}

		    return tableList;
	}

	
}
