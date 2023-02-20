package com.codehistory.myweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.codehistory.myweb.command.CategoryVO;
import com.codehistory.myweb.product.service.ProductService;

@RestController
public class AjaxController {
	
	@Autowired
	private ProductService productService;
	
	
	//대분류 카테고리 요청
	@GetMapping("/getCategory")
	public List<CategoryVO> getCategory(){
		return productService.getCategory();
	}
	
	//중분류, 소분류 카테고리 요청
	@GetMapping("/getCategoryChild/{group_id}/{category_lv}/{category_detail_lv}")
	public List<CategoryVO> getCategoryChild(@PathVariable("group_id") String group_id,
											@PathVariable("category_lv") int category_lv,
											@PathVariable("category_detail_lv")int category_detail_lv){
		
		CategoryVO vo =CategoryVO.builder().group_id(group_id).category_lv(category_lv).category_detail_lv(category_detail_lv).build();
		return productService.getCategoryChild(vo);
	}
	
}
