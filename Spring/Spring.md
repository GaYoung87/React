# Spring

### 기본 동작순서 및 구조

![image](https://user-images.githubusercontent.com/52685278/71863001-d769f400-313e-11ea-86eb-005596acd7f2.png)

- Spring은 MVC -> **model - view - controller**
- Django는 MTV -> **model-template-view**
- Spring - Django  -> model - model / view - template / controller - view



#### VO : 구현되는 덩어리

```java
// 변수들의 집합
Class Man{
	String name;
	int age;
	String address;생성자
	getter,setter;
}
```



## Model

####  DAO - Service

- 이 변수를 이용해서 DB 작업을 하는 기능들을 모아둔 것

- **D**ata **A**ccess **O**bject

-  이곳에는 DB와의 작업에 사용할 함수들을 선언하고 정의(DB와 연결)

-  Service : DAO에서 사용자에게 선보일 기능

-  ex. DAO 내부에 CRUD가 있고 조회수올리는 Countup함수는 사용자에게 줄 필요가 없음

  ​      조회수 올리는것은 DB부분 -> 사용자가 임의로 조작하게 끔 하면 안됨

- DAO의 기능수 >= Service의 기능수  (일반적)

- Service는 DAO기능을 활용해서 만들 수가 있음 -> Service는 DAO에서 구현된 기능을 기반으로 구현




## Controller (Django에서 View)

- 사용자가 url 어떤 거로 요청할지, 어떤걸 클릭할지 제어해주는 것
- @Controller인지 @RestController인지 확실히애야함

**RestController**

-  Rest API를 구현할 때 쓰는 Controller 어노테이션
- Rest: View가 없이 데이터만 전송되기에 View로 보내는 작업 없이 데이터만 보냄
- ajax , axios 처럼 비동기 처리방식에 주로 사용

**Controller**

- jsp, html, 와 같은 View 이미지로 보내주는 Controller
- 화면을 넘겨버림(pjt에서 react팀에서 해결)

<hr>

# SpringBoot crud기본

**project시작하기**

- Spring Starter Project
- package - com
- Spring Boot DevTools, JDBC API, Mybatis Framework, MySQL Driver, Spring Web (5개 선택)

**[ 0단계 ]**

- src/main/java에 package생성 -> vo, dao, service, controller

## [ VO ]

```java
package com.study.vo;

public class User {
    // 내가 입력하는 것
	private int num;
	private String name;
	private String message;
    
    // source - getter,setter (자동완성)
    // getter, setter: 보통 클래스의 변수는 private으로 접근 제한자를 설정한 후
    //                 getter, setter를 통해 변수의 값을 변경하고 호출함
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
    
    // source - toString() (자동완성)
    // toString(): 디버깅할 때 사용
	@Override
	public String toString() {
		return "User [num=" + num + ", name=" + name + ", message=" + message + "]";
	}
    
    // source - Constructor using Fields() (자동완성)
    // 변수를 private으로 해주었으므로
	public User(int num, String name, String message) {
		super();  // 기본생성자 의미 -> 맨 위에 있어야함(부모)
        // this.num(부모의 num) = num(public User()안의 num)
		this.num = num;
		this.name = name;
		this.message = message;
	}
	
	
}

```

## [ DAO ]

```java
package com.study.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.vo.User;

// 해당 클래스가 DAO라는 것을 알리기 위해 @repository작성
// implements로 인터페이스를 구현해주고 필요한 메소드를 오버라이드 하여 정의
@Repository("userdao")
public class UserDAO {
	
	// 객체의 의존성
	// controller는 service에게 일하라고 시키고, service는 dao에 일하라고 시키고,
    // dao는 sql에 일하라고 시키는 것임
	@Autowired
	SqlSession session;  // session = db
	
	// selectAll: 모든 db보여줌
	// List<User> -> python에서 list의미
	// 이때, List는 List.util을 자동완성 해야함
	public List<User> selectAll() {
		return session.selectList("gayoung.selectAll");
	}
	
	// selectOne: db중에 하나 보여줌
	// User에서 num123과 동일한 num을 가지고있는 정보를 보여주겠다
	public User selectOne(int num123) {
		return session.selectOne("gayoung.selectOne", num123);
	}
	
	// insert는 데이터를 추가하는 것임 -> 추가된 데이터는 selectAll을 통해 확인가능
	// void: return되는 데이터 타입이 없
	public void insert(User u123) {
		session.insert("gayoung.add", u123);
	}
	
	public void update(User u) {
		session.update("gayoung.update", u);
	}
    
    public void delete(int num) {
		 session.delete("gayoung.delete",num);
	 }

}

```

## [ Service ]

**interface**

```java
package com.study.service;

import java.util.List;

import com.study.vo.User;

// interface는 public class UserServiceInterface가 아니라 public interface UserServiceInterface
// 함수자체를 반복하겠다.
// 함수 구상할 때 사용 -> implement하면 반환형
public interface UserServiceInterface {
	List<User> selectAll();
	User selectOne(int num);
	void insert(User u);
	void update(User u);
	void delete(int num);
}

```

**service**

```java
package com.study.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.dao.UserDAO;
import com.study.vo.User;

@Service("userservice")
public class UserService implements UserServiceInterface {
	
	// UserDAO파일 내용들을 dao로 가지고온다
	// Service는 DAO에서 구현할 기능만 작성하는 곳
	@Autowired
	UserDAO dao;
	
	// 슈퍼 클래스에 존재하는 필드나 메소드를 서브 클래스에서 재정의하여 사용할 수 있다.
	@Override
	public List<User> selectAll() {
		return dao.selectAll();
	}
	
	@Override
	public User selectOne(int num) {
		return dao.selectOne(num);
	}
	
	@Override
	public void insert(User u) {
		dao.insert(u);
	}

	@Override
	public void update(User u) {
		dao.update(u);
	}

	@Override
	public void delete(int num) {
		dao.delete(num);
	}

}

```

## Controller

```java
package com.study.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.service.UserServiceInterface;
import com.study.vo.User;

@RestController
public class UserRestController {
	
	@Autowired
	UserServiceInterface service;
	
	// get: url로 이동
	@GetMapping("/selectAll")
	List<User> selectAll() {
		return service.selectAll();
	}
	
	// @PathVariable: url경로에 변수 넣을 때 사용
	@GetMapping("/selectOne/{num}")
	Map<String, String> selectOne(@PathVariable int num) {
		Map<String, String> map = new HashMap<>();  // 리스트로 받겠다는 의미
		User u = service.selectOne(num);  // u를 지정, 이때 selectOne(num)에서 num은 @PathVariable의 num
		map.put("name", u.getName());  // getName -> vo에서 getter
		map.put("message", u.getMessage());
		return map;
	}
	
	// @RequestBody: 자바 객체를 HTTP 응답 몸체로 변환
	// @PostMapping: url로 경로 이동 불가. 내용만 추가됨
	@PostMapping("/insert")
	Map<String, String> insert(@RequestBody User u) {
		service.insert(u);
		// 부모                                                  자식
		// 부모가 자식보다 크기는 작다
		// 앞으로 사용할 것은  map.___으로 사용할 것!
		Map<String, String> map = new HashMap<>();
		map.put("result", "성공적으로 추가됨!");
		return map;
	}
	
	// @PutMapping: update쓸때 특화되어있음
	@PutMapping("/update")
	Map<String, String> update(@RequestBody User u) {
		service.update(u);
		Map<String, String> map = new HashMap<>();
		map.put("result", "성공적으로 업데이트됨!");
		return map;
	}
	
	@DeleteMapping("/delete/{num}")
	Map<String, String> delete(@PathVariable int num) {
		service.delete(num);
		Map<String, String> map = new HashMap<>();
		map.put("result", "삭제했습니다!");
		return map;
	}

}

```

## src/main/resources

**application.properties**

```properties
server.port=8080
# 여기서 gayoung이라 설정했기때문에 gayoung.으로 작성함
server.servlet.context-path=/gayoung
# 이때 test는 sql에서 schema를 의미
spring.datasource.url=jdbc:mysql://70.12.246.42:3306/test?serverTimezone=UTC
spring.datasource.username=scott
spring.datasource.password=tiger
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

mybatis.config-location=classpath:/config/SqlMapConfig.xml

spring.devtools.restart.additional-paths=.

```

**config - SqlMapConfig.xml** 

```xml
<!-- 파일 생성시 만들어짐 -->
<?xml version="1.0" encoding="UTF-8"?>

<!-- 복붙 -->
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">

<!-- configuration안에 별명(typeAliases) 그다음에 mapping(mappers)-->
<configuration>
	<typeAliases>
        <typeAlias alias="User" type="com.study.vo.User" />
    </typeAliases>
    
    <mappers>
      <mapper resource="config/UserMapper.xml" />
    </mappers>
</configuration>

```

**UserMapper.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE mapper
   PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
   "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--application.properties에서 gayoung으로 설정-->
<mapper namespace="gayoung">
	
    <!--DB에서 가지고오는 방법-->
	<select id="selectAll" resultType="User">
		select * from user
	</select>
	
	<select id="selectOne" parameterType="intger" resultType="User">
         <!--num(변수이름) = #{num}(db 변수num의 내용인 숫자)-->
         <!--parameterType은 selectOne함수에 넣는 int num == #{num}-->
		select * from user where num = #{num}
	</select>
	
    <!--insert는 resultType 없음 -> parameterType에 추가됨-->
	<insert id="insert" parameterType="User">
		insert into user(num, name, message) values(null, #{name}, #{message})
	</insert>
	
    <!--update는 resultType 없음 -> parameterType에 추가됨-->
	<update id="update" parameterType="User">
		update user
		set name = #{name}, message = #{message}
		where num = #{num}
	</update>
	
    <!--delete는 resultType 없음 -> parameterType에 추가됨-->
	<delete id="delete" parameterType="string">
		delete from user where num = #{num}
	</delete>
</mapper>
```

