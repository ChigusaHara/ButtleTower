package rpg;

public class Monster extends Character{
	private String[] data = new String[6];
	
	public Monster(Integer no, MonsterDBConnect db) {
		data = db.makeInstance(no);
		this.setName(data[0]);
		this.setHp(Integer.parseInt(data[1]));
		this.setMp(Integer.parseInt(data[2]));
		this.setPower(Integer.parseInt(data[3]));
		this.setDefense(Integer.parseInt(data[4]));
		this.setSpecialPower(Integer.parseInt(data[5]));
	}
	
	public void attackSel(Hero h, FileOperation fo) {//攻撃選択 ランダム確率
		int attackRandom = new java.util.Random().nextInt(100) + 1;
		int[] rate = fo.rate();
		int attackRate = rate[0];
		int specialAttackRate = rate[1];
		
		if (attackRandom <= specialAttackRate) {
			this.attack(h);
		} else if (attackRandom <= specialAttackRate + specialAttackRate){
			this.specialAttack(h);
		} else {
			this.observation();
		}
	}

	public void observation() {
		System.out.println(this.getName() + "は様子を見ている");
	}
	
	public boolean life() {
		if (this.getHp() <= 0) {//倒れたら
			System.out.println(this.getName() + "をやっつけた！");
			return true;
		}else {
			return false;
		}
	}
	

}
