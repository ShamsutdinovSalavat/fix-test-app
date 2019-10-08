package ru.fix.algorithm.service.validation;

import org.springframework.stereotype.Component;
import ru.fix.algorithm.transfer.InputDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HorseStepAlgorithmValidator implements Validation {

    private final String NUMERIC_PATTERN = "[1-8]";
    private final String POSITION_PATTERN = "[A-Ha-h][1-8]";

    @Override
    public boolean isValid(InputDto inputDto) {
        if (hasNullField(inputDto)) {
            return false;
        }

        return isValidEnd(inputDto.getEnd()) &&
                isValidHeight(inputDto.getHeight()) &&
                isValidStart(inputDto.getStart()) &&
                isValidWidth(inputDto.getWidth());
    }

    private boolean hasNullField(InputDto inputDto) {
        return inputDto.getEnd() == null ||
                inputDto.getHeight() == null ||
                inputDto.getStart() == null ||
                inputDto.getEnd() == null;
    }

    private boolean isValidHeight(String height) {
        return Pattern.compile(NUMERIC_PATTERN).matcher(height).matches();
    }

    private boolean isValidWidth(String width) {
        return Pattern.compile(NUMERIC_PATTERN).matcher(width).matches();
    }

    private boolean isValidStart(String start) {
        return Pattern.compile(POSITION_PATTERN).matcher(start).matches();
    }

    private boolean isValidEnd(String end) {
        return Pattern.compile(POSITION_PATTERN).matcher(end).matches();
    }


}
