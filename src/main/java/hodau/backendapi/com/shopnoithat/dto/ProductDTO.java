package hodau.backendapi.com.shopnoithat.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ProductDTO implements Serializable {
    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stockQuantity;
    private String imageUrlPath;
    private Long categoryId;
    private String categoryName;
    private String status;
    private Long createdBy;
    private String createbyName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public ProductDTO(Long productId, String name, String description, BigDecimal price, int stockQuantity,
            String imageUrlPath, String categoryName, Long categoryId, String status, Long createdBy,String createbyName,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrlPath = imageUrlPath;
        this.categoryName = categoryName;
        this.categoryId = categoryId;
        this.status = status;
        this.createdBy = createdBy;
        this.createbyName=createbyName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ProductDTO(Long productId, String name, String description, BigDecimal price, int stockQuantity,
            String imageUrlPath, Long categoryId, String status, Long createdBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.imageUrlPath = imageUrlPath;
        this.categoryId = categoryId;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public String getImageFileName() {
        return imageUrlPath;
    }

    public void setImageFileName(String imageUrlPath) {
        this.imageUrlPath = imageUrlPath;
    }
}