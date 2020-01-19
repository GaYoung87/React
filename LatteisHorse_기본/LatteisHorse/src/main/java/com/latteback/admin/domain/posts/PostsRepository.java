package com.latteback.admin.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//보통 MyBatis 에서 Dao 라고 불리는 DB Layer 접근자입니다.
//JPA에선 Repository라고 부르며 인터페이스로 생성합니다.
//단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스,PK 타입>를 상속하면 기본적인 CRUD 메소드가
//자동으로 생성됩니다.
//@Repository를 추가할 필요도 없습니다. 다만 여기서 주의하실 점은 Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다는 점입니다.
public interface PostsRepository extends JpaRepository<Posts,Long> {

}
