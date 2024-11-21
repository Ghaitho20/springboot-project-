package com.example.firstProject.service;

import com.example.firstProject.dao.QuestionDao;
import com.example.firstProject.dao.QuizDao;
import com.example.firstProject.model.Question;
import com.example.firstProject.model.QuestionWrapper;
import com.example.firstProject.model.Quiz;
import com.example.firstProject.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> listQuestion = questionDao.findRandomQuestionsByCategory(category,numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(listQuestion);
        quizDao.save(quiz);
        return new ResponseEntity<>("sucess", HttpStatus.OK);
    }
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = Optional.of(quizDao.getById(id));
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionForUser = new ArrayList<>();
        for (Question question :questionsFromDB) {

            QuestionWrapper qw = new QuestionWrapper(question.getId(),question.getQuestiontitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
            questionForUser.add(qw);
        }

        return new ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions= quiz.get().getQuestions();
        int right = 0;
        int i = 0 ;
        for (Response res :responses ){
            if (res.getResponse().equals(questions.get(i).getRightanswer()))
                right++;
            i++;

        }
        return new ResponseEntity<>(right,HttpStatus.OK);



    }
}
