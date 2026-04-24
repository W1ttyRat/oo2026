package ee.tanel.veebipood.controller;

import ee.tanel.veebipood.dto.PersonLoginRecordDto;
import ee.tanel.veebipood.entity.Person;
import ee.tanel.veebipood.repository.PersonRepository;
import ee.tanel.veebipood.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // Dependency injection. kui luuakse see klass (PersonController), seotakse ära samal ajal
    // temaga kõik allolevad muutjuad
    // injectiga võib ka läbi ka constructorite
    @Autowired
    private PersonService personService;

    @GetMapping("persons")
    public List<Person> getPerson(){
        return personRepository.findAll();
    }

    @DeleteMapping("persons/{id}")
    public List<Person> deletePerson(@PathVariable Long id){
        personRepository.deleteById(id); // kustutan
        return personRepository.findAll();
    }

    @PostMapping("signup")
    public Person signup(@RequestBody Person person){ // TODO:  PersonSignupDTO (kus pole aadressi, ega ID-d)
        if (person.getId() != null) {
            throw new RuntimeException("Cannot sign up with ID");
        }
        personService.validate(person);
        return personRepository.save(person);
    }

    @PostMapping("login")
    public Person login(@RequestBody PersonLoginRecordDto personDto){ // TODO: PersonUpdateDTO (kus pole parooli)
        Person dbPerson = personRepository.findByEmail(personDto.email());
        if (dbPerson == null) {
            throw new RuntimeException("Invalid email");
        }
        if (dbPerson.getPassword().equals(personDto.password())) {
            throw new RuntimeException("Invalid password");
        }
        return dbPerson;
    }

    @PutMapping("profile")
    public Person updateProfile(@RequestBody Person person) {
        // kui on DTO, siis ei pea alumist kontrolli tegema
        if (person.getId() == null) {
            throw new RuntimeException("Cannot update without ID");
        }
        personService.validate(person);
        return personRepository.save(person);
    }

    @GetMapping("profile")
    public Person getProfile(@RequestParam Long id) {
        return personRepository.findById(id).orElseThrow();
    }
}
