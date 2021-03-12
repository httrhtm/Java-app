package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UsersBean;
import dao.UsersDao;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserRegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
		HttpSession u_session = request.getSession(false);

		//sessionがnullだった場合、ログイン画面へ遷移
		if (u_session == null) {
			u_session = request.getSession(true);
			String message = "ログインしてください";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {
			Object loginCheck = u_session.getAttribute("login_id");
			if (loginCheck == null){
				String message = "ログインしてください";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);

				//sessionがnullでなかった場合 = ログインしていた場合
			} else {
				//DBに保存するための処理
				try{
					//インスタンスの生成
					UsersDao dao = new UsersDao();

					//ユーザーのセッションの取得
					String user_check = (String) u_session.getAttribute("user_check");

					//セッションがなかった場合、もしくはセッションの値がOKでなかった場合、以下の処理を行う
					if (user_check == null || !user_check.equals("done")) {

						//パラメーターの取得
						String name = request.getParameter("name");
						String password = request.getParameter("password");
						String admin = request.getParameter("admin_flag");

						//String型のadminをbyte型にキャスト
						byte byte_admin = Byte.parseByte(admin);

						//リクエストパラメーターから受け取った値をセッタを使って書き込む
						UsersBean bean = new UsersBean();
						bean.setName(name);
						bean.setPassword(password);
						bean.setAdminFlag(byte_admin);

						//cerate DBに保存
						dao.create(bean);

						//登録フラグを設定
						user_check = "done";
						u_session.setAttribute("user_check", user_check);
					}
					//user_checkのOK/NG問わず行う処理
					List<UsersBean> list = dao.findAll();
					if (list != null) {
						//一覧画面に遷移
						RequestDispatcher rd = request.getRequestDispatcher("UserListsServlet");
						rd.forward(request, response);
					}

				}catch(Exception e){
					e.printStackTrace();
				}
			}

		}
	}
}
