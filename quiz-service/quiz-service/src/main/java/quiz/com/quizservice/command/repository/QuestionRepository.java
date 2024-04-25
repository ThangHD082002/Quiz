package quiz.com.quizservice.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import quiz.com.quizservice.command.entity.Question;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
}
