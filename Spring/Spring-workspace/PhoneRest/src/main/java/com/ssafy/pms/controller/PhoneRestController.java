package com.ssafy.pms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pms.service.PhoneService;
import com.ssafy.pms.vo.Phone;
import com.ssafy.pms.vo.User;

@RestController
public class PhoneRestController {
	@Autowired
	PhoneService service;

	@GetMapping("/phone")
	public List<Phone> list() {
		return service.selectAll();
	}

	@GetMapping("/phone/{num}")
	public Phone view(@PathVariable String num) {
		return service.selectOne(num);
	}

	@PostMapping("/phone")
	public Map insert(@RequestBody Phone phone) {
		service.insert(phone);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "insert success");
		return map;
	}

	@PutMapping("/phone")
	public Map update(@RequestBody Phone phone) {
		service.update(phone);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "update success");
		return map;
	}

	@DeleteMapping("/phone/{num}")
	public Map delete(@PathVariable String num) {
		service.delete(num);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "delete success");
		return map;
	}

	@GetMapping("/login/{id}")
	public User login(@PathVariable String id, HttpSession session) {
		User user = service.selectUser(id);
		if (user != null) {
			session.setAttribute("id", id);
		}
		return user;
	}

	@GetMapping("/logout/{id}")
	public void logout(@PathVariable String id, HttpSession session) {
		session.setAttribute("id", null);
		session.invalidate();
	}
}
