package com.latteback.admin.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor // public Posts(){} 와 같은 효과
public class PostsUpdateRequestDto {
    private String title;
    private String content;


    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}