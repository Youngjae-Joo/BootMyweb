package com.codehistory.myweb;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codehistory.myweb.command.ProductVO;
import com.codehistory.myweb.product.service.ProductMapper;
import com.codehistory.myweb.util.Criteria;

@SpringBootTest
public class TestCode {
	
	@Autowired
	ProductMapper productMapper;
	
//	@Test
//	public void testCode01() {
//		for(int i =1; i<=300;i++) {
//			ProductVO vo = ProductVO.builder()
//					.prod_enddate("2023-02-15")
//					.prod_writer("admin")
//					.prod_name("test"+i)
//					.prod_price(i*1000)
//					.prod_count(i+100)
//					.prod_discount(i)
//					.prod_purchase_yn("N")
//					.prod_content("컨텐츠"+i)
//					.prod_comment("hellow"+i).build();
//			
//			productMapper.regist(vo);
//		}
//	}
	
//	@Test
//	public void testCode02() {
//		Criteria cri = new Criteria();
//		ArrayList<ProductVO> list=productMapper.getList("admin", cri);
//		System.out.println(list.toString());
//		
//	}
	
	
	
}
