package com.innobliss.reservego_router.dto;

import java.util.List;

public class FetchalltablesstatusDto {
	  private String rgRestaurantId;

	    private List<PosTableDto> posTables;

	    public String getRgRestaurantId() {
	        return rgRestaurantId;
	    }

	    public void setRgRestaurantId(String rgRestaurantId) {
	        this.rgRestaurantId = rgRestaurantId;
	    }

	    public List<PosTableDto> getPosTables() {
	        return posTables;
	    }

	    public void setPosTables(List<PosTableDto> posTables) {
	        this.posTables = posTables;
	    }

	    public static class PosTableDto {

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

}
