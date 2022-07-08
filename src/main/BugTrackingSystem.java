package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gui.LoginPage;

public class BugTrackingSystem {

	private String database = "./src/BugTrackingDatabase.accdb";
	private Connection conn;

	// Create Connection
	public void createConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:ucanaccess://" + database);
		} catch (SQLException e) {
			System.out.println("Connection Failed");
			System.exit(1);
		}
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Close Connection Failed ?");
		}
	}

	public static void main(String[] args) {
		BugTrackingSystem bgs = new BugTrackingSystem();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		} catch (Exception e) {
			System.out.println("Error in Loading Driver");
		}
		bgs.createConnection();
		if (bgs.conn != null) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LoginPage loginPage = new LoginPage(bgs.conn);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

}
