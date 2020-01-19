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