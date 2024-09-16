package hodau.backendapi.com.shopnoithat.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Setter
@Getter
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "create_date")
    private LocalDateTime createdate;

    @Column(name = "update_date")
    private LocalDateTime updatedate;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;
}