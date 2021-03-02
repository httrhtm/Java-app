package servlet;

import java.io.IOException;

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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
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
			String[] str_answer_id = request.getParameterValues("answerId");
			String[] answer = request.getParameterValues("answer");

			// intに変換して取得
			int que_id = Integer.parseInt(question_id);

			//リクエストパラメーターから受け取った値をセッタを使って書き込む
			QuestionsBean qb = new QuestionsBean();
			qb.setId(que_id);
			qb.setQuestion(question);

			//配列で受け取る
			CorrectAnswersBean cab = new CorrectAnswersBean();

			//データベースに追加するデータを保持するQuestionsとAnswersオブジェクトを作成
			//QuestionsDaoのインスタンスオブジェクトを生成（インスタンス化）
			QuestionsDao qdao = new QuestionsDao();
			CorrectAnswersDao cadao = new CorrectAnswersDao();

			//QuestionsDAOをupdate
			qdao.delete(qb); //delete

			int[] answer_ids = new int[str_answer_id.length];

			//配列の長さ分繰り返してupdate
			for (int i = 0; i < answer.length; i++) {
				//数値型に変換
				answer_ids[i] =  Integer.parseInt(str_answer_id[i]);
				if (answer[i]!= null) {
					//idを配列で取得する
					cab.setId(answer_ids[i]);
					cab.setAnswer(answer[i]);
					cadao.delete(cab); //delete
				}
			}

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
