package jdbc_person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonCrud {
	
	public Connection getConnection()throws Exception
	{
		String className="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/persondb";
		String user="root";
		String pass="root";
		
		Class.forName(className);
		Connection connection=DriverManager.getConnection(url, user, pass);
		return connection;
	}
	public int singUp(Person person)throws Exception
	{
		Connection connection=getConnection();
		String sql="INSERT INTO PERSON VALUES(?,?,?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, person.getId());
		preparedStatement.setString(2,person.getName());
		preparedStatement.setLong(3, person.getPhno());
		preparedStatement.setString(4,person.getEmail());
		preparedStatement.setString(5, person.getPass());
		
		int result=preparedStatement.executeUpdate();
		return result;
	}
	public ResultSet login() throws Exception
	{
		String login="Select * from PERSON";
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(login);
		ResultSet set=preparedStatement.executeQuery();
		return set;
		
	}
	public int updateName(String name,String email)throws Exception
	{
		String update="UPDATE PERSON SET NAME=? WHERE EMAIL=?";
		Connection connection=getConnection();
		PreparedStatement preparedStatement=connection.prepareStatement(update);
		preparedStatement.setString(1,name);
		preparedStatement.setString(2, email);
		int result=preparedStatement.executeUpdate();
		return result;
		
	}
	public String getPassword(String email)throws Exception
	{
		Connection  connection=getConnection();
		String pass="SELECT PASSWORD FROM PERSON WHERE EMAIL=?";
		PreparedStatement preparedStatement=connection.prepareStatement(pass);
		preparedStatement.setString(1, email);
		ResultSet set=preparedStatement.executeQuery();
		String password=null;
		while (set.next()) {
			password=set.getString("password");
		}
		return password;
		
	}
}
