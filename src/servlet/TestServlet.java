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

import bean.QuestionsBean;
import dao.QuestionsDao;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String error = "";

		//test.jspに全てのquestionを送る

		try {
			//配列宣言
			List<QuestionsBean> qlist = new ArrayList<QuestionsBean>();

			//オブジェクト宣言
			QuestionsDao qdao = new QuestionsDao();

			//findAllで全メソッドを呼び出し
			qlist = qdao.RandAll();

			//検索結果を持ってlist.jspにフォワード
			request.setAttribute("qlist", qlist);

			RequestDispatcher rd = request.getRequestDispatcher("test.jsp");
			rd.forward(request, response);

		}catch(SQLException e) {
            error ="DB接続エラーの為、一覧表示はできませんでした。";
            RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
            rd.forward(request, response);
        }catch(Exception e){
            error ="予期せぬエラーが発生しました。<br>"+e;
            RequestDispatcher rd = request.getRequestDispatcher("Top.jsp");
            rd.forward(request, response);
	    }
	}

}
