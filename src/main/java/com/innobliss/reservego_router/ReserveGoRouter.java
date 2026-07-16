package com.innobliss.reservego_router;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservego_mapping")
public class ReserveGoRouter {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	
	    private String rgRestaurantId;

	    private Integer branchId;

	    private String appName;

	    private String baseUrl;
	    
	    public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getRgRestaurantId() {
	        return rgRestaurantId;
	    }

	    public void setRgRestaurantId(String rgRestaurantId) {
	        this.rgRestaurantId = rgRestaurantId;
	    }

	    public Integer getBranchId() {
	        return branchId;
	    }

	    public void setBranchId(Integer branchId) {
	        this.branchId = branchId;
	    }

	    public String getAppName() {
	        return appName;
	    }

	    public void setAppName(String appName) {
	        this.appName = appName;
	    }

	    public String getBaseUrl() {
	        return baseUrl;
	    }

	    public void setBaseUrl(String baseUrl) {
	        this.baseUrl = baseUrl;
	    }
}
