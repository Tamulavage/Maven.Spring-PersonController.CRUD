package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.entities.Person;
import io.zipcoder.crudapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

//    @PostMapping("/people/")
//    public Person createPerson(@RequestBody Person p){
//        return personRepository.save(p);
//
//    }

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        return new ResponseEntity<>(personRepository.save(p), HttpStatus.CREATED);
    }

    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable int id){
        return personRepository.findOne(id);
    }

    @GetMapping("/people/")
    public Iterable<Person> getPersonList(){
        return personRepository.findAll();
    }

    @PutMapping("/people/")
    public Person updatePerson(@RequestBody Person p){
        Person personToUpdate = personRepository.findOne(p.getId());
        personToUpdate.setFirstName(p.getFirstName());
        personToUpdate.setLastName(p.getLastName());
        return personRepository.save(personToUpdate);
    }

    @DeleteMapping("/people/{id}")
    public void DeletePerson(@PathVariable int id){
        personRepository.delete(id);
    }
}
