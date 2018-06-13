package web.pageObjects;

import org.testng.Assert;
import org.testng.Reporter;

import com.pearson.common.framework.shared.exception.ReportCreationException;
import com.pearson.common.framework.web.core.WebDriverBase;
import com.pearson.common.framework.web.objectlocators.WebObjectLocators;

public class LoginPage extends WebDriverBase {

	private String username = WebObjectLocators.getLocator("f_username");
	private String password = WebObjectLocators.getLocator("f_password");
	private String continuebutton = WebObjectLocators.getLocator("b_continue");
	private String loginbutton=WebObjectLocators.getLocator("login");

	public void loginStep1(String uname) {

		waitForPageToLoad();
		if (isElementPresent(username)) {
			type(username, uname);
			clickOnElement(continuebutton);
		} else
			Assert.fail();
	}

	public void loginStep2(String passwd) {

		waitForPageToLoad();
		if (isElementPresent(password)) {
			type(password, passwd);
			clickOnElement(loginbutton);
		} else
			Assert.fail();
	}

	public boolean isLoginLoaded() {
		return isElementPresent(username);

	}
}
