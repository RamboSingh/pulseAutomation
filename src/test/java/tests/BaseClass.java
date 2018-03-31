package tests;

import com.pearson.common.framework.shared.datareader.TestData;
import com.pearson.common.framework.web.core.EnvParameters;
import com.pearson.common.framework.web.core.WebBase;

public class BaseClass extends WebBase {
  
  protected static String url = TestData.getData(EnvParameters.ENV + "_Url");
  
}
