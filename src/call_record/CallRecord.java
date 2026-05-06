package call_record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CallRecord {
	public static void main(String[] args) {

		ArrayList<Order> orderList = new ArrayList<>();
		Scanner sc = new Scanner(System.in);

		Order o1 = new Order("2026-04", "太郎くん", "商品A", true);
		Order o2 = new Order("2026-04", "次郎くん", "商品A", true);
		Order o3 = new Order("2026-03", "三郎くん", "商品B", false);
		Order o4 = new Order("2026-04", "太郎くん", "商品B", true);
		Order o5 = new Order("2026-04", "次郎くん", "商品B", false);

		orderList.add(o1);
		orderList.add(o2);
		orderList.add(o3);
		orderList.add(o4);
		orderList.add(o5);

		// 全受注件数
		handleShowList(orderList);

		// 個人のリスト化
		Set<String> staffSet = new TreeSet<>();
		for (Order o : orderList) {
			staffSet.add(o.getName());
		}
//		 // 個人成績一覧
//		 for (String staff : staffSet) {
//		 calcRateAdvanced(orderList, null, staff, null);
//		 }

		while (true) {
			showMenu();
			int choice = inputNum(sc);
			System.out.println("--------------------");
			if (choice == 5) {
				break;
			}
			switch (choice) {
			case 1:
				// データの追加
				handleAdd(sc, staffSet, orderList);
				break;
			case 2:
				// 成績管理（定期率）
				handleSearch(sc, orderList);
				break;
			case 3:
				// 成績ランキング
				handleRanking(sc, staffSet, orderList);
				break;
			case 4:
				// 受注一覧
				handleShowList(orderList);
				break;

			default:
				System.out.println("入力をやり直してください");
				break;
			}
		}
		System.out.println("** 終了します **");
	}

	// case1のデータの追加
	public static void handleAdd(Scanner sc, Set<String> staffSet, ArrayList<Order> orderList) {
		System.out.println("＜＜データを追加します＞＞");
		System.out.print("月：");
		String month = sc.nextLine();
		System.out.print("担当者名：");
		String name = sc.nextLine();
		System.out.print("商品名：");
		String product = sc.nextLine();
		System.out.print("定期？(true/false)：");
		boolean isSub = inputSub(sc);
		Order o = new Order(month, name, product, isSub);
		orderList.add(o);
		staffSet.add(o.getName());

		System.out.println("\nデータを追加しました。");
		showOrder(o);
		System.out.println("--------------------");
	}
	
	// 定期入力（true / false）
	public static boolean inputSub(Scanner sc) {
		while(true) {
			String input = sc.nextLine().trim().toLowerCase();
			if(input.equals("true")) {
				return true;
			}else if(input.equals("false")){
				return false;
			}else {
				System.out.print("true または false を入力してください→");
			}
		}
	}

	// case2の成績管理
	public static void handleSearch(Scanner sc, ArrayList<Order> orderList) {
		System.out.println("＜＜定期率を調べます＞＞");
		System.out.print("指定する月を入力してください→");
		String targetMonth = inputTarget(sc);
		System.out.print("指定する担当者を入力してください→");
		String targetName = inputTarget(sc);
		System.out.print("指定する商品を入力してください→");
		String targetPro = inputTarget(sc);
		SearchCondition cond1 = new SearchCondition(targetMonth, targetName, targetPro);
		// 条件選択の定期率（複数可）
		calcRateAdvanced(orderList, cond1);
	}

	// 条件の入力
	public static String inputTarget(Scanner sc) {
		String target = sc.nextLine();
		if (target.replaceAll("　", " ").trim().isEmpty()) {
			return null;
		}
		return target;
	}

	// case3の成績ランキング
	public static void handleRanking(Scanner sc, Set<String> staffSet, ArrayList<Order> orderList) {
		System.out.println("＜＜ランキングを調べます＞＞");
		System.out.print("指定する月を入力してください→");
		String targetMonth = inputTarget(sc);
		System.out.print("指定する商品を入力してください→");
		String targetPro = inputTarget(sc);
		SearchCondition cond2 = new SearchCondition(targetMonth, null, targetPro);
		// 定期率ランキング（条件選択可）
		rankRateAdvanced(staffSet, orderList, cond2);
	}

	// 定期率ランキング（条件選択可）
	public static void rankRateAdvanced(Set<String> staffSet, ArrayList<Order> orderList, SearchCondition cond) {
		ArrayList<Result> resultList = new ArrayList<>();
		for (String staff : staffSet) {
			int total = 0;
			int subCount = 0;
			for (Order o : orderList) {
				// ↓条件の照合
				if (cond.matches(o) && o.getName().equals(staff)) {
					total++;
					if (o.getSub()) {
						subCount++;
					}
				}
			}
			double rate = calcRate(total, subCount);
			resultList.add(new Result(staff, rate, total, subCount));
		}
		// 並べ替え
		sortResult(resultList);
		// 表示
		showRank(resultList, cond);
	}

	// 降順に並べ替え
	public static void sortResult(ArrayList<Result> resultList) {
		Collections.sort(resultList, (a, b) -> Double.compare(b.getRate(), a.getRate()));
	}

	// ランキングの表示
	public static void showRank(ArrayList<Result> resultList, SearchCondition cond) {
		System.out.println("--------------------");
		System.out.println("＜＜個人成績ランキング＞＞");
		// 条件の表示
		String condition = buildConditionText(cond);
		System.out.println("◆条件：" + condition);
		// 成績の表示
		int rank = 1;
		int displayRank = 1;
		double prevRank = -1;
		for (Result r : resultList) {
			if (Math.abs(r.getRate() - prevRank) > 0.0001) { // 誤差が0.0001より大きかったら
				displayRank = rank;
			}
			System.out.printf("%d位: %s (%.2f%% / %d件中%d件)\n", displayRank, r.getName(), r.getRate(), r.getTotal(),
					r.getSubCount());
			prevRank = r.getRate();
			rank++;
		}
		System.out.println("--------------------");
	}

	// 計算結果の表示
	public static void showRate(int total, int subCount) {
		if (total > 0) {
			double rate = calcRate(total, subCount);
			System.out.printf("定期率：%.2f%%(%d件中%d)\n", rate, total, subCount);
		} else {
			System.out.println("データなし");
		}
		System.out.println("--------------------");
	}

	// 定期率の計算
	public static double calcRate(int total, int subCount) {
		if (total == 0) {
			return 0;
		}
		return (double) subCount / total * 100;
	}

	// 条件選択の定期率（複数可）
	public static void calcRateAdvanced(ArrayList<Order> orderList, SearchCondition cond) {
		// 条件の表示
		String conditionText = buildConditionText(cond);
		System.out.println("--------------------");
		System.out.println("◆条件：" + conditionText);

		int total = 0;
		int subCount = 0;
		for (Order o : orderList) {
			// ↓条件の照合
			if (cond.matches(o)) {
				total++;
				if (o.getSub()) {
					subCount++;
				}
			}
		}
		showRate(total, subCount);
	}

	// 条件の表示内容作成
	public static String buildConditionText(SearchCondition cond) {
		StringBuilder sb = new StringBuilder();
		if (cond.getTargetMonth() != null) {
			sb.append(cond.getTargetMonth());
		}
		if (cond.getTargetName() != null) {
			if (sb.length() > 0) {
				sb.append(" /");
			}
			sb.append(cond.getTargetName());
		}
		if (cond.getTargetPro() != null) {
			if (sb.length() > 0) {
				sb.append(" /");
			}
			sb.append(cond.getTargetPro());
		}
		if (sb.length() == 0) {
			sb.append("全体");
		}
		return sb.toString();
	}

	// case4の受注一覧
	public static void handleShowList(ArrayList<Order> orderList) {
		System.out.println("＜＜受注一覧＞＞");
		System.out.println(orderList.size() + "件");
		// 全受注の担当者表示
		for (Order o : orderList) {
			showOrder(o);
		}
		System.out.println("----------------------------------------");
	}

	// データの表示内容
	public static void showOrder(Order o) {
		System.out.printf("%-8s / %-8s /%-6s %s\n", o.getMonth(), o.getName(), o.getProduct(),
				o.getSub() ? " (定期)" : " (一般)");
	}

	// メニュー表示
	public static void showMenu() {
		System.out.println("＜メニュー＞" + "\n1.データの追加　2.成績管理 3.成績ランキング 4.受注一覧 5.終了");
		System.out.print("メニューを選択してください→");
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
				sc.nextLine();
				continue;
			}
		}
	}
}
