package cis560
import java.sql.*;
class LoginController {

	private static Connection connect = null;
	
    def login = { 
//TODO: produce error
	}
	
	def validateUser = {
		
		try {
			Class.forName("com.mysql.jdbc.Driver")
			connect = DriverManager.getConnection("jdbc:mysql://mysql.cis.ksu.edu/bsweeney","bsweeney", "a1b2c3d4e5")
			
			PreparedStatement loginStatement = connect.prepareStatement("select count(*) from Users where username=? and passwd=PASSWORD(?)")
			loginStatement.clearParameters()
			loginStatement.setString(1, params.userName)
			loginStatement.setString(2,params.password)
			
			ResultSet loginResult = loginStatement.executeQuery()
			loginResult.next()
			//0 bad login 
			//1 good login
			int loginInt = loginResult.getInt(1)
			loginResult.close()
		
			if(loginInt==1)
			{
				chain(controller:"homescreen",action:"index", model:[userName:params.userName])
			}
			else
			{
				chain(controller:"login", action:"login",model:[badLogin:true])
			}
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	
	}
}
