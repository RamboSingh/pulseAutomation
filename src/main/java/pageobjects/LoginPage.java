package pageobjects;

import com.pearson.common.framework.web.core.WebDriverBase;
import com.pearson.common.framework.web.objectlocators.WebObjectLocators;

public class LoginPage extends WebDriverBase {
  
  private String username = WebObjectLocators.getLocator("Username");
  private String password = WebObjectLocators.getLocator("Password");
  private String submitButton = WebObjectLocators.getLocator("SubmitButton");
  
  public void login(String uname, String pass) {
    
    waitForPageToLoad();
    type(username, uname);
    type(password, pass);
    clickOnElement(submitButton);
    waitForPageToLoad();
  }
  
  public boolean isLoginLoaded() {
    return isElementPresent(username);
  }

}
