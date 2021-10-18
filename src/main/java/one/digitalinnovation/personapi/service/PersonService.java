package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public MessageResponseDTO createPerson(Person person)
    {
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO.builder().message("Created person with ID "+savedPerson.getId()).build();
    }
}
