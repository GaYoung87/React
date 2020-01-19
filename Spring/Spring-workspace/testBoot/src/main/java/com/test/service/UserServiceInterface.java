package com.test.service;

import java.util.List;

import com.test.vo.User;

public interface UserServiceInterface {
	List<User> selectAll();
	User selectOne(int num);
	void insert(User u);
	void update(User u);
	void delete(String num);
}
