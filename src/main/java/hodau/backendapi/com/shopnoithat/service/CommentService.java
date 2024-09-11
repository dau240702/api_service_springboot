package hodau.backendapi.com.shopnoithat.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hodau.backendapi.com.shopnoithat.common.ResourceNotFoundException;
import hodau.backendapi.com.shopnoithat.dto.CommentDTO;
import hodau.backendapi.com.shopnoithat.entity.Comment;
import hodau.backendapi.com.shopnoithat.responsitory.CommentRepository;
import hodau.backendapi.com.shopnoithat.responsitory.PostRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setContent(commentDTO.getContent());
        comment.setCreatedate(LocalDateTime.now());
        comment.setUpdatedate(LocalDateTime.now());
        comment.setPost(postRepository.findById(commentDTO.getPostId()).orElse(null));

        comment = commentRepository.save(comment);
        return toDTO(comment);
    }

    public CommentDTO updateComment(Long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));

        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setContent(commentDTO.getContent());
        comment.setUpdatedate(LocalDateTime.now());
        comment.setPost(postRepository.findById(commentDTO.getPostId()).orElse(null));

        comment = commentRepository.save(comment);
        return toDTO(comment);
    }

    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new ResourceNotFoundException("Comment not found");
        }
        commentRepository.deleteById(commentId);
    }

    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CommentDTO getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        return toDTO(comment);
    }

    private CommentDTO toDTO(Comment comment) {
        return new CommentDTO(
                comment.getCommentId(),
                comment.getName(),
                comment.getEmail(),
                comment.getContent(),
                comment.getCreatedate(),
                comment.getUpdatedate(),
                comment.getPost().getPostId()
        );
    }
}

