import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet(urlPatterns={"/clear"})
public class ClearAccounts extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{
        resp.setContentType("text/html");
        var pw = resp.getWriter();
        AccountManager.accounts.clear();
        pw.println("Cleared accounts");
        pw.println("There are now " + AccountManager.accounts.size() + " accounts");
    }
}
