package com.hooman.incident.push.notification;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.jooq.tools.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hooman.incident.controller.IncidentController;

/**
 * @author shubham.agarwal
 *
 */
@Service
public class NotificationSender {

	private static final String DATA = "data";
	private static final String NOTIFICATION = "notification";
	private static final String BODY_IOS = "body_loc_key";
	private static final String TITLE_IOS = "title_loc_key";
	private static final String BODY = "body";
	private static final String TITLE = "title";
	private static final String FIREBASE_SERVER_KEY = "AAAATN3fa7A:APA91bGMwD7zufPQIQHz1g_fKS4JOCk_bgwtQ_LG4ybypqIdHOS-t31o80l7Zbpn02GS6fyeGrx0qYeYFTypoydva0DQrVVyec1ESOVcGgo3s9a4D-i23fe62HBdDuT-f7xfIXvgaG6-";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

	private static final Logger logger = LoggerFactory.getLogger(NotificationSender.class);

	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);
		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
		logger.info("firebase response is " + firebaseResponse);
		return CompletableFuture.completedFuture(firebaseResponse);
	}

	public void send(String title, String titleBody, Map<String, String> dataMap, String token, String type)
			throws Exception {
		JSONObject message = new JSONObject();

		if (type != null) {
			JSONObject body = new JSONObject();
			System.out.println("token is " + token);
			logger.info("token is " + token);
			JSONObject notification = new JSONObject();
			notification.put(TITLE_IOS, title);
			notification.put(BODY_IOS, titleBody);
			notification.put("sound", "default");
			notification.put("badge", "0");

			JSONObject data = new JSONObject();
			for (Map.Entry<String, String> entry : dataMap.entrySet()) {
				data.put(entry.getKey(), entry.getValue());
			}
			logger.info("sending to token " + token);
			message.put("to", token);
			message.put(NOTIFICATION, notification);
			message.put(DATA, data);
		}

		else {
			JSONObject body = new JSONObject();
			body.put("to", token);
			System.out.println("token is " + token);
			JSONObject notification = new JSONObject();
			notification.put(TITLE, title);
			notification.put(BODY, titleBody);

			JSONObject data = new JSONObject();
			for (Map.Entry<String, String> entry : dataMap.entrySet()) {
				data.put(entry.getKey(), entry.getValue());
			}
			logger.info("sending to token " + token);

			body.put(NOTIFICATION, notification);
			body.put(DATA, data);
			logger.info("sending data " + data);
			message.put("to", token);
			message.put("data", data);
		}

		HttpEntity<String> request = new HttpEntity<>(message.toString());

		CompletableFuture<String> pushNotification = send(request);
		try {
			String string = pushNotification.get();
			logger.info("notification sent successfully " + string);
			System.out.println("success hai " + string);
		} catch (InterruptedException | ExecutionException e) {
			logger.error("error while sending notification " + e.getMessage());
			throw new Exception("Failed to send notification");
		}
	}
}
