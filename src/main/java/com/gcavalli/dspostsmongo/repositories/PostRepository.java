package com.gcavalli.dspostsmongo.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gcavalli.dspostsmongo.models.entities.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	@Query("{ 'title': { $regex: ?0, $options: 'i'} }") //?0 = method first param
	List<Post> titleSearch(String text);
	
	@Query("{"
			+ "  $and: [  "
			+ "    {"
			+ "      $or: ["
			+ "      { title: { $regex: ?0, $options: 'i' } },"
			+ "      { body: { $regex: ?0, $options: 'i' } },"
			+ "      { 'comments.text': { $regex: ?0, $options: 'i' } }"
			+ "      ]"
			+ "    },"
			+ "    { moment: { $gte: ?1 } },"
			+ "    { moment: { $lte: ?2} }"
			+ "  ]"
			+ "}")
	List<Post> fullTextAndInstantSearch(String text, Instant start, Instant end);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
}
