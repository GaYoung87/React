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
