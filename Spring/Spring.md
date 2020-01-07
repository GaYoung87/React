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


### 

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

