package ru.fix.service.services.algorithm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Position {

    protected static final Integer INF = Integer.MAX_VALUE;

    @NonNull
    private int x;
    @NonNull
    private int y;
    private Integer move;

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
