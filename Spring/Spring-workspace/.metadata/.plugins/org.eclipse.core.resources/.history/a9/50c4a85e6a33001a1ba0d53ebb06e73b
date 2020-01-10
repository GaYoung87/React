package com.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.vo.User;

@Repository("userdao")
public class UserDAO {
	
	@Autowired
	SqlSession session;
	
	public List<User> selectAll(){
		return session.selectList("gayoung.selectAll");
	}
	
	public User selectOne(int num123123) {
		return session.selectOne("gayoung.selectOne",num123123);
	}
	
	public void insert(User u12321) {
		 session.insert("gayoung.add",u12321);
	 }
	 
	public void update(User u) {
		 session.update("gayoung.update",u);
	 }
	 
	public void delete(String num) {
		 session.delete("gayoung.delete",num);
	 }
	
}
