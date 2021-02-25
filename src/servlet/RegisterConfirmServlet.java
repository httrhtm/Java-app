package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterConfirmServlet")
public class RegisterConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//register.jspの入力値をregisterConfirm.jspで表示
		request.setCharacterEncoding("UTF-8");
		String question = request.getParameter("question");
		String[] answer = request.getParameterValues("answer");

		if(isEmpty(question) || answer.length == 0){
			//入力値が空だったら戻す
			request.setAttribute("error_message", "問題と回答を入力してください");
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
			rd.forward(request, response);
		}else if(question.length() > 255) {
			request.setAttribute("error_message", "文字数が多いです");
			RequestDispatcher rd = request.getRequestDispatcher("register.jsp");

			rd.forward(request, response);
		}else {
			//入力値をsetAttribute()で登録
			request.setAttribute("question", question);
			request.setAttribute("answer", answer);

			RequestDispatcher rd = request.getRequestDispatcher("registerConfirm.jsp");

			rd.forward(request,response);
		}
	}

	private boolean isEmpty(String question) {
		// TODO 自動生成されたメソッド・スタブ
		return (question == null || question.length() == 0);
	}
}