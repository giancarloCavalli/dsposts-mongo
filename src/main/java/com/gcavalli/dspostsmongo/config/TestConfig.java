package com.gcavalli.dspostsmongo.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gcavalli.dspostsmongo.models.entities.User;
import com.gcavalli.dspostsmongo.repositories.PostRepository;
import com.gcavalli.dspostsmongo.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@PostConstruct
	public void init() {
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brau", "maria@gmail.com");
		User alex = new User(null, "Alex Jaeger", "alex@gmail.com");
		User jonan = new User(null, "Jonan Meirand", "jonan@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, jonan));
	}
}
