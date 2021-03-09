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
import javax.servlet.http.HttpSession;

import bean.CorrectAnswersBean;
import bean.QuestionsBean;
import dao.CorrectAnswersDao;
import dao.QuestionsDao;

/**
 * Servlet implementation class DeleteConfirmServlet
 */
@WebServlet("/DeleteConfirmServlet")
public class DeleteConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String error = "";
		request.setCharacterEncoding("UTF-8");

	//questionは1つだけ使うからListを使う必要がない＝beanから直接持ってくる
	//answerは問題に対して答えが複数あるから配列で取る
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
				//パラメータの取得
				String question_id = request.getParameter("questionId");
				try {
					List<CorrectAnswersBean> calist = new ArrayList<CorrectAnswersBean>();
					//インスタンスを生成（変数でクラスにアクセス）
					QuestionsDao qdao = new QuestionsDao();
					CorrectAnswersDao adao = new CorrectAnswersDao();
					QuestionsBean qbean = new QuestionsBean();

					// intに変換して取得
					int que_id = Integer.parseInt(question_id);

					//メソッドを呼び出し
					qbean = qdao.find(que_id); //findの引数はint
					calist = adao.findAll();

					// DBに一致するものがあるかどうか判定
					if (question_id != null) {

						//あったらリクエストスコープに登録
						request.setAttribute("qbean", qbean);
						request.setAttribute("calist", calist);

						request.getRequestDispatcher("deleteConfirm.jsp").forward(request,response);
					}
				}catch(SQLException e) {
		            error ="DB接続エラーの為、一覧表示はできませんでした。";
		        }catch(Exception e){
		            error ="予期せぬエラーが発生しました。<br>"+e;
		        }finally{
		        	request.setAttribute("error", error);
		        	request.getRequestDispatcher("list.jsp").forward(request,response);
			    }

            }
		}
	}

}
