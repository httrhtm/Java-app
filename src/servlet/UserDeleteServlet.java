package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDao;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
		HttpSession session = request.getSession(false);

		//【サーブレットのバリデーション】
		//sessionがnullだった場合、login画面へ遷移
		if (session == null) {
			session = request.getSession(true);
			String message = "ログインしてください";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {
			Object loginCheck = session.getAttribute("login_id");
			if (loginCheck == null){
				String message = "ログインしてください";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} else {

				try {
					//リクエストパラメーターの取得
					String str_id = request.getParameter("id");

					// intに変換
					int id = Integer.parseInt(str_id);

					//インスタンス化
					UsersDao dao = new UsersDao();

					//delete
					dao.delete(id);

					String message = "問題を削除しました";
					request.setAttribute("message", message);

					//list.jspで更新後のデータを表示
					RequestDispatcher rd = request.getRequestDispatcher("UserListsServlet");
					rd.forward(request, response);

					//例外をキャッチ
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
		}

	}
}
