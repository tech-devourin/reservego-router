package com.innobliss.reservego_router.dto;

public class ReserveGoAdvancePaymentDto {
	private String rgGuestName;
	private String rgGuestPhoneNumber;
	private String rgGuestEmail;
	private Integer rgBookingType;
	private Integer rgPax;
	private String rgBookingDateTime;
	private String rgComments;
	private Double rgPrepaidAmount;
	private String rgPaymentMode;
	private String rgPaymentDateTime;
	private Boolean rgAdjustAgainstBill;
	private ReserveGoPaymentDetails rgPaymentDetails;
	private String rgRestaurantId;
	private String rgPrepaidTransactionId;
	private String rgBookingId;
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
	public String getRgBookingDateTime() {
		return rgBookingDateTime;
	}
	public void setRgBookingDateTime(String rgBookingDateTime) {
		this.rgBookingDateTime = rgBookingDateTime;
	}
	public String getRgComments() {
		return rgComments;
	}
	public void setRgComments(String rgComments) {
		this.rgComments = rgComments;
	}
	public Double getRgPrepaidAmount() {
		return rgPrepaidAmount;
	}
	public void setRgPrepaidAmount(Double rgPrepaidAmount) {
		this.rgPrepaidAmount = rgPrepaidAmount;
	}
	public String getRgPaymentMode() {
		return rgPaymentMode;
	}
	public void setRgPaymentMode(String rgPaymentMode) {
		this.rgPaymentMode = rgPaymentMode;
	}
	public String getRgPaymentDateTime() {
		return rgPaymentDateTime;
	}
	public void setRgPaymentDateTime(String rgPaymentDateTime) {
		this.rgPaymentDateTime = rgPaymentDateTime;
	}
	public Boolean getRgAdjustAgainstBill() {
		return rgAdjustAgainstBill;
	}
	public void setRgAdjustAgainstBill(Boolean rgAdjustAgainstBill) {
		this.rgAdjustAgainstBill = rgAdjustAgainstBill;
	}
	public ReserveGoPaymentDetails getRgPaymentDetails() {
		return rgPaymentDetails;
	}
	public void setRgPaymentDetails(ReserveGoPaymentDetails rgPaymentDetails) {
		this.rgPaymentDetails = rgPaymentDetails;
	}
	public String getRgRestaurantId() {
		return rgRestaurantId;
	}
	public void setRgRestaurantId(String rgRestaurantId) {
		this.rgRestaurantId = rgRestaurantId;
	}
	public String getRgPrepaidTransactionId() {
		return rgPrepaidTransactionId;
	}
	public void setRgPrepaidTransactionId(String rgPrepaidTransactionId) {
		this.rgPrepaidTransactionId = rgPrepaidTransactionId;
	}

	
	

}
