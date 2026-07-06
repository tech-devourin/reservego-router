package com.innobliss.reservego_router.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/devourin/reservGo/v1")
public class ReserveGoRouterWebhookController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReserveGoRouterWebhookController.class);
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

	@PostMapping("/swaptables")
	public ResponseEntity<?> swapTables(@RequestBody Object requestDto) {
	    try {
	        return ResponseEntity.ok(svc.swapTables(requestDto));
	    } catch (Exception e) {
	        return ResponseEntity.internalServerError().body(e.getMessage());
	    }
	}

	@PostMapping("/invoice")
	public ResponseEntity<?> invoice(@RequestBody Object requestDto) {
		try {
			return ResponseEntity.ok(svc.invoice(requestDto));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	
	@PostMapping("/updateTableDetails")
	public ResponseEntity<?> updateTableDetails(@RequestBody Object requestDto) {
		try {
			return ResponseEntity.ok(svc.updateTableDetails(requestDto));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

	@PostMapping("/updateTableSectionDetails")
	public ResponseEntity<?> updateTableSectionDetails(@RequestBody Object requestDto) {
		try {
			return ResponseEntity.ok(svc.updateTableSectionDetails(requestDto));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}
	

		@PostMapping(value = "/updateTableStatus", produces = "application/json")
		public ResponseEntity<?> updateTableStatus(@RequestBody Map<String, Object> requestDto) {
			try {
				logger.info("Router received updateTableStatus from Nebula POS: {}", requestDto);
				Object response = svc.updateTableStatus(requestDto);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch (Exception e) {
				logger.error("Router error routing updateTableStatus: {}", e.getMessage());
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

	@PostMapping("/voidTable")
	public ResponseEntity<?> voidTable(@RequestBody Object requestDto) {
		try {
			return ResponseEntity.ok(svc.voidTable(requestDto));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}