package com.gcavalli.dspostsmongo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcavalli.dspostsmongo.models.dto.UserDTO;
import com.gcavalli.dspostsmongo.models.entities.User;
import com.gcavalli.dspostsmongo.repositories.UserRepository;
import com.gcavalli.dspostsmongo.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll() {
		return repository.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
	}
	
	public UserDTO findById(String id) {
		Optional<User> result = repository.findById(id);
		User entity = result.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
		return new UserDTO(entity);
	}
	
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		return new UserDTO(repository.save(entity));
	}

	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
	}
}
