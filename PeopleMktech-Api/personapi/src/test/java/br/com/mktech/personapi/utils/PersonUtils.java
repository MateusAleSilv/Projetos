package br.com.mktech.personapi.utils;

import br.com.mktech.personapi.dto.request.PersonDTO;
import br.com.mktech.personapi.entity.Person;

import java.time.LocalDate;
import java.util.Collections;

public class PersonUtils {

    private static final String FIRST_NAME = "Mateus";
    private static final String LAST_NAME = "SIlva";
    private static final String CPG_NUMBER = "109.555.666-45";
    private static final long  PERSON_ID = 1L;
    public static final LocalDate BIRTH_DATE = LocalDate.of(2001, 03, 22);

    public static PersonDTO createFakeDTO() {
        return PersonDTO.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPG_NUMBER)
                .birthDate("22-04-2001")
                .phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
                .build();
    }

    public static Person createFakeEntity(){
        return Person.builder()
                .id(PERSON_ID)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .cpf(CPG_NUMBER)
                .birthDate(BIRTH_DATE)
                .phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
                .build();
    }
}
