package com.behsazan.aop.service;

import com.behsazan.aop.annotations.Loggable;
import com.behsazan.aop.domain.Person;
import com.behsazan.aop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Transactional
    @Loggable
    public List<Person> getAllPersons() {
        return (List<Person>) personRepository.findAll();
    }

    @Transactional
    @Loggable
    public List<Person> findByName(String name) {
        return personRepository.findByFirstName(name);
    }

    @Transactional
    @Loggable
    public Optional<Person> getById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    @Loggable
    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }

    @Transactional
//    @Loggable
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Transactional
    @Loggable
    public void updatePerson(Person person) {
        personRepository.save(person);
    }
}

