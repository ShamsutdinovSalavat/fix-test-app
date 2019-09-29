package ru.fix.algorithm.service;

import ru.fix.algorithm.model.Answer;
import ru.fix.algorithm.transfer.InputDto;

public interface HorseStepAlgorithmService {
    Answer getAnswer(InputDto inputDto);
}
