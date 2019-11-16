package apps.g0rba4ev;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CalcServlet extends HttpServlet {

    enum State {
        W4OP1,  // waiting for operand 1
        W4OP2   // waiting for operand 2
    }

    /**
     * field for storing (and output) the expression entered by the user
     */
    private String expr = "";
    /**
     * field for storing (and output) the expression entered by the user and the calculation result
     */
    private String result = "";
    /**
     * field for storing first operand
     */
    private double op1;
    /**
     * field for storing second operand
     */
    private double op2;
    /**
     * field for storing required operation for calculating
     */
    private String operation;
    /**
     * field for storing current state of calculator
     */
    private State currState = State.W4OP1;
    /**
     * html-style for calculator's buttons
     */
    private static final String ButtonStyle = "height: 40px; width: 40px;";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String key = req.getParameter("key");

        if(key != null){
            try{
                switch(key){
                    case "CE":
                        currState = State.W4OP1;
                        expr = "";
                        result = "";
                        break;
                    case "C":
                        expr = "";
                        break;
                    case "D":
                        if(!expr.equals("")){
                            expr = expr.substring(0, expr.length()-1);
                        }
                        break;
                    case "0":
                    case "1":
                    case "2":
                    case "3":
                    case "4":
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                    case "9":
                    case ".":
                        expr = expr.equals("") ? key : (expr + key);
                        if(currState == State.W4OP1){
                            result = "";
                        }
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        if(currState == State.W4OP1){
                            op1 = Double.parseDouble(expr);
                            operation = key;
                            result = String.valueOf(op1) + operation;
                            expr = "";
                            currState = State.W4OP2;
                        } else {
                            op2 = Double.parseDouble(expr);
                            calculate(); // нужно ли здесь писать this.calculate()  для лучшей читаемости?
                            operation = key;
                            result = String.valueOf(op1) + operation;
                            expr = "";
                        }
                        break;
                    case "=":
                        if(currState == State.W4OP2){
                            op2 = Double.parseDouble(expr);
                            calculate();
                            result = String.valueOf(op1);
                            expr = "";
                            currState = State.W4OP1;
                        }
                }
            } catch (NumberFormatException nfe) {
                    expr = "";
                    result = "Invalid expression";
                    currState = State.W4OP1;
            }

        }


        resp.setContentType("text/html");
        writePage(resp.getWriter());


    }

    /**
     * calculate result of current operation and put result into {@link CalcServlet#op1}
     */
    private void calculate(){
        switch(operation){
            case "+":
                op1 = op1 + op2;
                break;
            case "-":
                op1 = op1 - op2;
                break;
            case "/":
                op1 = op1 / op2;
                break;
            case "*":
                op1 = op1 * op2;
                break;
        }
    }

    /**
     * Write the page to {@code out}
     *
     * @param out PrintWriter obtained from HttpServletResponse
     */
    private void writePage(PrintWriter out){
        out.println("<!DOCTYPE html>");
        out.println("<html>");

        out.println("<head>");
        out.println("    <title>MyWebApps</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("   <p>");
        out.println("       <input type=\"text\" value=\"" + result + "\" readonly=\"readonly\"> - Result");
        out.println("       <br />");
        out.println("        <input type=\"text\" value=\"" + expr + "\" readonly=\"readonly\"> - Input field");
        out.println("   </p>");

        out.println("   <form action=/calculator>");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=CE>");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"C\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"D\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"/\">");
        out.println("       <br />");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"7\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"8\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"9\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"*\">");
        out.println("       <br  />");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"5\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"6\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"4\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"-\">");
        out.println("       <br />");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"1\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"2\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"3\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"+\">");
        out.println("       <br />");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\".\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"0\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"D\">");
        out.println("       <input type=\"submit\" style=\"" + ButtonStyle + "\" name=\"key\" value=\"=\">");
        out.println("   </form>");

        out.println("</body>");

        out.println("</html>");
    }

}


