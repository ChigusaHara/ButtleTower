package rpg;

public abstract class Character {
	private String name;
	private int hp;
	private int mp;
	private int power;
	private int defence;
	private int specialPower;
	
	public void attack(Character ch) {
		int damage;
		System.out.println(this.getName() + "の攻撃！");
		if (ch.getHp() >= this.getPower() - ch.getDefence()) {
			ch.setHp(ch.getHp() - (this.getPower() - ch.getDefence()));
			damage = this.getPower() - ch.getDefence();
		}else {
			damage = ch.getHp();
			ch.setHp(0);
		}
		System.out.println(ch.getName() + "に" + damage + "のダメージ！");
	}
	
	public void specialAttack(Character ch) {
		int specialDamage;
		System.out.println(this.getName() + "は必殺技を放った！！");
		
		if (this.getMp() > 0) {
			this.setMp(this.getMp() - 1);
			if (ch.getHp() >= this.getSpecialPower()) {
				ch.setHp(ch.getHp() - this.getSpecialPower());
				specialDamage = this.getSpecialPower();
			}else {
				specialDamage = ch.getHp();
				ch.setHp(0);
			}
			System.out.println(ch.getName() + "に" + specialDamage + "のダメージ！");
		} else {
			System.out.println("しかし技は失敗した・・・\n");
		}
	}
	
	public abstract boolean life();
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if (name.length() >= 1 || name.matches(".+")) {
			this.name = name;
		}else {
			this.name = "ヒーロー";
		}
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		if (hp > 0) {
			this.hp = hp;
		} else {
			this.hp =0;
		}
		
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public int getSpecialPower() {
		return specialPower;
	}
	public void setSpecialPower(int specialPower) {
		this.specialPower = specialPower;
	}
	
}
