package com.mysite.sbb.question;

import java.time.LocalDateTime;	//자신의 시스템의 (한국) 로케일의 시간설정
import java.util.List;
import java.util.Set;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

//persistence : JPA에서 사용된 어노테이션
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity		//자바 클래스를DateBase의 테이블과 매핑된 클래스 : 테이블명 - Question 
@ToString	//테스트 할 때 사용
public class Question {
	
	@Id 												//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 시퀀스 할당 
	private Integer id;					//primary key, 시퀀스 (1, 1)
	
	@Column(length = 200) 				//200자까지 
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
//	@Column(length = 300)
//	private String addr;
	
	//Question 테이블에서 Answer 테이블을 참조하는 컬럼생성 @OnetoMany 
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
		//question.getAnswerList();
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter; // 한명의 사용자가 여러 질문에 투표할 수 있다. / 하나의 질문에 여러명의 사용자가 투표를 할 수 있다. 1번만 투표가능 하도록 set
	// List : 방번호 (index)를 가지고 중복된 값을 저장 할 수 있다. 
	// Set : 자료형은 중복된 값을 넣을 수 없는 자료형
		// Set은 방번호를 가지지 않는다. 
}
