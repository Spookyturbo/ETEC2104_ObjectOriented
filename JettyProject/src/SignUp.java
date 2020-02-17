import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns={"/signup"})
public class SignUp extends HttpServlet
{    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        resp.setContentType("text/html");
        var pw = resp.getWriter();
        var name = req.getParameter("user");
        var password = req.getParameter("pass");
        var realName = req.getParameter("name");
        
        if( name == null ){
            pw.printf("No username provided");
        }
        else {
            for (int i = AccountManager.accounts.size() - 1; i >= 0; i--)
            {
                if (name.equals(AccountManager.accounts.get(i).userName))
                {
                    pw.print("Account already exists. </br>");
                    pw.printf("<a href=\"http://localhost:2020/srv/home\">Home</a>");
                    return;
                }
            }
        }
        
        if(password == null) {
            pw.printf("No password provided");
        }
        else if(realName == null) {
            pw.printf("No real name provided");
        }
        else {
            Account acc = new Account(name, password, realName);
            AccountManager.accounts.add(acc);
            var sess = req.getSession();
            sess.setAttribute("username", acc.userName);
            sess.setAttribute("realName", acc.name);
            
            pw.printf("Logged in as " + acc.userName + "\nWelcome " + realName);
        }
        pw.printf("<a href=\"http://localhost:2020/srv/home\">Home</a>");
    }

}
