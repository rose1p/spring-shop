package com.example.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.domain.ShopVO;

@Repository
public class ShopDAOImpl implements ShopDAO{
	@Autowired
	SqlSession session;
	String namespace="com.example.mapper.ShopMapper";
	
	@Override
	public void insert(ShopVO vo) {
		session.insert(namespace + ".insert", vo);
	}

	@Override
	public int check(String productId) {
		return session.selectOne(namespace + ".check", productId);
	}
}