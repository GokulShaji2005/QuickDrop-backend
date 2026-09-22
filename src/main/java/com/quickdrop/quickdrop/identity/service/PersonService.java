package com.quickdrop.quickdrop.identity.service;

import org.springframework.stereotype.Service;

import com.quickdrop.quickdrop.identity.entity.Person;
import com.quickdrop.quickdrop.identity.repository.PersonRepository;

@Service 
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository){
        this.personRepository=personRepository;
    }

    public Person findOrCreateByMobile(String mobile){
        return personRepository.findByMobile(mobile).orElseGet(()->{
            Person newPerson=new Person();
            newPerson.setMobile(mobile);
            return personRepository.save(newPerson);
        });
    }
}
