package com.example.crud.config;

import java.io.IOException;
import java.util.List;

import com.example.crud.domain.users.JsonResponse;
import com.example.crud.domain.users.Users;
import com.example.crud.domain.users.UsersRepository;
import com.example.crud.service.CsvToJsonConverter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.crud.domain.users.RequestUsers;
import com.example.crud.service.DataLoader;

@Component
public class ApplicationStartupConfig {

    private final DataLoader dataService;
    @Autowired
    private UsersRepository repository;

    @Autowired
    private CsvToJsonConverter csvToJsonConverter;

    public ApplicationStartupConfig(DataLoader dataService) {
        this.dataService = dataService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadDataOnStartup() {
        try {
            String jsonUrl = "https://storage.googleapis.com/juntossomosmais-code-challenge/input-backend.json";
            String jsonData = dataService.loadJsonData(jsonUrl);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonResponse jsonResponse = objectMapper.readValue(jsonData, JsonResponse.class);
            List<RequestUsers> requestUsersList = jsonResponse.getResults();

            for (RequestUsers data : requestUsersList) {
                Users newUsers = new Users(data);
                newUsers.setId(data.id());
                newUsers.setType(data.getType());
                newUsers.setGender(data.gender());
                newUsers.setName_title(data.name().title());
                newUsers.setName_first(data.name().first());
                newUsers.setName_last(data.name().last());
                newUsers.setLocation_region(data.getRegion());
                newUsers.setLocation_street(data.location().street());
                newUsers.setLocation_city(data.location().city());
                newUsers.setLocation_state(data.location().state());
                newUsers.setLocation_postcode(data.location().postcode());
                newUsers.setLocation_coordinates_latitude(data.location().coordinates().latitude());
                newUsers.setLocation_coordinates_longitude(data.location().coordinates().longitude());
                newUsers.setLocation_timezone_offset(data.location().timezone().offset());
                newUsers.setLocation_timezone_description(data.location().timezone().description());
                newUsers.setEmail(data.email());
                newUsers.setBirthday(data.dob().birthday());
                newUsers.setRegistered(data.registered().registered());
                newUsers.setTelephone_numbers(data.getTelephoneNumbersE164());
                newUsers.setMobile_numbers(data.getMobileNumbersE164());
                newUsers.setPicture_large(data.picture().large());
                newUsers.setPicture_medium(data.picture().medium());
                newUsers.setPicture_thumbnail(data.picture().thumbnail());
                repository.save(newUsers);
            }

            System.out.println("Dados JSON processados e inseridos no banco de dados com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao processar o JSON e inserir no banco de dados: " + e.getMessage());
        }
    }
}