package com.spring.boot.project.daoService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Repository;

import com.spring.boot.project.NamedParameterStatement.NamedParameterStatement;
import com.spring.boot.project.entity.Client;
import com.spring.boot.project.mysqlConnect.ConnectionManager;


@Repository
public class ClientDaoService implements IClientDaoService {
	private Connection connection = ConnectionManager.getConnection();
	private static final Logger logger = Logger.getLogger(ClientDaoService.class);
	
	private static final String QUERY_GET_ALL_CLIENTS = "SELECT id, lastName, name, numberPassport, address, phone FROM `client`;";
	private static final String QUERY_SELECT_CLIENT_BY_ID = "SELECT id, lastName, name, numberPassport, address, phone  FROM `client` WHERE id= :id;";
	private static final String QUERY_CREATE_CLIENT = "INSERT INTO `client`(lastName, name, numberPassport, address, phone) VALUES(:Sname, :name, :passport, :homeaddress, :telephone);";
	private static final String QUERY_UPDATE_CLIENT_BY_ID = "UPDATE `client` SET lastName= :lastName, name= :name, numberPassport= :numberPassport, address= :address, phone= :phone WHERE id= :id;";
	private static final String QUERY_DELETE_CLIENT = "DELETE FROM `client` WHERE id= :id;";
	
	/**
	 * This method select all client form DB
	 * @return List<Client>
	 */
	@Override
	public List<Client> getAll(){	
		try
		{
			NamedParameterStatement namedParameterStatement = new NamedParameterStatement(connection, QUERY_GET_ALL_CLIENTS);
			ResultSet resultSet = namedParameterStatement.executeQuery();

			List<Client> ListClients = new ArrayList<>();
			
			while(resultSet.next()) {
				ListClients.add(Client.getClient(resultSet));
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
	 * @return Client
	 */
	@Override
	public Client getById(Integer id) {
		try {
			NamedParameterStatement namedParameterStatement = new NamedParameterStatement(connection, QUERY_SELECT_CLIENT_BY_ID);
			namedParameterStatement.setLong("id", id);
			
			ResultSet resultSet = namedParameterStatement.executeQuery();
			
			resultSet.next();
			
			Client clients = Client.getClient(resultSet);
			
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
			NamedParameterStatement namedParameterStatement = new NamedParameterStatement(connection, QUERY_CREATE_CLIENT);
			namedParameterStatement.setString("lastName", client.getLastName());
			namedParameterStatement.setString("name", client.getName());
			namedParameterStatement.setString("numberPassport", client.getNumberPassport());
			namedParameterStatement.setString("address", client.getAddress());
			namedParameterStatement.setString("phone", client.getPhone());
			
			ResultSet generatedKeys = namedParameterStatement.getGeneratedKeys();
			
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
			NamedParameterStatement namedParameterStatement = new NamedParameterStatement(connection, QUERY_UPDATE_CLIENT_BY_ID);
			namedParameterStatement.setString("lastName", client.getLastName());
			namedParameterStatement.setString("name", client.getName());
			namedParameterStatement.setString("numberPassport", client.getNumberPassport());
			namedParameterStatement.setString("address", client.getAddress());
			namedParameterStatement.setString("phone", client.getPhone());
			namedParameterStatement.execute();

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
			NamedParameterStatement namedParameterStatement = new NamedParameterStatement(connection, QUERY_DELETE_CLIENT);
			namedParameterStatement.setLong("id", id);
			namedParameterStatement.execute();
			
			return id;
			
		} catch (SQLException | RuntimeException e) {
			logger.error(e);
			throw new RuntimeException(e);
		}
	}
}
