package rpg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class MonsterDBConnect {
	private final String URL = "jdbc:postgresql://localhost:5432/rpg";
	private final String USER = "postgres";
	private final String PASSWORD = "test";
	
	
	public MonsterDBConnect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int getMonstersNum() {
		int monstersNum = 0;//階数カウント
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT monster_no FROM monster");
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pst = con.prepareStatement(sql.toString());){
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {//階数分
				monstersNum++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return monstersNum;
		}
	}
	
	public String[] makeInstance(int no) {//DBから検索し、モンスターインスタンス生成に必要な要素を返す
		String[] data = new String[6];
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT name, hp, mp, power, defense, special_power FROM monster WHERE monster_no = ?");
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pst = con.prepareStatement(sql.toString());){
			pst.setInt(1, no);		
			ResultSet rs = pst.executeQuery();//検索
			while (rs.next()) {
				data[0] = rs.getString("name");
				data[1] = rs.getString("hp");
				data[2] = rs.getString("mp");
				data[3] = rs.getString("power");
				data[4] = rs.getString("defense");
				data[5] = rs.getString("special_power");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return data;
		}
	}

	public void inputMonsters(List<String> monsters) {
		String deleteTable = "DELETE FROM monster;";//monsterテーブル内データDELETE文
		
		try (Connection condel = DriverManager.getConnection(URL, USER, PASSWORD);//DELETE処理
				PreparedStatement pstdel = condel.prepareStatement(deleteTable);){
			pstdel.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO monster VALUES (?,?,?,?,?,?,?);";
		String[] data = new String[7];
		
		for (String monster : monsters) {//monster数文登録
			data = monster.split(",");//要素ごとに抜き取る
			
			try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement pst = con.prepareStatement(sql.toString());){
				
				pst.setInt(1, Integer.parseInt(data[0]));
				pst.setString(2, data[1]);
				pst.setInt(3, Integer.parseInt(data[2]));
				pst.setInt(4, Integer.parseInt(data[3]));
				pst.setInt(5, Integer.parseInt(data[4]));
				pst.setInt(6, Integer.parseInt(data[5]));
				pst.setInt(7, Integer.parseInt(data[6]));
				
				pst.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//monster数分登録
	}
	
	
	
	
	
}