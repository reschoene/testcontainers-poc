package br.com.reschoene.poc.testcontainers.dto;

import br.com.reschoene.poc.testcontainers.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private String name;

    public static PersonDto fromEntity(Person person){
        return new PersonDto(person.getName());
    }
}
