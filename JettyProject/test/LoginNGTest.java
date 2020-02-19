/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.eclipse.jetty.util.log.AbstractLogger;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.StdErrLog;



/**
 *
 * @author techn
 */
@Test(singleThreaded=true)
public class LoginNGTest {
    
    @BeforeClass
    public static void startJetty() throws Exception {
        String[] args = new String[]{
            "jetty.home=../jetty",
            "STOP.PORT=2021", "STOP.KEY=AutomaticTofu"
        };
        var LG = new StdErrLog();
        LG.setLevel(AbstractLogger.LEVEL_OFF);
        Log.setLog(LG);
        org.eclipse.jetty.start.Main.main(args);
    }

    @AfterClass
    public static void stopJetty() throws Exception {
        String[] args = new String[]{ "jetty.home=../jetty",
        "STOP.PORT=2021", "STOP.KEY=AutomaticTofu",
        "--stop"
        };
        
        org.eclipse.jetty.start.Main.main(args);
    }
    
    static CookieManager cookieManager = new CookieManager();
    
    @BeforeClass
    public static void setupSession(){
        CookieHandler.setDefault(cookieManager);
    }
    
    @BeforeMethod
    public void clearCookies(){
        cookieManager.getCookieStore().removeAll();
    }
    
    @BeforeMethod
    public void clearAccounts() throws Exception {
        fetch("/srv/clear");
    }
    
    /**
     * @throws java.lang.Exception
     */
    
    @Test
    public void testHomeNotLoggedIn() throws Exception {
        String txt = fetch("/srv/home");
        assertTrue(txt.contains("You are not logged in"));
    }
    
    @Test
    public void testHomeLoggedIn() throws Exception {
        String txt = fetch("/srv/signup?user=Cat&pass=1234&name=Andy", "/srv/home");
        assertTrue(txt.contains("You are logged in as"));
    }
    
    @Test
    public void testLogoutNotLoggedIn() throws Exception {
        String txt = fetch("/srv/logout");
        assertTrue(txt.contains("No one to logout"));
    }
    
    @Test
    public void testLogoutLoggedIn() throws Exception {
        String txt = fetch("/srv/signup?user=Cat&pass=1234&name=Andy", "/srv/logout");
        assertTrue(txt.contains("Logging out of Cat"));
    }
    
    @Test
    public void testLogin() throws Exception {
        //Setup account to login to
        fetch("/srv/signup?user=Cat&pass=1234&name=Andy", "/srv/logout");
        String txt = fetch("/srv/login?user=Cat&pass=1234");
        assertTrue(txt.contains("Logged in as Cat"));
    }
    
    //Does there need to be a login situation for when already logged in?
    @Test
    public void testInvalidLogins() throws Exception {
        String txt = fetch("/srv/login?pass=1234");
        assertTrue(txt.contains("No username provided"));
        txt = fetch("/srv/login?user=Cat");
        assertTrue(txt.contains("No password provided"));
        
        //Wrong both
        txt = fetch("/srv/login?user=Cat&pass=1234");
        assertTrue(txt.contains("That username or password does not exist!"));
        
        //Make exist
        fetch("/srv/signup?user=Cat&pass=1234&name=Andy", "/srv/logout");
        //Wrong password
        txt = fetch("/srv/login?user=Cat&pass=123456");
        assertTrue(txt.contains("That username or password does not exist!"));
        //Wrong username
        txt = fetch("/srv/login?user=Caawdawdt&pass=1234");
        assertTrue(txt.contains("That username or password does not exist!"));
    }    
    
    @Test
    public void testSignup() throws Exception {
        String txt = fetch("/srv/signup?user=Cat&pass=1234&name=Andy");
        assertTrue(txt.contains("Logged in as Cat"));
    }
    
    @Test
    public void testInvalidSignup() throws Exception {
        String user = fetch("/srv/signup?pass=1234&name=Andy");
        assertTrue(user.contains("No username provided"));
        
        String password = fetch("/srv/signup?user=Cat&name=Andy");
        assertTrue(password.contains("No password provided"));
        
        String realName = fetch("/srv/signup?user=Cat&pass=1234");
        assertTrue(realName.contains("No real name provided"));
    }
    
    @Test
    public void testExistingSignup() throws Exception {
        String firstUser = fetch("/srv/signup?user=Cat&pass=1234&name=Andy");
        String secondUser = fetch("/srv/signup?user=Cat&pass=234&name=Any");
        assertTrue(secondUser.contains("Account already exists."));
    }
    
    @Test
    public void testClearAccounts() throws Exception {
        //Works for when there are and are not accounts
        String txt = fetch("/srv/signup?user=Cats&pass=1234&name=Andy", "/srv/clear");
        assertTrue(txt.contains("There are now 0 accounts"));
        txt = fetch("/srv/clear");
        assertTrue(txt.contains("There are now 0 accounts"));
    }
     
    //Only returns result of last url
    String fetch(String... allurls) throws Exception{
        String str=null;
        byte[] returnedData=new byte[]{0};
        for(String oneurl: allurls ){
            var url = new URL("http://localhost:2020"+oneurl);
            var conn = url.openConnection();
            conn.connect();
            var istr = conn.getInputStream();
            returnedData = istr.readAllBytes();
        }
        return new String(returnedData,0,returnedData.length);
    }
}
