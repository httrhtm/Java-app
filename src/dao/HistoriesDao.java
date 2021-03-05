package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
