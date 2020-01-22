import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
 
@WebServlet(urlPatterns={"/logout"})
public class Logout extends HttpServlet
{
    public static ArrayList<Account> accounts = new ArrayList<Account>();
    
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/plain");
        var pw = resp.getWriter();
        var sess = req.getSession();
        var username = sess.getAttribute("username");
        
        if(username == null) {
            pw.print("No one to logout.");
            return;
        }
        
        pw.println("Logging out of " + username);
        pw.println("Cya " + sess.getAttribute("realName"));
        sess.setAttribute("username", null);
        sess.setAttribute("realName", null);
    }

}
