package ru.fix.service.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.fix.service.application.Application;
import ru.fix.service.services.HorseMoveService;
import ru.fix.service.transfer.ParametersDto;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest()
@ContextConfiguration(classes = {Application.class})
public class ControllerLayerTest {

    private final String BAD_REQUEST = "/horse/rest/count?width=0&height=14&start=B1&end=A3";
    private final String CORRECT_REQUEST = "/horse/rest/count?width=10&height=14&start=B1&end=A3";

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldReturnStatusOk() throws Exception {
        mvc.perform(get(CORRECT_REQUEST)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusBadRequest() throws Exception {
        mvc.perform(get(BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

