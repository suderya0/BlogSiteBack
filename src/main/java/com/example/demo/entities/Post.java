package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    Long id;
 //   Long userId; tabloyu tanımlarken kullandık

    @ManyToOne(fetch = FetchType.LAZY)  //bir sürüpostun tek user ı olabilir
    @JoinColumn(name= "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    User user;

    String title;

    @Lob
    @Column(columnDefinition = "text") //text denilmesi varchar olarak algılanmasın diye
    String text;
}
