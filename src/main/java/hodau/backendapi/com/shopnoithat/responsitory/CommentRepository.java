package hodau.backendapi.com.shopnoithat.responsitory;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hodau.backendapi.com.shopnoithat.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAll();

}

