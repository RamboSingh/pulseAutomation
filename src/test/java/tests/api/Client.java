package tests.api;

import java.util.HashMap;
import java.util.Map;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import pojos.ErrorResponse;

public class Client {

    /**
     * Sample GET request using native RestAssured with deserialization into ErrorResponse.
     *
     * @param request String request
     * @return ErrorResponse
     */
    public ErrorResponse sampleGetRequest(final String request) {
        final String requestUrl = "<End point for GET request>" + request;
        final String authToken = "badToken";
        final Map<String, String> headers = new HashMap<>();
        headers.put("application-id", "collection");
        headers.put("Content-Type", "application/json");
        headers.put("X-Authorization", authToken);
        return RestAssured.given().log().all().relaxedHTTPSValidation().headers(headers).log().all()
                .contentType(ContentType.JSON).log().all().request().when().log().all().get(requestUrl).then().extract()
                .as(ErrorResponse.class);
    }
}
