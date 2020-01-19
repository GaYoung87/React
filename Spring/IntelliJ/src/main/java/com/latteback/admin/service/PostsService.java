package com.latteback.admin.service;

import com.latteback.admin.domain.posts.Posts;
import com.latteback.admin.domain.posts.PostsRepository;
import com.latteback.admin.web.dto.PostsResponseDto;
import com.latteback.admin.web.dto.PostsSaveRequestDto;
import com.latteback.admin.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // @Transactional: 전산적으로나 데이터베이스에 있어서도 이처럼 하나의 묶음으로 처리가 이루어져야 하는 모든것에 사용
    @Transactional
    // repository가 dao의미
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts=postsRepository.findById(id).orElseThrow(()
                        -> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        posts.update(requestDto.getTitle(),requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts=postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id){
        Posts entity=postsRepository.findById(id)
                .orElseThrow(()
                        ->new IllegalArgumentException("해당 사용자가 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }
}
