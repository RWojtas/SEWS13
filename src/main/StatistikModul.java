package main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Sebastian
 */
public class StatistikModul {
	// Da es sich um ein Werbespiel für eine Saison handelt, wird die IP-Adresse in den Programmcode integriert
	// unt nicht ausgelagert
	private static final String connServerIp = "localhost";
	private static final String connUser = "root";
	private static final String connPass = "";
		
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private static final String STATS_FILE = "data\\stats.txt";
	private ArrayList<Integer> list;
	
	// Entnimmt die MAC-Adresse des primären Netzwerkadapters
	public static String getMacAddress() throws Exception {
		String result = "";
		try {
			for (NetworkInterface ni : Collections.list(NetworkInterface.getNetworkInterfaces())) {
				byte[] hardwareAddress = ni.getHardwareAddress();
				if (hardwareAddress != null) {
					for (int i = 0; i < hardwareAddress.length; i++) {
						result += String.format("%02X", hardwareAddress[i]);
					}
					if (result.length() > 0 && !ni.isLoopback()) { return result; }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}

	public void setStatistik() {
		list = readStatsFile();
		try {
			// Datenbanktreiber laden (jdbc)
			Class.forName("com.mysql.jdbc.Driver");
			// Verbindung starten
			connect = DriverManager
					.getConnection("jdbc:mysql://"+connServerIp+"/fdf?user="+connUser+"&password="+connPass);

			preparedStatement = connect
					.prepareStatement("select * from FDF.exec where hwaddress = ? ;");
			preparedStatement.setString(1, getMacAddress());
			resultSet = preparedStatement.executeQuery(); // Gibt es bereits einen Eintrag mit dieser Hardwareadresse?
			
			if(resultSet.next()) { // Wenn ja: Eintrag updaten
				int cnt = resultSet.getInt("count"); // entnehme die aktuelle Anzahl an gestarteten Spielen
				cnt++; // Spiele + 1
				cnt=cnt+list.get(2); // + die Spielstarts, die nicht hochgeladen werden konnten
				saveStatsFile_cnt(0); // Spieldateicounter auf 0 setzen, die Spiele sollen ja nicht immer addiert werden
				
				preparedStatement = connect
						.prepareStatement("UPDATE FDF.exec  SET count = ? , date_last = ? WHERE hwaddress = ?;");
				preparedStatement.setInt(1, cnt);
				preparedStatement.setDate(2, new java.sql.Date(new Date().getTime()));
				preparedStatement.setString(3, getMacAddress());
				preparedStatement.executeUpdate();
				
			} else { // Wenn nein: neuer Eintrag
				preparedStatement = connect
						.prepareStatement("insert into  FDF.exec values (default, ?, ?, ?, ?)");
				
				preparedStatement.setString(1, getMacAddress());
				preparedStatement.setInt(2, 1);
				preparedStatement.setDate(3, new java.sql.Date(new Date().getTime()));
				preparedStatement.setDate(4, new java.sql.Date(new Date().getTime()));
				preparedStatement.executeUpdate();
			}
			
			/* 
			 * Ausgeben von Einträgen zu Debugzwecken
				preparedStatement = connect
						.prepareStatement("select * from FDF.exec where hwaddress = ? ;");
				preparedStatement.setString(1, getMacAddress());
				resultSet = preparedStatement.executeQuery();
				writeResultSet(resultSet);
	
				preparedStatement = connect
						.prepareStatement("SELECT * from FDF.exec");
				resultSet = preparedStatement.executeQuery();
				writeResultSet(resultSet);
			*/

		} catch (Exception e) { // Wenn die Verbindung fehlschlägt: im Statsfile den Spielecounter hochzählen
			list = readStatsFile();
			int cnt = list.get(2)+1;
			saveStatsFile_cnt(cnt);
		} finally {
			close();
		}
	}

	/* 
	 * Ausgabe des Ergebnisses zu Debugzwecken
		private void writeResultSet(ResultSet resultSet) throws SQLException {
			// Alle Zeilen aus einem Ergebnis ausgeben
			// Für Debugzwecke gebe ich hier alle Spalten aus
			while (resultSet.next()) {
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
	*/

	// Alle geöffneten Verbindungen etc. schließen
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
	
	public ArrayList<Integer> readStatsFile() { // Funktion zum Statsfile auslesen
		FileReader file_reader;
		int c;
		StringBuffer buff = new StringBuffer();
		ArrayList<Integer> l = new ArrayList<Integer>();

		try {
			file_reader = new FileReader(new File(STATS_FILE));

			do {
				c = file_reader.read(); // zeichenweise
				if (c == ';' || c == -1 && buff.length() > 0) {
					l.add(Integer.parseInt(buff.toString())); // Bei Trennzeichen Nummer der Liste anhängen
					buff = new StringBuffer();
					
				} else
					buff.append((char) c); // schreibe solange in den Puffer bis Datei zu Ende oder Trennzeichen ; eingetreten
			} while (c != -1);

			file_reader.close();
		} catch (IOException e) {}
		
		return l;
	}
	
	public void saveStatsFile_cnt(int cnt) { // Schreibt den Counter neu
		FileWriter file_writer;
		list = readStatsFile();
		StringBuffer buff = new StringBuffer();
		try {
			file_writer = new FileWriter(new File(STATS_FILE),false);
			buff.append(list.get(0)+";"+list.get(1)+";"+cnt);
			
			file_writer.write(buff.toString());
			file_writer.close();
		} catch (IOException e) {}
		
	}
	
	public void saveStatsFile_first(int gender, int age) { // für den ersten Start, schreibt Geschlecht und Alter rein
		FileWriter file_writer;
		StringBuffer buff = new StringBuffer();
		try {
			file_writer = new FileWriter(new File(STATS_FILE),false);
			
			buff.append(gender+";"+age+";"+0);
			
			file_writer.write(buff.toString());
			file_writer.close();
		} catch (IOException e) {}
		
	}
}