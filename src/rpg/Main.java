package rpg;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		int mode;
		MonsterDBConnect db = new MonsterDBConnect();
		FileOperation fo = new FileOperation();
		System.out.println("★☆スッキリタワー☆★");
		System.out.println("1: ゲームスタート");
		System.out.println("2: モンスターデータ取込");
		
		mode = new java.util.Scanner(System.in).nextInt();
		switch (mode){
			case 1:
				System.out.println("ゲームを開始します。");
				Game game = new Game();
				System.out.print("ヒーローの名前を入力してください。>>");
				Hero hero = new Hero(new java.util.Scanner(System.in).nextLine(), fo);
				Tower tower = new Tower();
				
				Integer floor = game.floor(db);
				
				game.start(tower, hero, db, fo);//スタート
				break;
			case 2:
				System.out.println("モンスターデータを取り込みます");
				System.out.println("取込むCSVファイルのパスを入力してください。");
				String csvPath = new java.util.Scanner(System.in).nextLine();
				List<String> monsters = fo.readCsv(csvPath);
				db.inputMonsters(monsters);
				break;
			default:
				throw new IllegalArgumentException("入力値が適切ではありません。");
		}
		
	}

}
