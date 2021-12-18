package br.com.reschoene.poc.testcontainers.integration;

import br.com.reschoene.poc.testcontainers.entity.Person;
import br.com.reschoene.poc.testcontainers.service.PersonService;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author Renato Schoene
 * JUnit 5 (Jupiter) integration is provided by means of the @Testcontainers annotation.
 *
 * The extension finds all fields that are annotated with @Container and calls their container lifecycle
 * methods (methods on the Startable interface). Containers declared as static fields will be shared between test methods.
 * They will be started only once before any test method is executed and stopped after the last test method has executed.
 * Containers declared as instance fields will be started and stopped for every test method.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class TestcontainerPostgreIT {
    @LocalServerPort
    private int ramdomServerPost = 0;

    @Container
    public static final PostgreSQLContainer postgreContainer = new PostgreSQLContainer("postgres:12")
            .withUsername("postgres")
            .withPassword("mysecretpassword");

    @DynamicPropertySource
    private static void postgresqlProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgreContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreContainer::getUsername);
        registry.add("spring.datasource.password", postgreContainer::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get_person_returns_status_200_containing_initial_list_of_persons(){
        //given
        List<Person> expectedPersons = PersonService.getInitPersonList();

        //when
        ResponseEntity<Person[]> response = restTemplate.getForEntity(createURLWithPort("/person"), Person[].class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Arrays.asList(response.getBody())).isEqualTo(expectedPersons);
    }

    private String createURLWithPort(String uri) {
        return String.format("http://localhost:%s%s", ramdomServerPost, uri);
    }
}
