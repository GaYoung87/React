package com.ssafy.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pms.dao.PhoneDao;
import com.ssafy.pms.vo.Phone;
import com.ssafy.pms.vo.User;

@Service
public class PhoneServiceImpl implements PhoneService {
	@Autowired
	PhoneDao dao;
	
	@Override
	public List<Phone> selectAll() {
		return dao.selectAll();
	}
	@Override
	public Phone selectOne(String num) {
		return dao.selectOne(num);
	}
	@Override
	public int insert(Phone phone) {
		return dao.insert(phone);
	}
	@Override
	public int delete(String num) {
		return dao.delete(num);
	}
	@Override
	public int update(Phone phone) {
		return dao.update(phone);
	}
	@Override
	public User selectUser(String id) {
		return dao.selectUser(id);
	}
}
