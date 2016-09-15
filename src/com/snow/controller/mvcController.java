package com.snow.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.snow.model.Page;

import com.snow.model.Album;
import com.snow.model.User;
import com.snow.model.Image;
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
				session.setAttribute("id", user.getId());
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
    public String amessage(HttpSession session,String username,String descriptions){
    	String ousername = (String)session.getAttribute("username");
    	if(userService.amessage(ousername, username, descriptions)){
    		return "message";
    	}else{
    		return "message";
    	}
    	
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
    
    /*获取所有相册*/
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
    /*新建相册*/
    @RequestMapping("album+")
    public String album(HttpSession session,HttpServletRequest request){
    	String name = request.getParameter("albumname");
    	String description = request.getParameter("description");
    	Date date = new Date();
    	Album album = new Album();
    	album.setAlbumname(name);
    	album.setDescription(description);
    	album.setCreatedate(date);
    	album.setPv(0);
    	album.setUserid(Integer.parseInt(session.getAttribute("id").toString()));
    	if(userService.createAlbum(album)){
    		return "albums";
    	}else{
    		return "albums";
    	}
    }
    /*上传照片*/
    @RequestMapping("upload")
    public ModelAndView uploade(
			@RequestParam(value="file",required=false)MultipartFile[] files,
			HttpServletRequest request,Image image){
		ModelAndView mav = new ModelAndView();
		//获取路径本地上传图片路径
		String path=request.getSession().getServletContext().getRealPath("upload");
		path = path.replaceAll("\\\\", "/");
		for(MultipartFile file:files){
			//获取文件名
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			//创建目标文件，并指定路径
			File targetFile = new File(path,fileName);
			if(file.isEmpty()){
				request.setAttribute("error","文件未上传!");
			}
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			try{
				//将上传文件写到服务器上指定的文件
				file.transferTo(targetFile);
				String url = "upload/"+fileName;
				image.setUrl(url);
				//调用dao层方法，执行图片上传操作
				userService.uploadImg(image);	
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		mav.setViewName("redirect:/findPage.do");//采用重定向的方式			
		return mav;
	}
    
    /*相册详细页*/
    @RequestMapping(value=("/findPage.do"),method=RequestMethod.GET)
	public ModelAndView fingPage(HttpServletRequest request,Image image,Page page){
		ModelAndView mav = new ModelAndView();
		List<Image> list = imagedao.findImg(page);
		int totalpage = 0;//总共的页数
		int rows = imageservice.countImage();//总记录数
		//判断总记录数与每页显示的数取余   算法
	    if(rows%page.getPageSize() == 0){
		   totalpage=rows/page.getPageSize();//赋值给总页数
	    }else{
		   totalpage=rows/page.getPageSize()+1;
	    }
		mav.addObject("totalpage", totalpage);
		mav.addObject("courentpage", page.getPage());//当前页数
		mav.addObject("list",list);
		mav.setViewName("/result");
		return mav;
	}
    
    /*关于开发者*/
    @RequestMapping("about")
    public String about(){
    	return "about";
    }
    
    
}
