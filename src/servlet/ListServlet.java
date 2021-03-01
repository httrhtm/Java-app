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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String error = "";

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

			//検索結果を持ってlist.jspにフォワード
			request.setAttribute("qlist", qlist);
			request.setAttribute("calist", calist);

		}catch(SQLException e) {
            error ="DB接続エラーの為、一覧表示はできませんでした。";
        }catch(Exception e){
            error ="予期せぬエラーが発生しました。<br>"+e;
        }finally{
        	request.setAttribute("error", error);
        	RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
			rd.forward(request, response);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
