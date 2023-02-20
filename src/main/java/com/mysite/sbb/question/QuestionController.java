package com.mysite.sbb.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserForm;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	//final 필드의 생성자를 자동으로 만들어서 생성자를 통한 의존성 주입
@Controller	
public class QuestionController {
	/*
	 클래스를 객체로 생성 어노테이션 (빈(객체)등록, Spring Framework)  
	 @Component : 일반적인 클래스를 객체화
	 @Controller : 
	 	 1. 클라이언트 요청을 받아서 처리, @GetMapping, @PostMapping
	 	 2. 비즈니스 로직 처리  Service의 메소드 호출
	 	 3. View 페이지로 응답 
	 @service : DAO의 메소드를 인터페이스로 생성 후 인터페이스의 메소드를 구현한 클래스
	 	 - 유지보수를 쉽게 하기 위해서(약결합)
	 @Repository : DAO 클래스를 빈 등록
	 
	 DI (의존성 주입)
	 1. @Autowired : Spring Framework 의 생성된 빈(객체)을 주입, 타입을 찾아서 주입
	 	같은 타입의 객체가 존재할 경우 문제가 발생될 수 있다.
	 2. 생성자를 통한 의존성 주입 (권장 방식)
	 	
	 3. Setter 를 사용한 의존성 주입
	 	
	 	
	 생성자를 통한 의존성 주입 <== 권장하는 방식
	 	Clotroller 에서 직접 Repository 를 접근하지 않고 SErvice를 접근 하도록 함
	 */
	//private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	private final UserService userService;
//	@GetMapping("/question/list")	//http://localhost:9898/question/list
//	//@PostMapping("/question/list")	//Form 태그의 method=post action="/questio/list/"
//	//@ResponseBody					//요청을 브라우저에 출력
//	public String list(Model model) {
//		// 1. 클라이언트 요청 정보 : http://localhost:9898/questio/list
//		
//		// 2. 비즈니스 로직을 처리
//		//List<Question> questionList = this.questionRepository.findAll();
//		List<Question> questionList = this.questionService.getList();
//
//		// 3. 뷰(view) 페이지로 전송
//			// Model : 뷰 페이지로 서버의 데이터를 담아서 전송 객체 (Session, Application)
//		model.addAttribute("questionList", questionList);	
//		
//		return "question_list";
//	}
	
	// 2월 14일 페이징 처리를 위해 수정
	// http://localhost:9898/question/list/?page=0
	@GetMapping("/question/list")
	public String list(Model model, @RequestParam (value = "page", defaultValue = "0") int page,
			@RequestParam(value = "kw", defaultValue = "") String kw) {
		
		// 비즈니스 로직 처리 : 
		Page<Question> paging = this.questionService.getList(page, kw);
		
		// model 객체에 결과로 받은 paging 객체를 client 로 전송
		model.addAttribute("paging", paging);
		model.addAttribute("kw",kw);
		
		return "question_list";
	}
	
	// 상세 페이지를 처리하는 메소드 : /question/detail/1
	@GetMapping("/question/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {	
		// 서비스 클래스의 메소드 호출 : 상세페이지를 보여 달라
		Question q = this.questionService.getQuestion(id);
		
		// Model 객체에 담아서 클라이언트에 전달
		model.addAttribute("question", q);
		
		return "question_detail";	//templates / question_detail.html
	}
	@GetMapping("/question/create")
	@PreAuthorize("isAuthenticated()")
	public String questionCreate(QuestionForm questionForm) {
		return "question_form";
	}
	@PostMapping("/question/create")
	@PreAuthorize("isAuthenticated()")
	public String questionCreate(
			//@RequestParam String subject, @RequestParam String content
			@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal
			) {
			if(bindingResult.hasErrors()) { //subject, content 가 비어있을 때 
				return "question_form";
			}
			SiteUser siteUser = this.userService.getUser(principal.getName());
			this.questionService.create(questionForm.getSubject(), questionForm.getContent(),siteUser);
		// 로직 작성부분 (Service 에서 로직을 만들어서 작동)
		//this.questionService.create(subject, content);
		//값을 DB에 저장 후 List페이지로 리다이렉트 (질문 목록으로 이동)
		return "redirect:/question/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
			Principal principal, @PathVariable("id") Integer id) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		Question question = this.questionService.getQuestion(id);
		
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
		
		
		return String.format("redirect:/question/detail/%s",id);
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
		
		Question question = this.questionService.getQuestion(id);
		
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제권한이 없습니다.");
		}
		
		this.questionService.delete(question);
		
		return "redirect:/";
	}
	
	public String questionVote(Principal principal,@PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.questionService.vote(question, siteUser);
		
		return String.format("redirect:/question/detail/%s", id);
	}
	
	
}
