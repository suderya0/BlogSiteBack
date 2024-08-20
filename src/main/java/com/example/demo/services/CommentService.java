package com.example.demo.services;

import com.example.demo.entities.Comment;
import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.repos.CommentRepository;
import com.example.demo.requests.CommentCreateRequest;
import com.example.demo.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private UserServices userServices;
    private PostService postService;

    public CommentService(CommentRepository commentRepository, UserServices userServices, PostService postService) {
        this.commentRepository = commentRepository;
        this.userServices = userServices;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }else{
            return commentRepository.findAll();
        }
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest request) {
        User user = userServices.getOneUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());

        if(user != null && post != null){
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());
            return commentRepository.save(commentToSave);
        }else {
            return null;
        }
    }

    public Comment updateOneCommentById(Long commentId, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);

        }else {
            return null;
        }
    }

    public void deleteOneCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
