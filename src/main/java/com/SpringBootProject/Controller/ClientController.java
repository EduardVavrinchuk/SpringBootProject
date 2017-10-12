package com.SpringBootProject.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBootProject.DaoServices.ClientsDaoInterface;
import com.SpringBootProject.Entity.Clients;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private ClientsDaoInterface clientsDaoInterface;
	
	private Map<String, Integer> mapToResponse = new HashMap<>();
	
	/**
	 * Get information about all clients
	 * @return ResponseEntity<List<Clients>>
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Clients>> GetAllClients() {
		List<Clients> ListClients = clientsDaoInterface.GetAllClients();
		
		return new ResponseEntity<List<Clients>>(ListClients, HttpStatus.OK);
	}
	
	/**
	 * Select client by id
	 * @param id
	 * @return ResponseEntity<Clients>
	 */
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Clients> SelectClientById(@PathVariable("id") Integer id) {
		Clients client = clientsDaoInterface.SelectClientById(id);
		
		return new ResponseEntity<Clients>(client, HttpStatus.OK);
	}
	
	/**
	 * Create a new client in database
	 * @param client
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Map<String, Integer>> CreateClient(@RequestBody Clients client) {
		Integer id = clientsDaoInterface.CreateClient(client);
		
		mapToResponse.put("id", id);
		return new ResponseEntity<Map<String, Integer>>(mapToResponse, HttpStatus.OK);
	}
	
	/**
	 * Update information about client
	 * @param client
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Integer>> UpdateClient(@RequestBody Clients client) {
		Integer id = clientsDaoInterface.UpdateClient(client);
		
		mapToResponse.put("id", id);
		return new ResponseEntity<Map<String, Integer>>(mapToResponse, HttpStatus.OK);
	}
	
	/**
	 * Delete client from database
	 * @param id
	 * @return ResponseEntity<Map<String, Integer>>
	 */
	@ResponseBody
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String, Integer>> DeleteClient(@PathVariable("id") Integer id) {
		
		mapToResponse.put("id", id);
		return new ResponseEntity<Map<String, Integer>>(mapToResponse, HttpStatus.OK);
	}

}
