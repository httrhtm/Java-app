package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CorrectAnswersBean;
import bean.QuestionsBean;
import dao.CorrectAnswersDao;
import dao.QuestionsDao;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet("/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//変数 errorを定義
		String error = "";

		//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
		HttpSession session = request.getSession(false);

		/**
		 * ログインしていない場合の処理
		 */
		//sessionがnullだった場合、login画面へ遷移
		if (session == null) {
			String message = "ログインしてください";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {
			//nullではなかった場合、現在ログイン中のlogin_idを取得する
			Object loginCheck = session.getAttribute("login_id");
			//ログイン中のlogin_idがなかったらログイン画面へ遷移
			if (loginCheck == null){
				String message = "ログインしてください";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} else {
				/**
				 * ログインしている場合の処理
				 */
				try {
					//配列宣言
					List<QuestionsBean> qlist = new ArrayList<QuestionsBean>();
					List<CorrectAnswersBean> calist = new ArrayList<CorrectAnswersBean>();
					//インスタンス生成
					QuestionsDao qdao = new QuestionsDao();
					CorrectAnswersDao adao = new CorrectAnswersDao();
					//findAllでそれぞれのdaoで取得した全てのデータを、それぞれのlistへ格納
					qlist = qdao.findAll();
					calist = adao.findAll();

					//【条件分岐】
					//qlistの中身がなかった場合、新規登録画面へ遷移
					if(qlist.size() == 0) {
						RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
						rd.forward(request, response);
					} else {
						//qlistの中身があった場合、検索結果を持って問題一覧画面へ遷移
						request.setAttribute("qlist", qlist);
						request.setAttribute("calist", calist);
						RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
						rd.forward(request, response);
					}
				/**
				 * 例外処理
				 */
				} catch(SQLException e) {
					error ="DB接続エラーの為、一覧表示はできませんでした。";
					request.setAttribute("error", error);
					//login.jspにリダイレクト
					RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
					rd.forward(request, response);
				}catch(Exception e){
					error ="予期せぬエラーが発生しました。<br>"+e;
					request.setAttribute("error_msg", error);
					//login.jspにリダイレクト
					RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
					rd.forward(request, response);
				}
			}
		}
	}
}
