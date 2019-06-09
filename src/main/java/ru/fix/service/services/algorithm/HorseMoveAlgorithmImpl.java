package ru.fix.service.services.algorithm;

import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Setter
public class HorseMoveAlgorithmImpl implements HorseMoveAlgorithm {

    private Position start;
    private Position end;
    private List<Position> chessboard;

    public void setChessboard(int width, int height, Position start, Position end) {
        this.start = start;
        this.end = end;

        chessboard = IntStream.range(0, width).boxed().map(i ->
                IntStream.range(0, height).boxed().map(j ->
                        start.getX() == i && start.getY() == j
                                ? new Position(i, j, 0)
                                : new Position(i, j, Position.INF))
                        .collect(Collectors.toList()))
                .collect(Collector.of(
                        ArrayList::new,
                        (result, list) -> result.addAll(list),
                        (list1, list2) -> {
                            list1.addAll(list2);
                            return list1;
                        }
                ));
    }

    private List<Position> getNextPostitions(Position currentPosition) {
        List<Position> positions = new ArrayList<>();

        int currentX = currentPosition.getX();
        int currentY = currentPosition.getY();

        positions.add(new Position(currentX + 2, currentY + 1));
        positions.add(new Position(currentX + 2, currentY - 1));
        positions.add(new Position(currentX - 2, currentY + 1));
        positions.add(new Position(currentX - 2, currentY - 1));
        positions.add(new Position(currentX + 1, currentY + 2));
        positions.add(new Position(currentX - 1, currentY + 2));
        positions.add(new Position(currentX + 1, currentY - 2));
        positions.add(new Position(currentX - 1, currentY - 2));

        return positions.stream()
                .filter(this::isExist)
                .collect(Collectors.toList());
    }

    private boolean isExist(Position position) {
        return chessboard.parallelStream()
                .anyMatch(pos -> pos.equals(position));
    }

    private boolean reachToEnd() {
        return chessboard.parallelStream()
                .filter(pos -> pos.equals(end))
                .anyMatch(pos -> pos.getMove() != Position.INF);
    }

    private boolean saveCurrentMove(int move, List<Position> positions) {
        List<Position> modifiedPositions = chessboard.parallelStream()
                .filter(pos -> positions.contains(pos))
                .filter(pos -> pos.getMove() > move)
                .map(pos -> new Position(pos.getX(), pos.getY(), move))
                .collect(Collectors.toList());

        if (!modifiedPositions.isEmpty()) {
            chessboard = chessboard.parallelStream()
                    .map(pos -> {
                        for (Position position : modifiedPositions) {
                            if (position.equals(pos))
                                return position;
                        }
                        return pos;
                    })
                    .collect(Collectors.toList());
            return true;
        } else
            return false;
    }

    private List<Position> getPositionsWithCurrentMove(int move) {
        return chessboard.parallelStream()
                .filter(pos -> pos.getMove() == move)
                .collect(Collectors.toList());
    }

    private void count() {
        boolean hasModified = true;
        int move = 1;
        Set<Boolean> booleanSet;

        List<Position> nextPositions = new ArrayList<>();
        Position startPosition = new Position(start.getX(), start.getY(), 0);
        nextPositions.add(startPosition);

        while (!reachToEnd() && hasModified) {
            booleanSet = nextPositions.stream()
                    .map(position ->
                            saveCurrentMove(position.getMove() + 1,
                                    getNextPostitions(position)))
                    .collect(Collectors.toSet());
            hasModified = booleanSet.contains(true);

            nextPositions = getPositionsWithCurrentMove(move);
            move++;
        }
    }

    public Optional<Integer> getValue() {
        this.count();

        Optional<Integer> value = chessboard.parallelStream()
                .filter(position -> position.equals(end))
                .map(Position::getMove)
                .findFirst();

        return value.map(val ->
                val != Integer.MAX_VALUE ?
                        val : null);
    }
}
