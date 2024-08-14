package com.msig.phonebook.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "contact_person")
@Data
public class ContactPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String company;
    private String title;

}
