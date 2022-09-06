package org.zerock.fmt.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.mail.HtmlEmail;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.UserMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Log4j2
@NoArgsConstructor

@Service
public class UserServiceImpl implements UserService{

	@Setter(onMethod_ = @Autowired)
	private UserMapper userMapper;
	
	//----------------------------------------------------------- ADMIN
	@Override
	public List<UserVO> getStudent(CriteriaAdmin cri) throws ServiceException {
		log.trace("getAllUser() invoked.");
		
		try{ return this.userMapper.selectStudent(cri);}
		catch(Exception e) { throw new ServiceException(e); }
	}//getStudent
	
	@Override
	public List<UserVO> getTutor(CriteriaAdmin cri) throws ServiceException {
		log.trace("getAllUser() invoked.");
		
		try{ return this.userMapper.selectTutor(cri);}
		catch(Exception e) { throw new ServiceException(e); }
	}//getTutor
	
	@Override
	public List<UserVO> getStopUser(CriteriaAdmin cri) throws ServiceException {
		log.trace("getAllUser() invoked.");
		
		try{ return this.userMapper.selectStopUser(cri);}
		catch(Exception e) { throw new ServiceException(e); }
	}//getStopUser
	
	@Override
	public int userCount(String userGroup, String status) throws ServiceException {
		log.trace(" userCount - 회원조회용" );
		try { return this.userMapper.userCount(userGroup, status); }
		catch(Exception e) { throw new ServiceException(e); }//try-catch
	}//userCount

	@Override
	public UserVO getUserInfo(String user_email) throws ServiceException {
		log.trace("getUserInfo() invoked.");	
		
		try { return this.userMapper.selectUser(user_email);}
		catch(Exception e) { throw new ServiceException(e);}
		
	}//getUserInfo
	
	//============================================================== 회원가입
	
	@Override
	public boolean singUpStrudent(UserDTO newStudent) throws ServiceException {
		log.trace("singUpStrudent() invoked.");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		try {
			String bcrypyPW = encoder.encode(newStudent.getUser_pw());
			newStudent.setUser_pw(bcrypyPW);
			return this.userMapper.insertStudent(newStudent)==1;		
		}catch(Exception e) { throw new ServiceException(e);}
	}//singUpStrudent

	@Override
	public boolean singUPTutor(UserDTO newTutor) throws ServiceException {
		log.trace("singUPTutor() invoked.");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		try { 
			String bcrypyPW = encoder.encode(newTutor.getUser_pw());
			newTutor.setUser_pw(bcrypyPW);
			return this.userMapper.insertTutor(newTutor)==1;
		}catch(Exception e) { throw new ServiceException(e);}
	}//singUPTutor

	//----------------------------------------------------------- 유저정보수정
	
	@Override
	public boolean updateUser(UserDTO user) throws ServiceException {
		log.trace("updateUser() invoked");
		
		try { return this.userMapper.updateUser(user) ==1 ;}
		catch(Exception e) { throw new ServiceException(e); }
		
	}//updateUser

	//----------------------------------------------------------- 승인대기튜터
	
	@Override
	public List<UserVO> getWaitTutor(CriteriaAdmin cri) throws ServiceException {
		log.trace("getWaitTutor() invoked.");
		try{
			List<UserVO> list = this.userMapper.selectWaitTutor(cri);
			return list;
		}catch(Exception e) {throw new ServiceException(e); } //try-catch
	}//getWaitTutor
	
	@Override
	public int waitTutorCount() throws ServiceException{
		log.trace("waitTutorCount() invoked.");
		try{ return this.userMapper.waitTutorCount();
		}catch(Exception e) { throw new ServiceException(e);}//try-catch
	}//waitTutorCount
	
	@Override
	public boolean tutorPass(String user_email) throws ServiceException {
		log.trace("tutorPass() invoked.");
		
		try { return this.userMapper.updateTutorPass(user_email)==1;}
		catch(Exception e) {throw new ServiceException(e);}
	}//tutorPass
	
	//----------------------------------------------------------- 회원탈퇴 
	
	@Override
	public boolean userStatus(String user_email) throws ServiceException {
		log.trace("userStatus() invoked.");
		
		try { return this.userMapper.updateUserStop(user_email)==1; }
		catch(Exception e) { throw new ServiceException(e); }
	}//userStatus

	//----------------------------------------------------------- 손들기 구매/사용
	
	@Override
	public boolean updateHandGet(Integer h_count, String user_email) throws ServiceException {
		log.trace("updateHandGet() 손들기 구매, 손들기 획득");

		try{ return this.userMapper.updateHandGet(h_count, user_email)==1;}
		catch(Exception e) { throw new ServiceException(e); }
		
	}//updateHandGet

	@Override
	public boolean updateHandUse(Integer h_count, String user_email) throws ServiceException {
		log.trace("updateHandUse() 손들기 사용(차감)");
		
		try{ return this.userMapper.updateHandGet(h_count, user_email)==1;}
		catch( Exception e) { throw new ServiceException(e); }
		
	}//updateHandUse

	//----------------------------------------------------------- 닉네임 중복
	
	@Override
	public Integer getNicCheck(String newNick) throws ServiceException {
		log.trace("selectNicCheck() 닉네임 중복 조회");
		
		try {			
			 return this.userMapper.selectNicCheck(newNick);
		} catch (Exception e) { throw new ServiceException(e); }
	}//selectNicCheck

	//----------------------------------------------------------- 로그인
	@Override
	public UserVO gettLoginUser(String user_email, String user_pw) throws ServiceException {
		log.trace("selectLogin() 로그인 유저 조회2");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		try {
			UserVO vo = this.userMapper.loginEmail(user_email);
			if(encoder.matches(user_pw, vo.getUser_pw())) {
				return vo;
			} else { return null; }
		} catch ( Exception e) {throw new ServiceException(e); }//try-catch
	}//selectLogin
	
	@Override
	public UserVO loginEmail(String user_email) throws ServiceException {
		log.trace("selectLogin() 로그인 유저 정보조회1");
		try {
			return this.userMapper.loginEmail(user_email);
		} catch ( Exception e) {throw new ServiceException(e); }//try-catch
	}//selectLogin
	
	//----------------------------------------------------------- 비밀번호찾기 +
	//----------------------------------------------------------- 임시비밀번호 메일전송 
	
	@Override
	public String findPassword(UserDTO dto) throws ServiceException {
		log.trace("findPassword() 비밀번호 찾기" );
		try { 
				
			UserVO vo = this.userMapper.loginEmail(dto.getUser_email());
			String result = null;
			if( vo==null ) {//회원정보없으면 
				result = "등록되지 않은 아이디입니다.";
				log.info("\t+ result : {}", result);
				return result;

			} else { 
				String newPw = "";
				for(int i=0; i<12; i++) {
					newPw += (char) ((Math.random() * 26) + 97);
				}//랜덤비밀번호
				
				//이메일보내기
				sendEmail(dto, newPw);
				//비밀번호 암호화 
				BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
				String bcNewPW = encoder.encode(newPw);
				
				//비밀번호 변경 
				dto.setUser_pw(bcNewPW);
				this.userMapper.updatePW(dto);
				result = "등록된 이메일로 임시 비밀번호가 전송되었습니다."; 
				log.info("\t+ result : {}", result);
				return result;
			}//if-else 
		}catch(Exception e) {throw new ServiceException(e); }
	}//findPassword

	//----------------------------------------------------------- 이메일전송
	
	public void sendEmail(UserDTO dto, String newPW) throws Exception{
		String charSet = "utf-8";
		String hostSMTP = "smtp.naver.com"; 
		String hostSMTPid = "childhopp";		//계정아이디
		String hostSMTPpwd = "--------";		//계정비밀번호

		// 보내는 사람 EMail, 제목, 내용
		String fromEmail = "childhopp@naver.com";
		String fromName = "5분과외";
		String subject = "";
		String msg = "";

		subject = "5분과외 임시 비밀번호 입니다.";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += dto.getUser_name() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
		msg += "<p>임시 비밀번호 : ";
		msg += newPW + "</p></div>";

		// 받는 사람 E-Mail 주소
		String mail = dto.getUser_email();
		try {
			HtmlEmail email = new HtmlEmail();
			email.setDebug(true);
			email.setCharset(charSet);
			email.setSSL(true);
			email.setHostName(hostSMTP);
			email.setSmtpPort(465); //네이버 이용시 587

			email.setAuthentication(hostSMTPid, hostSMTPpwd);
			email.setTLS(true);
			email.addTo(mail, charSet);
			email.setFrom(fromEmail, fromName, charSet);
			email.setSubject(subject);
			email.setHtmlMsg(msg);
			email.send();
			log.info("임시비밀번호로 메일전송");
		} catch (Exception e) {
			log.info("메일발송 실패 : " + e);
		}//try-catch 
		
	}//sendEmail

	public void certifiedPhoneNumber(String user_phone, int randomNumber) {
		String api_key = "NCSQIM5WH5W7SLEB";
	    String api_secret = "CINAHABMCOGIMQXTOO1AHXITFXD7L0WT";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", user_phone);    // 수신전화번호
	    params.put("from", "01089814304");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	    params.put("type", "SMS");
	    params.put("text", "[5분과외] 인증번호는" + "["+randomNumber+"]" + "입니다."); // 문자 내용 입력
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	        JSONObject obj = (JSONObject) coolsms.send(params);
	        System.out.println(obj.toString());
	      } catch (CoolsmsException e) {
	        System.out.println(e.getMessage());
	        System.out.println(e.getCode());
	      }
	    
	}//certifiedPhoneNumber

//------------------------------------------------------------
}//end class
