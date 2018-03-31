package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pearson.common.framework.shared.datareader.ReadFromExcel;
import com.pearson.common.framework.shared.pagefactory.PageFactory;

import pageobjects.LoginPage;

public class SampleWebTest extends BaseClass {
  
  @Test
  public void sampleWebTest() throws InterruptedException {
      
      driver.get(url);
      LoginPage loginPage = PageFactory.instantiatePage(driver, LoginPage.class);
      Assert.assertTrue(loginPage.isLoginLoaded());
      loginPage.login(ReadFromExcel.getData("LoginTest", "Username"), ReadFromExcel.getData("LoginTest", "Password"));
  }
  
  @Test
  public void sampleWebTest1() throws InterruptedException {
      
      driver.get(url);
      LoginPage loginPage = PageFactory.instantiatePage(driver, LoginPage.class);
      Assert.assertTrue(loginPage.isLoginLoaded());
      loginPage.login(ReadFromExcel.getData("LoginTest", "Username"), ReadFromExcel.getData("LoginTest", "Password"));
  }

}
