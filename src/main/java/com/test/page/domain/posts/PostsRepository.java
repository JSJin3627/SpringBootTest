package com.test.page.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

//  ibatis나 MyBatis 등에서 DAO라고 불리는 DB Layer 접근자를 
//  JPA에서는 Repository라 부르며 인터페이스로 생성 JpaRepository<Entity클래스, PK타입> 을 상속하면 기본적인 CRUD 메소드 자동 추가
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
