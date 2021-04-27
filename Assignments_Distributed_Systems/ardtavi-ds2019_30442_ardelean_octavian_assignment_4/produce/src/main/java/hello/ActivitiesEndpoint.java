package hello;

import io.spring.guides.gs_producing_web_service.GetActivitiesRequest;
import io.spring.guides.gs_producing_web_service.GetActivitiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class ActivitiesEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private ActivitiesRepository activitiesRepository;

    @Autowired
    public ActivitiesEndpoint(ActivitiesRepository activitiesRepository) {
        this.activitiesRepository = activitiesRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getActivitiesRequest")
    @ResponsePayload
    public GetActivitiesResponse getActivities(@RequestPayload GetActivitiesRequest request) {
        GetActivitiesResponse response = new GetActivitiesResponse();
        response.getActivities().addAll(activitiesRepository.activities);
        return response;
    }
}
