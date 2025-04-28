package se.lexicon.todo_app.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.todo_app.dto.PersonDto;
import se.lexicon.todo_app.service.PersonService;

import java.util.List;

@RestController
// this annotation is used to mark a class as REST controller
// it will handle HTTP requests and responses
@RequestMapping("/api/persons")
@Validated
// this annotation is used to enable validation on the class
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public List<PersonDto> getPersons() {
        return personService.findAll();
    }


    @GetMapping("/{id}") // localhost:9091/api/persons/1
    @ResponseStatus(HttpStatus.OK) // 200 OK
    public PersonDto findById(@PathVariable("id")
                              @NotNull(message = "Id cannot be null")
                              @Positive(message = "Id must be a positive number")
                              Long id) {
        System.out.println(">>>>>>>> id = " + id); // 4
        return personService.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 Created
    public PersonDto createPerson(@RequestBody @Valid PersonDto personDto) {
        System.out.println("personDto = " + personDto);
        System.out.println(">>>>>>>>>> createPerson");
        return personService.createPerson(personDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content
    public void updatePerson(@PathVariable("id")
                             @NotNull(message = "Id cannot be null")
                             @Positive(message = "Id must be a positive number")
                             Long id,
                             @RequestBody
                             @Valid
                             PersonDto personDto) {

        personService.updatePerson(id, personDto);

    }

}
