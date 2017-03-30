package com.hpe.settle.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hpe.settle.demo.exception.HpeSettleException;

@Controller
public class DemoController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "Hello World !";
	}

	@RequestMapping("/index")
	public String index(ModelMap map){
		map.addAttribute("param", "我是后台返回的参数...");
		return "index";
	}
	
	
	@RequestMapping("/hello1")
    public String hello1() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws HpeSettleException {
        throw new HpeSettleException("发生错误2");
    }
}


