package ru.fix.algorithm.service.hsalg;

import org.springframework.stereotype.Component;
import ru.fix.algorithm.transfer.InputDto;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class HorseStepAlgorithmImpl implements HorseStepAlgorithm {

    private Integer width;
    private Integer height;
    private Position start;
    private Position end;

    @Override
    public Integer getAnswer(InputDto inputDto) {

        this.width = Integer.valueOf(inputDto.getWidth());
        this.height = Integer.valueOf(inputDto.getHeight());
        this.start = Position.from(inputDto.getStart());
        this.end = Position.from(inputDto.getEnd());

        return calculate();
    }


    /*
    *
    * @return -1 if end position is't reachable,
    *          0 if start equals end,
    *          count if end position is reachable
    *
    * */
    private int calculate() {
        if (start.equals(end)) {
            return 0;
        }

        Set<Position> visitedPos = new HashSet<>();
        List<Position> nextPositions;
        LinkedList<Position> currentPositions = new LinkedList<>();
        List<Position> positions = new LinkedList<>();
        positions.add(start);
        int count = 0;

        while (!positions.isEmpty()) {
            currentPositions.addAll(positions);
            positions.clear();
            count++;
            while (!currentPositions.isEmpty()) {
                Position current = currentPositions.pollFirst();
                nextPositions = getNextPositions(current, visitedPos);
                if (nextPositions.contains(end)) {
                    return count;
                } else {
                    visitedPos.addAll(nextPositions);
                    positions.addAll(nextPositions);
                    nextPositions.clear();
                }
            }
        }

        return -1;
    }

    private List<Position> getNextPositions(Position current, final Set<Position> visitedPos) {
        List<Position> positions = new ArrayList<>();
        positions.add(new Position(current.getX() + 2, current.getY() + 1));
        positions.add(new Position(current.getX() + 2, current.getY() - 1));
        positions.add(new Position(current.getX() + 1, current.getY() + 2));
        positions.add(new Position(current.getX() - 1, current.getY() + 2));
        positions.add(new Position(current.getX() - 2, current.getY() + 1));
        positions.add(new Position(current.getX() - 2, current.getY() - 1));
        positions.add(new Position(current.getX() + 1, current.getY() - 2));
        positions.add(new Position(current.getX() - 1, current.getY() - 2));

        return positions.stream()
                .filter(p -> p.getX() <= width && p.getY() <= height &&
                             p.getX() > 0 && p.getY() > 0)
                .filter(p -> !visitedPos.contains(p))
                .collect(Collectors.toList());
    }
}
