package com.example.demo.services;

import com.example.demo.entities.Like;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repos.LikeRepository;
import com.example.demo.repos.PostRepository;
import com.example.demo.requests.PostCreateRequest;
import com.example.demo.requests.PostUpdateRequest;
import com.example.demo.responses.LikeResponse;
import com.example.demo.responses.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private LikeService likeService;
    private UserServices userServices;

    @Autowired
    public PostService(PostRepository postRepository, UserServices userServices) {
        this.postRepository = postRepository;
        this.userServices = userServices;

    }

    @Lazy
    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()){
            list =  postRepository.findByUserId(userId.get());
        }else {
            list = postRepository.findAll();
        }
        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.empty(), Optional.of(p.getId()));
            return new PostResponse(p, likes);}).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostRequest) {
        User user =  userServices.getOneUserById(newPostRequest.getUserId());
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
