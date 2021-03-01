package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.QuestionsBean;
import dao.QuestionsDao;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//formの値を日本語にする
		request.setCharacterEncoding("UTF-8");

		try {
			//リクエストパラメーターの取得
			//変数に保存
			String question_id = request.getParameter("questionId");
			String question = request.getParameter("question");

			// intに変換して取得
			int que_id = Integer.parseInt(question_id);

			//リクエストパラメーターから受け取った値をセッタを使って書き込む
			QuestionsBean qb = new QuestionsBean();
			qb.setId(que_id);
			qb.setQuestion(question);

			//データベースに追加するデータを保持するQuestionsとAnswersオブジェクトを作成
			//QuestionsDaoのインスタンスオブジェクトを生成（インスタンス化）
			QuestionsDao qdao = new QuestionsDao();

			//QuestionsDAOをInsert
			qdao.update(qb);

			//QuestionsDAOで取ってきた情報をlistに入れる
//			List<QuestionsBean> list = qdao.findAll();

//			request.setAttribute("list", list);

			//list.jspで更新後のデータを表示
			RequestDispatcher rd = request.getRequestDispatcher("ListServlet");
			rd.forward(request, response);

		//例外をキャッチ
		} catch (Exception e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
		}
	}

}
