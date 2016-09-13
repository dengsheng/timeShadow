package com.snow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.snow.model.User;
import com.snow.service.UserService;

@Controller
public class mvcController {

	@Resource
	private UserService userService;
	
	/*登录页面*/
    @RequestMapping("/hello")
    public String hello(){        
        return "login";
    }
    
    /*登录验证*/
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
		//判断用户是否存在
		if (user != null) {
			if(user.getPassword().equals(" ")== false){
				modelAndView.addObject("user",user);
				session.setAttribute("username", user.getUsername());
				session.setAttribute("password", user.getPassword());
				session.setAttribute("descriptions", user.getDescriptions());
				session.setAttribute("email",user.getEmail());
				modelAndView.setViewName("/index");//如果存在并且密码正确的话，返回到index.jsp页面
				return modelAndView;
			}else{
				modelAndView.addObject("errorpwd",true);
				modelAndView.addObject("message","密码错误,请重新输入");
				modelAndView.setViewName("/login");
				return modelAndView;
			}
		}else{
				modelAndView.addObject("erroruser",true);
				modelAndView.addObject("message","Oh god,用户不存在，赶快去创建一个吧");
				modelAndView.setViewName("/login");
				return modelAndView;
		}	
	}
    
    /*个人信息页面*/
    @RequestMapping("message")
    public String messgae(HttpSession session){
    	if(session.getAttribute("username") != null){
    		return "message";
    	}else{
    		return "login";
    	}
    	
    }
    
    /*修改个人信息*/
    @RequestMapping("amessage")
    public String amessage(String username,String descriptions){
    	return "message";
    }
    
    /*注册用户*/
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
    
    /*关于开发者*/
    @RequestMapping("about")
    public String about(){
    	return "about";
    }
    
    
}
