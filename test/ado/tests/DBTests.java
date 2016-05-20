package ado.tests;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DBTests {

	private final static String USER = "root";
	private final static String PASSWD = "Starkiller00*";
	private final static String URL = "jdbc:mysql://localhost/test";
	private static Connection conn = null;

	@Before
	public void openConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PASSWD);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(conn != null);
	}

	public static void showData(String tableName) throws SQLException {

		Statement stmt = conn.createStatement();
		String sqlQuery = "SELECT * FROM " + tableName + " ;";
		ResultSet rs = stmt.executeQuery(sqlQuery);
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
				switch (rsmd.getColumnType(i)) {
					case Types.INTEGER:
						System.out.println(rsmd.getColumnLabel(i) + ": " + rs.getInt(rsmd.getColumnLabel(i)));
						break;
					case Types.VARCHAR:
						System.out.println(rsmd.getColumnLabel(i) + ": " + rs.getString(rsmd.getColumnLabel(i)));
						break;
					default:
						System.out.println("Type: " + rsmd.getColumnType(i) + " Column name: " + rsmd.getColumnLabel(i));
				}
			}
		}

	}

	public static void showMetaData(String tableName) throws SQLException {

		Statement stmt = conn.createStatement();
		String sqlQueryMeta = "SELECT * FROM " + tableName + " WHERE 1 = 2 ;";
		ResultSetMetaData rsmd = stmt.executeQuery(sqlQueryMeta).getMetaData();
		for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
			System.out.println("Type: " + rsmd.getColumnType(i) + " Column name: " + rsmd.getColumnLabel(i));
		}

	}

	// @Test
	public void testCREATE() {
		try {
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE test(id INT NOT NULL AUTO_INCREMENT," + "name varchar(255), " + "age INT,"
					+ "CONSTRAINT PRIMARY KEY(id)); ";
			stmt.execute(sql);
			showData("test");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(true);
	}

	// @Test
	public void testINSERT() {
		try {
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO test(name, age) VALUES(" + "'Ado', " + "25" + "); ";
			stmt.execute(sql);
			showData("test");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSELECT() {
		try {
			showData("test");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
