import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CalcServlet extends HttpServlet {

    private String expr = "Enter expression";
    private String result;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String key = req.getParameter("key");
        if(key == null){

        } else if ( "0123456789CECD.*/+-".contains(key)){ // каким способом здесь лучше проверять принадлежность параметра диапозону допустимых значений? нормально так или лучше коллекцию создать с допустимым диапозоном?
            if(expr == "Enter expression"){
                expr = key;
            } else {
                expr = expr + key;
            }
        }

        resp.setContentType("text/html");
        writePage(resp.getWriter());


    }

    private void writePage(PrintWriter out){
        out.println("<!DOCTYPE html>");
        out.println("<html>");

        out.println("<head>");
        out.println("    <title>MyWebApps</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("   <p>");
        out.println("       Expression:" + expr);
        out.println("   </p>");
        out.println("   <p>");
        out.println("       Result:" + result);
        out.println("   </p>");

        out.println("   <br />");
        out.println("   <form action=/calculator>");
        out.println("       <input type=\"submit\" name=\"key\" value=\"CE\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"C\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"D\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"/\">");
        out.println("       <br />");
        out.println("       <input type=\"submit\" name=\"key\" value=\"7\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"8\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"9\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"*\">");
        out.println("       <br />");
        out.println("       <input type=\"submit\" name=\"key\" value=\"4\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"5\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"6\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"-\">");
        out.println("       <br  />");
        out.println("       <input type=\"submit\" name=\"key\" value=\"1\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"+\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\".\">");
        out.println("       <input type=\"submit\" name=\"key\" value=\"0\">");
        out.println("   </form>");

        out.println("</body>");

        out.println("</html>");
    }

}


