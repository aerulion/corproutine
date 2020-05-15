package net.aerulion.corproutine.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {
	private String HOST, PORT, DATABASE, USER, PASSWORD;
	private Connection con;

	public MySQL(String host, String port, String database, String user, String password) {
		HOST = host;
		PORT = port;
		DATABASE = database;
		USER = user;
		PASSWORD = password;
		connect();
	}

	private void connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?autoReconnect=true&useSSL=false", USER, PASSWORD);
			System.out.println("[CorpRoutine] Verbindung zur Datenbank aufgebaut.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void close() {
		try {
			if (con != null) {
				con.close();
				System.out.println("[CorpRoutine] Verbindung zur Datenbank getrennt.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void update(String NextDate, String DoneBy, String Comment, int ID, String UserName, String AufgabeName) throws SQLException {
		PreparedStatement state;
		state = con.prepareStatement("UPDATE tool_routine SET nextdate=?,done_by=?,comment=? WHERE id=?");
		state.setString(1, NextDate);
		state.setString(2, DoneBy);
		state.setString(3, Comment);
		state.setInt(4, ID);
		state.executeUpdate();
		state.close();
		updateChangelog(ID, UserName, AufgabeName);
	}

	public ResultSet query(String query) {
		ResultSet rs = null;
		try {
			PreparedStatement state = con.prepareStatement(query);
			rs = state.executeQuery();
		} catch (SQLException e) {
			connect();
			e.printStackTrace();
		}

		return rs;
	}

	public void updateChangelog(int id, String UserName, String AufgabeName) throws SQLException {
		PreparedStatement state;
		state = con.prepareStatement("INSERT INTO tool_changelog (toolname,id_intool,type,changeby,meta) VALUES (?,?,?,(SELECT uid FROM tool_users WHERE username=?),?)");
		state.setString(1, "routinearbeiten");
		state.setInt(2, id);
		state.setString(3, "update");
		state.setString(4, UserName);
		state.setString(5, "{\"lang_args\":[\"" + UserName + "\",\"" + AufgabeName + "\"]}");
		state.executeUpdate();
		state.close();
		PreparedStatement state2;
		state2 = con.prepareStatement("UPDATE stafftool.tool_datacache SET cache = (SELECT CONCAT('[', GROUP_CONCAT(JSON_OBJECT('id', id, 'task', task, 'category', category, 'nextdate', nextdate) separator ','), ']') FROM stafftool.tool_routine WHERE nextdate <= UNIX_TIMESTAMP()) WHERE title = 'routine_urgent'");
		state2.executeUpdate();
		state2.close();
	}
}