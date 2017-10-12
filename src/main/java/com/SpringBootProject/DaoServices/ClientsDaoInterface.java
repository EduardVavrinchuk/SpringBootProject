package com.SpringBootProject.DaoServices;

import java.util.List;

import com.SpringBootProject.Entity.Clients;

public interface ClientsDaoInterface {
	/**
	 * This method select all client form db
	 * @return List<Clients>
	 */
	List<Clients> GetAllClients();
	
	/**
	 * This method will select client by id from db
	 * @param id
	 * @return Clients
	 */
	Clients SelectClientById(Integer id);
	
	/**
	 * This method create a new client in db
	 * @param client
	 * @return Integer
	 */
	Integer CreateClient(Clients clients);
	
	/**
	 * This method update information about client
	 * @param client 
	 * @return Integer
	 */
	Integer UpdateClient(Clients clients);
	
	/**
	 * This method delete client by id
	 * @param id
	 * @return Integer
	 */
	Integer DeleteClient(Integer id);
}
