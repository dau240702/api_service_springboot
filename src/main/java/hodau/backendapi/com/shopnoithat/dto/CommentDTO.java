package hodau.backendapi.com.shopnoithat.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import hodau.backendapi.com.shopnoithat.common.LocalDateTimeDeserializer;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class CommentDTO {

    private Long commentId;
    private String name;
    private String email;
    private String content;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateDate;
    private Long postId;

    public CommentDTO() {
    }

    public CommentDTO(Long commentId, String content,  String email, String name, LocalDateTime createDate,LocalDateTime updateDate, Long postId) {
        this.commentId = commentId;
        this.content = content;
        this.createDate = createDate;
        this.email = email;
        this.name = name;
        this.postId = postId;
        this.updateDate = updateDate;
    }
}
