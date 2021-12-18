package br.com.reschoene.poc.testcontainers.controller;

import br.com.reschoene.poc.testcontainers.dto.PersonDto;
import br.com.reschoene.poc.testcontainers.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/person")
    List<PersonDto> getPerson(){
        return personService.getAllPersons()
                .stream()
                .map(PersonDto::fromEntity)
                .collect(Collectors.toList());
    }
}
