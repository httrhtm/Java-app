package dao;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bean.HistoriesBean;

public class HistoriesDao extends ConnectionDao {

	public HistoriesDao() throws Exception {
		setConnection();//例外クラス
		//		setConnectionメソッド内で例外が発生すると、その呼び出し元（setConnection）のcatchに処理が移る
	}

	/**
	 * レコードの新規作成
	 */
	public void create(HistoriesBean hb) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO histories (user_id, point, created_at) values (?,?,current_timestamp())";

			st = con.prepareStatement(sql);
			st.setInt(1, hb.getUserId());
			st.setInt(2, hb.getPoint());
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

	public List<HistoriesBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null; //事前に準備したSQL文がない
		ResultSet rs = null; //データ表がない

		try {
			//レコードを全件
			String sql = "SELECT id, user_id, point, created_at FROM histories";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			 //return用オブジェクトの生成
			ArrayList<HistoriesBean> hlist = new ArrayList<HistoriesBean>();
			while (rs.next()) { //繰り返し処理
				int id = rs.getInt("id");
				int user_id = rs.getInt("user_id");
				int point = rs.getInt("point");
				Timestamp created_at = rs.getTimestamp("created_at") ;

				HistoriesBean bean = new HistoriesBean(id, user_id, point, created_at);
				hlist.add(bean);
			}
			return hlist;
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
}
