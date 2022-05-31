package com.dev.contacts.app.controller;

import com.dev.contacts.app.dto.ContactDto;
import com.dev.contacts.app.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/contacts/")
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<String> createContact(@RequestBody ContactDto contactDto){
        contactService.createContact(contactDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Contact Created Successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ContactDto>> showAllContacts() {
        return new ResponseEntity<>(contactService.showAllContacts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ContactDto> getSingleContact(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(contactService.readSingleContact(id), HttpStatus.OK);
    }
}
