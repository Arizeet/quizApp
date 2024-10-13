package com.arizeet.quizWebApp.service;

import com.arizeet.quizWebApp.model.Question;
import com.arizeet.quizWebApp.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;


    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category) {
//        return repo.getAllQuestionsByCategory(category);
        try{
            return new ResponseEntity<>(repo.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {

        try{
            repo.save(question);
            return new ResponseEntity<>("Success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public Question findQuestionById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Question updateQuestion(int id, Question question) throws IOException {
        Optional<Question> existingQuestion = repo.findById(id);

        if(existingQuestion.isPresent()) {
            // Update the fields of the existing question with the new data
            Question questionToUpdate = existingQuestion.get();
            questionToUpdate.setQuestionTitle(question.getQuestionTitle());
            questionToUpdate.setOption1(question.getOption1());
            questionToUpdate.setOption2(question.getOption2());
            questionToUpdate.setOption3(question.getOption3());
            questionToUpdate.setOption4(question.getOption4());
            questionToUpdate.setRightAnswer(question.getRightAnswer());
            questionToUpdate.setDifficultyLevel(question.getDifficultyLevel());
            questionToUpdate.setCategory(question.getCategory());

            // Save the updated question back to the database
            return repo.save(questionToUpdate);
        } else {
            throw new IOException("Question with id " + id + " not found");
        }
    }

    public void deleteQuestion(int id) {
        repo.deleteById(id);
    }
}
