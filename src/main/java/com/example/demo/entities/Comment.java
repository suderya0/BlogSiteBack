package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "comment")
@Data
public class Comment {
    @Id
    Long id;
    //Long postId;

    @ManyToOne(fetch = FetchType.LAZY)  //bir sürüpostun tek user ı olabilir
    @JoinColumn(name= "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Post post;

   // Long userId;
   @ManyToOne(fetch = FetchType.LAZY)  //bir sürüpostun tek user ı olabilir
   @JoinColumn(name= "user_id", nullable = false)
   @OnDelete(action = OnDeleteAction.CASCADE)
   @JsonIgnore
   User user;

    @Lob
    @Column(columnDefinition = "text")
    String text;
}
