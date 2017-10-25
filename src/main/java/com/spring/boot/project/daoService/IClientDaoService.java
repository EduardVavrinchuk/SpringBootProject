package com.spring.boot.project.daoService;

import java.util.List;

import com.spring.boot.project.entity.Client;

public interface IClientDaoService {
	/**
	 * This method select all client form db
	 * @return List<Client>
	 * @throws Exception 
	 */
	List<Client> getAll();
	
	/**
	 * This method will select client by id from db
	 * @param id
	 * @return Client
	 */
	Client getById(Integer id);
	
	/**
	 * This method create a new client in db
	 * @param client
	 * @return Integer
	 */
	Integer create(Client clients);
	
	/**
	 * This method update information about client
	 * @param client 
	 * @return Integer
	 */
	Integer update(Client clients);
	
	/**
	 * This method delete client by id
	 * @param id
	 * @return Integer
	 */
	Integer delete(Integer id);
}
