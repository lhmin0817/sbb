package com.mysite.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@NoArgsConstructor
@RequiredArgsConstructor	// 생성자 생성 시 필드이름 앞에 final이 들어있는 필드만 아규먼트로 생성
//@AllArgsConstructor
public class HelloLombok04 {
	private final String hello;
	private int lombok;
		
	/*
	  	@RequiredArgsConstructor
	  	public HelloLombok04(String hello){
	  		this.hello = hello;
	  	}
	 */
	
	public static void main(String[] args) {
		HelloLombok04 lombok04 = new HelloLombok04("hello");
		
		System.out.println(lombok04.getHello());
		
		System.out.println(lombok04);
	}
}
