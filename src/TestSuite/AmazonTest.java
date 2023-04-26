package TestSuite;

import org.junit.After;
import org.junit.Before;
import utilities.Utility;

public class Test extends Utility {

    String baseUrl = "https://www.amazon.co.uk/";

    @Before
    public void setup() {
        openBrowser(baseUrl);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }

    @org.junit.Test
    


}
