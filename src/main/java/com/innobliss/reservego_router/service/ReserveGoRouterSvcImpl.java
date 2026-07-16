package com.innobliss.reservego_router.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.innobliss.reservego_router.ReserveGoRouter;
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

	private static final Logger logger = LoggerFactory.getLogger(ReserveGoRouterSvcImpl.class);

// -----------------------------------------webhook apis starts here-----------------------------------------------------------------

	public Object fetchAllTables(String rgRestaurantId) throws Exception {
		ReserveGoRouter config = repo.findByRgRestaurantId(rgRestaurantId);

		if (config != null) {
			String url = config.getBaseUrl() + "/" + config.getAppName() + "/fetchalltables?rgRestaurantId="
					+ rgRestaurantId + "&br_id=" + config.getBranchId();

			logger.info("Forwarding fetchAllTables to branch URL: {}", url);
			// hit the api and return the data
			Object dto = webBuilder.build().get().uri(url).retrieve().bodyToMono(Object.class).block();

			if (dto != null) {
				return dto;
			}
		}

		return null;

	}

	public Object fetchalltablesstatus(String rgRestaurantId) throws Exception {
		ReserveGoRouter config = repo.findByRgRestaurantId(rgRestaurantId);

		if (config != null) {
			String url = config.getBaseUrl() + "/" + config.getAppName() + "/fetchalltablesstatus?rgRestaurantId="
					+ rgRestaurantId + "&br_id=" + config.getBranchId();
			logger.info("Forwarding fetchalltablesstatus to branch URL: {}", url);
			Object dto = webBuilder.build().get().uri(url).retrieve().bodyToMono(Object.class).block();
			if (dto != null) {
				return dto;
			}
		}
		return null;
	}

	public Object fetchstatusesbytables(String rgRestaurantId, String posTableIds) throws Exception {
		ReserveGoRouter config = repo.findByRgRestaurantId(rgRestaurantId);

		if (config != null) {
			String url = config.getBaseUrl() + "/" + config.getAppName() + "/fetchstatusbytables";
			
			Map<String, Object> payload = new HashMap<>();
			payload.put("rgRestaurantId", rgRestaurantId);
			if (posTableIds != null && !posTableIds.trim().isEmpty()) {
				payload.put("posTableIds", Arrays.asList(posTableIds.split(",")));
			} else {
				payload.put("posTableIds", Arrays.asList());
			}

			logger.info("Forwarding fetchstatusbytables as POST to branch URL: {}, payload: {}", url, payload);
			Object dto = webBuilder.build().post()
					.uri(url)
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(payload)
					.retrieve()
					.bodyToMono(Object.class)
					.block();
			if (dto != null) {
				return dto;
			}
		}
		return null;
	}

	public Object seatcustomer(String rgRestaurantId, SeatCustomerRequestDto requestDto) throws Exception {

		saveWebhookLog("/seatCustomer", requestDto);
		ReserveGoRouter config = repo.findByRgRestaurantId(rgRestaurantId);

		if (config != null) {

			String url = config.getBaseUrl() + "/" + config.getAppName() + "/seatCustomer?rgRestaurantId="
					+ rgRestaurantId;

			logger.info("Forwarding seatCustomer to branch URL: {}, payload: {}", url, requestDto);
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
		ReserveGoRouter config = repo.findByRgRestaurantId(rgRestaurantId);
		if (config != null) {
			String url = config.getBaseUrl() + "/" + config.getAppName() + "/advancePayment?rgRestaurantId="
					+ rgRestaurantId;
			logger.info("Forwarding advancePayment to branch URL: {}, payload: {}", url, advancepayDto);
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
			logger.error("Failed to log ReserveGo payload for endpoint {}: {}", endpoint, e.getMessage());
		}
	}

	// --------------------------webhook apis end here---------------------------------------------------------------------------

	public Object swapTables(Object requestDto) throws Exception {
	    String url = reserveGoBaseUrl + "/api/bookings/reservation/table/swap";
	    logger.info("Forwarding swapTables to ReserveGo URL: {}, payload: {}", url, requestDto);

	    return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
	            .bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object invoice(Object requestDto) throws Exception {
		String url = reserveGoBaseUrl + "/api/bookings/reservation/update/bill";
		logger.info("Forwarding invoice to ReserveGo URL: {}, payload: {}", url, requestDto);

		return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
				.bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object updateTableDetails(Object requestDto) throws Exception {
		String url = reserveGoBaseUrl + "/api/bookings/reservation/table/update";
		logger.info("Forwarding updateTableDetails to ReserveGo URL: {}, payload: {}", url, requestDto);

		return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
				.bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object updateTableSectionDetails(Object requestDto) throws Exception {
		String url = reserveGoBaseUrl + "/api/bookings/reservation/section/update";
		logger.info("Forwarding updateTableSectionDetails to ReserveGo URL: {}, payload: {}", url, requestDto);

		return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
				.bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object voidTable(Object requestDto) throws Exception {
		String url = reserveGoBaseUrl + "/api/bookings/reservation/voidcheckin";
		logger.info("Forwarding voidTable to ReserveGo URL: {}, payload: {}", url, requestDto);

		return webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
				.bodyValue(addApiKeyToBody(requestDto)).retrieve().bodyToMono(Object.class).block();
	}

	public Object updateTableStatus(Map<String, Object> requestDto) throws Exception {

		addApiKeyToBody(requestDto);
		String url = reserveGoBaseUrl + "/api/bookings/reservation/table/update/status";
		logger.info("Forwarding updateTableStatus to ReserveGo URL: {}, payload: {}", url, requestDto);

		try {
			// 3. Forward the request to ReserveGo via WebClient
			Object response = webBuilder.build().post().uri(url).contentType(MediaType.APPLICATION_JSON)
					.bodyValue(requestDto).retrieve().bodyToMono(Object.class).block();
			logger.info("Response from ReserveGo: {}", response);
			return response;
		} catch (Exception e) {
			logger.error("Error calling ReserveGo updateTableStatus: {}", e.getMessage());
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	private java.util.Map<String, Object> addApiKeyToBody(Object requestDto) {
		java.util.Map<String, Object> payload = (java.util.Map<String, Object>) requestDto;
		String rgRestaurantId = (String) payload.get("rgRestaurantId");
		if (rgRestaurantId == null) {
			rgRestaurantId = (String) payload.get("restaurantId");
		}
		if (rgRestaurantId != null) {
			ReserveGoRouter config = repo.findByRgRestaurantId(rgRestaurantId);
			if (config != null && config.getRgApiKey() != null) {
				payload.put("rgApiKey", config.getRgApiKey());
			} else {
				logger.warn("No API Key mapping found for rgRestaurantId: {}", rgRestaurantId);
			}
		} else {
			logger.warn("rgRestaurantId not found in request payload: {}", payload);
		}
		return payload;
	}

}
