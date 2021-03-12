package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String error = "";
    	request.setCharacterEncoding("UTF-8");

    	//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
    	HttpSession session = request.getSession(false);
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
    			//パラメータの取得
    			String str_user_id = request.getParameter("user_id");

    			try {
    				//インスタンスを生成（変数でクラスにアクセス）
    				UsersDao dao = new UsersDao();

    				UsersBean bean = new UsersBean();

    				//int型に変換
    				int user_id = Integer.parseInt(str_user_id);

    				//user_idと一致するidを探し、レコードのデータをbeanに格納
    				bean = dao.find(user_id);

    				//リクエストスコープにセットして送る
    				request.setAttribute("bean", bean);
    				request.getRequestDispatcher("userEdit.jsp").forward(request,response);

    			}catch(SQLException e) {
    				error ="DB接続エラーの為、一覧表示はできませんでした。";
    				request.setAttribute("error", error);
    				request.getRequestDispatcher("UserListsServlet").forward(request,response);
    			}catch(Exception e){
    				error ="予期せぬエラーが発生しました。<br>"+e;
    				request.setAttribute("error", error);
    				request.getRequestDispatcher("UserListsServlet").forward(request,response);
    			}
    		}
    	}
    }
}
