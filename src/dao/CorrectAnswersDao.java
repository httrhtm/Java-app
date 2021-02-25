package dao;

import java.sql.PreparedStatement; //SQL文を事前に準備しておくことでJDBCを高速化
import java.sql.ResultSet; //データベースの結果セットを表すデータの表
import java.util.ArrayList;

import bean.CorrectAnswersBean;

public class CorrectAnswersDao extends ConnectionDao {
	public CorrectAnswersDao() throws Exception {
		setConnection();//例外クラス
//		setConnectionメソッド内で例外が発生すると、その呼び出し元（setConnection）のcatchに処理が移る
	}

	public ArrayList<CorrectAnswersBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null; //事前に準備したSQL文がない
		ResultSet rs = null; //データ表がない

		try {
			//レコードを全件
			String sql = "SELECT id, question_id, answer FROM correct_answers";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			ArrayList<CorrectAnswersBean> list = new ArrayList<CorrectAnswersBean>();
			while (rs.next()) { //繰り返し処理
				int id = rs.getInt("id");
				int questionId = rs.getInt("question_id");
				String answer = rs.getString("answer");
				CorrectAnswersBean bean = new CorrectAnswersBean(id, questionId, answer);
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
	public CorrectAnswersBean find(int pid) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question_id, answer FROM correct_answers WHERE id = ?";
			/** PreparedStatement オブジェクトの取得**/
			//PreparedStatement#executeQueryメソッドでSELECT命令を実行
			st = con.prepareStatement(sql);
			st.setInt(1, pid);
			rs = st.executeQuery();
			CorrectAnswersBean bean = new CorrectAnswersBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				int questionId = rs.getInt("question_id");
				String answer = rs.getString("answer");
				bean.setId(id);
				bean.setQuestionId(questionId);
				bean.setAnswer(answer);
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
	public void create(CorrectAnswersBean cb) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO correct_answers (question_id, answer, created_at, updated_at) values (?,?,current_timestamp(),current_timestamp())";
			
			st = con.prepareStatement(sql);
			st.setInt(1, cb.getQuestionId());
			st.setString(2, cb.getAnswer());
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

