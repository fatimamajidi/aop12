package com.behsazan.aop.repository;

import com.behsazan.aop.domain.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {
    List<Person> findByFirstName(String firstName);
}

