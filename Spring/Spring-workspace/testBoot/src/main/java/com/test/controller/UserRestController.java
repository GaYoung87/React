package com.test.controller;

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

import com.test.service.UserServiceInterface;
import com.test.vo.User;

@RestController
public class UserRestController {
	@Autowired
	UserServiceInterface service;
	
	@GetMapping("/selectAll")
	List<User> selectAll(){
		return service.selectAll();
	}
	
	@GetMapping("/selectOne/{num}")
	Map<String, String> selectOne(@PathVariable int num) {
		Map<String, String> map=new HashMap<>();
		User u=service.selectOne(num);
		map.put("name", u.getName());
		map.put("message", u.getMassage());
		return map;
	}
	
	@PostMapping("/insert")
	Map<String,String> insert(@RequestBody User u){
		service.insert(u);
		Map<String,String> map=new HashMap<>();
		map.put("result","성공적으로 추가됨!");
		return map;
	}
	@PutMapping("/update")
	Map<String,String> update(@RequestBody User u){
		service.update(u);
		Map<String,String> map=new HashMap<>();
		map.put("result","성공적으로 업데이트됨!");
		return map;
	}
	@DeleteMapping("/delete/{num}")
	Map<String, String> delete(@PathVariable String num) {
		service.delete(num);
		Map<String, String> map=new HashMap<>();
		map.put("message", "삭제했습니당");
		return map;
	}
	
}
