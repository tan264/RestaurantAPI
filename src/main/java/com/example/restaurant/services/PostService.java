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

    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post deleteById(int id) {
        Post foundedPost = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException(
                        String.format("Post with id = %d does not exist", id)));

        postRepository.deleteById(id);
        return foundedPost;
    }
}
