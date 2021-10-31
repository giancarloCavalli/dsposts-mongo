package com.gcavalli.dspostsmongo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gcavalli.dspostsmongo.models.dto.PostDTO;
import com.gcavalli.dspostsmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

	@Autowired
	private PostService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findAll(@PathVariable String id) {
		PostDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(defaultValue = "", name = "text", value = "text") String text) {
		List<PostDTO> list = service.findByTitle(text);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<PostDTO>> fullTextAndInstantSearch(
			@RequestParam(defaultValue = "", value = "text") String text,
			@RequestParam(defaultValue = "", value = "start") String start,
			@RequestParam(defaultValue = "", value = "end") String end
			) {
		List<PostDTO> list = service.fullTextAndInstantSearch(text, start, end);
		return ResponseEntity.ok(list);
	}
	
}
