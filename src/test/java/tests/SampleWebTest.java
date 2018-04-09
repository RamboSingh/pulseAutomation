package tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.common.framework.shared.dataprovider.DataProviderArguments;
import com.pearson.common.framework.shared.dataprovider.DataProviderClass;
import com.pearson.common.framework.shared.pagefactory.PageFactory;

import pageobjects.LoginPage;

public class SampleWebTest extends BaseClass {
  
  @Test
  public void sampleWebTest() throws InterruptedException {
      
      driver.get(url);
      LoginPage loginPage = PageFactory.instantiatePage(driver, LoginPage.class);
      Assert.assertFalse(loginPage.isLoginLoaded());
      loginPage.login(username, password);
  }
  
  @Test(dataProvider = "DP_JXL", dataProviderClass = DataProviderClass.class)
  @DataProviderArguments(dataFolder = "dataprovider", dataFileName = "logindata.xls", sheetName = "Sheet1", tableName = "login")
  public void sampleWebTestWithDataProvider(Map<String, String> testData) throws InterruptedException {
      
      driver.get(url);
      LoginPage loginPage = PageFactory.instantiatePage(driver, LoginPage.class);
      Assert.assertFalse(loginPage.isLoginLoaded());
      loginPage.login(testData.get("Username"), testData.get("Password"));
  }
  
  
}
