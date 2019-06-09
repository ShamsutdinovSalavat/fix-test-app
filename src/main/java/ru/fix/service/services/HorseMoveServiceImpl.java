package ru.fix.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fix.service.services.algorithm.HorseMoveAlgorithm;
import ru.fix.service.services.algorithm.Position;
import ru.fix.service.transfer.ParametersDto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class HorseMoveServiceImpl implements HorseMoveService {

    @Autowired
    private HorseMoveAlgorithm algorithmic;

    public Integer getValue(ParametersDto params) {
        Integer width = params.getWidth();
        Integer hight = params.getHeigth();
        String start = params.getStart();
        String end = params.getEnd();

        Map<String, Position> posMap;
        Integer value;

        if (isValid(width, hight, start, end)) {
            posMap = adoptData(start.toLowerCase(), end.toLowerCase());

            algorithmic.setChessboard(width, hight, posMap.get("start"), posMap.get("end"));
            value = algorithmic.getValue().orElse(-1);

            return value;
        } else {
            throw new IllegalArgumentException("Parameters is not valid");
        }
    }

    private boolean isValid(Integer width, Integer height, String start, String end) {

        if (Objects.isNull(width) || Objects.isNull(height) ||
                Objects.isNull(start) || Objects.isNull(end)) {
            return false;
        } else {
            if (width > 0 && height > 0 && isValidString(start) && isValidString(end)) {

                int xStart = start.toLowerCase().codePointAt(0) - 'a';
                int yStart = Integer.valueOf(start.substring(1)) - 1;

                int xEnd = end.toLowerCase().codePointAt(0) - 'a';
                int yEnd = Integer.valueOf(end.substring(1)) - 1;

                if ((xStart < width && xEnd < width) &&
                        (yEnd < height && yStart < height))
                    return true;
                else
                    return false;
            } else
                return false;
        }
    }

    private boolean isValidString(String str) {
        return str.chars()
                .allMatch(sign -> (sign >= 'a' && sign <= 'z') ||
                        (sign >= 'A' && sign <= 'Z') ||
                        (sign >= '1' && sign <= '9'));
    }

    private Map<String, Position> adoptData(String start, String end) {
        Map<String, Position> positionMap = new HashMap<>();
        Integer x;
        Integer y;

        x = start.codePointAt(0) - 97;
        y = Integer.valueOf(start.substring(1)) - 1;
        positionMap.put("start", new Position(x, y));

        x = end.codePointAt(0) - 97;
        y = Integer.valueOf(end.substring(1)) - 1;
        positionMap.put("end", new Position(x, y));

        return positionMap;
    }
}
