package com.ssafy.pms.service;

import java.util.List;

import com.ssafy.pms.vo.Phone;
import com.ssafy.pms.vo.User;

public interface PhoneService {
	List<Phone> selectAll();// num, model, vendor

	Phone selectOne(String num);// num, model, price, vendor

	int insert(Phone phone);

	int delete(String num);

	int update(Phone phone);// num 기준으로 model, price 수정

	User selectUser(String id);// login check
}