package com.SpringBootProject.DaoServices;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.SpringBootProject.Entity.Clients;


public class ClientsDaoService implements ClientsDaoInterface {
	private static String URL = "jdbc:mysql://localhost:3306/TestProject";
	private static String USERNAME = "root";
	private static String PASSWORD = "0905";
	private Connection connection = CreateConnection();
	
	
	private static final Logger logger = Logger.getLogger(ClientsDaoService.class);
	
	private static String QUERY_GET_ALL_CLIENTS = "{SELECT id, SurName, Name, Passport, HomeAddress, Telephone FROM `Client`}";
	private static String QUERY_SELECT_CLIENT_BY_ID = "{SELECT id, SurName, Name, Passport, HomeAddress, Telephone  FROM `Client` WHERE id= :id}";
	private static String QUERY_CREATE_CLIENT = "{INSERT INTO `Client`(SurName, Name, Passport, HomeAddress, Telephone) VALUES(:Sname, :name, :passport, :homeaddress, :telephone)}";
	private static String QUERY_UPDATE_CLIENT_BY_ID = "{UPDATE `Client` SET SurName= :Sname, Name= :name, Passport= :passport, HomeAddress= :homeaddress, Telephone= :telephone WHERE id= :id}";
	private static String QUERY_DELETE_CLIENT = "{DELETE FROM `Client` WHERE id= :id}";
		
	/**
	 * This method create and return a connection
	 * @return Connection
	 */
	private Connection CreateConnection(){
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch(SQLException e){
			logger.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * This method create instance of Reader and return it
	 * @return Reader
	 * @throws SQLException 
	 */
	
	private Clients getClient(ResultSet resultSet) throws SQLException {
		return new Clients(resultSet.getInt("id"), resultSet.getString("SurName"), resultSet.getString("Name"),
				resultSet.getString("Passport"),resultSet.getString("HomeAddress"),resultSet.getString("Telephone"));
	}
	
	/**
	 * This method select all client form DB
	 * @return List<Clients>
	 */
	@Override
	public List<Clients> GetAllClients() {	
		
		try
		{
			CallableStatement statement = connection.prepareCall(QUERY_GET_ALL_CLIENTS);
			ResultSet resultSet = statement.executeQuery();
			
			List<Clients> ListClients = new ArrayList<>();
			
			while(resultSet.next()) {
				ListClients.add(getClient(resultSet));
			}
			
			return ListClients;
		
			
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
		}
		
		return null;
	}

	/**
	 * This method will select client by id from DB
	 * @param id
	 * @return Clients
	 */
	@Override
	public Clients SelectClientById(Integer id) {
		try {
			CallableStatement statement = connection.prepareCall(QUERY_SELECT_CLIENT_BY_ID);
			statement.setLong("id", id);
			statement.execute();
			
			ResultSet resultSet = statement.executeQuery();
			
			resultSet.next();
			
			Clients clients = getClient(resultSet);
			
			return clients;
			
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
		}
		
		return null;
	}

	/**
	 * This method create a new client in DB
	 * @param client
	 * @return Integer
	 */
	@Override
	public Integer CreateClient(Clients client) {
		
		try {
			CallableStatement statement = connection.prepareCall(QUERY_CREATE_CLIENT);
			statement.setString("Sname", client.getSurName());
			statement.setString("name", client.getName());
			statement.setString("passport", client.getPassport());
			statement.setString("homeaddress", client.getHomeAddress());
			statement.setString("telephone", client.getTelephone());
			statement.execute();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			generatedKeys.next();
			
			return generatedKeys.getInt(1); 
			
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
		}
		
		return null;		
	}

	/**
	 * This method update information about client
	 * @param client 
	 * @return Integer
	 */
	@Override
	public Integer UpdateClient(Clients client) {
		try {
			CallableStatement statement = connection.prepareCall(QUERY_UPDATE_CLIENT_BY_ID);
			statement.setString("Sname", client.getSurName());
			statement.setString("name", client.getName());
			statement.setString("passport", client.getPassport());
			statement.setString("homeaddress", client.getHomeAddress());
			statement.setString("telephone", client.getTelephone());
			statement.setLong("id", client.getId());
			statement.execute();
			return client.getId();
					
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
		}
		
		return null;
	}

	/**
	 * This method delete client by id
	 * @param id
	 * @return Integer
	 */
	@Override
	public Integer DeleteClient(Integer id) {
		
		try{
			CallableStatement statement = connection.prepareCall(QUERY_DELETE_CLIENT);
			statement.setLong("id",id);
			statement.execute();
			
			return id;
			
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
		}
		
		return null;
	}
}
