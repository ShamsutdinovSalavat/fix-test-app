package ru.fix.algorithm.service;

import org.springframework.stereotype.Service;
import ru.fix.algorithm.model.Answer;
import ru.fix.algorithm.service.hsalg.HorseStepAlgorithm;
import ru.fix.algorithm.service.validation.Validation;
import ru.fix.algorithm.transfer.InputDto;

import static ru.fix.algorithm.config.AppConfig.ERROR_MESSAGE;

@Service
public class HorseStepAlgorithmServiceImpl implements HorseStepAlgorithmService {

    private Validation validation;
    private HorseStepAlgorithm algorithm;

    public HorseStepAlgorithmServiceImpl(Validation validation, HorseStepAlgorithm algorithm) {
        this.validation = validation;
        this.algorithm = algorithm;
    }

    @Override
    public Answer getAnswer(InputDto inputDto) {
        Answer answer = new Answer();

        if (validation.isValid(inputDto)) {
            Integer ans = algorithm.getAnswer(inputDto);
            answer.setAnswer(ans.toString());
        } else {
            answer.setAnswer(ERROR_MESSAGE);
        }

        return answer;
    }
}
