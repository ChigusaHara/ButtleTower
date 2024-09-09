package rpg;

import java.util.ArrayList;
import java.util.List;

public class Hero extends Character{
	List<String> candy = new ArrayList<String>();
	List<Integer> spodri = new ArrayList<Integer>();
	
	public Hero(String name, FileOperation fo) {
		int[] heroData = fo.getHeroProperties();
		
		this.setName(name);
		this.setHp(heroData[0]);
		this.setMp(heroData[1]);
		this.setPower(heroData[2]);
		this.setDefense(heroData[3]);
		this.setSpecialPower(heroData[4]);
		
		System.out.println(this.getName() + "のステータス");
		System.out.println("名前:" + this.getName() + " HP:" + this.getHp() 
							+ " MP:"+ this.getMp() + "\n攻撃力:" + this.getPower() 
							+ " 防御力:" + this.getDefense() + " 必殺技:" + this.getSpecialPower());
	}
	
	public void useSpodri() {
		if (this.spodri.size() > 0) {//スポドリを持っていたら
			if (this.getHp() < 70) {//HPが残り少なければ
				System.out.print("HPの残りが" + this.getHp() + "となった。\nスポドリを飲んで回復する？ １：使う ２：使わない >>");
				int useSpodri = new java.util.Scanner(System.in).nextInt();
				
				if (useSpodri == 1) {
					System.out.println(this.getName() + "はスポドリを飲んだ。");
					System.out.println(this.getName() + "のHPが" + spodri.get(0) + "回復した！");
					this.setHp(this.getHp() + spodri.get(0));
					System.out.println("現在のHP：" + this.getHp());
					spodri.remove(0);
				}
			}
			
		}
	}
		
	public boolean run() {
		if (this.candy.size() > 0) {
			System.out.print("俊足キャンディを持っている。使う？ １：使う ２：使わない >>");
			int useCandy = new java.util.Scanner(System.in).nextInt();
			
			if (useCandy == 1) {
				System.out.println(this.getName() + "はキャンディを食べた。");
				candy.remove(0);
				System.out.println(this.getName() + "はうまく逃げ切れた");
				return true;
			}
		}
		
		int runRandom = new java.util.Random().nextInt(100) + 1;
		
		System.out.println(this.getName() + "は逃げだした！");
		if (runRandom <= 20) {
			System.out.println(this.getName() + "はうまく逃げ切れた");
			return true;
		} else {
			System.out.println("しかし、" + this.getName() + "は逃げ切れなかった");
			return false;
		}
	}
	
	public boolean life() {
		if (this.getHp() <= 0) {//倒れたら
			System.out.println(this.getName() + "は死んでしまった。");
			return true;
		}else {
			return false;
		}
	}	
	
	

}
