package com.gcavalli.dspostsmongo.models.embedded;

import com.gcavalli.dspostsmongo.models.entities.User;

public class Author {
	
	private String id;
	private String name;
	
	public Author() {
	}
	
	public Author(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Author(User entity) {
		super();
		this.id = entity.getId();
		this.name = entity.getName();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
