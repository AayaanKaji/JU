package Q18_SSE_Servlet;

import java.io.*;
import java.util.Random;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/Q18_SSE_Servlet/stock_price")
public class StockPriceServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the response content type to text/event-stream
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");

        // Create a PrintWriter to write the data to the response
        PrintWriter out = response.getWriter();
        Random random = new Random();

        while (true) {
            String price1 = String.format("%.2f", 300 * (1 + random.nextDouble() / 10));
            out.write("data: { \"GOOG\": \"" + price1 + "\" }\n\n");
            out.flush();

            try {
                int sleep = (int) (random.nextDouble() * 1000);
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                out.write("Interrupted: " + e);
                break;
            }

            String price2 = String.format("%.2f", 500 * (1 + random.nextDouble() / 10));
            out.write("data: { \"IBM\": \"" + price2 + "\" }\n\n");
            out.flush();

            // Wait for a while before sending the next IBM update
            try {
                int sleep = (int) (random.nextDouble() * 1000);
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                out.write("Interrupted: " + e);
                break;
            }
        }
        out.close();
    }
}
