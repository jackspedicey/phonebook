package com.msig.phonebook.controller;

import com.msig.phonebook.dto.APIResponse;
import com.msig.phonebook.dto.DataTablesInput;
import com.msig.phonebook.dto.DataTablesOutput;
import com.msig.phonebook.entity.ContactPerson;
import com.msig.phonebook.repository.ContactPersonRepository;
import com.msig.phonebook.service.ContactPersonService;
import com.msig.phonebook.util.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cp")
public class ContactPersonRestController {

    @Autowired
    private ContactPersonService contactPersonService;

    @GetMapping("/")
    public ResponseEntity<List<ContactPerson>> getAllContactPersons () {

        return contactPersonService.findAll();
    }

//    @GetMapping("/{phoneNumber}")
//    public ResponseEntity<ContactPerson> getContactByPhoneNumber (@PathVariable("phoneNumber") String phoneNumber) {
//
//        return  contactPersonService.findByPhone(phoneNumber);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactPerson> getContactById (@PathVariable("id") Long id) {
        System.out.println("HELLOO CONTACT");
        return contactPersonService.findById(id);
    }

    @PostMapping("/")
    public ResponseEntity<ContactPerson> createContactPerson (@RequestBody ContactPerson contactPerson) {

        return contactPersonService.save(contactPerson, false);
    }

    @PutMapping("/")
    public ResponseEntity<ContactPerson> updateContactPerson (@RequestBody ContactPerson contactPerson) {

        return contactPersonService.save(contactPerson, true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactPerson> deleteContactPerson (@PathVariable("id") long id) {

        return contactPersonService.delete(id);
    }

    @PostMapping("/datatable")
    public DataTablesOutput<ContactPerson> dataTableCourses(@RequestBody DataTablesInput input) {
        Specification<ContactPerson> spec = Specifications.contactSearch(input.getSearchValue());
        return contactPersonService.getContactPersonDataTables(input, spec);
    }

}
