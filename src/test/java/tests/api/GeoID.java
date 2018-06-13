package tests.api;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.pearson.common.framework.api.core.EnvParameters;
import com.pearson.common.framework.api.core.RESTServiceBase;
import com.pearson.common.framework.shared.dataprovider.DataProviderArguments;
import com.pearson.common.framework.shared.dataprovider.DataProviderClass;
import com.pearson.common.framework.shared.dataprovider.Xls_Reader;

import api.pageObjects.WebUtils;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;


public class GeoID extends APIBaseClass {
	public static String geoID;
	public static String geoName;

	/** PUT geo - This method is used to Create GeoID */

	public Xls_Reader datatable = new Xls_Reader(
			EnvParameters.TEST_ROOT_DIR + File.separator + "dataprovider" + File.separator + "Service_Controller.xlsx");

	/**
	 * The parameters specified here must be same as specified in the Controller
	 *
	 * Prerequisite: Check For Test Data Expected Response code to be read from the
	 * excel
	 */

	// Functional testing
	@Test(dataProvider = "DP_POI", dataProviderClass = DataProviderClass.class)
	@DataProviderArguments(dataFolder = "dataprovider", dataFileName = "Service_Controller.xlsx", sheetName = "PutGeoID", tableName = "Dummy")
	public void GeoIDTest(Map<String, String> geo) throws InterruptedException {

		if (geo.get("Runmode").equals("No")) {
			skipTestData();
		} else {
			try {
				Response responseforAuthenticate = authenticate(PearsonAdminUser, PearsonAdminPassword);
				Assertion(responseforAuthenticate.getStatusCode(), 200, "Authentication");

				int responsecode = (int) Float.parseFloat(geo.get("Responsecode"));
							
				if(geo.get("Description").contains("Name Blank"))
				{
						    Response responseforgeoID1 = creategeoID("", geo.get("terms"), geo.get("privacy"),
							geo.get("cookie"), geo.get("moodle"), geo.get("services"), geo.get("website"),
							geo.get("languagePackAPI"), geo.get("appConfigAPI"), geo.get("moodleProvisionService"),
							geo.get("moodleProvisionUrl"), geo.get("heroImage"), geo.get("textbooksThumbnail"),
							geo.get("projectsThumbnail"), geo.get("discussionsThumbnail"), geo.get("tag"),
							geo.get("aapi_hawkId"),  geo.get("aapi_auth"),geo.get("aapi_url"), geo.get("ugc_hawkId"), geo.get("ugc_auth"),
							geo.get("ugc_url"),geo.get("mypedia_auth"),geo.get("mypedia_url"),geo.get("secretKey"),400);
										
					Assertion(responseforgeoID1.statusCode(), 400, "PUT GEO");

				}			
					
				else if(geo.get("Description").contains("Duplicate Name"))
				{
					
					        Response responseforgeoID = creategeoID(namegenerator("Geo"), geo.get("terms"), geo.get("privacy"),
							geo.get("cookie"), geo.get("moodle"), geo.get("services"), geo.get("website"),
							geo.get("languagePackAPI"), geo.get("appConfigAPI"), geo.get("moodleProvisionService"),
							geo.get("moodleProvisionUrl"), geo.get("heroImage"), geo.get("textbooksThumbnail"),
							geo.get("projectsThumbnail"), geo.get("discussionsThumbnail"), geo.get("tag"),
							geo.get("aapi_hawkId"),  geo.get("aapi_auth"),geo.get("aapi_url"), geo.get("ugc_hawkId"), geo.get("ugc_auth"),
							geo.get("ugc_url"),geo.get("mypedia_auth"),geo.get("mypedia_url"),geo.get("secretKey"),responsecode);
					        
					        Assertion(responseforgeoID.statusCode(), responsecode, "PUT GEO");
	
					
					     /*   Response responseforgeoID1 = creategeoID(RESTservices.name, geo.get("terms"), geo.get("privacy"),
							geo.get("cookie"), geo.get("moodle"), geo.get("services"), geo.get("website"),
							geo.get("languagePackAPI"), geo.get("appConfigAPI"), geo.get("moodleProvisionService"),
							geo.get("moodleProvisionUrl"), geo.get("heroImage"), geo.get("textbooksThumbnail"),
							geo.get("projectsThumbnail"), geo.get("discussionsThumbnail"), geo.get("tag"),
							geo.get("aapi_hawkId"),  geo.get("aapi_auth"),geo.get("aapi_url"), geo.get("ugc_hawkId"), geo.get("ugc_auth"),
							geo.get("ugc_url"),geo.get("mypedia_auth"),geo.get("mypedia_url"),geo.get("secretKey"),400);
										
					Assertion(responseforgeoID1.statusCode(), 400, "PUT GEO");*/

				}		
				else
				{
					    Response responseforgeoID = creategeoID(namegenerator("Geo"), geo.get("terms"), geo.get("privacy"),
						geo.get("cookie"), geo.get("moodle"), geo.get("services"), geo.get("website"),
						geo.get("languagePackAPI"), geo.get("appConfigAPI"), geo.get("moodleProvisionService"),
						geo.get("moodleProvisionUrl"), geo.get("heroImage"), geo.get("textbooksThumbnail"),
						geo.get("projectsThumbnail"), geo.get("discussionsThumbnail"), geo.get("tag"),
						geo.get("aapi_hawkId"),  geo.get("aapi_auth"),geo.get("aapi_url"), geo.get("ugc_hawkId"), geo.get("ugc_auth"),
						geo.get("ugc_url"),geo.get("mypedia_auth"),geo.get("mypedia_url"),geo.get("secretKey"),responsecode);

				
				Assertion(responseforgeoID.statusCode(), responsecode, "PUT GEO");
									
				if (responseforgeoID.statusCode() == 200) 
				{
					String json = responseforgeoID.asString();
					geoID = JsonPath.with(json).get("id");
					geoName = JsonPath.with(json).get("name");
				/*	int rowcount = Xls_Reader.getRowCount("GeoSASchoolID");
					Xls_Reader.setCellData("GeoSASchoolID", "ID", rowcount + 1, geoID);
					Xls_Reader.setCellData("GeoSASchoolID", "IDName", rowcount + 1, "geoID");
					Xls_Reader.setCellData("GeoSASchoolID", "Name", rowcount + 1, geoName);*/


					
					// Verify the response for Geo

					Response responseforGetGEoID = getGeoservice(geoID);
					Assertion(responseforGetGEoID.statusCode(), 200, "GET GEO");
	
				}
			}
			}

			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public static Response creategeoID(String name, String terms, String privacy, String cookie, String moodle,
			String services, String website, String languagePackAPI, String appConfigAPI, String moodleProvisionService,
			String moodleProvisionUrl, String heroImage, String textbooksThumbnail, String projectsThumbnail,
			String discussionsThumbnail, String tag, String aapi_hawkId, String aapi_auth,String aapi_url, String ugc_hawkId, String ugc_auth,
			String ugc_url, String mypedia_auth,String mypedia_url,String secretKey, int responsecode) throws InterruptedException {
		Response response = null;
		try {
			JSONObject linksJsonObject = new JSONObject();
			JSONObject tenantsObject = new JSONObject();
			JSONObject aapiJsonObject = new JSONObject();
			JSONObject tenantsObject1 = new JSONObject();
			JSONObject aapiJsonObject1 = new JSONObject();
			JSONObject aapiparentJsonObject = new JSONObject();
			JSONObject parentJsonObject = new JSONObject();
			JSONObject jsonparrent = new JSONObject();
                        JSONObject tenantsObject2= new JSONObject();
			JSONObject aapiJsonObject2=new JSONObject();
			JSONArray tenantsarray = new JSONArray();
			JSONArray tenantsarray1 = new JSONArray();
			JSONArray jarray = new JSONArray();
                        JSONArray tenantsarray2=new JSONArray();

			linksJsonObject.put("terms", terms);
			linksJsonObject.put("privacy", privacy);
			linksJsonObject.put("cookie", cookie);
			linksJsonObject.put("moodle", moodle);
			linksJsonObject.put("services", services);
			linksJsonObject.put("website", website);
			linksJsonObject.put("languagePackAPI", languagePackAPI);
			linksJsonObject.put("appConfigAPI", appConfigAPI);
			linksJsonObject.put("moodleProvisionService", moodleProvisionService);
			linksJsonObject.put("moodleProvisionUrl", moodleProvisionUrl);
			linksJsonObject.put("heroImage", heroImage);
			linksJsonObject.put("textbooksThumbnail", textbooksThumbnail);
			linksJsonObject.put("projectsThumbnail", projectsThumbnail);
			linksJsonObject.put("discussionsThumbnail", discussionsThumbnail);

			tenantsObject.put("tag", tag);
                        tenantsObject.put("auth", aapi_auth);
			tenantsObject.put("hawkId", aapi_hawkId);
			tenantsarray.add(tenantsObject);

			aapiJsonObject.put("tenants", tenantsarray);
			aapiJsonObject.put("url", aapi_url);

			tenantsObject1.put("tag", tag);
tenantsObject1.put("auth", ugc_auth);
			tenantsObject1.put("hawkId", ugc_hawkId);
			tenantsarray1.add(tenantsObject1);
			
			tenantsObject2.put("auth", mypedia_auth);
			tenantsObject2.put("secretKey", secretKey);
			tenantsarray2.add(tenantsObject2);

			aapiJsonObject1.put("tenants", tenantsarray1);
			aapiJsonObject1.put("url", ugc_url);
			
			aapiJsonObject2.put("tenants", tenantsarray2);
			aapiJsonObject2.put("url", mypedia_url);

			aapiparentJsonObject.put("aapi", aapiJsonObject);
			aapiparentJsonObject.put("ugc", aapiJsonObject1);
			aapiparentJsonObject.put("mypediaapi", aapiJsonObject2);

			parentJsonObject.put("name", name);
			parentJsonObject.put("links", linksJsonObject);
			parentJsonObject.put("serviceDirectory", aapiparentJsonObject);

			jarray.add(parentJsonObject);
			jsonparrent.put("geo", jarray);


			Map<String, String> headers = new HashMap<String, String>();

			headers.put("Authorization", "Bearer " + AppToken);
			headers.put("deviceid", deviceid);
			headers.put("appVersion", appversion);
			headers.put("Accept-Language", "en-gb");
			
			response = RESTServiceBase.putCallWithHeaderAndBodyParam(headers, jsonparrent, APIGabUrl + "/geo");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			Assert.fail();
		}
		return response;
			}
	
	public static Response getGeoservice(String geoID) {
		Response response = null;
		try {
			Map<String, String> headers = new HashMap<String, String>();
			List<String> GetGeoID = new ArrayList<String>();
			List<String> GetGeoName = new ArrayList<String>();

			deviceid = "abc" + WebUtils.generateid();
			appversion = "xyz" + WebUtils.generateid();

			headers.put("Authorization", "Bearer " + AppToken);




			headers.put("deviceid", deviceid);
			headers.put("appVersion", appversion);
			headers.put("Accept-Language", "en-gb");

			headers.put("Authorization", "Bearer " + AppToken);

			response = RESTServiceBase.getCallWithHeaderParam(headers, APIGabUrl + "/geo?id=" + geoID);
			String json = response.asString();
			GetGeoID = JsonPath.with(json).get("id");
			GetGeoName = JsonPath.with(json).get("name");


		} catch (Exception e) {

			Assert.fail();
		}
		return response;
	}
	
	
}