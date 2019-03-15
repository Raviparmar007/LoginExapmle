package ExamplePackege;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDAO {
	private static final Statement ConnectionManager = null;
	private static final String Lastname = null;
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static UserBean login(UserBean bean) { // preparing some objects for
													// connection
		Statement stmt = null;
		String username = bean.getFirstName();
		String password = bean.getPassword();
		String searchQuery = "select * from users where username='" + username + "' AND password='" + password + "'";
		// "System.out.println" prints in the console; Normally used to trace
		// the process
		System.out.println("Your user name is " + username);
		System.out.println("Your password is " + password);
		System.out.println("Query: " + searchQuery);
		try {
			// connect to DB
			currentCon = ConnectionManager.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();
			// if user does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Sorry, you are not a registered user! Please sign up first");
				bean.setValid(false);
			}
			// if user exists set the isValid variable to true
			else if (more) {
				String Firstname = null;
				System.out.println("Welcome " + Firstname);
				bean.setFirstName(Firstname);
				bean.setFirstName(Lastname);
				bean.setValid(true);
			}
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		} // some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {

				}
				stmt = null;
			}
			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {

				}
				currentCon = null;
			}
		}
		return bean;
	}
}
