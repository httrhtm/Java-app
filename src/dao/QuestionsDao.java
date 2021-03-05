package dao;

import java.sql.PreparedStatement; //SQL文を事前に準備しておくことでJDBCを高速化
import java.sql.ResultSet; //データベースの結果セットを表すデータの表
import java.util.ArrayList;
import java.util.List;

import bean.QuestionsBean;

public class QuestionsDao extends ConnectionDao {

	public QuestionsDao() throws Exception {
		setConnection();//例外クラス
		//		setConnectionメソッド内で例外が発生すると、その呼び出し元（setConnection）のcatchに処理が移る
	}

	//データベースから全てのアカウント情報の検索を行うメソッド
	//戻り値としてArrayList<AccountInfo>型の変数を利用
	public List<QuestionsBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null; //事前に準備したSQL文がない
		ResultSet rs = null; //データ表がない

		try {
			//レコードを全件
			String sql = "SELECT id, question FROM questions";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			 //return用オブジェクトの生成
			ArrayList<QuestionsBean> list = new ArrayList<QuestionsBean>();
			while (rs.next()) { //繰り返し処理
				int id = rs.getInt("id");
				String question = rs.getString("question");
				QuestionsBean bean = new QuestionsBean(id, question);
				list.add(bean);
			}
			return list;
			//例外発生時の処理
		} catch (Exception e) { //例外をキャッチ
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally { //ファイルのクローズ処理。例外の有無関係なく実行される。
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				//closeに失敗したときの処理
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}

	public List<QuestionsBean> RandAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null; //事前に準備したSQL文がない
		ResultSet rs = null; //データ表がない

		try {
			//レコードを全件
			String sql = "SELECT * FROM questions ORDER BY RAND()";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			 //return用オブジェクトの生成
			ArrayList<QuestionsBean> list = new ArrayList<QuestionsBean>();
			while (rs.next()) { //繰り返し処理
				int id = rs.getInt("id");
				String question = rs.getString("question");
				QuestionsBean bean = new QuestionsBean(id, question);
				list.add(bean);
			}
			return list;
			//例外発生時の処理
		} catch (Exception e) { //例外をキャッチ
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally { //ファイルのクローズ処理。例外の有無関係なく実行される。
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				//closeに失敗したときの処理
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}


	/**
	 * 指定IDのレコードを取得する
	 */
	public QuestionsBean find(int pid) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question FROM questions WHERE id = ?";
			/** PreparedStatement オブジェクトの取得**/
			//PreparedStatement#executeQueryメソッドでSELECT命令を実行
			st = con.prepareStatement(sql);
			st.setInt(1, pid);
			rs = st.executeQuery();
			QuestionsBean bean = new QuestionsBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				bean.setId(id);
				bean.setQuestion(question);
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}

	/**
	 * レコードの新規作成
	 */
	public void create(QuestionsBean qb) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO questions (question, created_at, updated_at) values (?,current_timestamp(),current_timestamp())";

			st = con.prepareStatement(sql);
			st.setString(1, qb.getQuestion());
			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの操作に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}

	/**
	 * 一番大きいIdを取得する
	 */
	public int getMaxQuestionId() throws Exception {
		if (con == null) {
      setConnection();
		}
  	PreparedStatement st = null;
  	ResultSet rs = null;
		try {
			String sql = "SELECT MAX(id) FROM questions";
			st = con.prepareStatement(sql);
			rs = st.executeQuery();
			int max_id = 0;
			if (rs.next()) {
				max_id = rs.getInt(1);
			}
			return max_id;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの操作に失敗しました。");
		}
	}

	/**
	 * 指定IDのレコードをupdate
	 */
	public void update(QuestionsBean qb) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		//編集する値のidを変数に代入
		int questionId = qb.getId();
		//edit.jspで入力した値を変数に代入
		String question = qb.getQuestion();

		try {
			String sql = "UPDATE questions SET question = ?, updated_at = current_timestamp() WHERE id = ?";

			st = con.prepareStatement(sql);
			st.setString(1, question);
			st.setInt(2, questionId);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの操作に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}

	/**
	 * 指定IDのレコードをdelete
	 */
	public void delete(QuestionsBean qb) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;

		//編集する値のidを変数に代入
		int questionId = qb.getId();

		try {
			//指定idのデータを削除する
			String sql = "DELETE FROM questions WHERE id = ?";

			st = con.prepareStatement(sql);
			st.setInt(1, questionId);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの操作に失敗しました。");
		} finally {
			try {
				// リソースの開放
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}
}
