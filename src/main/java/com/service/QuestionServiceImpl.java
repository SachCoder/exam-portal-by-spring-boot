package com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.dto.AnswerDto;
import com.dto.QuestionDto;
import com.dto.QuizDto;
import com.dto.UserCreateDto;
import com.entities.Answer;
import com.entities.Question;
import com.entities.Quiz;
import com.entities.User;
import com.repos.AnswerRepo;
import com.repos.QuestionRepo;
import com.repos.QuizRepo;
import com.repos.UserRepo;
import com.security.CustomUserDetails;

@Service
public   class QuestionServiceImpl implements QuestionService {

	private final QuestionRepo QuestionRepo;
	private final UserRepo userRepo;
	QuizRepo quizRepo;    
    private final ModelMapper modelMapper;
    private  final AnswerRepo ansRepo;


    public QuestionServiceImpl(QuestionRepo QuestionRepo,ModelMapper modelMapper,UserRepo userRepo,QuizRepo quizRepo,AnswerRepo ansRepo) {
        this.QuestionRepo = QuestionRepo;
		this.userRepo = userRepo;
		this.quizRepo = quizRepo;
        this.modelMapper=modelMapper;
        this.ansRepo = ansRepo;
    }

    @Override
    public QuestionDto saveQuestion(QuestionDto question ) {    		 
    		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
     	        if(auth.isAuthenticated()) {
     	        	Quiz quiz= quizRepo.findByIdAndTitle(question.getQid(),question.getQtitle());
//     	        	System.out.println(quiz.getDescription());
    			if(quiz!=null) {
    				QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
       				question.setQuiz(quizDto);
       				CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
       	  	        String userId = obj.getId();	
       	  	   Optional<User>  user = userRepo.findById(userId);
       	  	   if(user.isPresent()){
       	  		   User u1 = user.get();
       	  		   UserCreateDto dto = modelMapper.map(u1, UserCreateDto.class);
       	  		   
       	  		question.setUser(dto);
       	  	   }
        			Question save = QuestionRepo.save(modelMapper.map(question, Question.class));
            		return modelMapper.map(save, QuestionDto.class) ;
    			}
    	        }
//    			
//    	        }
    		
//    	}
    	 
    		return null;
    }
 

    @Override
    public QuestionDto updateQuestion(QuestionDto Question) {
    	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        if(auth.isAuthenticated()) {
	        	CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
  	        String userId = obj.getId();	
  	        
  	      Optional<User>  user = userRepo.findById(userId);
			if(user.isPresent()) {
//				User u1 = user.get();
//				UserCreateDto userDto =modelMapper.map(u1, UserCreateDto.class) ;
  			Optional<Question>  emp = QuestionRepo.findById(Question.getId());
  			if(emp.isPresent() ) {
   				Question e1 = emp.get();
  				Question.setId(e1.getId());  			
  				Question updatedQuestion = modelMapper.map(Question, Question.class);
        Question savedQuestion = QuestionRepo.save(updatedQuestion);
        return modelMapper.map(savedQuestion, QuestionDto.class);
			}
	        }
	        }
			return null;
    }

	 
    @Override
    public void deleteQuestion( String id) {
    	
    	if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
    		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
 if(auth.isAuthenticated()) {
    		QuestionRepo.deleteById(id);
 }
    	}
        
    }
 
    @Override
    public List<QuestionDto> getQuestionByQuizId(String id) {
    	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();	        
	        if(auth.isAuthenticated()) {
	        	List<Question> questions = QuestionRepo.findByQuizId(id);
	            return questions.stream()
	                            .map(question -> modelMapper.map(question, QuestionDto.class))
	                            .collect(Collectors.toList());
	        }
		return null;         
    }

    @Override
    public QuestionDto getQuestionById(String id) {
    	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();	        
	        if(auth.isAuthenticated()) {
//	        	System.out.println(id);
	        	Question questions = QuestionRepo.findByIds(id);
	        	if(questions!=null) {
//	        		System.out.println(questions.getContent());
	 	            return  modelMapper.map(questions, QuestionDto.class);
	        	}                      
	        }
		return null;         
    }
    

    @Override
    public List<QuestionDto> getAllQuestions() {
    	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  if(auth.isAuthenticated()) {
//    Fetch departments associated with the authenticated user
//    List<Question> Questions = QuestionRepo.findByUser_Id(userId);

//	    Comparator<QuestionDto> byId = Comparator.comparingInt(QuestionDto::getEmp_id);
//	    return Questions.stream()
//	            .map(e -> modelMapper.map(e, QuestionDto.class))
//	            .sorted(byId)
//	            .toList();
	return null;
}
      return null;

    }

	@Override
	public Map<String, Object> get( List<QuestionDto> questions) {
			double marksGot = 0 ;
			int correctAnswers = 0;
			int attempted = 0 ;
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    	CustomUserDetails obj = (CustomUserDetails) auth.getPrincipal();
		  	    String userId = obj.getId();
			for(QuestionDto q:questions) {	
				Question question = QuestionRepo.findByIds(q.getId());
// 							if(q.getGivenAnswer()!=null) {
 								
 				if(question.getAnswer().equals(q.getGivenAnswer()))
				{
				correctAnswers++;
				double marksSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
				marksGot += marksSingle;
				//answer me jane ka
					AnswerDto ans=new AnswerDto();
				    ans.setQuiz(q.getQuiz());
				   ans.setQuestion(q); 
				    ans.setCorrectAnswer(true);
				    if(q.getGivenAnswer()!= null) {
 						 ans.setAttempted(true);
					}
				   
				     	
			  	    Optional<User>  user = userRepo.findById(userId);
			  	    UserCreateDto u1=  modelMapper.map(user, UserCreateDto.class);
				    ans.setUser(u1);
				    LocalDateTime now = LocalDateTime.now();
				    ans.setFsubmit(now);
				    ans.setGivenAnswer(q.getGivenAnswer());
				   Answer answer =  modelMapper.map(ans, Answer.class);
				    ansRepo.save(answer);
 			}
				else {
					AnswerDto ans=new AnswerDto();
				    ans.setQuiz(q.getQuiz());
				   ans.setQuestion(q); 
				    ans.setCorrectAnswer(false);
 				    if(q.getGivenAnswer()!= null) {
	 						 ans.setAttempted(true);						
					}
			  	    Optional<User>  user = userRepo.findById(userId);
			  	    UserCreateDto u1=  modelMapper.map(user, UserCreateDto.class);
				    ans.setUser(u1);
				    LocalDateTime now = LocalDateTime.now();
				    ans.setFsubmit(now);
				   Answer answer =  modelMapper.map(ans, Answer.class);
				   ansRepo.save(answer);
				}
				if(q.getGivenAnswer()!= null) {
					attempted++; 
				}
			}
 							
 							 
			
	  Map<String,Object> map = Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
			
	 		return map;
 
}
	}
