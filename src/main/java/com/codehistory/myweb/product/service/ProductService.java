package com.codehistory.myweb.product.service;

import java.util.ArrayList;

import com.codehistory.myweb.command.ProductVO;
import com.codehistory.myweb.util.Criteria;

public interface ProductService {
	public int regist(ProductVO vo);
	public ArrayList<ProductVO> getList(String user_id,Criteria cri);
	public ProductVO getDetail(String prod_id);
	public int getTotal(String user_id,Criteria cri);
}
