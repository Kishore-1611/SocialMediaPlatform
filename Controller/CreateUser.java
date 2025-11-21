package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Database;
import Model.User;
import View.Alert;

public class CreateUser {
	
	private User u;
	private Database database;
	
	public CreateUser(User u, Database database) {
		this.u = u;
		this.database = database;
	}
	
	public void create() {
		String insert = "INSERT INTO `users`(`firstname`, `lastname`, `email`, "
				+ "`password`) VALUES ('"+u.getFirstName()+"','"+u.getLastName()
				+"','"+u.getEmail()+"','"+u.getPassword()+"');";
		try {
			database.getStatement().execute(insert);
		} catch (SQLException e) {
			new Alert(e.getMessage(), null);
		}
	}
	
	public boolean isEmailUsed() {
		String select = "SELECT * FROM `users` WHERE `email` = '"+u.getEmail()+"';";
		boolean used = false;
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			used = rs.next();
		} catch (SQLException e) {
			new Alert(e.getMessage(), null);
		}
		return used;
	}
	
	public User getUser() {
		u.setFriends(new ArrayList<>());
		String select = "SELECT `ID` FROM `users` WHERE `email` = '"+u.getEmail()+"' AND `password` = '"+u.getPassword()+"';";
		try {
			ResultSet rs = database.getStatement().executeQuery(select);
			rs.next();
			u.setID(rs.getInt("ID"));
		} catch (SQLException e) {
			new Alert(e.getMessage(), null);
		}
		return u;
	}

}
