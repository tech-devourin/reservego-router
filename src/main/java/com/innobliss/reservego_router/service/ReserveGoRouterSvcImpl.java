package com.innobliss.reservego_router.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.innobliss.reservego_router.ReserveGoRouterPojo;
import com.innobliss.reservego_router.ReserveGoRouterRepository;
import com.innobliss.reservego_router.ReserveGoWebhookLog;
import com.innobliss.reservego_router.ReserveGoWebhookLogRepository;
import com.innobliss.reservego_router.controller.ReserveGoRouterWebhookController;
import com.innobliss.reservego_router.dto.ReserveGoAdvancePaymentDto;
import com.innobliss.reservego_router.dto.SeatCustomerRequestDto;

@Service
public class ReserveGoRouterSvcImpl {

	@Autowired
	ReserveGoRouterRepository repo;

	@Autowired
	ReserveGoWebhookLogRepository logRepo;
	@Autowired
	com.fasterxml.jackson.databind.ObjectMapper objectMapper;

	@Autowired
	WebClient.Builder webBuilder;

	@Value("${reservego.baseurl}")
	private String reserveGoBaseUrl;

	@Value("${reservego.apikey}")
	private String reserveGoApiKey;

	@Value("${reservego.apikeyname}")
	private String reserveGoApiKeyName;

	private static final Logger logger = LoggerFactory.getLogger(ReserveGoRouterWebhookController.class);

// -----------------------------------------webhook apis starts here-----------------------------------------------------------------

	public Object fetchAllTables(String rgRestaurantId) throws Exception {
		ReserveGoRouterPojo config = repo.findByRgRestaurantId(rgRestaurantId);

		if (config != null) {
			String url = config.getBaseUrl() + "/" + config.getAppName() + "/fetchalltables?rgRestaurantId="
					+ rgRestaurantId + "&br_id=" + config.getBranchId();

			// hit the api and return the data
			Object dto = webBuilder.build().get().uri(url).retrieve().bodyToMono(Object.class).block();

			if (dto != null) {
				return dto;
			}
		}

		return null;

	}

	public Object fetchalltablesstatus(String rgRestaurantId) throws Exception {
		ReserveGoRouterPojo config = repo.findByRgRestaurantId(rgRestaurantId);

		if (config != null) {
			String url = config.getBaseUrl() + "/" + config.getAppName() + "/fetchalltablesstatus?rgRestaurantId="
					+ rgRestaurantId + "&br_id=" + config.getBranchId();
			Object dto = webBuilder.build().get().uri(url).retrieve().bodyToMono(Object.class).block();
			if (dto != null) {
				return dto;
			}
		}
		return null;
	}

	public Object fetchstatusesbytables(String rgRestaurantId, String posTableIds) throws Exception {
		ReserveGoRouterPojo config = repo.findByRgRestaurantId(rgRestaurantId);

		if (config != null) {
			String url = config.getBaseUrl() + "/" + config.getAppName() + "/fetchstatusbytables?rgRestaurantId="
					+ rgRestaurantId + "&posTableIds=" + posTableIds;
			Object dto = webBuilder.build().get().uri(url).retrieve().bodyToMono(Object.class).block();
			if (dto != null) {
				return dto;
			}
		}
		return null;
	}

	public Object seatcustomer(String rgRestaurantId, SeatCustomerRequestDto requestDto) throws Exception {

		saveWebhookLog("/seatCustomer", requestDto);
		ReserveGoRouterPojo config = repo.findByRgRestaurantId(rgRestaurantId);

		if (config != null) {

			String url = config.getBaseUrl() + "/" + config.getAppName() + "/seatCustomer?rgRestaurantId="
					+ rgRestaurantId;

			Object dto = webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
					.bodyValue(requestDto).retrieve().bodyToMono(Object.class).block();

			if (dto != null) {
				return dto;
			}
		}

		return null;
	}

	public Object advancepayment(String rgRestaurantId, ReserveGoAdvancePaymentDto advancepayDto) throws Exception {
		saveWebhookLog("/advancePayment", advancepayDto);
		ReserveGoRouterPojo config = repo.findByRgRestaurantId(rgRestaurantId);
		if (config != null) {
			String url = config.getBaseUrl() + "/" + config.getAppName() + "/advancePayment?rgRestaurantId="
					+ rgRestaurantId;
			Object dto = webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
					.bodyValue(advancepayDto).retrieve().bodyToMono(Object.class).block();
			if (dto != null) {
				return dto;
			}
		}
		return null;
	}

	// 4. Helper method to save the payload from reservego
	private void saveWebhookLog(String endpoint, Object payloadDto) {
		try {
			ReserveGoWebhookLog log = new ReserveGoWebhookLog();
			log.setEndpoint(endpoint);
			log.setPayload(objectMapper.writeValueAsString(payloadDto));
			logRepo.save(log);
		} catch (Exception e) {
			// Catching exception so logging failure doesn't stop the main POS process
			System.err.println("Failed to log ReserveGo payload: " + e.getMessage());
		}
	}

	// --------------------------webhook apis end here---------------------------------------------------------------------------

	public Object swapTables(Object requestDto) throws Exception {
	    String url = reserveGoBaseUrl + "/api/bookings/reservation/table/swap";

	    return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object invoice(Object requestDto) throws Exception {
		String url = reserveGoBaseUrl + "/api/bookings/reservation/update/bill";

		return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
				.bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object updateTableDetails(Object requestDto) throws Exception {
		String url = reserveGoBaseUrl + "/api/bookings/reservation/table/update";

		return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
				.bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object updateTableSectionDetails(Object requestDto) throws Exception {
		String url = reserveGoBaseUrl + "/api/bookings/reservation/section/update";

		return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
				.bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object voidTable(Object requestDto) throws Exception {
		String url = reserveGoBaseUrl + "/api/bookings/reservation/voidcheckin";

		return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
				.bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object updateTableStatus(Map<String, Object> requestDto) throws Exception {

		requestDto.put("rgApiKey", reserveGoApiKey);
		String url = reserveGoBaseUrl + "/api/bookings/reservation/table/update/status";
		System.out.println("-----> FORWARDING TABLE STATUS TO RESERVEGO AT: " + url);
		System.out.println("-----> PAYLOAD: " + requestDto);

		try {
			// 3. Forward the request to ReserveGo via WebClient
			Object response = webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
					.bodyValue(requestDto).retrieve().bodyToMono(Object.class).block();
			System.out.println("-----> RESPONSE FROM RESERVEGO: " + response);
			return response;
		} catch (Exception e) {
			System.out.println("-----> ERROR CALLING RESERVEGO: " + e.getMessage());
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	private java.util.Map<String, Object> addApiKeyToBody(Object requestDto) {
		java.util.Map<String, Object> payload = (java.util.Map<String, Object>) requestDto;
		payload.put("rgApiKey", reserveGoApiKey);
		return payload;
	}

}
