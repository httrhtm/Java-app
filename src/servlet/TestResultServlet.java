package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CorrectAnswersBean;
import bean.HistoriesBean;
import bean.QuestionsBean;
import dao.CorrectAnswersDao;
import dao.HistoriesDao;
import dao.QuestionsDao;

/**
 * Servlet implementation class TestResultServlet
 */
@WebServlet("/TestResultServlet")
public class TestResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

/**
 * ログインユーザー名を表示するための処理
 */
//HttpServletRequest.getSession()メソッドを呼び出しHttpSessionを取得
		HttpSession session = request.getSession(false);
//取得できるか判定
		if (session != null) {
			try {
				String name = (String)session.getAttribute("login_name");
				request.setAttribute("user_name", name);
			} catch(Exception e) {
				e.printStackTrace();
                request.setAttribute("error_message", "内部でエラーが発生しました");
			    RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
			    rd.forward(request, response);
			}
		}
/**
 * 採点するための処理
 */
		try {
//パラメータ取得
			//questionsのidを変数に代入
			String str_question_id = request.getParameter("questionId");
			//入力値を変数に代入
			String[] str_answer = request.getParameterValues("answer");
//変換
			int question_id = Integer.parseInt(str_question_id);
//宣言（クラスにアクセス）
			QuestionsDao qdao = new QuestionsDao();
			CorrectAnswersDao cadao = new CorrectAnswersDao();
			HistoriesDao hdao = new HistoriesDao();
//リストの呼び出し
			List<CorrectAnswersBean> calist = cadao.findAll();
			List<QuestionsBean> qlist = qdao.findAll();
//問題数を取得
			int q_total = qlist.size();
//Beanの呼び出し
			HistoriesBean hb = new HistoriesBean();


//【処理】
//pointを定義
			int point = 0;
//【問題】繰り返し処理
			for(int i=0;i< q_total;i++){
//【答え】繰り返し処理
				for(int j=0;j<calist.size();j++){
//【条件】questions.idとcalist.question_idが同じ場合
//correct_answersのanswerを取得
					if(question_id != calist.get(j).getQuestionId()){
						continue;
					}
//【入力値】繰り返し処理
					for(int k=0;k<str_answer.length; k++) {
//【条件】correct_answers.answerと入力値が同じ場合（stringだからequalsメソッド）
						if(calist.get(j).getAnswer().equals(str_answer[k])) {
							point++;
							break;
						}else {
							System.out.println("指定されたインデックスの値が不正です。index = " + j);
						}
					} //for(問題)
				} //for(答え)
			} //for(入力値)
/**
 * 点数を表示するための処理
 */
			int score = 0;
			score = Math.round((point* 100) / q_total);
			System.out.println(score);
/**
 * 現在時刻を取得するための処理
 */
//インスタンスの生成
			Date dateObj = new Date();
//日時情報を指定フォーマットの文字列で取得
			SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
			String date = format.format( dateObj );
/**
 * testResultへ渡すための処理
 */
//問題数
			request.setAttribute("total", q_total);
//正解数
			request.setAttribute("point", point);
//点数
			request.setAttribute("score", score);
//現在時刻
			request.setAttribute("date", date);
/**
 * historiesにレコードを作成ための処理
 */
//user_idをセッションで取得
			int user_id = (Integer)session.getAttribute("login_id");
//beanにセット
			hb.setUserId(user_id);
			hb.setPoint(point);
//createメソッドを呼び出して保存
			hdao.create(hb);
/**
 * testResultへ送信
 */
		RequestDispatcher rd = request.getRequestDispatcher("testResult.jsp");
		rd.forward(request, response);
/**
 * 例外処理
 */
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
