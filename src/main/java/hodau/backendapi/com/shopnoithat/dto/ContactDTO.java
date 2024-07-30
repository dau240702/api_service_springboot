package hodau.backendapi.com.shopnoithat.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class ContactDTO {
    private Long categoryId;
    private String name;
    private String email;
    private String phone;
    private String title;
    private String content;

    // Constructors
    public ContactDTO() {
    }

    public ContactDTO(Long categoryId, String name, String email, String phone, String title, String content) {
        this.categoryId = categoryId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.title = title;
        this.content = content;
    }

}