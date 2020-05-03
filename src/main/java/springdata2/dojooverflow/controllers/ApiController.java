package springdata2.dojooverflow.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import springdata2.dojooverflow.services.ApiService;

@Controller
public class ApiController {
	private final ApiService s;

	public ApiController(ApiService s) {
		this.s = s;
	}
	
	@RequestMapping("/questions")
	public String questions(Model model) {
		model.addAttribute("questions", s.allQuestions());
		return "files/questions.jsp";
	}
	
	@RequestMapping("/questions/new")
	public String newQuestion() {
		return "files/newquestion.jsp";
	}
	
	@RequestMapping(value="/questions/new", method=RequestMethod.POST)
	public String saveQuestion(@RequestParam("question") String q, @RequestParam("tags") String tags) {
		String[] arrTags = tags.split(",");
		List<String> listTags = Arrays.asList(arrTags);
		s.createQuestion(q, listTags);
		return "redirect:/questions";
	}
	
	@RequestMapping("/questions/{id}")
	public String showQuestion(Model model, @PathVariable Long id) {
		model.addAttribute("question", s.findQuestionById(id));
		return "files/showquestion.jsp";
	}
	
	@RequestMapping(value="/questions/{id}", method=RequestMethod.POST)
	public String saveAnswer(@PathVariable Long id, Model model, @RequestParam("answer") String answer) {
		s.createAnswer(answer, id);
		return "redirect:/questions/"+id;
	}
}
