package com.gcavalli.dspostsmongo.services;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcavalli.dspostsmongo.models.dto.PostDTO;
import com.gcavalli.dspostsmongo.models.entities.Post;
import com.gcavalli.dspostsmongo.repositories.PostRepository;
import com.gcavalli.dspostsmongo.services.exceptions.ResourceNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;
	
	public PostDTO findById(String id) {
		Post entity= getEntityById(id);
		return new PostDTO(entity);
	}
	
	public List<PostDTO> findByTitle(String text) {
		List<Post> list= repository.titleSearch(text);
		return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	public List<PostDTO> fullTextAndInstantSearch(String text, String start, String end) {
		Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
		Instant endMoment = convertMoment(end, Instant.now());
		List<Post> list= repository.fullTextAndInstantSearch(text, startMoment, endMoment);
		return list.stream().map(x -> new PostDTO(x)).collect(Collectors.toList());
	}
	
	private Instant convertMoment(String originalString, Instant alternative) {
		try {
			return Instant.parse(originalString);
		} catch (DateTimeParseException dtpe) {
			return alternative;
		}
	}

	private Post getEntityById(String id) {
		Optional<Post> result = repository.findById(id);
		return result.orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
	}
	
}
