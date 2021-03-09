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
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String message = "ログアウトしました";

		try {
			HttpSession session = request.getSession(true);
		    session.invalidate();

		    request.setAttribute("message", message);
	        //login.jspにリダイレクト
		    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		  //リスエストパラメータとレスポンスを引き継いでもらう
		    rd.forward(request, response);
		  //例外処理
		} catch (Exception e) {
			//実行したメソッドの時系列の一覧
			e.printStackTrace();
			//エラーメッセージ
			message = "内部でエラーが発生しました";
            request.setAttribute("error_msg", message);
            //login.jspにリダイレクト
		    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		  //リスエストパラメータとレスポンスを引き継いでもらう
		    rd.forward(request, response);
		}

	}

}
