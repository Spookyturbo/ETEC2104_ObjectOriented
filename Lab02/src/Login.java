import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns={"/login"})
public class Login extends HttpServlet
{
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/plain");
        var pw = resp.getWriter();
        var name = req.getParameter("user");
        var password = req.getParameter("pass");
        if( name == null ){
            pw.printf("No username provided");
        }
        else if( password == null) {
            pw.printf("No password provided");
        }
        else {
            //Needs to check existing accounts
            var sess = req.getSession();
            Account account = null;
            for(Account acc : SignUp.accounts) {
                if(acc.equals(name, password)) {
                    account = acc;
                    break;
                }
            }
            
            if(account == null) {
                pw.printf("That account does not exist!");
                return;
            }
            
            sess.setAttribute("username", account.userName);
            sess.setAttribute("realName", account.name);
            pw.printf("Logged in as " + name);
        }
    }

}
