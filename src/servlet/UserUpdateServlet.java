package servlet;

import java.io.IOException;

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
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
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
    		//sessionがnullでなかった場合 = ログインしていた場合
    		} else {
    			//DBに保存するための処理
    			try {
    				//パラメーターの取得
    				String str_user_id = request.getParameter("id");
					String password = request.getParameter("password");
					String admin = request.getParameter("admin_flag");
					System.out.println(str_user_id);
					System.out.println(password);
					System.out.println(admin);

					//インスタンスの生成
					UsersDao dao = new UsersDao();

					//String型のidをint型に変換
					int user_id = Integer.parseInt(str_user_id);
					System.out.println(user_id);

					//String型のadminをbyte型に変換
					byte byte_admin = Byte.parseByte(admin);
					System.out.println(admin);

					//リクエストパラメーターから受け取った値をセッタを使って書き込む
					UsersBean bean = new UsersBean();
					bean.setId(user_id);
					bean.setPassword(password);
					bean.setAdminFlag(byte_admin);

					//cerate DBに保存
					dao.update(bean);

    				//一覧画面に遷移
    				RequestDispatcher rd = request.getRequestDispatcher("UserListsServlet");
    				rd.forward(request, response);

    				//例外をキャッチ
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }

	private Integer parseInt(String str_user_id) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
