package com.snow.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
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
	private JdbcTemplate template;
	@Resource
	private UserService userService;
	
	/*��¼ҳ��*/
    @RequestMapping("/hello")
    public String hello(HttpSession session){ 
    	/*�����ȡһ����Ƭ*/
    	int count = template.queryForObject("SELECT COUNT(*) from imgs",Integer.class);
    	Random random = new Random();
    	int imgid = random.nextInt(count) + 1;
    	Object user = template.queryForObject("SELECT uname FROM user WHERE uid in (SELECT uid from album WHERE aid in(SELECT aid FROM imgs WHERE tid=?))", new Object[]{imgid},Object.class);
    	System.out.println(user);
    	session.setAttribute("img",userService.findImgOne(imgid));
    	session.setAttribute("user", user);
    	/*
    	model.addAttribute("img",userService.findImgOne(imgid));
    	model.addAttribute("user", user);*/
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
		if(session.getAttribute("username") != null){
			modelAndView.addObject("user",user);
			modelAndView.setViewName("/index");
			List<Image> limg = userService.findShareImg();
			modelAndView.addObject("imgs", limg);
			return modelAndView;
		}
		//�ж��û��Ƿ����
		if (user != null) {
			if(user.getPassword().equals(" ")== false){
				modelAndView.addObject("user",user);
				session.setAttribute("id", user.getId());
				session.setAttribute("username", user.getUsername());
				session.setAttribute("password", user.getPassword());
				session.setAttribute("descriptions", user.getDescriptions());
				session.setAttribute("email",user.getEmail());
				List<Image> limg = userService.findShareImg();
				modelAndView.addObject("imgs", limg);
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
    		return "redirect:/amessage";
    	}else{
    		return "redirect:/amessage";
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
    
    /*��ȡ�������*/
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
    /*�½����*/
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
    		return "redirect:/albums";
    	}else{
    		return "redirect:/albums";
    	}
    }
    /*�ϴ���Ƭ*/
    @RequestMapping("/upload.do")
    public ModelAndView upload(@RequestParam(value="file",required=false)MultipartFile[] files,
			HttpServletRequest request,Image image){
		ModelAndView mav = new ModelAndView();
		//��ȡ·�������ϴ�ͼƬ·��
		String path=request.getSession().getServletContext().getRealPath("upload");
		String albumname = request.getParameter("albumname");
		path = path.replaceAll("\\\\", "/");
		for(MultipartFile file:files){
			//��ȡ�ļ���
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			//����Ŀ���ļ�����ָ��·��
			File targetFile = new File(path,fileName);
			if(file.isEmpty()){
				request.setAttribute("error","�ļ�δ�ϴ�!");
			}
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			try{
				//���ϴ��ļ�д����������ָ�����ļ�
				file.transferTo(targetFile);
				String url = "upload/"+fileName;
				image.setUrl(url);
				image.setCreatedate(new Date());
				image.setName(request.getParameter("name"));
				System.out.println(request.getParameter("albumname"));
				System.out.println(userService.getAid(albumname));
				image.setAid(userService.getAid(albumname));
				//image.setAid(template.queryForObject("SELECT aid FROM album where aname=?", new Object[]{request.getParameter("albumname")}, Integer.class));
				//image.setAid(template.query("SELECT aid FROM album where aname=?",new Object[]{request.getParameter("albumname")}));
				//����dao�㷽����ִ��ͼƬ�ϴ�����
				userService.uploadImg(image);	
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		mav.setViewName("redirect:/findPage.do");//�����ض���ķ�ʽ			
		return mav;
	}
    
    /*�����ϸҳ*/
    @RequestMapping(value=("/findPage.do"),method=RequestMethod.GET)
	public ModelAndView fingPage(HttpServletRequest request,Image image,Page page,String id){
    	//System.out.println(id);
    	int aid = Integer.parseInt(id);
		ModelAndView mav = new ModelAndView();
		List<Image> list = userService.findImg(page,aid);//��ȡ������Ƭ
		int totalpage = 0;//�ܹ���ҳ��
		int rows = userService.countImage(aid);//�ܼ�¼(��Ƭ)��
		//�ж��ܼ�¼����ÿҳ��ʾ����ȡ��   �㷨
	    if(rows%page.getPageSize() == 0){
		   totalpage=rows/page.getPageSize();//��ֵ����ҳ��
	    }else{
		   totalpage=rows/page.getPageSize()+1;
	    }
		mav.addObject("totalpage", totalpage);
		mav.addObject("currentpage", page.getPage());//��ǰҳ��
		mav.addObject("list",list);
		mav.setViewName("/result");
		return mav;
	}
    
    /*��Ƭ����ҳ*/
    @RequestMapping(value=("/img"),method=RequestMethod.GET)
    public String imgs(String id,Model model){
    	int iid = Integer.parseInt(id);
    	Image img = userService.findImgOne(iid);
    	model.addAttribute("img", img);
    	return "img";
    }
    /*����*/
    @RequestMapping("friends")
    public String friends(HttpSession session,Model model){
    	String name = (String) session.getAttribute("username");
		if(userService.getAddf(name).size()>0){
			model.addAttribute("addfs",userService.getAddf(name));
		}
		if(userService.getFriends(name).size()>0){
			model.addAttribute("friends",userService.getFriends(name));
		}
    	return "friends";
    }
    /*��Ӻ���*/
    @RequestMapping("addFriend")
    public void addFriend(String who,String name){
    	System.out.println(who+"�������"+name+"Ϊ����");
    	int row = template.update("INSERT INTO addf VALUES(?,?)",new Object[]{who,name});
    }
    
    /*�����������*/
    @RequestMapping("aFriend")
    public ModelAndView aFriend(String user,String friend,String message){
    	System.out.println(user+message+friend);
    	if(message.equals("ˬ��Ľ���")){
    		userService.acceptFriend(user,friend);
    	}
    	userService.deleteAdd(user,friend);
		ModelAndView yesorno = new ModelAndView();
		yesorno.setViewName("friends");
    	return yesorno;
    }
    
    /*share*/
    @RequestMapping("share")
    public String share(String id,String desc){
    	int tid = Integer.parseInt(id);
    	System.out.println(id+""+desc);
    	userService.shareImg(tid, desc);
    	return "redirect:/hello";
    }
    
    
    /*���ڿ�����*/
    @RequestMapping("about")
    public String about(){
    	return "about";
    }
    
    
}
