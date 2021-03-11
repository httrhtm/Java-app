package servlet;

import java.io.IOException;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		//formで入力された値をDBに保存する

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
			rd.forward(request, response);
		} else {
			Object loginCheck = session.getAttribute("login_id");
			if (loginCheck == null) {
				String message = "ログインしてください";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} else {

				//登録
				try {
					QuestionsDao qdao = new QuestionsDao();

					//セッションの取得
					String register_check = (String) session.getAttribute("register_check");
					//register_checkがnullもしくは、register_checkがOKでなかった場合、以下の処理を行う
					if (register_check == null || !register_check.equals("OK")) {

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

						//AnswersDaoのインスタンスオブジェクトを生成（インスタンス化）
						CorrectAnswersDao adao = new CorrectAnswersDao();

						//QuestionsDAOをInsert
						qdao.create(qb);

						//QuestionsDAOから一番大きいidの値を取得
						int max_id = qdao.getMaxQuestionId();

						//caにquestuons_id をセット
						cab.setQuestionId(max_id);

						//繰り返し処理
						for (int i = 0; i < answer.length; i++) {
							// 答えの入力値が空じゃない場合はセットしてinsert
							if (answer[i] != null) {
								cab.setAnswer(answer[i]);
								adao.create(cab);
							}
						}
						//登録フラグを設定
						register_check = "OK";
						session.setAttribute("register_check", register_check);
					}
					//register_checkのOK/NG問わず行う処理
					List<QuestionsBean> qlist = qdao.findAll();
					if (qlist != null) {
						//必要なデータを一覧画面に渡す
						RequestDispatcher rd = request.getRequestDispatcher("ListServlet");
						rd.forward(request, response);
					}
					//例外をキャッチ
				} catch (Exception e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}


			}
		}
	}
}
