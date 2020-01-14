package com.latteback.admin.web;

import com.latteback.admin.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌.이전에 @ResponseBody라고 각 메소드마다 선언했던 것을 한번에 통틀어서 할 수 있게 해주는 것
public class HelloController {
    @GetMapping("/hello") //Http 메서드인 Get의 요청을 받을 수 있는 API를 만들어 줍니다.
    public String hello(){
        return "hello";
    }//실제 테스트는 Application class를 실행시키고, localhost:8080/요청 으로 확인하실 수가 있게 됩니다.


    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        //여기서 RequestParam은, 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션입니다.
        //여기서는 외부에서 name(@RequestParam("name"))이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장하게 됩니다.
        return new HelloResponseDto(name,amount);
    }
}
