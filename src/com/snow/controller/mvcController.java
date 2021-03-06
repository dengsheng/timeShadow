
	package com.snow.controller;

	import com.geetest.sdk.java.GeetestLib;
	import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
	import java.util.Date;
import java.util.HashMap;
	import java.util.List;
import java.util.Map;
	import java.util.Random;

	import javax.annotation.Resource;
	import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
	import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
	import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestMethod;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
	//import org.springframework.web.multipart.MultipartHttpServletRequest;
	import org.springframework.web.multipart.MultipartFile;
	import org.springframework.web.servlet.ModelAndView;


	import com.snow.model.Page;

	import com.snow.model.Album;
import com.snow.model.Comment;
	import com.snow.model.User;
	import com.snow.model.Image;
import com.snow.model.UserMapper;
import com.snow.service.UserService;

	@Controller
	public class mvcController {
		@Resource
		private JdbcTemplate template;
		@Resource
		private UserService userService;
		
		/*极验验证*/
		@RequestMapping(value = ("/geetest"),method=RequestMethod.GET)
		public void geetest(HttpServletRequest req,HttpServletResponse res) throws IOException{
			GeetestLib gtSdk = new GeetestLib("f53044a7a6d83db812a727fbb9f7ca4e","242a45d5223baeab79e034013b131dbd");
			
			String resStr = "{}";
			
			int gtServerStatus = gtSdk.preProcess();
			
			req.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
		    resStr = gtSdk.getResponseStr();
		    PrintWriter out = res.getWriter();
		    out.println(resStr);
		}
		/*二次验证*/
		@RequestMapping(value = ("/geetest"),method=RequestMethod.POST)
		public void geetestp(HttpServletRequest req,HttpServletResponse res){
			GeetestLib gtSdk = new GeetestLib("f53044a7a6d83db812a727fbb9f7ca4e","242a45d5223baeab79e034013b131dbd");
			
			String challenge = req.getParameter(GeetestLib.fn_geetest_challenge);
			String validate = req.getParameter(GeetestLib.fn_geetest_validate);
			String seccode = req.getParameter(GeetestLib.fn_geetest_seccode);
			
			//从session中获取gt-server状态
			int gt_server_status_code = (Integer) req.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);

            int gtResult;

            if (gt_server_status_code == 1) {
                      //gt-server正常，向gt-server进行二次验证
                      gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode);
                      System.out.println(gtResult);
             } else {
                      // gt-server非正常情况下，进行failback模式验证
                      gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
             }
            
            
            System.out.println("二次验证");
            /*
             if (gtResult.equals(GeetestLib.)) {
                      PrintWriter out = res.getWriter();
                      out.println(GeetestLib.success_res + ":" + gtSdk.getVersionInfo());
             } else{
                      PrintWriter out = res.getWriter();
                      out.println(GeetestLib.fail_res + ":" + gtSdk.getVersionInfo());
             }*/

		}
		
		/*登录页面*/
	    @RequestMapping("/hello")
	    public String hello(HttpSession session){ 
	    	/*随机获取一张照片*/
	    	
	    	int count = template.queryForObject("SELECT COUNT(*) from imgs",Integer.class);
	    	if(count >= 1){
		    	Random random = new Random();
		    	int imgid = random.nextInt(count) + 1;
		    	Object user = template.queryForObject("SELECT uname FROM user WHERE uid in (SELECT uid from album WHERE aid in(SELECT aid FROM imgs WHERE tid=?))", new Object[]{imgid},Object.class);
		    	System.out.println(user);
		    	session.setAttribute("img",userService.findImgOne(imgid));
		    	session.setAttribute("user", user);
		    	return "login";
	    	}
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
			if(session.getAttribute("username") != null){
				modelAndView.addObject("user",user);
				modelAndView.setViewName("/index");
				List<Image> limg = userService.findShareImg();
				List<Image> uimg = userService.findUserImg(Integer.parseInt(session.getAttribute("id").toString()));
				modelAndView.addObject("uimgs",uimg);
				modelAndView.addObject("imgs", limg);
				return modelAndView;
			}
			//判断用户是否存在
			if (user != null) {
				if(user.getPassword().equals(" ")== false){
					modelAndView.addObject("user",user);
					session.setAttribute("id", user.getId());
					session.setAttribute("username", user.getUsername());
					session.setAttribute("password", user.getPassword());
					session.setAttribute("descriptions", user.getDescriptions());
					session.setAttribute("email",user.getEmail());
					List<Image> limg = userService.findShareImg();
					List<Image> uimg = userService.findUserImg(Integer.parseInt(session.getAttribute("id").toString()));
					modelAndView.addObject("uimgs",uimg);
					modelAndView.addObject("imgs", limg);
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
	    public String messgae(HttpSession session,Model model){
	    	if(session.getAttribute("username") != null){
	    		RowMapper<User> userMapper = new UserMapper();
	    		List<User> user =  template.query("SELECT * from user WHERE uname=?", new Object[]{session.getAttribute("username")}, userMapper);
	    		model.addAttribute("user", user.get(0));
	    		return "message";
	    	}else{
	    		return "login";
	    	}
	    	
	    }
	    
	    /*修改个人信息*/
	    @RequestMapping(value="amessage",method=RequestMethod.POST)
	    public @ResponseBody String amessage(HttpSession session,String username,String descriptions,Model model){
	    	String ousername = (String)session.getAttribute("username");
	    	if(userService.amessage(ousername, username, descriptions)){
	    		Map<String,Object> map = new HashMap<String,Object>();
	    		map.put("name", username);
	    		map.put("desc", descriptions);
	    		model.addAttribute("user", map);
	    		return map.toString();
	    	}else{
	    		return null;
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
	    		return "redirect:/albums";
	    	}else{
	    		return "redirect:/albums";
	    	}
	    }
	    /*修改相册*/
	    @RequestMapping("updatealbum")
	    public String updatealbum(HttpServletRequest request){
	    	String oldname = request.getParameter("oldname");
	    	String albumname = request.getParameter("albumname");
	    	String desc = request.getParameter("description");
	    	Date date = new Date();
	    	userService.updateAlbum(oldname, albumname, desc, date);
	    	return "redirect:/albums";
	    }
	    /*删除相册*/
	    @RequestMapping("deleteAlbum")
	    public String deleteAlbum(String name){
	    	userService.deleteAlbum(name);
	    	return "redirect:/albums";
	    }
	    /*上传照片*/
	    @RequestMapping("/upload.do")
	    public ModelAndView upload(@RequestParam(value="file",required=false)MultipartFile[] files,
				HttpServletRequest request,Image image){
			ModelAndView mav = new ModelAndView();
			//获取路径本地上传图片路径
			String path=request.getSession().getServletContext().getRealPath("upload");
			String albumname = request.getParameter("albumname");
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
					image.setCreatedate(new Date());
					image.setName(request.getParameter("name"));
					System.out.println(request.getParameter("albumname"));
					System.out.println(userService.getAid(albumname));
					image.setAid(userService.getAid(albumname));
					String name = template.queryForObject("SELECT uname from user WHERE uid in(SELECT uid from album WHERE aid =?)", new Object[]{userService.getAid(albumname)},String.class);
					image.setAuthor(name);
					//image.setAid(template.queryForObject("SELECT aid FROM album where aname=?", new Object[]{request.getParameter("albumname")}, Integer.class));
					//image.setAid(template.query("SELECT aid FROM album where aname=?",new Object[]{request.getParameter("albumname")}));
					//调用dao层方法，执行图片上传操作
					userService.uploadImg(image);	
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			mav.setViewName("redirect:/albums");//采用重定向的方式			
			return mav;
		}
	    
	    /*相册详细页*/
	    @RequestMapping(value=("/findPage.do"),method=RequestMethod.GET)
		public ModelAndView fingPage(HttpServletRequest request,Image image,Page page,String id){
	    	//System.out.println(id);
	    	int aid = Integer.parseInt(id);
			ModelAndView mav = new ModelAndView();
			List<Image> list = userService.findImg(page,aid);//获取所有照片
			int totalpage = 0;//总共的页数
			int rows = userService.countImage(aid);//总记录(照片)数
			//判断总记录数与每页显示的数取余   算法
		    if(rows%page.getPageSize() == 0){
			   totalpage=rows/page.getPageSize();//赋值给总页数
		    }else{
			   totalpage=rows/page.getPageSize()+1;
		    }
			mav.addObject("totalpage", totalpage);
			mav.addObject("currentpage", page.getPage());//当前页数
			mav.addObject("list",list);
			mav.setViewName("/result");
			return mav;
		}
	    
	    /*照片详情页*/
	    @RequestMapping(value=("/img"),method=RequestMethod.GET)
	    public String imgs(String id,Model model){
	    	int iid = Integer.parseInt(id);
	    	Image img = userService.findImgOne(iid);
	    	List<Comment> comments = userService.getComment(iid);
	    	template.update("UPDATE imgs SET tpv = tpv+1 WHERE tid=?", new Object[]{iid});
	    	model.addAttribute("comments",comments);
	    	model.addAttribute("img", img);
	    	model.addAttribute("iid",iid);
	    	return "img";
	    }
	    /*好友*/
	    @RequestMapping("friends")
	    public String friends(HttpSession session,Model model){
	    	String name = (String) session.getAttribute("username");
	    	model.addAttribute("name",name);
			if(userService.getAddf(name).size()>0){
				model.addAttribute("addfs",userService.getAddf(name));
			}
			if(userService.getFriends(name).size()>0){
				model.addAttribute("friends",userService.getFriends(name));
			}
	    	return "friends";
	    }
	    /*添加好友*/
	    @RequestMapping("addFriend")
	    public void addFriend(String who,String name){
	    	System.out.println(who+"请求添加"+name+"为好友");
	    	int row = template.update("INSERT INTO addf VALUES(?,?)",new Object[]{who,name});
	    }
	    
	    /*评论*/
	    @RequestMapping("comment")
	    public String Comment(String para,String iid,HttpSession session){
	    	Comment c = new Comment();
	    	c.setPara(para);
	    	c.setTid(Integer.parseInt(iid));
	    	c.setTime(new Date());
	    	c.setName((String)session.getAttribute("username"));
	    	userService.addComment(c);
	    	System.out.println(para+iid);
	    	return "redirect:/img?id=1";
	    }
	    /*处理好友请求*/
	    @RequestMapping("aFriend")
	    public String aFriend(String user,String friend,String message){
	    	System.out.println(user+message+friend);
	    	if(message.equals("爽快的接受")){
	    		userService.acceptFriend(user,friend);
	    	}
	    	userService.deleteAdd(user,friend);
			return "redirect:/friends";
	    }
	    
	    /*share
	    @RequestMapping("share")
	    public String share(String id,String desc){
	    	int tid = Integer.parseInt(id);
	    	System.out.println(id+""+desc);
	    	userService.shareImg(tid, desc);
	    	return "redirect:/hello";
	    }*/
	    
	    
	    /*关于开发者*/
	    @RequestMapping("about")
	    public String about(){
	    	return "about";
	    }
	    
	    
	}


