package com.innobliss.reservego_router.dto;

import java.util.List;

public class RestaurantTableLayoutDto {

    private String rgRestaurantId;
    private List<TableLayoutDetailDto> tables;

    public String getRgRestaurantId() {
        return rgRestaurantId;
    }

    public void setRgRestaurantId(String rgRestaurantId) {
        this.rgRestaurantId = rgRestaurantId;
    }

    public List<TableLayoutDetailDto> getTables() {
        return tables;
    }

    public void setTables(List<TableLayoutDetailDto> tables) {
        this.tables = tables;
    }
}
