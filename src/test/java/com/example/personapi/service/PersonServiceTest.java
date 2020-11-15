package com.example.personapi.service;

import com.example.personapi.dto.request.PersonDTO;
import com.example.personapi.dto.response.MessageResponseDTO;
import com.example.personapi.entity.Person;
import com.example.personapi.repository.PersonRepository;
import com.example.personapi.utils.PersonUtils;
import com.example.personapi.utils.PersonUtils.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSavedMessage() {
        PersonDTO personDTO = PersonUtils.createFakeDTO();
        Person expectedSavedPerson = PersonUtils.createFakeEntity();

        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedSuccessMessage, successMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
