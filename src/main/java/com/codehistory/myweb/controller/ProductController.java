package com.codehistory.myweb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codehistory.myweb.command.PageVO;
import com.codehistory.myweb.command.ProductVO;
import com.codehistory.myweb.product.service.ProductService;
import com.codehistory.myweb.util.Criteria;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productService")
	private ProductService productService;
	

	@GetMapping("/productReg")
	public String reg() {
		return "product/productReg";
	}
	
	@GetMapping("/productList")
	public String list(HttpSession session/*, HttpServletRequest request*/,Model model,Criteria cri) {
		//프로세스
		//admin이라고 가정
		session.setAttribute("user_id", "admin");
		
		//로그인한 회원만 조회
		String user_id=(String)session.getAttribute("user_id");
		ArrayList<ProductVO> list=productService.getList(user_id,cri);
		model.addAttribute("list", list);
		
		//페이지네이션 처리
		int total=productService.getTotal(user_id,cri);
		PageVO pageVO=new PageVO(cri,total);
		model.addAttribute("pageVO", pageVO);
		
		return "product/productList";
	}
	
	
	//상세
	@GetMapping("/productDetail")
	public String detail(@ModelAttribute("prod_id")String prod_id, Model model) {
		ProductVO vo = productService.getDetail(prod_id);
		model.addAttribute("vo", vo);
		return "product/productDetail";
	}
	
	
	
	//등록
	@PostMapping("/registForm")
	public String registForm(@Valid ProductVO vo, Errors error, Model model,RedirectAttributes ra) {
		if(error.hasErrors()) {
			List<FieldError> list=error.getFieldErrors();
			ArrayList<String> msglist=new ArrayList<>();
			for(FieldError err : list) {
				if(err.isBindingFailure()) {
					model.addAttribute("msg", "형식이 잘못되었습니다.");
				}else {
					msglist.add(err.getDefaultMessage());
				}
			}
			model.addAttribute("msglist", msglist);
			model.addAttribute("vo", vo);
			return "product/productReg";
		}
		
		int result=productService.regist(vo);
		String msg=result==1?"정상 등록되었습니다":"등록에 실패했습니다";
		ra.addFlashAttribute("msg", msg);
		
		return "redirect:/product/productList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
