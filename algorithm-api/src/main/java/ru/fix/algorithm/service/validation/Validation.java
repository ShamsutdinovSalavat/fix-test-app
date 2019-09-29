package ru.fix.algorithm.service.validation;

import ru.fix.algorithm.transfer.InputDto;

public interface Validation {
    boolean isValid(InputDto inputDto);
}
