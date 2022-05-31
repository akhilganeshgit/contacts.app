package com.dev.contacts.app.repository;

import com.dev.contacts.app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Long> {

}
