package com.ssafy.pms.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.pms.vo.Phone;
import com.ssafy.pms.vo.User;

@Repository
public class PhoneDaoImpl implements PhoneDao{
	@Autowired
	SqlSession session;

	@Override
	public List<Phone> selectAll() {
		return session.selectList("pms.phone.selectAll");
	}
	@Override
	public Phone selectOne(String num) {
		return session.selectOne("pms.phone.selectOne",num);
	}
	@Override
	public int insert(Phone phone) {
		if(phone.getVcode().equals("애플")) {
			phone.setVcode("30");
		}else if(phone.getVcode().equals("엘지")) {
			phone.setVcode("20");
		}else {
			phone.setVcode("10");
		}
		return session.insert("pms.phone.insert",phone);
	}
	@Override
	public int delete(String num) {
		return session.delete("pms.phone.delete",num);
	}
	@Override
	public int update(Phone phone) {
		return session.update("pms.phone.update",phone);
	}
	@Override
	public User selectUser(String id) {
		return session.selectOne("pms.user.selectUser",id);
	}
}
