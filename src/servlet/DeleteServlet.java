package servlet;

import java.io.IOException;

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
			//リクエストパラメーターの取得
			//変数に保存
			String str_question_id = request.getParameter("questionId");
			String question = request.getParameter("question");
			String[] str_answer_id = request.getParameterValues("answerId");
			String[] str_questions_id = request.getParameterValues("questionsId");
			String[] answer = request.getParameterValues("answer");

			// intに変換して取得
			int question_id = Integer.parseInt(str_question_id);

			//リクエストパラメーターから受け取った値をセッタを使って書き込む
			QuestionsBean qb = new QuestionsBean();
			qb.setId(question_id);
			qb.setQuestion(question);

			//配列で受け取る
			CorrectAnswersBean cab = new CorrectAnswersBean();

			//データベースに追加するデータを保持するQuestionsとAnswersオブジェクトを作成
			//QuestionsDaoのインスタンスオブジェクトを生成（インスタンス化）
			QuestionsDao qdao = new QuestionsDao();
			CorrectAnswersDao cadao = new CorrectAnswersDao();

			//QuestionsDAOをdelete
			qdao.delete(qb); //delete

			int[] answer_ids = new int[str_answer_id.length];
			int[] questions_id = new int[str_questions_id.length];

			//配列の長さ分繰り返してdelete
			for (int i = 0; i < answer.length; i++) {
				//数値型に変換
				answer_ids[i] =  Integer.parseInt(str_answer_id[i]);
				questions_id[i] =  Integer.parseInt(str_questions_id[i]);

				//question.idとcorrect_answers.question_idが同じ場合にdeleteする
				if (question_id == questions_id[i]) {
					//idを配列で取得する
					cab.setId(answer_ids[i]);
					cab.setQuestionId(questions_id[i]);
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
	}

}
