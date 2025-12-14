package com.diworksdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.diworksdev.template.dto.LoginDTO;
import com.diworksdev.template.util.DBConnector;

public class LoginDAO {

	public LoginDTO getLoginUserInfo(String loginUserId, String loginPassword) {

		DBConnector dbConnector = new DBConnector();
		Connection connection = dbConnector.getConnection();
		LoginDTO loginDTO = new LoginDTO();
		//SQL内のテーブルlogin_user_transactionからlogin_idとlogin_passを参照して、該当するデータを抽出する
		String sql = "SELECT * FROM login_user_transaction where login_id = ? AND login_pass = ?";

		try {
			//sql文をコンパイル（機械語に翻訳)してから処理するのと、sql直接記入だと悪い人が余裕で書き換えられる（SQLインジェクション）
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			/* SQL文の"?"パラメータに指定した値を挿入する（login.jspで入力された値) */
			preparedStatement.setString(1, loginUserId);//1番目の?にloginUserIdを設定
			preparedStatement.setString(2, loginPassword);//2番目の?にloginPasswordを設定

			ResultSet resultSet = preparedStatement.executeQuery();//結果セットを取得

			//Setされた行が存在する場合はそのまま進める（next）？
			if(resultSet.next()) {
				loginDTO.setLoginId(resultSet.getString("login_id"));
				loginDTO.setLoginPassword(resultSet.getString("login_pass"));
				loginDTO.setUserName(resultSet.getString("user_name"));

				//上記のifに加えてlogin_idがnullではない場合はtrueを返す
				if(resultSet.getString("login_id") != null) {
					loginDTO.setLoginFlg(true);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return loginDTO;
	}
}
