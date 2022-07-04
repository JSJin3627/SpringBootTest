package com.test.page.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void contentSaveLoad() {
        String title = "Test Title";
        String content = "Test Content";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("Writer")
                .build());

        List<Posts> postList = postsRepository.findAll();

        Posts posts = postList.get(0);
        assert (posts.getTitle()).equals(title);
        assert (posts.getContent()).equals(content);
    }

    @Test
    public void BaseTimeEntity_Create(){
        LocalDateTime now = LocalDateTime.of(2022,7,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>createDate = "+posts.getCreateDate() + ", modifiedDate = " + posts.getModifiedDate());
        assert (posts.getCreateDate()).isAfter(now);
        assert (posts.getModifiedDate()).isAfter(now);
    }
}
