package com.snow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.snow.entity.User;

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
    @RequestMapping("/login.do")
	public ModelAndView login(HttpServletRequest request){
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
				modelAndView.addObject("username",username);
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
    
    /*ע���û�*/
    @RequestMapping("register.do")
    public String register(HttpServletRequest request){
    	ModelAndView modelAndView = new ModelAndView();
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String email = request.getParameter("email");
    	User newuser = new User();
    	newuser.setUsername(username);
    	newuser.setPassword(password);
    	newuser.setEmail(email);
    	if(userService.register(newuser)){
    		return "index";
    	}else{
    		return "login";
    	}
    }
    
}
