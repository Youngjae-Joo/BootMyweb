package com.codehistory.myweb.product.service;

import java.util.ArrayList;
import java.util.List;

import com.codehistory.myweb.command.CategoryVO;
import com.codehistory.myweb.command.ProductVO;
import com.codehistory.myweb.util.Criteria;

public interface ProductService {
	public int regist(ProductVO vo);
	public ArrayList<ProductVO> getList(String user_id,Criteria cri);
	public ProductVO getDetail(String prod_id);
	public int getTotal(String user_id,Criteria cri);
	
	//카테고리대분류
	public List<CategoryVO> getCategory();
	
	//카테고리 중,소분류
	public List<CategoryVO> getCategoryChild(CategoryVO vo);
}
