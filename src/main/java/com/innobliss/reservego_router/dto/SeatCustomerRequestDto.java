package com.innobliss.reservego_router.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SeatCustomerRequestDto {
	
	private String rgRestaurantId;

	private String rgBookingId;

	private String rgGuestName;

	private String rgGuestPhoneNumber;

	private String rgGuestEmail;

	private List<String> posTableIds;

	private Integer rgBookingType;

	private Integer rgPax;

	private String rgComments;

	private String rgSeatedTime;

	private Double rgPrepaidAmount;

	private String rgPrepaidTransactionId;

	private String rgPaymentMode;

	private Boolean rgAdjustAgainstBill;

	public String getRgRestaurantId() {
		return rgRestaurantId;
	}

	public void setRgRestaurantId(String rgRestaurantId) {
		this.rgRestaurantId = rgRestaurantId;
	}

	public String getRgBookingId() {
		return rgBookingId;
	}

	public void setRgBookingId(String rgBookingId) {
		this.rgBookingId = rgBookingId;
	}

	public String getRgGuestName() {
		return rgGuestName;
	}

	public void setRgGuestName(String rgGuestName) {
		this.rgGuestName = rgGuestName;
	}

	public String getRgGuestPhoneNumber() {
		return rgGuestPhoneNumber;
	}

	public void setRgGuestPhoneNumber(String rgGuestPhoneNumber) {
		this.rgGuestPhoneNumber = rgGuestPhoneNumber;
	}

	public String getRgGuestEmail() {
		return rgGuestEmail;
	}

	public void setRgGuestEmail(String rgGuestEmail) {
		this.rgGuestEmail = rgGuestEmail;
	}

	public List<String> getPosTableIds() {
		return posTableIds;
	}

	public void setPosTableIds(List<String> posTableIds) {
		this.posTableIds = posTableIds;
	}

	public Integer getRgBookingType() {
		return rgBookingType;
	}

	public void setRgBookingType(Integer rgBookingType) {
		this.rgBookingType = rgBookingType;
	}

	public Integer getRgPax() {
		return rgPax;
	}

	public void setRgPax(Integer rgPax) {
		this.rgPax = rgPax;
	}

	public String getRgComments() {
		return rgComments;
	}

	public void setRgComments(String rgComments) {
		this.rgComments = rgComments;
	}

	public String getRgSeatedTime() {
		return rgSeatedTime;
	}

	public void setRgSeatedTime(String rgSeatedTime) {
		this.rgSeatedTime = rgSeatedTime;
	}

	public Double getRgPrepaidAmount() {
		return rgPrepaidAmount;
	}

	public void setRgPrepaidAmount(Double rgPrepaidAmount) {
		this.rgPrepaidAmount = rgPrepaidAmount;
	}

	public String getRgPrepaidTransactionId() {
		return rgPrepaidTransactionId;
	}

	public void setRgPrepaidTransactionId(String rgPrepaidTransactionId) {
		this.rgPrepaidTransactionId = rgPrepaidTransactionId;
	}

	public String getRgPaymentMode() {
		return rgPaymentMode;
	}

	public void setRgPaymentMode(String rgPaymentMode) {
		this.rgPaymentMode = rgPaymentMode;
	}

	public Boolean getRgAdjustAgainstBill() {
		return rgAdjustAgainstBill;
	}

	public void setRgAdjustAgainstBill(Boolean rgAdjustAgainstBill) {
		this.rgAdjustAgainstBill = rgAdjustAgainstBill;
	}


	
	
}