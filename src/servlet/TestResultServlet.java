package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class TestResultServlet
 */
@WebServlet("/TestResultServlet")
public class TestResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		//answer
//		String	str_question_id = request.getParameter("questionId");
		//test.jspで入力された値を取得
//		String answer = request.getParameter("answer");

		//入力値とanswerが同じか？ if文で確認

		//同じ場合 historiesのpointをプラス1してDBに保存 historiesDaoにメソッドを作成


		/**
	     * ログインユーザー名を表示するための処理
	     */
		//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
		HttpSession session = request.getSession(false);

		//セッションオブジェクトが取得でき、セッションオブジェクトのcounterオブジェクトに値があるか判定
		if (session != null) {
			try {
				String name = (String)session.getAttribute("login_name");
				request.setAttribute("user_name", name);
				request.getRequestDispatcher("testResult.jsp").forward(request,response);
			} catch(Exception e) {
				e.printStackTrace();
                request.setAttribute("error_message", "内部でエラーが発生しました");
			    RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
			    rd.forward(request, response);
			}

		}



	}

}
