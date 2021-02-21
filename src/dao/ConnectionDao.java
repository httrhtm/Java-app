package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {

	final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	final String DB_NAME ="java_db";
	final String DB_USER = "root";
	final String DB_PASSWORD = "hacchan82";

	/* DBコネクション */
	public Connection con;
	/**
//	 * コンストラクタ
//	 */
	//例外処理の呼び出し
	//ConnectionDao内で例外が発生すると、その呼び出し元（ConnectionDao）のcatchに処理が移る
	public ConnectionDao() throws Exception {
		setConnection(); //例外クラス
//		setConnectionメソッド内で例外が発生すると、その呼び出し元（setConnection）のcatchに処理が移る
	}

	/**
	 * コネクション接続
	 */
	public void setConnection() throws Exception {

		try {
			String url = "jdbc:mysql://localhost:3306/java_db?characterEncoding=UTF-8＆serverTimezone=JST";

			//Class.forName()メソッドを利用し、そのクラス内でJDBCドライバを読み込み利用を可能な状態にしている
			Class.forName(DRIVER_NAME).newInstance();

			//DriverManager.getConnection()メソッドを利用してデータベースに接続する処理を行なっている。
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/java_db?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false", "root", "hacchan82");

			//接続成功メッセージとコネクション情報の表示
			//生成した接続をSystem.out.println()メソッドで表示
			System.out.println("JDBCデータベース接続成功");


		} catch (SQLException e) {
			System.out.println("JDBCデータベース接続エラー:" + e);
		}
	}

	/**
	 * クローズ
	 */
	public void close() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
}
