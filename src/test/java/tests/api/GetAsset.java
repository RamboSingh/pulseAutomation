package tests.api;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.pearson.common.framework.api.core.RESTServiceBase;
import com.pearson.common.framework.shared.dataprovider.DataProviderArguments;
import com.pearson.common.framework.shared.dataprovider.DataProviderClass;

import api.pageObjects.WebUtils;

public class GetAsset extends APIBaseClass {

	@Test(dataProvider = "DP_POI", dataProviderClass = DataProviderClass.class)
	@DataProviderArguments(dataFolder = "dataprovider", dataFileName = "Service_Controller.xlsx", sheetName = "GetAssetPulse", tableName = "Dummy")
	public void GETAssetIDs(Map<String, String> getasset) throws Exception {

		if (getasset.get("Runmode").equals("No")) {
			skipTestData();
		} else {
			Response responseforAuthenticate = authenticate(PearsonAdminUser, PearsonAdminPassword);
			Assert.assertEquals(responseforAuthenticate.getStatusCode(), 200);
			int responsecode = (int) Float.parseFloat(getasset.get("ResponseCode"));
			Response responseforGetAssetID = getAssetservice(getasset.get("assetid"), "Pulse");
			Assert.assertEquals(responseforGetAssetID.statusCode(), responsecode);

		}
	}

	public static Response getAssetservice(String assetID, String URL) {
		Response response = null;
		try {
			Map<String, String> headers = new HashMap<String, String>();
			deviceid = "abc" + WebUtils.generateid();
			appversion = "xyz" + WebUtils.generateid();
			headers.put("deviceid", deviceid);
			headers.put("appVersion", appversion);
			headers.put("Accept-Language", "en-gb");
			headers.put("Authorization", "Bearer " + AppToken);

			if (URL.equalsIgnoreCase("Gab")) {
				response = RESTServiceBase.getCallWithHeaderParam(headers, APIGabUrl + "/asset?id=" + assetID);

			} else if (URL.equalsIgnoreCase("Pulse")) {
				response = RESTServiceBase.getCallWithHeaderParam(headers, APIPulseUrl + "/asset?id=" + assetID);
			} else {

			}

		} catch (Exception e) {

			e.printStackTrace();

			Assert.fail();

		}
		return response;
	}
}
