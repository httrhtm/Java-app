package dao;

import java.sql.PreparedStatement; //SQL文を事前に準備しておくことでJDBCを高速化
import java.sql.ResultSet; //データベースの結果セットを表すデータの表
import java.util.ArrayList;
import java.util.List;

import bean.UsersBean;

public class UsersDao extends ConnectionDao {
	/**
	 * コンストラクタ
	 */
	public UsersDao() throws Exception {
		setConnection();//例外クラス
		//setConnectionメソッド内で例外が発生すると、その呼び出し元（setConnection）のcatchに処理が移る
	}
	/**
	 * users テーブルを全件取得
	 */
	public List<UsersBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null; //事前に準備したSQL文がない
		ResultSet rs = null; //データ表がない
		try {
			// 削除フラグがたってないレコードを全件
			String sql = "SELECT id, name, password, admin_flag FROM users WHERE deleteflag = 0";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			List<UsersBean> list = new ArrayList<UsersBean>();
			while (rs.next()) { //繰り返し処理
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				byte admin_flag = rs.getByte("admin_flag");
				UsersBean bean = new UsersBean(id, name, password, admin_flag);
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
	public UsersBean find(int pid) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, name, password, admin_flag FROM users WHERE id = ?";
			/** PreparedStatement オブジェクトの取得**/
//			PreparedStatement#executeQueryメソッドでSELECT命令を実行
			st = con.prepareStatement(sql);
			st.setInt(1, pid);
			rs = st.executeQuery();
			UsersBean bean = new UsersBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				Byte admin_flag =rs.getByte("admin_flag");
				bean.setId(id);
				bean.setName(name);
				bean.setPassword(password);
				bean.setAdminFlag(admin_flag);
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
	public void create(UsersBean ub) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO users (name, password, admin_flag, created_at, updated_at) values (?,?,?,current_timestamp(),current_timestamp())";
			st = con.prepareStatement(sql);
			st.setString(1, ub.getName());
			st.setString(2, ub.getPassword());
			st.setByte(3, ub.getAdminFlag());
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
	  * レコードの論理削除
	  */
	 public void delete(int id) throws Exception{
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "UPDATE users SET deleteflag = ?, deleted_at = current_timestamp() WHERE id = ?";

			st = con.prepareStatement(sql);
			// 削除フラグを立てる
			st.setInt(1, 1);
			st.setInt(2, id);
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
