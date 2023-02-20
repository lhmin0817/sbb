package com.mysite.sbb;

import java.sql.Date;
//import java.sql.Time;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class HelloLombok02 {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	
	public static void main(String[] args) {
		//객체 생성 후 테스트
		HelloLombok02 lombok02 = new HelloLombok02();
		
//		var theBigDay = new Date(1962,6,8);
//		theBigDay.setDate(22);
//		System.out.println(theBigDay);
//		Time ti = new Time(lombok02.getTime());
		
		lombok02.setSeq(1);
		lombok02.setTitle("제목");
		lombok02.setWriter("작성자");
		lombok02.setContent("내용");
		lombok02.setRegdate(null);
		lombok02.setCnt(0);
		
		System.out.println(lombok02.getSeq());
		System.out.println(lombok02.getTitle());
		System.out.println(lombok02.getWriter());
		System.out.println(lombok02.getContent());
		System.out.println(lombok02.getRegdate());
		System.out.println(lombok02.getCnt());
		
		System.out.println(lombok02);
		
	}
}
