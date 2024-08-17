package com.example.demo.services;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repos.PostRepository;
import com.example.demo.requests.PostCreateRequest;
import com.example.demo.requests.PostUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;
    private UserServices userServices;

    public PostService(PostRepository postRepository, UserServices userServices) {
        this.postRepository = postRepository;
        this.userServices = userServices;
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if(userId.isPresent()){
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user =  userServices.getOneUser(newPostRequest.getUserId());
        if (user == null){
            return null;
        }

        Post toSave = new Post();
        toSave.setId(newPostRequest.getId());
        toSave.setText(newPostRequest.getText());
        toSave.setTitle(newPostRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest updateRequest) {
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(updateRequest.getText());
            toUpdate.setTitle(updateRequest.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
