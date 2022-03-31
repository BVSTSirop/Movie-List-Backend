package application.controller;

import application.entity.Person;
import application.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    public PersonController(PersonRepository personRepository) {
        this.userRepository = personRepository;
    }

    // standard constructors
    private final PersonRepository userRepository;

    @GetMapping("/users")
    public List<Person> getUsers() {
        return (List<Person>) userRepository.findAll();
    }

    @PostMapping("/users")
    void addUser(@RequestBody Person user) {
        userRepository.save(user);
    }
}
