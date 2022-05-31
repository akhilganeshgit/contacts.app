package com.dev.contacts.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDto {

    private long id;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String emailId;
}
