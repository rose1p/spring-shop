package com.example.dao;

import com.example.domain.ShopVO;

public interface ShopDAO {
	public void insert(ShopVO vo);
	public int check(String productId);
}
