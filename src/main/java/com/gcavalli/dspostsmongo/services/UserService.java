package com.gcavalli.dspostsmongo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcavalli.dspostsmongo.models.dto.UserDTO;
import com.gcavalli.dspostsmongo.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll() {
		return repository.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
}
