package ru.fix.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.fix.service.services.HorseMoveService;
import ru.fix.service.transfer.ParametersDto;

@RestController
public class HorseMoveController {

    @Autowired
    private HorseMoveService horseMoveService;

    @GetMapping("/horse/rest/count")
    public ResponseEntity<Integer> getCountOfHorseMove(@RequestParam("width") Integer width,
                                                      @RequestParam("height") Integer height,
                                                      @RequestParam("start") String start,
                                                      @RequestParam("end") String end) {
        try {
            Integer value = horseMoveService.getValue(
                    ParametersDto.builder()
                            .width(width)
                            .heigth(height)
                            .start(start)
                            .end(end).build());

            return ResponseEntity.ok(value);
        } catch (IllegalArgumentException exc) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong parameters", exc);
        }
    }
}
