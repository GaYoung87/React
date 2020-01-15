package com.latteback.admin.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // 클래스 내의 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor //Gettter와 NoargsConstructor는 롬복의 어노테이션. 기본 생성자 자동 추가, public Posts(){} 와 같은 효과
@Entity //JPA의 어노테이션. 테이블과 링크될 클래스임을 나타냅니다. 기본 값으로 클래스의 카멜 케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭합니다. ex) SalesManager.java -> sales_manager table
public class Posts {

    @Id//해당 테이블의 PK 필드를 나타냅니다. 왠만하면 Entity의 PK는 Long타입의 Auto_increment를 추천합니다.
    //이렇게 하면 MySQL 기준으로 bigint 타입이 됩니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK의 생성 규칙을 나타냅니다.
    //스프링 부트 2.0에서는 Generation Type.IDENTITY 옵션을 추가해야만 auto_increment가 됩니다.
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타내며, 굳이 선언하지 않더라도, 해당 클래스의 필드는 모두 칼럼이 됩니다.
    //사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다.
    //문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고싶거나(ex:title), 타입을 TEXT로 변경하고 싶거나(ex:content) 등의 경우에 사용됩니다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length=500, nullable = false)
    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
//Setter 메소드가 없다는 점이 아주 특이한 클래스이다. 만일 getter/setter를 무작정 생성하게 되면, 해당 클래스의
//인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 없어, 차후 기능 변경시 정말 복잡해집니다.
//그래서 Entity 클래스에는 절대로 Setter 메소드를 만들지 않습니다! 대신, 해당 필드의 값 변경이 필요하면
//명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만 합니다!

