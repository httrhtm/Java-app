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

import bean.UsersBean;
import dao.UsersDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
//extendsでHttpServletクラスを継承した子クラス
//親クラス（HttpServlet）のインスタンスや変数にアクセスすることが可能
//HTTPServletを継承することで、Webサイトに適したHTTPサーブレットを作成
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//シリアライズを使うことでインスタンスを外部記憶装置などに保存し、インスタンスの情報を永続化できる
	//Serializableクラスのバージョン管理に使う

	//オーバーライド（親クラスのメソッドを子クラスで継承）された変数やインスタンスを参照する場合に使用する
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		//IDとpwの入力値を取得
		String str_id = request.getParameter("id");
		String str_pw = request.getParameter("password");
		String message = null;

		//どちらか一方でも入力値が空欄の場合
		if(isEmpty(str_id) || isEmpty(str_pw)){
			message = "IDとpwを入力してください";
			request.setAttribute("error_msg", message);
			//JSPへ遷移
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);

		//入力値が空でなかった場合
		}else{
			//成功させたい処理
			try {
				//入力値のstr_idをintに変換
				int form_id = Integer.parseInt(str_id);
				//UsersDaoをインスタンス化
				UsersDao dao = new UsersDao();
				//UsersDaoデータを全件取得し、listに格納
				List<UsersBean> list = dao.findAll();
				// リストのデータ数分ループ処理
				for(int i = 0; i < list.size(); i++) {
					//listに格納されているidをdb_idに代入して取得
					int db_id = list.get(i).getId();
					//listに格納されているnameをdb_nameに代入して取得
					String db_name = list.get(i).getName();
					//listに格納されているpasswordをdb_pwに代入して取得
					String db_pw = list.get(i).getPassword();
					// 入力値とリストから取得した値が一致していた場合
					if (form_id == db_id && str_pw.equals(db_pw)) {
						//セッションオブジェクトの生成、取得
						HttpSession session = request.getSession();
					//sessionにデータを登録
						//db_idをlogin_idとして登録　【session.setAttribute("データの名前", 登録するデータ);】
						session.setAttribute("login_id", db_id);
						session.setAttribute("login_name", db_name);
						session.setAttribute("login_pw", db_pw);
						//フォワード先の指定
						RequestDispatcher rd = request.getRequestDispatcher("top.jsp");
						//リスエストパラメータとレスポンスを引き継いでもらうために渡す
						rd.forward(request, response);
					}
				}
			//例外処理
			} catch (Exception e) {
				//実行したメソッドの時系列の一覧
				e.printStackTrace();
				//エラーメッセージ
				message = "内部でエラーが発生しました";
                request.setAttribute("error_msg", message);
                //login.jspにリダイレクト
			    RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			  //リスエストパラメータとレスポンスを引き継いでもらう
			    rd.forward(request, response);
			}
			// DBに一致するものがない場合
			message = "ID又はpwが違います";
			request.setAttribute("error_msg", message);
			//login.jspにリダイレクト
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			//リスエストパラメータとレスポンスを引き継いでもらう
			rd.forward(request, response);
		}
	}

	//isEmpty(String str_pw)メソッドは、str_pwをnullもしくはデータ数を0で返す
	private boolean isEmpty(String str_pw) {
		return (str_pw == null || str_pw.length() == 0);
	}

}
