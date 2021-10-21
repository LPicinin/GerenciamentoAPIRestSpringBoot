package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public MessageResponseDTO createPerson(PersonDTO personDTO)
    {
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        MessageResponseDTO messageResponse = createMessageResponse("Person successfully created with ID ", savedPerson.getId());
        return messageResponse;
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                    .collect(Collectors.toList());//mapeia todos os objets pra uma lista de DTO
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExistis(id);
        return personMapper.toDTO(person);
    }

    public void delete(Long id) throws PersonNotFoundException {
        Person person = verifyIfExistis(id);
        personRepository.delete(person);
    }
    
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExistis(id);
        Person person = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(person);
        MessageResponseDTO messageResponse = createMessageResponse("Person successfully update with ID ", savedPerson.getId());
        return messageResponse;
    }

    private Person verifyIfExistis(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(String s, Long id2) {
        return MessageResponseDTO.builder()
                .message(s + id2)
                .build();
    }
}
