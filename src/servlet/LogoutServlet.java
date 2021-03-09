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

		//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
		HttpSession session = request.getSession(false);
		//sessionがnullだった場合、login画面へ遷移
		if (session == null) {
			session = request.getSession(true);
			String message = "ログインしてください";
        	request.setAttribute("message", message);
        	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		}else {
            Object loginCheck = session.getAttribute("login_id");
            if (loginCheck == null){
            	String message = "ログインしてください";
            	request.setAttribute("message", message);
            	RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
            } else {
		String message = "ログアウトしました";

		try {
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
	}

}
