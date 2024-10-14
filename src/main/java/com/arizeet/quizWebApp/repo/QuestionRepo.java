package com.arizeet.quizWebApp.repo;

import com.arizeet.quizWebApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    @Query("SELECT q FROM Question q WHERE LOWER(q.category) = LOWER(:category)")
    List<Question> getAllQuestionsByCategory(String category);

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM Question q WHERE LOWER(q.category) = LOWER(:category) ORDER BY RANDOM() LIMIT :numQ",nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(String category, int numQ);
}
