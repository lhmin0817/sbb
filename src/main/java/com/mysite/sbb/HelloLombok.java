package com.mysite.sbb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter				// 필드에 대한 getter를 자동으로 생성
@Setter				// 필드에 대한 setter를 자동으로 생성
@NoArgsConstructor	// 기본 생성자 자동으로 생성	HelloLombok(){}
@ToString			// 객체 자체를 출력 시 자동으로 필드의 내용을 출력
public class HelloLombok {
	private String hello;	//필드 : private 
	private int lombok;		//필드 : private 

	public static void main(String[] args) {
		//위에 생성된 클래스의 내용이 롬북이 잘 적용되었는 지 확인
		//객체 생성 <== 객체 내부의 필드와 메소드를 사용함
		HelloLombok lombok = new HelloLombok();
		
		//setter를 사용해서 값 입력
		lombok.setHello("안녕하세요");
		lombok.setLombok(40);
		
		//getter를 상ㅇ해서 값 출력
		System.out.println(lombok.getHello());
		System.out.println(lombok.getLombok());
		
		//toString() 메소드 호출
		System.out.println(lombok);
	}
}
