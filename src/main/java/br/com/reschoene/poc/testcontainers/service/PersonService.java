package br.com.reschoene.poc.testcontainers.service;

import br.com.reschoene.poc.testcontainers.entity.Person;
import br.com.reschoene.poc.testcontainers.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }

    @PostConstruct
    @Transactional
    private void prepareDatabase(){
        personRepository.deleteAll();
        personRepository.saveAll(getInitPersonList());
    }

    public static List<Person> getInitPersonList(){
        return List.of(new Person(null, "Renato Schoene"),
                       new Person(null, "John Schimidt"));
    }
}
