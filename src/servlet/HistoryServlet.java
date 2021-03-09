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

import bean.HistoriesBean;
import dao.HistoriesDao;

/**
 * Servlet implementation class HistoryServlet
 */
@WebServlet("/HistoryServlet")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoryServlet() {
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
				try {
					//オブジェクト宣言
					HistoriesDao hdao = new HistoriesDao();
					//findAll：hlistに全てのデータを格納
					List<HistoriesBean> hlist = hdao.findAll();

					//リクエストスコープにhlist属性を追加
					request.setAttribute("hlist", hlist);

					//history.jspに遷移
					RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
					rd.forward(request, response);

				} catch(Exception e) {
					e.printStackTrace();
		            request.setAttribute("error_message", "内部でエラーが発生しました");
				    RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
				    rd.forward(request, response);
				}
            }
		}
	}

}
