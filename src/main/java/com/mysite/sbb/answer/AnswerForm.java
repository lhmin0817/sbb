package com.mysite.sbb.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnswerForm {
	
	@NotEmpty(message = "내용이 비어있습니다. 반드시 입력해주세요.")
	private String content;
	
}
