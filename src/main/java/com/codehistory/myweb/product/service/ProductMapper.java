package com.codehistory.myweb.product.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.codehistory.myweb.command.ProductVO;
import com.codehistory.myweb.util.Criteria;

@Mapper
public interface ProductMapper {
	public int regist(ProductVO vo);
	public ArrayList<ProductVO> getList(@Param("user_id") String user_id, @Param("cri") Criteria cri); //조회, 특정회원정보
	public ProductVO getDetail(String prod_id);
	public int getTotal(@Param("user_id") String user_id, @Param("cri") Criteria cri);
}