package com.springBoot.project.daoService;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.springBoot.project.entity.Client;

@Repository
public class ClientsDaoService implements IClientDaoService {
	@Value("${jdbc.url}") private String URL;
	@Value("${jdbc.username}") private String USERNAME;
	@Value("${jdbc.password}") private String PASSWORD;
	private Connection connection = CreateConnection();
	
	/**
	 * This method create instance of Client and return it
	 * @return Client
	 * @throws SQLException 
	 */
	public Client getClient(ResultSet resultSet) throws SQLException {
		return new Client(resultSet.getInt("id"), resultSet.getString("lastName"), resultSet.getString("name"),
				resultSet.getString("numberPassport"),resultSet.getString("address"),resultSet.getString("phone"));
	}
	private static final Logger logger = Logger.getLogger(ClientsDaoService.class);
	
	private static String QUERY_GET_ALL_CLIENTS = "{SELECT id, lastName, name, numberPassport, address, phone FROM `client`}";
	private static String QUERY_SELECT_CLIENT_BY_ID = "{SELECT id, lastName, name, numberPassport, address, phone  FROM `client` WHERE id= :id}";
	private static String QUERY_CREATE_CLIENT = "{INSERT INTO `client`(lastName, name, numberPassport, address, phone) VALUES(:Sname, :name, :passport, :homeaddress, :telephone)}";
	private static String QUERY_UPDATE_CLIENT_BY_ID = "{UPDATE `client` SET lastName= :lastName, name= :name, numberPassport= :numberPassport, address= :address, phone= :phone WHERE id= :id}";
	private static String QUERY_DELETE_CLIENT = "{DELETE FROM `client` WHERE id= :id}";
		
	/**
	 * This method create and return a connection
	 * @return Connection
	 */
	private Connection CreateConnection(){
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		catch(SQLException e){
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * This method select all client form DB
	 * @return List<Clients>
	 */
	@Override
	public List<Client> getAll(){	
		try
		{
			CallableStatement statement = connection.prepareCall(QUERY_GET_ALL_CLIENTS);
			ResultSet resultSet = statement.executeQuery();
			
			List<Client> ListClients = new ArrayList<>();
			
			while(resultSet.next()) {
				ListClients.add(getClient(resultSet));
			}
			
			return ListClients;
		
			
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method will select client by id from DB
	 * @param id
	 * @return Clients
	 */
	@Override
	public Client getById(Integer id) {
		try {
			CallableStatement statement = connection.prepareCall(QUERY_SELECT_CLIENT_BY_ID);
			statement.setLong("id", id);
			statement.execute();
			
			ResultSet resultSet = statement.executeQuery();
			
			resultSet.next();
			
			Client clients = getClient(resultSet);
			
			return clients;
			
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method create a new client in DB
	 * @param client
	 * @return Integer
	 */
	@Override
	public Integer create(Client client) {
		
		try {
			CallableStatement statement = connection.prepareCall(QUERY_CREATE_CLIENT);
			statement.setString("lastName", client.getLastName());
			statement.setString("name", client.getName());
			statement.setString("numberPassport", client.getNumberPassport());
			statement.setString("address", client.getAddress());
			statement.setString("phone", client.getPhone());
			statement.execute();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			generatedKeys.next();
			
			return generatedKeys.getInt(1); 
			
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}	
	}

	/**
	 * This method update information about client
	 * @param client 
	 * @return Integer
	 */
	@Override
	public Integer update(Client client) {
		try {
			CallableStatement statement = connection.prepareCall(QUERY_UPDATE_CLIENT_BY_ID);
			statement.setString("lastName", client.getLastName());
			statement.setString("name", client.getName());
			statement.setString("numberPassport", client.getNumberPassport());
			statement.setString("address", client.getAddress());
			statement.setString("phone", client.getPhone());
			statement.setLong("id", client.getId());
			statement.execute();
			return client.getId();
					
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method delete client by id
	 * @param id
	 * @return Integer
	 */
	@Override
	public Integer delete(Integer id) {
		
		try{
			CallableStatement statement = connection.prepareCall(QUERY_DELETE_CLIENT);
			statement.setLong("id",id);
			statement.execute();
			
			return id;
			
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
}
