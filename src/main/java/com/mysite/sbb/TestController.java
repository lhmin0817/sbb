package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@GetMapping("/testweb")
	@ResponseBody
	public String test() {
		
		return "Test 코드 블락입니다. - My Web Site - 추가된 내용입니다. - 추가 항목입니다.";
	}
}
