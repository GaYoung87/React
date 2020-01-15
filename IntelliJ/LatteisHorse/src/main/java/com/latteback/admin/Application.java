package com.latteback.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//앞으로 만들 프로젝트의 메인클래스가 된다.
//@SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야만 한다.
@EnableJpaAuditing //JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);//내장 WAS를 실행. 스프링 부트에서는 내장 WAS 사용하는 것을 권장
        //그 이유는 언제 어디서나 같은 환경에서 스프링 부트를 배포할 수 있기 때문
    }
}
