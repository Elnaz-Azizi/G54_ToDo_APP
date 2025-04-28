package se.lexicon.todo_app.service.impl;

import org.springframework.stereotype.Service;
import se.lexicon.todo_app.dto.PersonDto;
import se.lexicon.todo_app.entity.Person;
import se.lexicon.todo_app.repository.PersonRepository;
import se.lexicon.todo_app.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    // inject the person repository
    private PersonRepository personRepository;

    // constructor injection
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonDto> findAll() {

        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(
                        person ->
                                new PersonDto(
                                        person.getId(),
                                        person.getName(),
                                        person.getEmail()))
                .toList();

    }

    @Override
    public PersonDto findById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person id not found"));
        return new PersonDto(person.getId(), person.getName(), person.getEmail());
    }

    @Override
    public PersonDto createPerson(PersonDto personDto) {
        Person toEntity = new Person(personDto.name(), personDto.email());
        Person createdPerson = personRepository.save(toEntity);
        return new PersonDto(createdPerson.getId(), createdPerson.getName(), createdPerson.getEmail());
    }

    @Override
    public void updatePerson(Long id, PersonDto personDto) {
        Person original = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Person id not found"));

        original.setName(personDto.name()); //
        original.setEmail(personDto.email()); //
        personRepository.save(original);
    }


}
