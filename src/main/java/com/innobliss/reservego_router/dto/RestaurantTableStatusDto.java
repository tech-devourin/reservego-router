package com.innobliss.reservego_router.dto;

import java.util.List;

public class RestaurantTableStatusDto {
    private String rgRestaurantId;
    private List<TableStatusDetailDto> posTables;

    public String getRgRestaurantId() {
        return rgRestaurantId;
    }

    public void setRgRestaurantId(String rgRestaurantId) {
        this.rgRestaurantId = rgRestaurantId;
    }

    public List<TableStatusDetailDto> getPosTables() {
        return posTables;
    }

    public void setPosTables(List<TableStatusDetailDto> posTables) {
        this.posTables = posTables;
    }
}
