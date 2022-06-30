package com.test.page.web;

import com.test.page.domain.posts.Posts;
import com.test.page.domain.posts.PostsRepository;
import com.test.page.web.Dto.PostsSaveRequestDto;
import com.test.page.web.Dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;
    @After
    public void tearDown()throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public  void Posts_Create()throws Exception{
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("auther")
                .build();

        String url = "http://localhost:" + port + "/api/vl/posts";

        ResponseEntity<Long> responseEntity = restTemplate
                .postForEntity(url, requestDto, Long.class);

        assert (responseEntity.getStatusCode())
                .equals(HttpStatus.OK);
        assert(responseEntity.getBody()) >= 0L;
        List<Posts> all = postsRepository.findAll();
        assert (all.get(0).getTitle().equals(title));
        assert (all.get(0).getContent()).equals(content);
    }

    @Test
    public void Posts_update() throws Exception{
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/vl/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestDtoHttpEntity
                , Long.class);

        assert (responseEntity.getStatusCode()).equals(HttpStatus.OK);
        assert (responseEntity.getBody())>=0L;

        List<Posts> all = postsRepository.findAll();
        assert (all.get(0).getTitle().equals(expectedTitle));
        assert (all.get(0).getContent().equals(expectedContent));
    }
}
