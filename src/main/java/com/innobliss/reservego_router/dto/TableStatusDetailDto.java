package com.innobliss.reservego_router.dto;

public class TableStatusDetailDto {

    private String posTableId;
    private String posTableName;
    private String posTableStatus;

    public String getPosTableId() {
        return posTableId;
    }

    public void setPosTableId(String posTableId) {
        this.posTableId = posTableId;
    }

    public String getPosTableName() {
        return posTableName;
    }

    public void setPosTableName(String posTableName) {
        this.posTableName = posTableName;
    }

    public String getPosTableStatus() {
        return posTableStatus;
    }

    public void setPosTableStatus(String posTableStatus) {
        this.posTableStatus = posTableStatus;
    }
}
