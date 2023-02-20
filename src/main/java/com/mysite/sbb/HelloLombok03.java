package com.mysite.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor	// 기본 생성자 : 
@ToString
@AllArgsConstructor	// 객체 생성 시 모든 필드의 값을 입력 박아 필드의 초기값을 할당

public class HelloLombok03 {
	private String hello;
	private int lombok;
	
	// public HelloLombok03(){} - 기본 생성자 
	/*
	 public HelloLombok03(String hello, int lombok){ <== @AllArgsConstructor
	 	this.hello = hello;
	 	this.lombok = lombok;
	 }
	 */
	
	public static void main(String[] args) {
		
		//@AllArgsConstructor 테스트 - 객체 생성 시 필드의 값 할당
		HelloLombok03 lombok03 = new HelloLombok03("안녕",10);
		
		// 필드의 내용을 출력
		System.out.println(lombok03.getHello());
		System.out.println(lombok03.getLombok());
		
		//toString() 호출
		System.out.println(lombok03);
		
	}
}
