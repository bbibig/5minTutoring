package org.zerock.fmt.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.fmt.mapper.UserMapper;

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
	private UserMapper mapper;
	
//	@ResponseBody 
	@RequestMapping("/kakao")
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

            //  URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
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
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return access_Token;
    }
	
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
        
        //DB에 정보저장 
//        UserDTO dto = this.mapper.findKakao(userInfo);
//        log.info("\t+ Kakao binding for dto : {}", dto);
//        if(dto==null) {
//        	UserDTO vo = this.mapper.findKakao(userInfo);
//        	return null;
//        } else {
//        	return null;
//        }
//        return null;
        return userInfo;//유저정보전달
    }
}//end class
