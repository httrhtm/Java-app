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
		// TODO Auto-generated method stub
		String error = "";

		try {
			
			
			//配列宣言
			List<HistoriesBean> hlist = new ArrayList<HistoriesBean>();

			//オブジェクト宣言
			HistoriesDao hdao = new HistoriesDao();

			//findAllで全メソッドを呼び出し
			hlist = hdao.findAll();
			

			//検索結果を持ってlist.jspにフォワード
			request.setAttribute("hlist", hlist);

			RequestDispatcher rd = request.getRequestDispatcher("history.jsp");
			rd.forward(request, response);

		}catch(SQLException e) {
            error ="DB接続エラーの為、一覧表示はできませんでした。";
        }catch(Exception e){
            error ="予期せぬエラーが発生しました。<br>"+e;
        }finally{
        	request.setAttribute("error", error);
	    }
	}

}
