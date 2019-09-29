package ru.fix.algorithm.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fix.algorithm.service.MyService;

import static ru.fix.algorithm.config.AppConfig.API_PATH;

@RestController
@RequestMapping(API_PATH)
public class SimpleController {

    @GetMapping("/simple")
    public ResponseEntity<String> getSimpleAnswer() {
        MyService service = new MyService();
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getString());
    }
}
