package com.example.restaurant.services;

import com.example.restaurant.models.Post;
import com.example.restaurant.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post addPost(Post post) {
        return postRepository.save(post);
    }
}
