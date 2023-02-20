package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	
	@GetMapping("/")	//http://localhost:9898
	public String index() {
		
		return "redirect:/question/list"; //http://localhost:9898/question/list
	}
	
}
