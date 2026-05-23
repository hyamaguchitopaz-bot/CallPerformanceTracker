package call_record.util;

import java.util.Scanner;

import call_record.model.Order;

public class InputUtil {

	// 条件の入力
	public static String inputTarget(Scanner sc) {
		String target = sc.nextLine();
		if (target.replaceAll("　", " ").trim().isEmpty()) {
			return null;
		}
		return target;
	}
	
	//データの入力
	public static Order inputOrder(Scanner sc) {
		System.out.print("月：");
		String month = sc.nextLine();
		System.out.print("担当者名：");
		String name = sc.nextLine();
		System.out.print("商品名：");
		String product = sc.nextLine();
		System.out.print("定期？(true/false)：");
		boolean isSub = InputUtil.inputSub(sc);
		Order o = new Order(month, name, product, isSub);
		return o;	
	}

	// データ追加の定期入力（true / false）
	public static boolean inputSub(Scanner sc) {
		while (true) {
			String input = sc.nextLine().trim().toLowerCase();
			if (input.equals("true")) {
				return true;
			} else if (input.equals("false")) {
				return false;
			} else {
				System.out.print("true または false を入力してください→");
			}
		}
	}

	// メニュー選択の例外処理
	public static int inputNum(Scanner sc) {
		while (true) {
			try {
				int num = sc.nextInt();
				sc.nextLine();
				return num;

			} catch (Exception e) {
				System.out.print("数字で入力してください：");
				sc.nextLine(); // 例外発生時に排除できなかった文を排除
				continue;
			}
		}
	}

}
