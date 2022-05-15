package com.savindi.ElectroGrid;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class EmployeeRepository 
{

	
	Connection con =null;
	
	public EmployeeRepository()
	{
		String url = "jdbc:mysql://localhost:3306/electrogridDB";
		String username = "root";
		String password = "";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
		con = DriverManager.getConnection(url,username,password);
		
		}
		catch(Exception erorr) {
			System.out.println(erorr);
		}
	}
	
	
	public List<Employee> getEmployees()
	{
		List<Employee> employees = new ArrayList<>();
		String sql = "select * from employee";
		try
		{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while(rs.next())
		{
			Employee e = new Employee();
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setPhone(rs.getString(3));
			e.setEmail(rs.getString(4));
			e.setPosition(rs.getString(5));
			
			employees.add(e);
			
		}
		}
		catch(Exception erorr)
		{
			System.out.println(erorr);
		}
		return employees;
	}
	
	public Employee getEmployee(int id)
	{
		String sql = "select * from employee where id = " +id;
		Employee e = new Employee();
		
		try
		{
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		if(rs.next())
		{
			
			e.setId(rs.getInt(1));
			e.setName(rs.getString(2));
			e.setPhone(rs.getString(3));
			e.setEmail(rs.getString(4));
			e.setPosition(rs.getString(5));
			
			}
		}
		catch(Exception error)
		{
			System.out.println(error);
		}
		
		return e;
	
		
		
	}


	public void create(Employee e1) 
	{
		String sql = "insert into employee values(?,?,?,?,?)";
		try
		{
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, e1.getId());
		st.setString(2, e1.getName());
		st.setString(3, e1.getPhone());
		st.setString(4, e1.getEmail());
		st.setString(5, e1.getPosition());
		st.executeUpdate();
	
		
		}
		catch(Exception erorr)
		{
			System.out.println(erorr);
		}
	
	}
	
	public void update(Employee e1) 
	{
		
		String sql = "Update employee set name=?,phone=?,email=?,position=? where id=?";
		
		try {
			PreparedStatement st= con.prepareStatement(sql);
			
			st.setString(1, e1.getName());
			st.setString(2, e1.getPhone());
			st.setString(3, e1.getEmail());
			st.setString(4, e1.getPosition());
			st.setInt(5, e1.getId());
			st.executeUpdate();
			}
		catch(Exception erorr) {
			System.out.println(erorr);
		}
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
	String sql = "delete from employee  where id=? ";
		
		try {
			PreparedStatement st= con.prepareStatement(sql);
		
			st.setInt(1,id);
			st.executeUpdate();
			}
		catch(Exception erorr) {
			System.out.println(erorr);
		}
	}
		
		
		
	}


