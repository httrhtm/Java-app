package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditConfirmServlet
 */
@WebServlet("/EditConfirmServlet")
public class EditConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditConfirmServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
		HttpSession session = request.getSession(false);
		//sessionがnullだった場合、login画面へ遷移
		if (session == null) {
			session = request.getSession(true);
			String message = "ログインしてください";
			request.setAttribute("message", message);
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}else {
			Object loginCheck = session.getAttribute("login_id");
			if (loginCheck == null){
				String message = "ログインしてください";
				request.setAttribute("message", message);
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			} else {
				//edit.jspの入力値をeditConfirm.jspで表示
				request.setCharacterEncoding("UTF-8");
				String question_id = request.getParameter("questionId");
				String question = request.getParameter("question");
				String[] answer_id = request.getParameterValues("answerId");
				String[] answer = request.getParameterValues("answer");

				if(isEmpty(question)){
					//入力値が空だったら戻す
					request.setAttribute("error_msg", "問題を入力してください");
					RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request, response);
				}else if(question.length() > 500) {
					request.setAttribute("error_msg", "問題の文字数が500文字を超えています");
					RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
					rd.forward(request,response);

				}else{
					//answerの配列の長さ分、ループ処理
					for(int j=0;j<answer.length;j++){
						if(answer[j].length() == 0) {
							request.setAttribute("error_msg", "答えを入力してください");
							RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
							rd.forward(request, response);
							//answerが200文字以上だった場合
						}else if (answer[j].length() > 200) {
							request.setAttribute("error_msg", "答えの文字数が200文字を超えています");
							RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
							rd.forward(request,response);
						}else {
							//入力値をsetAttribute()で登録
							request.setAttribute("question_id", question_id);
							request.setAttribute("question", question);
							request.setAttribute("answer_id", answer_id);
							request.setAttribute("answer", answer);
							RequestDispatcher rd = request.getRequestDispatcher("editConfirm.jsp");
							rd.forward(request,response);
						}
					}
				}
			}
		}
	}

	private boolean isEmpty(String question) {
		return (question == null || question.length() == 0);
	}

}
