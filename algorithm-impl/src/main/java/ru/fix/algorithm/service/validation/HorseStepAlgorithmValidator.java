package ru.fix.algorithm.service.validation;

import org.springframework.stereotype.Component;
import ru.fix.algorithm.transfer.InputDto;

@Component
public class HorseStepAlgorithmValidator implements Validation {

    @Override
    public boolean isValid(InputDto inputDto) {
        return false;
    }
}
