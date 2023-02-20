package com.mysite;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 사용자가 생성한 예외 : 실행 시 처리
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "요청한 레코드를 찾지 못하였습니다.")
public class DataNotFoundException extends RuntimeException{
	private static final long seriaVersionUID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
