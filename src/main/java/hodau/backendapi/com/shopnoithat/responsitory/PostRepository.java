package hodau.backendapi.com.shopnoithat.responsitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import hodau.backendapi.com.shopnoithat.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAll();
}
