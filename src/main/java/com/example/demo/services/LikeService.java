package com.example.demo.services;

import com.example.demo.entities.Like;
import com.example.demo.repos.LikeRepository;
import com.example.demo.responses.LikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserServices userServices;
    private PostService postService;

    @Autowired
    public LikeService(LikeRepository likeRepository, UserServices userServices, PostService postService) {
        this.likeRepository = likeRepository;
        this.userServices = userServices;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()){
            list =  likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()){
            list=  likeRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            list =  likeRepository.findByPostId(postId.get());
        }else{
            list =  likeRepository.findAll();
        }
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }}
