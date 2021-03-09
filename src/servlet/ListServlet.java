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
		String error_msg = null;

		try {

			//配列宣言
			List<QuestionsBean> qlist = new ArrayList<QuestionsBean>();
			List<CorrectAnswersBean> calist = new ArrayList<CorrectAnswersBean>();

			//オブジェクト宣言
			QuestionsDao qdao = new QuestionsDao();
			CorrectAnswersDao adao = new CorrectAnswersDao();

			//findAllで全メソッドを呼び出し
			qlist = qdao.findAll();
			calist = adao.findAll();

			//qlistがなかった場合、新規登録画面へ遷移
			if(qlist.size() == 0) {
				RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
				rd.forward(request, response);
			} else {
				//検索結果を持ってlist.jspにフォワード
				request.setAttribute("qlist", qlist);
				request.setAttribute("calist", calist);

				RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
				rd.forward(request, response);
			}

		}catch(SQLException e) {
            error_msg ="DB接続エラーの為、一覧表示はできませんでした。";
            request.setAttribute("error_msg", error_msg);
          //login.jspにリダイレクト
		    RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
		    rd.forward(request, response);
        }catch(Exception e){
            error_msg ="予期せぬエラーが発生しました。<br>"+e;
            request.setAttribute("error_msg", error_msg);
          //login.jspにリダイレクト
		    RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
		    rd.forward(request, response);
	    }
	}

}
