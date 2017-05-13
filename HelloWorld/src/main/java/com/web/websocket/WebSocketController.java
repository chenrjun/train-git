package com.web.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebSocketController 
{
	@RequestMapping("/")
	public String hello(){
		return "home";
	}
}
