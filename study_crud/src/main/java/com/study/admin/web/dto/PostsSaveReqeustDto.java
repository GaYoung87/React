package com.study.admin.web.dto;

import com.study.admin.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveReqeustDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveReqeustDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // toEntity() 메서드를 통해서 DTO에서 필요한 것을 객체로 만듦
    // Posts에서 @Builder로 만든 것을 여기서 객체화시킴
    public Posts toEntity() {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
