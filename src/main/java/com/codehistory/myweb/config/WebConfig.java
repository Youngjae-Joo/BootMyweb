package com.codehistory.myweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.codehistory.myweb.util.interceptor.MenuHandler;
import com.codehistory.myweb.util.interceptor.UserAuthHandler;

@Configuration//스프링 설정파일
public class WebConfig implements WebMvcConfigurer{

	//프리핸들러
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	
	//포스트핸들러
	@Bean
	public MenuHandler menuHandler() {
		return new MenuHandler();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		registry.addInterceptor(userAuthHandler())
//			.addPathPatterns("/main")
//			.addPathPatterns("/product/*")
//			.addPathPatterns("/user/*")
//			.excludePathPatterns("/user/login")
//			.excludePathPatterns("/user/join");
//		
//		.addPathPatterns("/**")
//		.excludePathPatterns("/user/login")
//		.excludePathPatterns("/user/join")
//		.excludePathPatterns("/js/*")
//		.excludePathPatterns("/css/*").excludePathPatterns("/img/*");
//		REST API 패스에서 제외..?
		// 경로를 ("/**")로 하면 js, css, img, rest api패스까지 다 설정해줘야 한다. 따라서 /**로 하지 말고 필요한 페이지를 지정하기.
		
		
		registry.addInterceptor(menuHandler())
			.addPathPatterns("/main")
			.addPathPatterns("/product/*")
			.addPathPatterns("/user/*");
	}
	
	
	
	
	
}
