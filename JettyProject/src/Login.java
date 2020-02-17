import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns={"/login"})
public class Login extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/html");
        var pw = resp.getWriter();
        var name = req.getParameter("user");
        var password = req.getParameter("pass");
        if( name == null ){
            pw.printf("No username provided</br>");
        }
        else if( password == null) {
            pw.printf("No password provided</br>");
        }
        else {
            //Needs to check existing accounts
            var sess = req.getSession();
            Account account = null;
            for(Account acc : AccountManager.accounts) {
                if(acc.equals(name, password)) {
                    account = acc;
                    break;
                }
            }
            
            if(account == null) {
                pw.printf("That username or password does not exist!");
                pw.printf("<a href = \"http://localhost:2020/srv/home\" >Home</a>");
                return;
            }
            
            sess.setAttribute("username", account.userName);
            sess.setAttribute("realName", account.name);
            pw.printf("Logged in as " + name + "</br>");
        }
        pw.printf("<a href=\"http://localhost:2020/srv/home\">Home</a>");
    }

}
