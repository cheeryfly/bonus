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
					StringBuffer data = new StringBuffer();
					data.append("\"showname\":"+JSON.toJSONString(user.getNickname())+","+"\"role\":"+JSON.toJSONString(user.getRole()));
					repStr = ActionUtil.getResponse("200", "查询信息成功", data.toString());	
				}
			} catch (Exception e) {
				e.printStackTrace();
			repStr=ActionUtil.getResponse("500", "获取信息失败！");
		}
		ActionUtil.sendJSONToClient(repStr, response);
	}
}
