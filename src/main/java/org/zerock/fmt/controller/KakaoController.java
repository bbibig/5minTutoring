package org.zerock.fmt.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.fmt.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@Controller
@RequestMapping("/test")
public class KakaoController {
	
	@Setter(onMethod_ = @Autowired)
	private UserService userservice;
	
	@ResponseBody 
	@GetMapping("/kakao")
	public void kakaoCallback(@RequestParam String code) throws JsonMappingException, JsonProcessingException {
		log.info(" 1. kakao code : {}", code);
		
		String token = getAccessToken(code);
		
		//token으로 사용자정보 얻기
		HashMap<String, Object> userInfo = createKakaoUser(token);
		log.info("\t + userInfo: {}", userInfo);
		
		
		
	}// kakaoCallback
	
	@PostMapping("/kakaoLoginPro")
	public Map<String, Object> kakaologinPro(@RequestParam HashMap<String,Object> paramMap, HttpSession session){
		log.info("paramMap : {}", paramMap);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> kakao = userservice.kakaoCheck(paramMap);
		
//		if(kakaoConnectionCheck == null) { //일치하는 이메일 없으면 가입
//			resultMap.put("JavaData", "register");
//		}else if(kakaoConnectionCheck.get("KAKAOLOGIN") == null && kakaoConnectionCheck.get("EMAIL") != null) { //이메일 가입 되어있고 카카오 연동 안되어 있을시
//			System.out.println("kakaoLogin");
//			userservice.setKakaoConnection(paramMap);
//			Map<String, Object> loginCheck = userservice.userKakaoLoginPro(paramMap);
//			session.setAttribute("userInfo", loginCheck);
//			resultMap.put("JavaData", "YES");
//		}else{
//			Map<String, Object> loginCheck = userservice.userKakaoLoginPro(paramMap);
//			session.setAttribute("userInfo", loginCheck);
//			resultMap.put("JavaData", "YES");
//		}
		return resultMap;
	}//kakaologinPro
	
	//access_token을 이용하여 사용자 정보 조회
	public HashMap<String, Object> createKakaoUser(String token) {
		// *** 정보 map 으로 저장 ***
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		
		String reqURL = "https://kapi.kakao.com/v2/user/me";
	    
	    try {
	       URL url = new URL(reqURL);
	       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	       conn.setRequestMethod("POST");
	       conn.setDoOutput(true);
	       conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

	       //결과 코드가 200이라면 성공
	       int responseCode = conn.getResponseCode();
	       log.info("\t+ accessToken 1. responseCode : " + responseCode);

	       //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	       String line = "";
	       String result = "";

	       while ((line = br.readLine()) != null) {
	           result += line;
	       }
	       log.info("\t+ accessToken 2. response body : " + result);

	       //Gson 라이브러리로 JSON파싱
	       JsonParser parser = new JsonParser();
	       JsonElement element = parser.parse(result);

	       String id = element.getAsJsonObject().get("id").getAsString();
	       JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
	       boolean hasEmail = kakao_account.get("has_email").getAsBoolean();
	      
	       //JSON -> 데이터 넣기 
	       String email = "";
	       String ninkName = "";
	       String birth = "";
	       String kakaoGender = ""; //카카오성별 -> 변경 
	       String gender = "";
	       
	       if(hasEmail){
	           email = kakao_account.getAsJsonObject().get("email").getAsString();
	           ninkName = kakao_account.get("profile").getAsJsonObject().get("nickname").getAsString();
	           birth = kakao_account.get("birthday").getAsString();
	           kakaoGender = kakao_account.get("gender").getAsString();
	           gender = (kakaoGender.equals("female"))?"여자":"남자";
	       }
	       log.info("\t ***** kakao / id : "+id);
	       log.info("\t ***** kakao / email : {} *****", email);
	       log.info("\t ***** kakao / ninkName : {} *****", ninkName);	       
	       log.info("\t ***** kakao / birth : {} *****", birth);	       
	       log.info("\t ***** kakao / kakaoGender : {} *****", kakaoGender);	       
	       log.info("\t ***** kakao / gender : {} *****", gender);	       
	       
	       userInfo.put("id", id);
	       userInfo.put("email", email);
	       userInfo.put("ninkName", ninkName);
	       userInfo.put("birth", birth);
	       userInfo.put("gender", gender);
	       
	       br.close();
	       } catch (IOException e) {
	            e.printStackTrace();
	       }//try-catch
	    return userInfo;
	 }//createKakaoUser
	
	
//	@RequestMapping("/kakao")
	public String kakaoCallback(@RequestParam(value = "code", required = false) String code, 
								HttpSession session) throws Exception{
		log.trace("kakaoCallback");

		//1. 카카오 서버로부터 받는 인가 코드
		log.info("\t + 1. code : {}", code);
		
		//2. 토큰얻어옴
		String access_Token = getAccessToken(code);
		log.info("\t + 2. access_Token : {}", access_Token);
		
		//3. 사용자정보 얻음 
		HashMap<String, Object> userInfo = getUserInfo(access_Token);
	    log.info("\t + 3. userInfo : {}", userInfo);
	    
	    if(userInfo.get("email") != null) {
	    	session.setAttribute("userid", userInfo.get("email"));
	    	session.setAttribute("access_Token", access_Token);
	    }
	    //일단 이메일만 얻어
	    //log.info("\t + 3. userInfo-nick : {}", userInfo.get("nickname")); 
	    
		return "/login";
	}//getKakaoURL 
		
    //토큰발급
	public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  URL연결은 입출력에 사용 될 수 있고, 
            // POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //	POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=f242881542c06c438c6f81728a868bf9");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost:8080/test/kakao");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("\t + GetToken 1. responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            log.info("\t+ GetToken 2. response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
            log.info("\t+ GetToken 3. access_token : " + access_Token);
            log.info("\t+ GetToken 4. refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//try-catch

        return access_Token;
    }//getAccessToken
	
    //유저정보조회
    public HashMap<String,Object> getUserInfo (String access_Token) {
    	
        //  요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";
            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

//            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();
            
            userInfo.put("accessToken", access_Token);
//            userInfo.put("nickname", nickname);
            userInfo.put("email", email);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//여기까지 유저정보 파싱 
        
        return userInfo;//유저정보전달
    }
}//end class
