package com.behsazan.aop.web.rest;

import com.behsazan.aop.annotations.Loggable;
import com.behsazan.aop.domain.Person;
import com.behsazan.aop.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/person/{id}")
    @Loggable
    public ResponseEntity<Person> getPerson(@PathVariable Long id) {
        return personService.getById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @GetMapping(value = "/personByName/{name}")
    @Loggable
    public ResponseEntity<List<Person>> getPersonByName(@PathVariable String name) {
        return ResponseEntity.ok(personService.findByName(name));
    }

    @GetMapping(value = "/person")
    @Loggable
    public ResponseEntity<List<Person>> getAll() {
        return ResponseEntity.ok(personService.getAllPersons());
    }

    @DeleteMapping(value = "/person/{id}")
    @Loggable
    public HttpStatus deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return HttpStatus.NO_CONTENT;
    }

    @PostMapping(value = "/person")
    @Loggable
    public HttpStatus insertPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return HttpStatus.CREATED;
    }

    @PutMapping(value = "/person")
    @Loggable
    public HttpStatus updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        return HttpStatus.ACCEPTED;
    }
}

