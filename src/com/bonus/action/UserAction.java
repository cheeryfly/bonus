package com.bonus.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.bonus.bean.User;
import com.bonus.action.ActionUtil;
import com.bonus.service.UserService;

@Controller
public class UserAction {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/login/validate")
	public void validate(HttpServletRequest request,HttpServletResponse response, String json){
       Enumeration<String> headers=request.getHeaderNames();
		String repStr="";
		while(headers.hasMoreElements()){
			String head=headers.nextElement();
		}
		try {
			if(json==null || json.equals("")) {
				repStr = ActionUtil.getResponse("500", "网络传输错误");
			}
			
			Map map = (Map) JSON.parse(json);
			if(map==null) {
				map = new HashMap();
			}
			
			String name = map.get("name")==null?null:(String)map.get("name");
			String pwd = map.get("pwd")==null?null:(String)map.get("pwd");
		    User user = userService.loginValidate(name, pwd);
		    
		    if(user != null){
		    	request.getSession().setAttribute("user",user);
		    	repStr=ActionUtil.getResponse("200", "登陆成功");
		    }
		    else{
		    	repStr=ActionUtil.getResponse("500", "登陆失败");
		    }
			} catch (Exception e) {
				e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "登陆失败");
		}
		ActionUtil.sendJSONToClient(repStr, response);
	}
	
	/**
	 * 获取登陆用户信息
	 * @param request
	 * @param response
	 * @param json
	 */
	@RequestMapping("/user/getinfo")
	public void getUserInfo(HttpServletRequest request,HttpServletResponse response, String json){
       Enumeration<String> headers=request.getHeaderNames();
		String repStr="";
		while(headers.hasMoreElements()){
			String head=headers.nextElement();
		}
		try {
				User user = (User)request.getSession().getAttribute("user");
				if(user == null){
					repStr=ActionUtil.getResponse("500", "没有有效用户");
				}
				else{
					int event = userService.getEvent(user);
					StringBuffer data = new StringBuffer();
					data.append("\"username\":"+JSON.toJSONString(user.getUsername())+","+"\"showname\":"+JSON.toJSONString(user.getNickname())+","+"\"role\":"+JSON.toJSONString(user.getRole())+","+"\"event\":"+event);
					repStr = ActionUtil.getResponse("200", "查询信息成功", data.toString());	
				}
			} catch (Exception e) {
				e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "获取信息失败！");
		}
		ActionUtil.sendJSONToClient(repStr, response);
	}
	
	@RequestMapping("/login/change")
	public void changePassword(HttpServletRequest request,HttpServletResponse response, String json){
       Enumeration<String> headers=request.getHeaderNames();
		String repStr="";
		while(headers.hasMoreElements()){
			String head=headers.nextElement();
		}
		try {
			if(json==null || json.equals("")) {
				repStr = ActionUtil.getResponse("500", "网络传输错误");
			}
			
			Map map = (Map) JSON.parse(json);
			if(map==null) {
				map = new HashMap();
			}
			
			String username = map.get("username")==null?null:(String)map.get("username");
			String password = map.get("password")==null?null:(String)map.get("password");
			String newpassword = map.get("newpassword")==null?null:(String)map.get("newpassword");
		    User user = userService.loginValidate(username, password);
		    
		    if(user != null){
		    	user.setPassword(newpassword);
		    	userService.changePwd(user);
		    	repStr=ActionUtil.getResponse("200", "修改密码成功");
		    }
		    else{
		    	repStr=ActionUtil.getResponse("500", "用户名密码错误");
		    }
			} catch (Exception e) {
				e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "修改失败");
		}
		ActionUtil.sendJSONToClient(repStr, response);
	}
}
