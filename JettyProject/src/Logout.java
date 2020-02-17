import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
 
@WebServlet(urlPatterns={"/logout"})
public class Logout extends HttpServlet
{   
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/html");
        var pw = resp.getWriter();
        var sess = req.getSession();
        var username = sess.getAttribute("username");
        
        if(username == null) {
            pw.print("No one to logout.</br>");
            pw.printf("<a href=\"http://localhost:2020/srv/home\">Home</a>");
            return;
        }
        
        pw.println("Logging out of " + username + "</br>");
        pw.println("Cya " + sess.getAttribute("realName") + "</br>");
        sess.setAttribute("username", null);
        sess.setAttribute("realName", null);
        pw.printf("<a href=\"http://localhost:2020/srv/home\">Home</a>");
    }

}
