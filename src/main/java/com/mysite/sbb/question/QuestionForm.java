package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class QuestionForm {
	// subject 폼의 값과 content 폼의 값의 유효성 체크
	
	@NotEmpty(message="제목은 필수 사항입니다.")	// subject의 값이 비어있을 때 작동
	@Size(max=200)							// suvject의 값을 최대 200까지 
	private String subject;
	
	@NotEmpty(message="내용은 필수 사함입니다.")
	private String content;
	
}
