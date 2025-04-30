package error_pages;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/error_pages/ExceptionHandlerServlet")
public class ExceptionHandlerServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Analyze the servlet exception
		Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

		if (servletName == null) {
			servletName = "Unknown";
		}

		String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

		if (requestUri == null) {
			requestUri = "Unknown";
		}

		// Set response content type
		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		String title = "Error/Exception Information";

		out.println("<!doctype html>\n" + "<html>\n" +
				"<head><title>" + title + "</title></head>\n" +
				"<body bgcolor = \"#f0f0f0\">\n");

		if (throwable == null && statusCode == null) {
			out.println("<h1>Error information not found</h1>");
			out.println(
					"Let's go back to <a href=\"" + response.encodeURL("/") + "\">Home Page</a>.");
		} else if (statusCode != null) {
			out.println("The status code of an error is : " + statusCode);
		} else {
			out.println("<h2>Error information</h2>");
			out.println("Servlet Name : " + servletName + "</br></br>");
			out.println("Exception Type : " + throwable.getClass().getName() + "</br></br>");
			out.println("The request URI: " + requestUri + "<br><br>");
			out.println("The exception message: " + throwable.getMessage());
		}
		out.println("</body>");
		out.println("</html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
