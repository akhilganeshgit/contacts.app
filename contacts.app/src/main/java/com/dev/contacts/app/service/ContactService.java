package com.dev.contacts.app.service;

import com.dev.contacts.app.dto.ContactDto;
import com.dev.contacts.app.exception.ContactNotFoundException;
import com.dev.contacts.app.model.Contact;
import com.dev.contacts.app.model.User;
import com.dev.contacts.app.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final AuthService authService;

    public void createContact(ContactDto contactDto) {
        Contact contact = mapFromDtoToContact(contactDto);
        contactRepository.save(contact);
    }

    public List<ContactDto> showAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream().map(this::mapFromContactToDto).collect(toList());
    }

    public ContactDto readSingleContact(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException("No Contact Found with id "+id));
        return mapFromContactToDto(contact);
    }

    private Contact mapFromDtoToContact(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setMobileNumber(contactDto.getMobileNumber());
        contact.setEmailId(contactDto.getEmailId());
        User loggedInUser = authService.getCurrentUser();
        contact.setCreatedBy(loggedInUser.getUsername());
        contact.setCreatedAt(Instant.now());
        contact.setUpdatedAt(Instant.now());
        return contact;
    }

    private ContactDto mapFromContactToDto(Contact contact){
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setMobileNumber(contact.getMobileNumber());
        contactDto.setEmailId(contact.getEmailId());
        return contactDto;
    }
}
