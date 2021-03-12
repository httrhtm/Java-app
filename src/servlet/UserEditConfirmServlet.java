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
 * Servlet implementation class UserEditConfirmServlet
 */
@WebServlet("/UserEditConfirmServlet")
public class UserEditConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditConfirmServlet() {
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
    			//パラメーターの取得
    			String str_user_id = request.getParameter("id");
    			String name = request.getParameter("name");
    			String password = request.getParameter("password");
    			String password_confirm = request.getParameter("password_confirm");
    			String admin_check = request.getParameter("admin_check");

    			//【バリデーション】
    			//passwordの入力値が空、もしくは半角英数字でなかった場合、ユーザー登録画面に戻す
    			if(password == null || !password.matches("^[A-Za-z0-9]+$")){
    				request.setAttribute("message", "パスワードを半角英数字で入力してください");
    				request.setAttribute("user_id", str_user_id);
    				RequestDispatcher rd = request.getRequestDispatcher("UserEditServlet");
    				rd.forward(request, response);
    				//passwordがpassword_confirmと一致しなかった場合、ユーザー登録画面に戻す
    			}else if(!password.equals(password_confirm)) {
    				request.setAttribute("message", "PWとPW確認が一致しませんでした");
    				request.setAttribute("user_id", str_user_id);
    				RequestDispatcher rd = request.getRequestDispatcher("UserEditServlet");
    				rd.forward(request, response);
    				//passwordの長さが8文字より短かった場合、ユーザー登録画面に戻す
    			}else if(password.length() < 8) {
    				request.setAttribute("message", "パスワードを8文字以上で入力してください");
    				request.setAttribute("user_id", str_user_id);
    				RequestDispatcher rd = request.getRequestDispatcher("userEditServlet");
    				rd.forward(request, response);

    				//全てが正しく入力されていた場合、確認画面へ遷移
    			}else{
    				//admin_checkがnullでない場合、adminに"あり"をセットする
    				//admin_checkがnullでない場合、admin_flagに"1"をセットする
    				if(admin_check != null){
    					request.setAttribute("admin", "あり");
    					request.setAttribute("admin_flag", 1);
    				}else{
    					request.setAttribute("admin", "なし");
    					request.setAttribute("admin_flag", 0);
    				}

    				//入力値をセット
    				request.setAttribute("id", str_user_id);
    				request.setAttribute("name", name);
    				request.setAttribute("password", password);
    				request.setAttribute("password_confirm", password_confirm);
    				//リクエストパラメータを送る
    				RequestDispatcher rd = request.getRequestDispatcher("userEditConfirm.jsp");
    				rd.forward(request,response);
    			}
    		}
    	}
    }
}
