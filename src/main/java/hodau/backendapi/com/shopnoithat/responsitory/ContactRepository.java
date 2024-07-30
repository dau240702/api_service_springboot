package hodau.backendapi.com.shopnoithat.responsitory;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hodau.backendapi.com.shopnoithat.dto.ContactDTO;
import hodau.backendapi.com.shopnoithat.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT new hodau.backendapi.com.shopnoithat.dto.ContactDTO(c.categoryId, c.name, c.email, c.phone, c.title, c.content) FROM Contact c WHERE c.categoryId = :categoryId")
    ContactDTO findContactDTOById(@Param("categoryId") Long categoryId);

    @Query("SELECT new hodau.backendapi.com.shopnoithat.dto.ContactDTO(c.categoryId, c.name, c.email, c.phone, c.title, c.content) FROM Contact c WHERE c.name = :name")
    List<ContactDTO> findContactDTOsByName(@Param("name") String name);
}