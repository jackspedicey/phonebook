package com.msig.phonebook.service;

import com.msig.phonebook.dto.DataTablesInput;
import com.msig.phonebook.dto.DataTablesOutput;
import com.msig.phonebook.entity.ContactPerson;
import com.msig.phonebook.repository.ContactPersonRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactPersonService {

    @Autowired
    private ContactPersonRepository contactPersonRepository;

    @Autowired
    private DataTablesService dataTablesService;

    public ResponseEntity<List<ContactPerson>> findAll() {
        return ResponseEntity.ok(contactPersonRepository.findAll());
    }

    public ResponseEntity<ContactPerson> findById(Long id) {
        Optional<ContactPerson> optionalContactPerson = contactPersonRepository.findById(id);

        return optionalContactPerson.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<ContactPerson> findByPhone(String phone) {
        Optional<ContactPerson> optionalContactPerson = contactPersonRepository.findByPhone(phone);

        return optionalContactPerson.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<ContactPerson> save(ContactPerson contactPerson, boolean isUpdate) {
        Optional<ContactPerson> optionalContactPerson = contactPersonRepository.findByPhone(contactPerson.getPhone());
        if (!isUpdate && optionalContactPerson.isPresent()) {
            return ResponseEntity.badRequest().body(optionalContactPerson.get());
        } else {
            ContactPerson savedContactPerson = contactPersonRepository.save(contactPerson);
            return ResponseEntity.ok(savedContactPerson);
        }
    }

    public ResponseEntity<ContactPerson> delete(Long id) {
        Optional<ContactPerson> optionalContactPerson = contactPersonRepository.findById(id);
        if (optionalContactPerson.isPresent()) {
            contactPersonRepository.delete(optionalContactPerson.get());
            return ResponseEntity.ok(optionalContactPerson.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public DataTablesOutput<ContactPerson> getContactPersonDataTables(DataTablesInput input, Specification<ContactPerson> spec) {
        DataTablesOutput<ContactPerson> output = dataTablesService.getDataTableOutput(input, contactPersonRepository, contactPersonRepository, spec);

        List<ContactPerson> dtos = output.getData().stream().collect(Collectors.toList());

        DataTablesOutput<ContactPerson> dtoOutput = new DataTablesOutput<>();
        dtoOutput.setDraw(output.getDraw());
        dtoOutput.setRecordsTotal(output.getRecordsTotal());
        dtoOutput.setRecordsFiltered(output.getRecordsFiltered());
        dtoOutput.setData(dtos);

        return dtoOutput;
    }

}
