package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class ActivityEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityEndpoint(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getActivityRequest")
    @ResponsePayload
    public GetActivityResponse getActivity(@RequestPayload GetActivityRequest request) {
        GetActivityResponse response = new GetActivityResponse();
        response.setActivity(activityRepository.findActivity(request.getName()));

        return response;
    }
}
