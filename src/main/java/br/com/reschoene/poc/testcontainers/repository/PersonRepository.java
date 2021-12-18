package br.com.reschoene.poc.testcontainers.repository;

import br.com.reschoene.poc.testcontainers.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
