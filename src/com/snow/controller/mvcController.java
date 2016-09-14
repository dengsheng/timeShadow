package com.snow.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.snow.model.Album;
import com.snow.model.User;
import com.snow.service.UserService;

@Controller
public class mvcController {

	@Resource
	private UserService userService;
	
	/*��¼ҳ��*/
    @RequestMapping("/hello")
    public String hello(){        
        return "login";
    }
    
    /*��¼��֤*/
    @RequestMapping("/index")
	public ModelAndView login(HttpSession session,HttpServletRequest request){
		System.out.println("login.do");
		ModelAndView modelAndView = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);
		User user = userService.login(u);
		//�ж��û��Ƿ����
		if (user != null) {
			if(user.getPassword().equals(" ")== false){
				modelAndView.addObject("user",user);
				session.setAttribute("id", user.getId());
				session.setAttribute("username", user.getUsername());
				session.setAttribute("password", user.getPassword());
				session.setAttribute("descriptions", user.getDescriptions());
				session.setAttribute("email",user.getEmail());
				modelAndView.setViewName("/index");//������ڲ���������ȷ�Ļ������ص�index.jspҳ��
				return modelAndView;
			}else{
				modelAndView.addObject("errorpwd",true);
				modelAndView.addObject("message","�������,����������");
				modelAndView.setViewName("/login");
				return modelAndView;
			}
		}else{
				modelAndView.addObject("erroruser",true);
				modelAndView.addObject("message","Oh god,�û������ڣ��Ͽ�ȥ����һ����");
				modelAndView.setViewName("/login");
				return modelAndView;
		}	
	}
    
    /*������Ϣҳ��*/
    @RequestMapping("message")
    public String messgae(HttpSession session){
    	if(session.getAttribute("username") != null){
    		return "message";
    	}else{
    		return "login";
    	}
    	
    }
    
    /*�޸ĸ�����Ϣ*/
    @RequestMapping("amessage")
    public String amessage(HttpSession session,String username,String descriptions){
    	String ousername = (String)session.getAttribute("username");
    	if(userService.amessage(ousername, username, descriptions)){
    		return "message";
    	}else{
    		return "message";
    	}
    	
    }
    
    /*ע���û�*/
    @RequestMapping("register")
    public String register(HttpServletRequest request){
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String email = request.getParameter("email");
    	User newuser = new User();
    	newuser.setUsername(username);
    	newuser.setPassword(password);
    	newuser.setEmail(email);
    	if(userService.register(newuser)){
    		return "login";
    	}else{
    		return "login";
    	}
    }
    
    /*���*/
    @RequestMapping("albums")
    public String albums(HttpSession session,Model model){
    	int uid = 0;
    	try{
    		uid = Integer.parseInt(session.getAttribute("id").toString());
    	}catch(NumberFormatException e){
    		e.printStackTrace();
    	}
		List<Album> albums = userService.getAllProblems(uid);
    	model.addAttribute("albums", albums);
    	return "albums";
    }
    
    /*���ڿ�����*/
    @RequestMapping("about")
    public String about(){
    	return "about";
    }
    
    
}
