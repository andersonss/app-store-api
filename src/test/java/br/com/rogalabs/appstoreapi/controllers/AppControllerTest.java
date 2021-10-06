package br.com.rogalabs.appstoreapi.controllers;

import br.com.rogalabs.appstoreapi.controllers.dto.AppRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Anderson on 06/10/2021
 * @project app-store-api
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppControllerTest {

    public static final String ROOT_URL  = "http://localhost:";

    @LocalServerPort
    int randomServerPort;

    @Test
    public void testAddNewAppSuccess() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = ROOT_URL + randomServerPort + "/app/";
        URI uri = new URI(baseUrl);
        AppRequest appRequest = new AppRequest();
        appRequest.setName("AppCheap");
        appRequest.setDescription("A cheap app");
        appRequest.setPrice(1);

        HttpEntity<AppRequest> request = new HttpEntity<>(appRequest);

        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCodeValue());
    }

}