package tests.web;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pearson.common.framework.shared.pagefactory.PageFactory;

import web.pageObjects.LoginPage;

public class PulseLogin extends WebBaseClass {
  
  @Test
  public void Pulse_SignIn() throws InterruptedException {
      
      driver.get(url);
      LoginPage loginPage = PageFactory.instantiatePage(driver, LoginPage.class);
      Assert.assertTrue(loginPage.isLoginLoaded());
      loginPage.loginStep1(username);
      loginPage.loginStep2(password);
    
  }
  
    
}

