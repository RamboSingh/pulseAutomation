/*package tests.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.pearson.common.framework.api.core.Header;
import com.pearson.common.framework.api.core.Method;
import com.pearson.common.framework.api.core.RESTServiceBase;
import com.pearson.common.framework.api.core.RESTWrapper;
import com.pearson.common.framework.api.core.Responses;
import com.pearson.common.framework.shared.report.LoggerUtil;
import net.minidev.json.JSONObject;
import pojos.ErrorResponse;
//import pojos.ErrorResponse;
import pojos.PiValidAuthResponse;

public class SampleAPITest extends APIBaseClass {

  *//**
   * Sample test with POST call using legary RESTServiceBase class available in Integrated Framework
   *
   * @throws Exception
   *//*
  @Test
  public void piLoginTest() throws Exception {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("userName", username);
    jsonObject.put("password", password);
    Response response = RESTServiceBase.postCallWithJsonBodyParam(jsonObject, url);
    LoggerUtil.log(response.asString());
    PiValidAuthResponse res = mapper.readValue(response.asString(), PiValidAuthResponse.class);
    Assert.assertEquals(res.getStatus(), "success");
    Assert.assertNotNull(res.getData());
  }

  *//**
   * Sample test with POST call using new RESTWrapper
   *
   * @throws Exception
   *//*
  @Test
  public void piLoginTest1() throws Exception {

    HashMap<String, Header> authHeader = new HashMap<String, Header>();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("userName", username);
    jsonObject.put("password", password);
    Responses responses = RESTWrapper.doRequest(authHeader, ContentType.JSON, url,
        jsonObject.toString(), Method.POST);
    System.out.println(responses.getBody().toString());
    PiValidAuthResponse res =
        mapper.readValue(responses.getBody().getBodyString(), PiValidAuthResponse.class);
    Assert.assertEquals(res.getStatus(), "success");
    Assert.assertNotNull(res.getData());
  }

  @Test(description = "validating error response for a get request")
  public void sampleGetTest() throws Exception {

    //Normally client would be located in some sort of base class
    final Client client = new Client();

    //Setting up GET request
    final String request = "sampleRequest";

    //Actual GET is implemented within Client
    final ErrorResponse response = client.sampleGetRequest(request);

    //Validation of actual response
   
  }
}
*/