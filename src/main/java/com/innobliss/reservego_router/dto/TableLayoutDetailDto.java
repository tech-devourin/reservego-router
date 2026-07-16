package com.innobliss.reservego_router.dto;

public class TableLayoutDetailDto {

    private String posTableId;
    private String posTableName;
    private Integer posTableStatus;
    private String posTableCapacity; // Optional
    private String posSectionId;
    private String posSectionName;
    private Integer posTablePosition; // Optional
    private Integer posTabletype; // Optional

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

    public Integer getPosTableStatus() {
        return posTableStatus;
    }

    public void setPosTableStatus(Integer posTableStatus) {
        this.posTableStatus = posTableStatus;
    }

    public String getPosTableCapacity() {
        return posTableCapacity;
    }

    public void setPosTableCapacity(String posTableCapacity) {
        this.posTableCapacity = posTableCapacity;
    }

    public String getPosSectionId() {
        return posSectionId;
    }

    public void setPosSectionId(String posSectionId) {
        this.posSectionId = posSectionId;
    }

    public String getPosSectionName() {
        return posSectionName;
    }

    public void setPosSectionName(String posSectionName) {
        this.posSectionName = posSectionName;
    }

    public Integer getPosTablePosition() {
        return posTablePosition;
    }

    public void setPosTablePosition(Integer posTablePosition) {
        this.posTablePosition = posTablePosition;
    }

    public Integer getPosTabletype() {
        return posTabletype;
    }

    public void setPosTabletype(Integer posTabletype) {
        this.posTabletype = posTabletype;
    }
}
