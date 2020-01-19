package com.study.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    // 굳이 작성할 필요없는데, Query를 작성하면 이대로 들어간다.
    @Query("select p from Posts p order by p.id desc")
    List<Posts> findAllDesc();
}
