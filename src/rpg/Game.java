
package rpg;
//ゲームの進行
//生死の判別
public class Game {
	private int floorNum;//全階数
	private int attackCommand;
	boolean stayFloor = true;
	
	public Integer floor(MonsterDBConnect db){
		floorNum = db.getMonstersNum();//全階数検索
		System.out.println("このタワーは、" + floorNum + "階まであります。");
		return floorNum;
	}
	
	public void start(Tower tw, Hero h, MonsterDBConnect db, FileOperation fo) {
		
		for (int f = 1; f <= floorNum; f++) {
			System.out.println("\n\n【" + f +"階に到着した】");
			Monster m = new Monster(f, db);//f階のモンスター
			
			System.out.println(m.getName() + "があらわれた！！");
			
			while (stayFloor) {//各階ゲーム
				boolean heroRun = false;
				boolean monsterRun = false;
				
				System.out.println("[ HP：" + m.getHp() + " MP：" + m.getMp() + " ]------------------------------------");
				
				h.useSpodri();//スポドリの使用ができるか調査
				
				if (f == floorNum) {//最上階なら
					System.out.println("敵が強くて逃げられなさそうだ。");
					System.out.print("コマンドを入力(1:攻撃 2:必殺技)>>");
				}else {
					System.out.print("コマンドを入力(1:攻撃 2:必殺技 3:逃げる)>>");
				}
				setAttackCommand(floorNum, f, new java.util.Scanner(System.in).nextInt());
				
				switch (attackCommand) {
					case 1:
						boolean canAttack;//ミニゲーム成功かどうか
						canAttack = tw.floorGame(f);
						if (canAttack) {
							h.attack(m);
						}else {
							System.out.println("攻撃できなかった。");
						}
						break;
					case 2:
						h.specialAttack(m);
						break;
					case 3:
						heroRun = h.run();
						break;
					default ://setAttackCommandメソッドで-1が返された場合等
						System.out.println(h.getName() + "は、攻撃できなかった。");
						break;
				}
				if (m.life() || heroRun){//モンスターが倒れたら、もしくは逃げられたら次の階へ
					tw.drop(h);
					break;
				}
				
				m.attackSel(h, fo);

				if (h.life()){//ヒーローが倒れたらループを抜ける
					break;
				}
			}//各階ゲーム 生きてたら、逃げてなかったら繰り返し
			if (h.getHp() <= 0) {
				break;
			}
			System.out.println(h.getName() + "の現在のHP：" + h.getHp());
		}//次の階へ
		if (h.getHp() <= 0) {//ヒーローが倒れたら
			System.out.println("-----GAME OVER-----");
		}else {
			System.out.println("おめでとう！クリアです！！\n -----END-----");
		}
	}

	public int setAttackCommand(int fn, int f, int ac) {
		if (ac == 1 || ac == 2) {
			this.attackCommand = ac;
			return this.attackCommand;
		}else if (ac == 3) {
			if (f == fn) {//現在のフロアが最上階ならば
				this.attackCommand = -1;
				return -1;
			}else {
				this.attackCommand = ac;
				return this.attackCommand;
			}
		}else {
			this.attackCommand = -1;
			return -1;
		}
	}
	
}
