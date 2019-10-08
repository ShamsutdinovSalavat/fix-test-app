package ru.fix.algorithm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.fix.algorithm.model.Answer;
import ru.fix.algorithm.service.HorseStepAlgorithmService;
import ru.fix.algorithm.transfer.InputDto;

import java.util.Optional;

import static ru.fix.algorithm.config.AppConfig.API_PATH;

@RestController
@RequestMapping(API_PATH)
public class HorseStepAlgorithmController {

	private HorseStepAlgorithmService service;

	public HorseStepAlgorithmController(HorseStepAlgorithmService service) {
		this.service = service;
	}

	@GetMapping("/hsalg")
	public ResponseEntity<Answer> algorithm(InputDto inputDto) {
		Answer answer = service.getAnswer(inputDto);

		return ResponseEntity.of(Optional.of(answer));
	}
}
