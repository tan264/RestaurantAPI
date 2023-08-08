package com.example.restaurant.repositories;

import com.example.restaurant.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}
