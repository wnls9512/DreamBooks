package publisher.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PublisherPostingBookServlet
 */
@WebServlet("/publisher/postingBookFrm")
public class PublisherPostingBookFrmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public PublisherPostingBookFrmServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		
		request.getRequestDispatcher("/WEB-INF/views/publisher/BookInsertFormView.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
