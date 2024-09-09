package rpg;

import java.util.Date;

//モンスターの管理
public class Tower {
	String candy = "candy";//必ず逃げられる俊足キャンディ
	int spodri = 70;//hp回復
	
	public void drop(Hero h) {
		int drop = new java.util.Random().nextInt(100) + 1;
		int dropCandy = 40;
		int dropSpodri = 40;
		
		if (drop <= dropCandy) {
			System.out.println("俊足キャンディをゲットした！");
			System.out.println("これを食べると絶対に逃げられる！！");
			h.candy.add(candy);
		}else if (drop <= dropCandy + dropSpodri) {
			System.out.println("スポドリをゲットした！");
			System.out.println("これを飲むとHPが" + spodri + "回復する！！");
			h.spodri.add(spodri);
		}
	}
	
	public boolean floorGame(int f) {
		System.out.println("ミニゲームをクリアして攻撃しよう！");
		int num;
		int input;
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb;
		String ans = "";
		Date start;
		Date end;
		Date afterTen;
		
		switch (f) {
			case 1:
				int button = 3;
				System.out.print("Enterキーを" + button + "回連打して攻撃！");
				for (int b = 1; b <= button; b++) {
					new java.util.Scanner(System.in).nextLine();
					System.out.print(b);
				}
				System.out.println();
				return true;
				
			case 2:
				System.out.println("正しい数字を入力して攻撃！");
				
				num = new java.util.Random().nextInt(1000) + 1;
				System.out.println(num);
				
				input = inputNum();//解答入力
					
				if (input == num) {
					return true;
				} else {
					return false;
				}
								
			case 3:
				System.out.println("正しい文字を入力して攻撃！");
				sb = new StringBuilder();
				for (int q = 0; q < 3; q++) {
					 sb.append(str.charAt(new java.util.Random().nextInt(52)));
				}
				System.out.println(sb.toString());
				
				ans = new java.util.Scanner(System.in).nextLine();
					
				if (ans.equals(sb.toString())) {
					return true;
				}else {
					return false;
				}
				
			case 4:
				System.out.println("計算問題に正解して攻撃！");
				int mulLeft = new java.util.Random().nextInt(10) + 1;
				int mulRight = new java.util.Random().nextInt(10) + 1;
				int correct = mulLeft * mulRight;
				
				System.out.print("問題  " + mulLeft + "×" + mulRight + "＝");
				input = inputNum();//解答入力
				
				if (input == correct) {
					return true;
				}else {
					return false;
				}
				
			case 5:
				System.out.println("10秒以内に正しい文字を入力して攻撃！");
				sb = new StringBuilder();
				for (int q = 0; q < 5; q++) {
					 sb.append(str.charAt(new java.util.Random().nextInt(52)));
				}
				System.out.println(sb.toString());
				
				start = new Date();//開始時刻設定
				afterTen = new Date(start.getTime() + 10000);//10秒後
				
				while (true) {
					ans = new java.util.Scanner(System.in).nextLine();
					end = new Date();
					
					if (this.time(end, afterTen)) {//制限時間の判定
						System.out.println("時間切れ～");
						return false;
					} else if (ans.equals(sb.toString())) {
						return true;
					}else {
						System.out.println("惜しい！もう一度！");
					}
				}
				
			case 6:
				System.out.println("10秒以内に正しい数字を入力して攻撃！");
				
				num = new java.util.Random().nextInt(1000000000) + 1000;
				System.out.println(num);
				start = new Date();//開始時刻設定
				afterTen = new Date(start.getTime() + 10000);//10秒後
				
				while (true) {
					input = inputNum();//解答入力
					end = new Date();
					
					if (this.time(end, afterTen)) {//制限時間の判定
						System.out.println("時間切れ～");
						return false;
					} else if (input == num) {
						return true;
					}else {
						System.out.println("惜しい！もう一度！");
					}
				}
			default :
				return false;
		}
	}
	
	public boolean time(Date end, Date afterTen) {
		if(end.getTime() > afterTen.getTime()) {
			return true;
		}else {
			return false;
		}
	}
	
	public int inputNum() {
		int returnNum;
		String input = new java.util.Scanner(System.in).nextLine();
		if (input.length() <= 9) {
			if (input.matches("[0-9]+")) {
				returnNum = Integer.parseInt(input);
				return returnNum;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}

}
