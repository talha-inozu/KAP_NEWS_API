package com.nirengi.kapnews.user.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StockCodeRequest {

    @NotNull
    private List<String> stockCodes;
}
