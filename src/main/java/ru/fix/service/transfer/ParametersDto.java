package ru.fix.service.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParametersDto {
    private Integer width;
    private Integer heigth;
    private String start;
    private String end;
}
