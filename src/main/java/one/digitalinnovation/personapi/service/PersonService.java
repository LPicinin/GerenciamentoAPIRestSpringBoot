package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    
    private 

    public MessageResponseDTO createPerson(PersonDTO personDTO)
    {
        Person personToSave = Person.builder().build();
        Person savedPerson = personRepository.save(personDTO);
        return MessageResponseDTO.builder().message("Created person with ID "+savedPerson.getId()).build();
    }
}
