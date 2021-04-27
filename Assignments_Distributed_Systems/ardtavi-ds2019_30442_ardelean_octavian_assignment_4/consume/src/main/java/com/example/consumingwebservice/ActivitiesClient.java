
package com.example.consumingwebservice;

import com.example.consumingwebservice.wsdl.GetActivitiesRequest;
import com.example.consumingwebservice.wsdl.GetActivitiesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class ActivitiesClient extends WebServiceGatewaySupport {

	private static final Logger log = LoggerFactory.getLogger(ActivitiesClient.class);

	public GetActivitiesResponse getActivities() {

		GetActivitiesRequest request = new GetActivitiesRequest();

		log.info("Requesting activities");

		GetActivitiesResponse response = (GetActivitiesResponse) getWebServiceTemplate()
				.marshalSendAndReceive("http://localhost:8080/ws/activities", request,
						new SoapActionCallback(
								"http://spring.io/guides/gs-producing-web-service/GetActivitiesRequest"));

		return response;
	}

}
