package com.savindi.ElectroGrid;

import java.util.Arrays;
import java.util.List;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("employees")
public class EmployeeResource 
{
	EmployeeRepository repo = new EmployeeRepository();
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<Employee> getEmployees()
	{
		
		System.out.println("get Employee called...");
		
		
		return repo.getEmployees();
		
		}
	
	@GET
	@Path("employee/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Employee getEmployee(@PathParam("id") int id)
	{
		return repo.getEmployee(id);
	}
	
	@POST
	@Path("employee")
	public Employee createEmployee(Employee e1)
	{
		System.out.println(e1);
		repo.create(e1);
		return e1;
	}
	
	
	

}
