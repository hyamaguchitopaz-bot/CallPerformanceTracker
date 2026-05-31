package call_record.util;

import java.util.List;

import call_record.model.Order;
import call_record.model.Result;
import call_record.model.SearchCondition;
import call_record.service.CallService;

public class DisplayUtil {

	// ランキングの表示フォーム
	public static void showRankForm(List<Result> resultList, SearchCondition cond) {
		System.out.println("--------------------");
		System.out.println("＜＜個人成績ランキング＞＞");
		// 条件の表示
		String condition = CallService.buildConditionText(cond);
		System.out.println("◆条件：" + condition);
		// 成績の表示
		showRank(resultList);
		System.out.println("--------------------");
	}

	// ランキングの表示
	public static void showRank(List<Result> resultList) {
		int rank = 1;
		int displayRank = 1;
		double prevRank = -1;
		for (Result r : resultList) {
			// ↓↓絶対値を表すメソッド
			if (Math.abs(r.getRate() - prevRank) > 0.0001) { // 誤差が0.0001より大きかったら
				displayRank = rank;
			}
			System.out.printf("%d位: %s (%.2f%% / %d件中%d件)\n", displayRank, r.getName(), r.getRate(), r.getTotal(),
					r.getSubCount());
			prevRank = r.getRate();
			rank++;
		}
	}

	// 計算結果の表示
	public static void showRate(int total, int subCount) {
		if (total > 0) {
			double rate = CallService.calcRate(total, subCount);
			System.out.printf("定期率：%.2f%%(%d件中%d)\n", rate, total, subCount);
		} else {
			System.out.println("データなし");
		}
		System.out.println("--------------------");
	}

	// データの表示内容
	public static void showOrder(Order o) {
						//↓↓TODO日本語対応にしたい
		System.out.printf("%-8s / %-8s /%-6s %s\n", o.getMonth(), o.getName(), o.getProduct(),
				o.getSub() ? " (定期)" : " (一般)");
	}

	// メニュー表示
	public static void showMenu() {
		System.out.println("＜メニュー＞" + "\n1.データの追加　2.成績管理 3.成績ランキング 4.受注一覧 5.終了");
		System.out.print("メニューを選択してください→");
	}

}
