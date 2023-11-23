package com.example.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.dao.ShopDAO;
import com.example.domain.ShopVO;
import java.io.*;

@Service
public class ShopServiceImin implements ShopService {
	@Autowired
	ShopDAO dao;

	@Transactional
	@Override
	public void insert(ShopVO vo) {
		int result = dao.check(vo.getProductId());
		if (result == 0) {
			// 이미지 업로드
			UUID uuid = UUID.randomUUID();
			String image = uuid.toString().substring(0, 8) + ".jpg";
			try {
				URL url = new URL(vo.getImage());
				InputStream is=url.openStream();
				FileOutputStream fos = new FileOutputStream("c:/upload/shop/" + image);
				int data;
				while((data=is.read()) != -1) {
					fos.write(data);
				}
				fos.close();
				vo.setImage("/upload/shop/" + image);
				//네이버저장
				dao.insert(vo);
			} catch (Exception e) {
				System.out.println("이미지 업로드오류:" + e.toString());
			}
		}
	}
}
