package tests;

import com.pearson.common.framework.shared.datareader.ReadFromExcel;
import com.pearson.common.framework.shared.datareader.TestData;
import com.pearson.common.framework.web.core.EnvParameters;
import com.pearson.common.framework.web.core.WebBase;

public class BaseClass extends WebBase {
  
  protected static String Env = EnvParameters.ENV;
  protected static String url = TestData.getData(Env + "_Url");
  protected static String username = ReadFromExcel.getData("LoginTest", Env + "_Username");
  protected static String password = ReadFromExcel.getData("LoginTest", Env + "_Password");
  
}
