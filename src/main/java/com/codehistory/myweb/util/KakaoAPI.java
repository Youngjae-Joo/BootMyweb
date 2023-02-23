package com.codehistory.myweb.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component("kakao")
public class KakaoAPI {
	
	//토큰발급기능
	public String getAccessToken(String code) {
		
		String requestURL = "https://kauth.kakao.com/oauth/token";
		String redirect_url="http://127.0.0.1:8484/user/kakao";
		
		String access_token="";
		String refresh_token="";
		
		//post의 폼데이터 형식 키=값&키=값...
		String data="grant_type=authorization_code"
					+"&client_id=c70a246db2d671e62329fc5dfccffd68"
					+"&redirect_uri="+redirect_url
					+"&code="+code;
		
		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST"); //post형식
			conn.setDoOutput(true); //카카오서버로부터 데이터응답을 허용
			
			//데이터전송을 위한 클래스
			//OutputStream out = conn.getOutputStream();
			//OutputStreamWriter ows=new OutputStreamWriter(out);
			//BufferedWriter br=new BufferedWriter(ows);
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			
			bw.write(data);
			bw.flush();
			
			//응답결과를 conn객체에서 받음. 데이터를 날리고 결과도 받는다.
			System.out.println("요청결과:"+conn.getResponseCode());
			if(conn.getResponseCode()==200) {//요청성공
				
				//데이터 받기.
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String result="";
				String str=null;
						
				while((str=br.readLine())!=null) { //한줄씩 읽음-읽을값이 없다면 null반환
					result+=str;
				}

				System.out.println(result);
				
				//제이슨 데이터 분해
				JsonParser json=new JsonParser(); //com.google.~~
				JsonElement element= json.parse(result);
				JsonObject obj=element.getAsJsonObject(); //json오브젝트 형변환
				
				access_token=obj.get("access_token").getAsString();//json데이터를 문자혈로 형변환
				refresh_token=obj.get("refresh_token").getAsString();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		return access_token;
	}
	
	//토큰기반으로 유저정보 얻기
	public Map<String, Object> getUserInfo(String access_token){
		
		//데이터 저장할 map
		Map<String, Object> map = new HashMap<>();
		
		String requestURL="https://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setRequestMethod("POST"); //post형식
			conn.setDoOutput(true); //카카오서버로부터 데이터응답을 허용
			
			//헤더에 토큰정보를 추가
			conn.setRequestProperty("Authorization","Bearer "+access_token);
			
			System.out.println("토큰요청결과:"+conn.getResponseCode());
			if(conn.getResponseCode()==200) { //사용자 데이터 요청 성공
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String result="";
				String str=null;
						
				while((str=br.readLine())!=null) { //한줄씩 읽음-읽을값이 없다면 null반환
					result+=str;
				}
				
				System.out.println(result);
				
				JsonParser json=new JsonParser(); 
				JsonElement element= json.parse(result);
				
				JsonObject properties=element.getAsJsonObject().get("properties").getAsJsonObject();
				String nickname=properties.getAsJsonObject().get("nickname").getAsString();
				
				JsonObject kakao_account=element.getAsJsonObject().get("kakao_account").getAsJsonObject();
				String email=kakao_account.getAsJsonObject().get("email").getAsString();
				
				map.put("nickname", nickname);
				map.put("email",email);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return map;
	}
	
	
}
