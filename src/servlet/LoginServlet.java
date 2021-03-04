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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String str_id = request.getParameter("id");
		String str_pw = request.getParameter("password");

		if(isEmpty(str_id) || isEmpty(str_pw)){
			//入力値が空だったら戻す
			request.setAttribute("error_message", "ユーザーIDとパスワードを入力してください");
			//JSPへ遷移
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}else{
			try {
				// intに変換して取得
				int form_id = Integer.parseInt(str_id);
				UsersDao dao = new UsersDao();
				List<UsersBean> list = dao.findAll();
				// 取得した件数分ループ
				for(int i = 0; i < list.size(); i++) {
					int db_id = list.get(i).getId();
					String db_name = list.get(i).getName();
					String db_pw = list.get(i).getPassword();
					// DBに一致するものがあるかどうか判定
					if (form_id == db_id && str_pw.equals(db_pw)) {
						//あったらセッションに入れる
						HttpSession session = request.getSession();
						session.setAttribute("login_id", db_id);
						session.setAttribute("login_name", db_name);
						session.setAttribute("login_pw", db_pw);
						//ログイン成功でTop画面へ遷移
						RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
						rd.forward(request, response);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
                request.setAttribute("error_message", "内部でエラーが発生しました");
			    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			    rd.forward(request, response);
			}

			// DBに一致するものがないので画面戻す
			request.setAttribute("error_message", "ユーザーIDかパスワードが違います");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

	private boolean isEmpty(String str_pw) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
