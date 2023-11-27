package com.example.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.dao.ShopDAO;
import com.example.domain.QueryVO;
import com.example.domain.ShopVO;
import com.example.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopRestController {
	@Autowired
	ShopDAO dao;

	@Autowired
	ShopService service;

	@GetMapping("/insert/favorits")
	public void insert(int pid, String uid) {
		service.insertFavorites(pid, uid);
	}

	@GetMapping("/delete/favorits")
	public void delete(int pid, String uid) {
		service.deleteFavorites(pid, uid);
	}

	@PostMapping("/image")
	public void Image(ShopVO vo, MultipartHttpServletRequest multi) {
		MultipartFile file = multi.getFile("file");
		String path = "/upload/shop";
		String fileName = System.currentTimeMillis() + ".jpg";
		try {
			file.transferTo(new File("c:" + path + fileName));
			vo.setImage(path + fileName);
			dao.image(vo);
		} catch (Exception e) {
			System.out.println("이미지 변경: " + e.toString());
		}
	}

	@PostMapping("/insert")
	public void insert(@RequestBody ShopVO vo) {
		service.insert(vo);
	}

	@PostMapping("/update")
	public void update(@RequestBody ShopVO vo) {
		dao.update(vo);
	}

	@GetMapping("/list.json")
	public HashMap<String, Object> list(QueryVO vo) {
		return service.list(vo);
	}

	@GetMapping("/delete")
	public void delete(int pid) {
		dao.delete(pid);
	}

	@GetMapping("/read/{pid}")
	public HashMap<String, Object> read(@PathVariable("pid") int pid) {
		return dao.read(pid);
	}

	@GetMapping("/info/{pid}")
	public HashMap<String, Object> info(@PathVariable("pid") int pid, String uid) {
		return service.read(pid, uid);
	}

}
