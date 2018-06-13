package tests.api;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.pearson.common.framework.api.core.EnvParameters;
import com.pearson.common.framework.api.core.RESTServiceBase;
import com.pearson.common.framework.shared.datareader.TestData;

import api.pageObjects.WebUtils;
import net.minidev.json.JSONObject;

public class APIBaseClass extends RESTServiceBase {

	protected static String Env = EnvParameters.ENV;
	protected static String url = TestData.getData(Env + "_Url");
	// protected static String username = ReadFromExcel.getData("LoginTest", Env +
	// "_Username");
	// protected static String password = ReadFromExcel.getData("LoginTest", Env +
	// "_Password");
	protected ObjectMapper mapper = new ObjectMapper();

	public static String deviceid;
	public static String appversion;
	public static String APIGabUrl;
	protected static String APIPulseUrl;
	protected static String AppToken;
	public static String PearsonAdminUser;
	public static String PearsonAdminPassword;
	public static String PUsername;
	public static String PPassword;
	public static String name;


	@BeforeClass
	public void setUp() {
		
		chooseEnv();
		chooseUserAccount();
	}
	
	public void skipTestData() {
		throw new SkipException("Runmode set to No");
	}
	
/*	@BeforeMethod
	public void setUp(Object[] testArgs, ITestContext ctx, Method method) {
		final String testName = method.getName();
		final String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
		ReportHelper.initTest(testName, suiteName);
		ReportHelper.assignCatogory("Pulse");

	}
	*/


	public static Response authenticate(String userName, String password) {
		Response response = null;
		try {
			JSONObject myJsonObject = new JSONObject();
			Map<String, String> headers = new HashMap<String, String>();

			/**
			 * Header details This is used for providing the header details
			 */

			deviceid = "11" + WebUtils.generateid();
			appversion = "10" + WebUtils.generateid();
			// extrefOrigin = "Origin" + WebUtils.generateid();
			// extrefSource = "Src" + WebUtils.generateid();
			//
			headers.put("deviceid", deviceid);
			headers.put("appVersion", appversion);
			headers.put("Accept-Language", "en-gb");

			// This is used for passing the username and Password parameters

			myJsonObject.put("username", userName);
			myJsonObject.put("password", password);
			response = RESTServiceBase.postCallWithHeaderAndBodyParam(headers, myJsonObject,
					APIGabUrl + "/user/authenticate");

			String json = response.asString();
			AppToken = JsonPath.with(json).get("access_token");

		} catch (Exception e) {

			e.printStackTrace();

		}
		return response;
	}


	public static void chooseEnv() {

		if (EnvParameters.ENV.equals("WebDEV")) {
			APIGabUrl = TestData.getData("GABDEV_APIUrl");
			APIPulseUrl = TestData.getData("PulseDEV_APIUrl");
			// CourseUrl= TestData.getData("CourseDEV_APIUrl");
			// AAPIUrl= TestData.getData("AAPIDEV_Url");
		} else if (EnvParameters.ENV.equals("WebSTAGE")) {
			APIGabUrl = TestData.getData("GABSTAGE_APIUrl");
			APIPulseUrl = TestData.getData("PulseSTAGE_APIUrl");
			// CourseUrl= TestData.getData("CourseSTAGE_APIUrl");
			// AAPIUrl= TestData.getData("AAPISTAGE_Url");
		} else if (EnvParameters.ENV.equals("DevNoip")) {
			APIGabUrl = TestData.getData("GABDevNoip_APIUrl");
			APIPulseUrl = TestData.getData("PulseDevNoip_APIUrl");
			// CourseUrl= TestData.getData("CourseDevNoip_APIUrl");
			// AAPIUrl= TestData.getData("AAPIDevNoip_Url");
		}

		else {
			// log.info("Invalid Environment, Please specify a valid enviroment for choosing
			// the API");
		}
		System.out.println("Gab>>>> " + APIGabUrl);
		System.out.println("Pulse >>>> " + APIPulseUrl);
		// System.out.println("Course API>>>> " + CourseUrl);
	}

	public static void chooseUserAccount() {

		if ((Env.equals("WebDEV")) || (Env.equals("WebSTAGE")) || (Env.equals("DevNoip"))) {

			PearsonAdminUser = TestData.getData("QA_PearsonAdminUser");
			PearsonAdminPassword = TestData.getData("QA_PearsonAdminPassword");
			PUsername = TestData.getData("QA_PearsonNonAdminUser");
			PPassword = TestData.getData("QA_PearsonNonAdminPassword");

		} else {
			// log.info("Invalid Environment, Please specify a valid enviroment for choosing
			// the users");
		}
		// log.info("GLS API URL: " + APIGabUrl);
	}
	public static void Assertion(int actual, int expected, String APIname) {
		try {
			// Assert.assertEquals(actual, expected);
			if (actual == expected)
{
				//ReportHelper.log(LogStatus.PASS, "Actual ReponseCode " + actual
						//+ " Matches with the expected Responsecode " + expected + " for " + APIname, "Pass");
	//log.info("Success");
}
			else {
				//ReportHelper.log(
						//LogStatus.FAIL, "Actual ReponseCode " + actual
								//+ " Doesn't match with the expected Responsecode " + expected + " for " + APIname,
						//"Fail");
				Assert.fail();
			}
		} catch (Exception e) {
		
			//ReportHelper.log(LogStatus.FAIL, "Actual ReponseCode " + actual
					//+ " Doesn't match with the expected Responsecode " + expected + " for " + APIname, "Fail");
		}
	}
	
public String namegenerator(String APIName) {
		
		if (APIName.equalsIgnoreCase("Geo")) {
			name = "Geo_" + WebUtils.TextrandomGen() +"@#$";
		} else if (APIName.equalsIgnoreCase("SAID")) {
			name = "SAID_" + WebUtils.TextrandomGen() +"@#$";
		} else if (APIName.equalsIgnoreCase("School")) {
			name = "School_" + WebUtils.TextrandomGen() +"@#$";
		} else if (APIName.equalsIgnoreCase("User")) 
		{
			name = "User_" + WebUtils.TextrandomGen()+"@$%";
		}
		else if (APIName.equalsIgnoreCase("Username1")) {
			name = "User_" + WebUtils.TextrandomGen()+"@1";
	    }
		else if (APIName.equalsIgnoreCase("Username")) {
				name = "User_" + WebUtils.TextrandomGen()+".1";
		} else if (APIName.equalsIgnoreCase("teacher")) {
			name = "teacher" + "." + WebUtils.generateid1();
		} 
		else if (APIName.equalsIgnoreCase("student")) {
			name = "student" + "." + WebUtils.generateid1();
		} 
		else if (APIName.equalsIgnoreCase("Product")) {
			name = "Product" + WebUtils.generateid1();
		} 
		else if (APIName.equalsIgnoreCase("Asset")) {
			name = "Asset" + WebUtils.generateid1();
		} else if (APIName.equalsIgnoreCase("AssetDescription")) {
			name = "Asset description" + WebUtils.generateid1();
		} 
		 else if (APIName.equalsIgnoreCase("AssetImageURL")) {
				name = "http://i.booksee.org/covers/605000/" + WebUtils.generateid1()+".jpg";
			} 
		else if (APIName.equalsIgnoreCase("Coursesection")) {
			name = "Coursesection" + WebUtils.generateid1();
		} else if (APIName.equalsIgnoreCase("ContentCartridge")) {
			name = "ContentCartridge" + WebUtils.generateid1();
		}
		else if (APIName.equalsIgnoreCase("CourseSection")) {
			name = "CourseSection" + WebUtils.generateid1();
		}
		return name;
	}


}
