package com.mysite.sbb.answer;

import java.time.LocalDateTime;
import java.util.Set;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity			//JPA에서 자바 객체를 DB의 테이블에 매핑설정
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;					//Primary key, 자동 증가 (1,1)
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;	//create_date
	
	@ManyToOne							//Foreign key : 부모 테이블의 primary key와 uk컬럼의 값을 참조해서 값을 할당
	private Question question;			//부모 테이블이 Question 테이블의 primary key를 참조(id)
										//puestion_id
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;
}
