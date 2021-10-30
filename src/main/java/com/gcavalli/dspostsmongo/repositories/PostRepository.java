package com.gcavalli.dspostsmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gcavalli.dspostsmongo.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
