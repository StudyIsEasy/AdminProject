package com.example.study.service;

import com.example.study.StudyApplicationTests;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.logic.UserLogicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class UserLogicServiceTest extends StudyApplicationTests {

    @Autowired
    private UserLogicService userLogicService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void create(){
        UserApiRequest userApiRequest = UserApiRequest.builder()
                .account("CreateTest01")
                .password("CreateTest01")
                .status("UNREGISTERED")
                .phoneNumber("010-1234-5678")
                .email("CreateTest01@GMAIL.COM")
                .build();


        try {
            Header<UserApiRequest> request = Header.OK(userApiRequest);
            String requestStr = objectMapper.writeValueAsString(request);
            log.info("req : {}",requestStr);


            Header<UserApiResponse> response = userLogicService.create(request);
            String responseStr = objectMapper.writeValueAsString(response);
            log.info("res : {}",responseStr);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
