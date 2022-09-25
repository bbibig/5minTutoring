package org.zerock.fmt.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.fmt.common.SharedScopeKeys;
import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.FileService;
import org.zerock.fmt.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import oracle.jdbc.proxy.annotation.Post;

@NoArgsConstructor
@Log4j2
@Controller
@RequestMapping("/kakao")
public class KakaoController {

	@Setter(onMethod_ = @Autowired)
	private UserService userservice;
	
	@Setter(onMethod_ = @Autowired)
	private FileLoad fileupload;
	
	@Setter(onMethod_ = @Autowired)
	private FileService fileservice;

	// ------------------------ 카카오로그인 : JavaScriptKey사용
	// (순서) 1. 파라미터를 Map으로 받기
	// 2. DB 작업 or 로그인처리

	@PostMapping("/kakaoLoginPro")
	@ResponseBody
	public Map<String, Object> kakaologinPro(@RequestParam HashMap<String, Object> paramMap, HttpSession session)
			throws ServiceException {
		log.info("paramMap : {}", paramMap);

		Map<String, Object> resultMap = new HashMap<String, Object>();

		// 카카오정보 확인하여 map에 담는다.
		Map<String, Object> kakao = userservice.kakaoCheck(paramMap);
		log.info("kakao: {}", kakao);

		if (kakao == null) { // 일치하는 이메일 없으면 가입
			log.info("회원정보 없으므로 추가 회원가입");
			resultMap.put("JavaData", "register");

			// 회원가입은 되어있는데 카카오 연동 안되어 있을시
		} else if (kakao.get("KAKAOLOGIN") == null && kakao.get("USER_EMAIL") != null) {
			log.info("회원정보에 카카오정보 업데이트");
			// 카카오연동 넣어준다.
			this.userservice.updateKakao(paramMap);

			// 로그인해서 session에 올림
			UserVO vo = this.userservice.kakaoLogin(paramMap);
			session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
			resultMap.put("JavaData", "YES");

		} else {
			// 카카오 로그인 처리 함
			UserVO vo = this.userservice.kakaoLogin(paramMap);
			log.info("\t + vo : {}", vo);
			session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
			resultMap.put("JavaData", "YES");
		} // if-else

		return resultMap;
	}// kakaologinPro

	// 추가 가입신청 페이지(학생)
	@PostMapping(value = "/setStudent")
	public String setStudent(Model model, @RequestParam Map<String, Object> paramMap) {
		log.trace("\t + paramMap : {}", paramMap);

		model.addAttribute("user_email", paramMap.get("user_email"));
		model.addAttribute("kakaologin", paramMap.get("kakaologin"));

		return "login/1-04_kakaoStudent";
	}// userKakaoRegister

	// 추가 가입신청 페이지(튜터)
	@PostMapping(value = "/setTutor")
	public String setTutor(Model model, @RequestParam Map<String, Object> paramMap) {
		log.trace("\t + paramMap : {}", paramMap);

		model.addAttribute("user_email", paramMap.get("user_email"));
		model.addAttribute("kakaologin", paramMap.get("kakaologin"));

		return "login/1-04_kakaoTutor";
	}// userKakaoRegister

	// 학생 회원가입
	@PostMapping(value = "/KakaoRegisterStu")
	@ResponseBody
	public Map<String, Object> userKakaoregister(@RequestParam HashMap<String, Object> paramMap, HttpSession session) {
		log.trace("\t + paramMap : {}", paramMap);

		HashMap<String, Object> resultMap = new HashMap<>();

		// 추가 회원가입 진행
		Integer registerCheck = this.userservice.kakaoRegister(paramMap);
		
		// 회원가입 성공시 
		if (registerCheck != null && registerCheck > 0) {
			UserVO vo = this.userservice.kakaoLogin(paramMap);
			session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
			resultMap.put("JavaData", "YES");
		} else {
			resultMap.put("JavaData", "NO");
		}
		return resultMap;
	}// userKakaoRegister
	
	// 튜터 회원가입
	@PostMapping(value = "/KakaoRegisterTu")
	@ResponseBody
	public Map<String, Object> KakaoRegisterTu(@RequestParam HashMap<String, Object> paramMap,
			List<MultipartFile> file, HttpSession session) throws ServiceException {
		log.trace("\t + paramMap : {}, file : {}", paramMap,file);

		HashMap<String, Object> resultMap = new HashMap<>();

		//추가 회원가입 진행
		Integer registerCheck = this.userservice.kakaoRegisterTu(paramMap);
		
		// file이 null이 들어오네....수정중..
		//튜터 첨부파일 저장
		String user_email = (String) paramMap.get("email");
		List<FileDTO> fileDTO = this.fileupload.uploadFile(file,user_email);
		log.info("\t + fileDTO : {}", fileDTO);
		for(FileDTO filedto : fileDTO) {
			int fileResult = this.fileservice.createFiles(filedto);
			log.info("\t + File Mapper insert : {}", fileResult);
		}//for 

		//회원가입 성공시 로그인
		if (registerCheck != null && registerCheck > 0) {
			UserVO vo = this.userservice.kakaoLogin(paramMap);
			session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
			resultMap.put("JavaData", "YES");
		} else {
			resultMap.put("JavaData", "NO");
		}
		
		return resultMap;
	}// userKakaoRegister

	// ------------------------ 카카오로그인 : REST API Key사용
	// (순서) 1. 인가코드 받기
	// 2. 인가코드로 토큰얻기
	// 3. 토큰으로 회원정보 조회
	// 4. 정보 받아서 DB처리 or 로그인처리

	@ResponseBody
	@GetMapping("/kakao")
	public void kakaoCallback(@RequestParam String code) throws Exception {

		// 1. 카카오 서버로부터 받는 인가 코드
		log.info(" 1. kakao code : {}", code);

		// 2. 토큰얻어옴
		String token = getAccessToken(code);

		// 3. 토큰으로 사용자정보 얻음
		HashMap<String, Object> userInfo = createKakaoUser(token);
		log.info("\t + userInfo: {}", userInfo);

	}// kakaoCallback

	// -- 2. 인가코드로 토큰 얻기
	public String getAccessToken(String authorize_code) {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// URL연결은 입출력에 사용 될 수 있고,
			// POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=f242881542c06c438c6f81728a868bf9"); // 본인이 발급받은 key
			sb.append("&redirect_uri=http://localhost:8080/test/kakao"); // 본인이 설정해 놓은 경로
			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			log.info("\t + GetToken 1. responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			log.info("\t+ GetToken 2. response body : " + result);

			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
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
		} // try-catch

		return access_Token;
	}// getAccessToken

	// -- 3. access_token을 이용하여 사용자 정보 조회
	public HashMap<String, Object> createKakaoUser(String token) {
		// *** 정보 map 으로 저장 ***
		HashMap<String, Object> userInfo = new HashMap<String, Object>();

		String reqURL = "https://kapi.kakao.com/v2/user/me";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + token); // 전송할 header 작성, access_token전송

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			log.info("\t+ accessToken 1. responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			log.info("\t+ accessToken 2. response body : " + result);

			// Gson 라이브러리로 JSON파싱
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			String id = element.getAsJsonObject().get("id").getAsString();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			boolean hasEmail = kakao_account.get("has_email").getAsBoolean();

			// JSON -> 데이터 넣기
			String email = "";
			String ninkName = "";
			String birth = "";
			String kakaoGender = ""; // 카카오성별 -> 변경
			String gender = "";

			if (hasEmail) {
				email = kakao_account.getAsJsonObject().get("email").getAsString();
				ninkName = kakao_account.get("profile").getAsJsonObject().get("nickname").getAsString();
				birth = kakao_account.get("birthday").getAsString();
				kakaoGender = kakao_account.get("gender").getAsString();
				gender = (kakaoGender.equals("female")) ? "여자" : "남자";
			}
			log.info("\t ***** kakao / id : " + id);
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
		} // try-catch
		return userInfo;
	}// createKakaoUser

	// -----------------------------------------------------

}// end class
