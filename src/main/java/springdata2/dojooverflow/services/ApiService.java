package springdata2.dojooverflow.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import springdata2.dojooverflow.models.Answer;
import springdata2.dojooverflow.models.Question;
import springdata2.dojooverflow.models.Tag;
import springdata2.dojooverflow.repositories.AnswerRepository;
import springdata2.dojooverflow.repositories.QuestionRepository;
import springdata2.dojooverflow.repositories.TagRepository;

@Service
public class ApiService {
	private final QuestionRepository qRepo;
	private final TagRepository tRepo;
	private final AnswerRepository aRepo;
	
	public ApiService(QuestionRepository qRepo, TagRepository tRepo, AnswerRepository aRepo) {
		this.qRepo = qRepo;
		this.tRepo = tRepo;
		this.aRepo = aRepo;
	}
	
	public List<Question> allQuestions(){
		return (List<Question>) qRepo.findAll();
	}
	
	public Question findQuestionById(Long id) {
		Optional<Question> optQ = qRepo.findById(id);
		if(optQ.isPresent()) {
			return optQ.get();
		}
		else {
			return null;
		}
	}
	
	public void createAnswer(String ans, Long id) {
		Answer answer = new Answer(ans, qRepo.findById(id).get());
		aRepo.save(answer);
	}
	
	public void createQuestion(String q, List<String> tags) {
		Question question = new Question(q);
		List<Tag> objTags = createTag(tags);
		addTagsToQuestion(objTags, question);
	}
	
	
	public List<Tag> createTag(List<String> tags) {
		List<Tag> returnTags = new ArrayList<Tag>(); // lista que se devuelve a createQuestion
		List<Tag> allTags = (List<Tag>) tRepo.findAll(); 
		for(int i=0; i<tags.size(); i++) { // For para agregar o descartar cada tag según exista o no.
			String name = tags.get(i); // Obtiene cada nombre de cada tag en la lista string de tags.
			for(int j=0; j<allTags.size(); j++) {
				if(name.equals(allTags.get(j).getName())) { // Si el tag ya existe en la lista de tags, entonces agrega el tag que coincida con el nombre y luego lo cambia el string a "exists"
					returnTags.add(tRepo.findByName(name));
					name = "exists";
					break;
				}
			}
			if(!name.equals("exists")) { // si el nombre no es igual a exists, entonces lo crea, lo agrega a la lista de tags que se van a retornar y lo guarda en la db
				Tag tag = new Tag(name);
				returnTags.add(tag);
				tRepo.save(tag);
			}
		}
		return returnTags;
	}
	
	public void addTagsToQuestion(List<Tag> tags, Question q) {
		q.setTags(tags);
		qRepo.save(q);
	}
	
}
