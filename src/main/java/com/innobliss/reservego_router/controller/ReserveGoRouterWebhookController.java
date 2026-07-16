package com.innobliss.reservego_router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.innobliss.reservego_router.dto.ReserveGoAdvancePaymentDto;
import com.innobliss.reservego_router.dto.SeatCustomerRequestDto;
import com.innobliss.reservego_router.service.ReserveGoRouterSvcImpl;

@RestController
@RequestMapping("/devourin/reservGo/v1")
public class ReserveGoRouterWebhookController {
	
	@Autowired
	ReserveGoRouterSvcImpl svc;

	@GetMapping("/fetchalltables")
	public ResponseEntity<?> fetchAllTables(@RequestParam("rgRestaurantId") String rgRestaurantId) {

		try {
			return ResponseEntity.ok(svc.fetchAllTables(rgRestaurantId));

		} catch (Exception e) {

			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@GetMapping("/fetchalltablesstatus")

	public ResponseEntity<?> fetchalltablestatus(@RequestParam("rgRestaurantId") String rgRestaurantId) {
		try {
			return ResponseEntity.ok(svc.fetchalltablesstatus(rgRestaurantId));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@GetMapping("/fetchstatusbytables")

	public ResponseEntity<?> fetchstatusesbytables(@RequestParam("rgRestaurantId") String rgRestaurantId,
			@RequestParam("posTableIds") String posTableIds) {
		try {
			return ResponseEntity.ok(svc.fetchstatusesbytables(rgRestaurantId, posTableIds));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@PostMapping("/seatCustomer")
	public ResponseEntity<?> seatCustomer(@RequestParam("rgRestaurantId") String rgRestaurantId,
			@RequestBody SeatCustomerRequestDto requestDto) {

		try {
			return ResponseEntity.ok(svc.seatcustomer(rgRestaurantId, requestDto));

		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@PostMapping("/advancePayment")
	public ResponseEntity<?> advancePayment(@RequestParam("rgRestaurantId") String rgRestaurantId,
			@RequestBody ReserveGoAdvancePaymentDto requestDto) {
		try {
			return ResponseEntity.ok(svc.advancepayment(rgRestaurantId, requestDto));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}