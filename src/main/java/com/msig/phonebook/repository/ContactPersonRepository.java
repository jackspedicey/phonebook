package com.msig.phonebook.repository;

import com.msig.phonebook.entity.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, Long>, JpaSpecificationExecutor<ContactPerson> {

   Optional<ContactPerson> findByPhone(String phone);
}
