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

import bean.UsersBean;
import dao.UsersDao;

/**
 * Servlet implementation class UserListServlet
 */
@WebServlet("/UserListsServlet")
public class UserListsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserListsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//変数 errorを定義
    	String error = "";
    	try {
    		//配列宣言
    		List<UsersBean> list = new ArrayList<UsersBean>();

    		//インスタンス生成
    		UsersDao dao = new UsersDao();

    		//findAllでdaoで取得した全てのデータを、それぞれのlistへ格納
    		list = dao.findAll();

    		//setしてuserListへ送る
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("userLists.jsp");
			rd.forward(request, response);

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
