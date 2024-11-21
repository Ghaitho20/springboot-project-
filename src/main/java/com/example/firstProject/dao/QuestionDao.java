package com.example.firstProject.dao;

import com.example.firstProject.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

    // Find questions by category
    List<Question> findByCategory(String category);

    // Custom query to fetch random questions by category with a limit
    @Query(value = "SELECT * FROM \"question\" q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);

}
