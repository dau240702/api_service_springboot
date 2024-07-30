package hodau.backendapi.com.shopnoithat.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hodau.backendapi.com.shopnoithat.dto.ContactDTO;
import hodau.backendapi.com.shopnoithat.service.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@RequestBody ContactDTO contactDTO) {
        ContactDTO createdContact = contactService.createContact(contactDTO);
        return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable Long categoryId) {
        ContactDTO contactDTO = contactService.getContactById(categoryId);
        if (contactDTO != null) {
            return new ResponseEntity<>(contactDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contacts = contactService.getAllContacts();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Long categoryId, @RequestBody ContactDTO contactDTO) {
        try {
            ContactDTO updatedContact = contactService.updateContact(categoryId, contactDTO);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long categoryId) {
        try {
            contactService.deleteContact(categoryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<ContactDTO>> getContactsByName(@RequestParam String name) {
        List<ContactDTO> contacts = contactService.getContactsByName(name);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

}
