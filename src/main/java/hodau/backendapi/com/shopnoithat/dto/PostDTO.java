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
public class PostDTO {

    private Long postId;
    private String title;
    private String excerptImage;
    private String content;
    private Long createdBy;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime fromDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime toDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String status;
    private int viewcount;
    private Long categoryId;
    private String categoryName;

    // Constructors
    public PostDTO() {
    }

    public PostDTO(Long postId, String title, String excerptImage, String content, Long createdBy,
            LocalDateTime fromDate, LocalDateTime toDate, LocalDateTime createdAt, LocalDateTime updatedAt,
            String status, int viewcount, Long categoryId) {
        this.postId = postId;
        this.title = title;
        this.excerptImage = excerptImage;
        this.content = content;
        this.createdBy = createdBy;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.viewcount = viewcount;
        this.categoryId = categoryId;
    }

    public PostDTO(Long postId, String title, String excerptImage, String content, Long createdBy,
            LocalDateTime fromDate, LocalDateTime toDate, LocalDateTime createdAt, LocalDateTime updatedAt,
            String status, int viewcount, Long categoryId, String categoryName) {
        this.postId = postId;
        this.title = title;
        this.excerptImage = excerptImage;
        this.content = content;
        this.createdBy = createdBy;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.viewcount = viewcount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

}