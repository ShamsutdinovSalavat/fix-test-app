package ru.fix.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.fix.service.application.Application;
import ru.fix.service.services.HorseMoveService;
import ru.fix.service.transfer.ParametersDto;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
public class ServiceLayerTest {

    @Autowired
    private HorseMoveService service;

    public ServiceLayerTest() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException() {
        assertEquals(Integer.valueOf(0),
                service.getValue(new ParametersDto(10, -1, "B3", "A1")));
    }

    @Test
    public void shouldReturnExpectedValue() {
        assertEquals(Integer.valueOf(1),
                service.getValue(new ParametersDto(10, 14, "B3", "A1")));
    }

    @Test
    public void shouldReturnMinusOne() {
        assertEquals(Integer.valueOf(-1),
                service.getValue(new ParametersDto(1, 14, "A1", "A2")));
    }
}
