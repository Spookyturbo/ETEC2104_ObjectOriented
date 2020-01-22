import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
 
@WebServlet(urlPatterns={"/signup"})
public class SignUp extends HttpServlet
{
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/plain");
        var pw = resp.getWriter();
        var name = req.getParameter("user");
        var password = req.getParameter("pass");
        var realName = req.getParameter("name");
        
        if( name == null ){
            pw.printf("No username provided");
        } 
        else if(password == null) {
            pw.printf("No password provided");
        }
        else if(realName == null) {
            pw.printf("No real name provided");
        }
        else {
            Account acc = new Account(name, password, realName);
            accounts.add(acc);
            var sess = req.getSession();
            sess.setAttribute("username", acc.userName);
            sess.setAttribute("realName", acc.name);
            
            pw.printf("Logged in as " + acc.userName + "\nWelcome " + realName);
        }
    }

}
