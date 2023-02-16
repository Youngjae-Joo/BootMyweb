package com.codehistory.myweb.product.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codehistory.myweb.command.ProductVO;
import com.codehistory.myweb.util.Criteria;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper productMapper;

	@Override
	public int regist(ProductVO vo) {
		return productMapper.regist(vo);
	}

	@Override
	public ArrayList<ProductVO> getList(String user_id,Criteria cri) {
		return productMapper.getList(user_id,cri);
	}

	@Override
	public ProductVO getDetail(String prod_id) {
		return productMapper.getDetail(prod_id);
	}

	@Override
	public int getTotal(String user_id,Criteria cri) {
		return productMapper.getTotal(user_id,cri);
	}

}