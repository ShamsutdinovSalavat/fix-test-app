package ru.fix.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fix.service.application.Application;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class ServletTest {

    private final String BAD_REQUEST = "/horse/servlet/count?width=0&height=14&start=B1";
    private final String CORRECT_REQUEST = "/horse/servlet/count?width=10&height=14&start=C1&end=A3";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnStatusOk() {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://localhost:" + port + CORRECT_REQUEST, String.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void shouldReturnStatusBadRequest() {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://localhost:" + port + BAD_REQUEST, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void shouldReturnValue() {
        ResponseEntity<String> responseEntity =
                restTemplate.getForEntity("http://localhost:" + port + CORRECT_REQUEST, String.class);

        assertEquals(String.valueOf(4), responseEntity.getBody());
    }

}
