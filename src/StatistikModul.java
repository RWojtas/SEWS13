import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Date;

public class StatistikModul {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public static String getMacAddress() throws Exception {
		String result = "";
		try {
			for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
				byte[] hardwareAddress = ni.getHardwareAddress();
				if (hardwareAddress != null) {
					for (int i = 0; i < hardwareAddress.length; i++) {
						result += String.format((i == 0 ? "" : "") + "%02X", hardwareAddress[i]);
					}
					if (result.length() > 0 && !ni.isLoopback()) { return result; }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setStatistik() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/fdf?"
							+ "user=root&password=");

			preparedStatement = connect
					.prepareStatement("select * from FDF.exec where hwaddress = ? ;");
			preparedStatement.setString(1, getMacAddress());
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				int cnt = resultSet.getInt("count");
				cnt++;
				preparedStatement = connect
						.prepareStatement("UPDATE FDF.exec  SET count = ? , date_last = ? WHERE hwaddress = ?;");
				preparedStatement.setInt(1, cnt);
				preparedStatement.setDate(2, new java.sql.Date(new Date().getTime()));
				preparedStatement.setString(3, getMacAddress());
				preparedStatement.executeUpdate();
			} else {
				// PreparedStatements can use variables and are more efficient
				preparedStatement = connect
						.prepareStatement("insert into  FDF.exec values (default, ?, ?, ?, ?)");
				// Parameters start with 1
				preparedStatement.setString(1, getMacAddress());
				preparedStatement.setInt(2, 1);
				preparedStatement.setDate(3, new java.sql.Date(new Date().getTime()));
				preparedStatement.setDate(4, new java.sql.Date(new Date().getTime()));
				preparedStatement.executeUpdate();
			}
			

			preparedStatement = connect
					.prepareStatement("select * from FDF.exec where hwaddress = ? ;");
			preparedStatement.setString(1, getMacAddress());
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			preparedStatement = connect
					.prepareStatement("SELECT * from FDF.exec");
			resultSet = preparedStatement.executeQuery();
			writeResultSet(resultSet);

			// Remove again the insert comment
			// preparedStatement = connect
			//		.prepareStatement("delete from FDF.exec where hwaddress= ? ; ");
			// preparedStatement.setString(1, "MAC");
			// preparedStatement.executeUpdate();
			
			statement = connect.createStatement();
			resultSet = statement
					.executeQuery("select * from FDF.exec");
			writeMetaData(resultSet);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}

	private void writeMetaData(ResultSet resultSet) throws SQLException {
		// Now get some metadata from the database
		// Result set get the result of the SQL query

		System.out.println("The columns in the table are: ");

		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			System.out.println("Column " + i + " "
					+ resultSet.getMetaData().getColumnName(i));
		}
	}

	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String mac = resultSet.getString("hwaddress");
			int cnt = resultSet.getInt("count");
			Date date_first = resultSet.getDate("date_first");
			Date date_last = resultSet.getDate("date_last");
			System.out.println("MAC-Adresse: " + mac);
			System.out.println("Counter: " + cnt);
			System.out.println("Erster Start: " + date_first);
			System.out.println("Letzter Start " + date_last);
		}
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
		}
	}
}