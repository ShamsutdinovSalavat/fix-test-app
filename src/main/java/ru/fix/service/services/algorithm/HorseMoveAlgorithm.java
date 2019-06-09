package ru.fix.service.services.algorithm;

public interface HorseMoveAlgorithm extends Algorithmic<Integer> {
    void setChessboard(int width, int height, Position start, Position end);
}
