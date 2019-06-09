package ru.fix.service.services.algorithm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Position {

    protected static final Integer INF = Integer.MAX_VALUE;

    private int x;
    private int y;
    private Integer move;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position))
            return false;
        else {
            Position pos = (Position) obj;

            if (pos.getMove() == null || pos.getMove() == INF) {
                if (this.x == pos.getX() && this.y == pos.getY())
                    return true;
                else
                    return false;
            } else {
                if (this.x == pos.getX() && this.y == pos.getY() && this.move == pos.getMove())
                    return true;
                else
                    return false;
            }
        }
    }
}
