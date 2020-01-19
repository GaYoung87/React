package com.study.admin.web.dto;

import com.study.admin.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostsListResponseDto {
    // DB에서 한 줄 보여줌(id부터 모든 것!)
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDate createdDate;
    private LocalDate modifiedDate;

    // Posts에서 toEntity로 만든 객체에서 가지고와서 보여주기
    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }
}
