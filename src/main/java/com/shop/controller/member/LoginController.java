package com.shop.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.Session;
import com.shop.service.member.MailService;
import com.shop.service.member.MailServiceImpl;
import com.shop.vo.Prod_Tbl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/carshop/*")
@Slf4j
public class LoginController {
	

	@Autowired
	MailServiceImpl service = new MailServiceImpl();
	
	@Autowired
	private MailService mailService;
	private JavaMailSenderImpl mailSender;
	
	@GetMapping("/index") 
	public String index() {
		return "carshop/index";
	}

	@GetMapping("/login")
	public String login() {
		return "carshop/login";
	}
	


	@PostMapping("/login") 
	public String login_success(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
		System.out.println("post2들어옴");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("password");
		
		System.out.println("id : " + id + " pw : " + pw);

		//service.getAllUser();                            //db연결시 사용하기

		

		if(id.equals("admin") && pw.equals("admin")) {      //db없이 test해보기 위함

			session.setAttribute("id" , id);
			session.setAttribute("pw", pw);

			String memberId = (String)session.getAttribute("id");
			boolean login = memberId == null ? false : true;
			
			System.out.println("memberId는 : " + memberId);
			System.out.println("login세션결과는 : " + login);

			return "/carshop/indexlogin";  						 
			
		} else {
			System.out.println("로그인정보 불일치");
			return "/carshop/loginerror";
		}    
	}
//	@GetMapping("/logout")
//	public String login2() {
//		System.out.println("logout들어옴");
//		
//		return "carshop/login";
//	}
//	
//
//	@PostMapping("/logout") 
//	public String login_success3(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		
//		System.out.println("logout들어옴");
//		
//		if() {    
//			
//
//			return "/carshop/indexlogin";  						 
//			
//		} else {
//			
//			return "/carshop/loginerror";
//		}    
//	}
	
	@GetMapping("/all")
	public void all() {
		System.out.println("누구나 접근가능");
	}
	
	@GetMapping("/member")
	public void member() {
		System.out.println("회원만 접근가능");
	}
	
	@GetMapping("/admin")
	public void admin() {
		System.out.println("관리자만 접근가능");
	}

	@GetMapping("/indexlogin")
	public String indexlogin() {
		return "carshop/indexlogin";
	}
	
	@PostMapping("/indexlogin")
	public String indexlogin2() {
		return "carshop/indexlogin";
	}
	@GetMapping("/loginerror")
	public String loginerror() {
		return "carshop/loginerror";
	}
	
	@PostMapping("/loginerror")
	public String loginerror2() {
		return "carshop/indexlogin";
	}
	
	@GetMapping("/pwsearch")
	public String pwsearch() {
		return "/carshop/pwsearch";
	}
	
	@PostMapping("/pwsearch")
	public String pwsearch2() {
		return "/carshop/index";
	}
	
	@GetMapping("/sendmail")
	public String sendMail1() {
		return "carshop/pwsearch";
	}
	
	@PostMapping("/sendmail")
	public String sendMail(HttpServletRequest request, HttpServletResponse response) {
		
		String email = request.getParameter("email");
		service.sendMail();						        //MailServiceImpl
		
		return "carshop/pwsearch";
	}
	
	@GetMapping("/pwsetting")
	public String pwsetting() {
		return "/carshop/pwsetting";
	}
	
	@PostMapping("/pwsetting")
	public String pwsetting2() {
		return "/carshop/login";
	}

	
}
