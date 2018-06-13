package tests.web;

import com.pearson.common.framework.shared.datareader.ReadFromExcel;
import com.pearson.common.framework.shared.datareader.TestData;
import com.pearson.common.framework.web.core.EnvParameters;
import com.pearson.common.framework.web.core.WebBase;

public class WebBaseClass extends WebBase {
  
  protected static String Env = EnvParameters.ENV;
  protected static String url = TestData.getData(Env + "_Url");
  protected static String username = TestData.getData("STG_Username");
  protected static String password =  TestData.getData("STG_Password");
  
}
