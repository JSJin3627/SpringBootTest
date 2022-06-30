package com.test.page.web;

import com.test.page.web.Dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 준다.
//각 매소드마다 @responsbody를 선언했지만 한 번에 할 수 있도록 통합시킨것이다.
@RestController
public class HelloController
{
    //http method인 get의 요청을 받을 수 있는 api를 만든것이다.
    // "/hello"로 요청이 오면 문자열 hello를 반환한다.
    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            @RequestParam("name")String name,
            @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}