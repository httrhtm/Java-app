package servlet;

import java.io.IOException;
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
 * Servlet implementation class Register
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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

		//formで入力された値をDBに保存する

		//formの値を日本語にする
		request.setCharacterEncoding("UTF-8");

		//登録
		try {
			//リクエストパラメーターの取得
			//変数に保存
			String question = request.getParameter("question");
			String[] answer = request.getParameterValues("answer");

			//リクエストパラメーターから受け取った値をセッタを使って書き込む
			QuestionsBean qb = new QuestionsBean();
			qb.setQuestion(question);

			//配列で受け取る
			CorrectAnswersBean cab = new CorrectAnswersBean();

			//データベースに追加するデータを保持するQuestionsとAnswersオブジェクトを作成
			//QuestionsDaoのインスタンスオブジェクトを生成（インスタンス化）
			QuestionsDao qdao = new QuestionsDao();
			//AnswersDaoのインスタンスオブジェクトを生成（インスタンス化）
			CorrectAnswersDao adao = new CorrectAnswersDao();

			//QuestionsDAOをInsert
			qdao.create(qb);

			//QuestionsDAOから一番大きいidの値を取得して+1
			int max_id = qdao.getMaxQuestionId() + 1;

			//caにquestuons_id をセット
			cab.setQuestionId(max_id);
			//繰り返し処理
			for (int i = 0; i < answer.length; i++) {
				// 答えの入力値が空じゃない場合はセットしてinsert
				if (answer[i]!= null) {
					cab.setAnswer(answer[i]);
					adao.create(cab);
				}
			}

			//QuestionsDAOで取ってきた情報をlistに入れる
			List<QuestionsBean> list = qdao.findAll();

			if(list != null) {
				request.setAttribute("list", list);

				//画面を/listに遷移する
				RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
				rd.forward(request, response);
			}
				//例外をキャッチ
			} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
