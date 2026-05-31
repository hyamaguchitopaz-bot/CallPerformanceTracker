package call_record.service;

import java.util.List;
import java.util.Scanner;

import call_record.model.Order;
import call_record.model.SearchCondition;
import call_record.util.DisplayUtil;
import call_record.util.InputUtil;

public class SearchRateService {

	// case2の成績管理
	public static void handleSearch(Scanner sc, List<Order> orderList) {
		System.out.println("＜＜定期率を調べます＞＞");
		System.out.print("指定する月を入力してください→");
		String targetMonth = InputUtil.inputTarget(sc);
		System.out.print("指定する担当者を入力してください→");
		String targetName = InputUtil.inputTarget(sc);
		System.out.print("指定する商品を入力してください→");
		String targetPro = InputUtil.inputTarget(sc);
		SearchCondition cond = new SearchCondition(targetMonth, targetName, targetPro);
		// 条件選択の定期率（複数可）
		calcRateAdvanced(orderList, cond);
	}

	// 条件選択の定期率（複数可）
	public static void calcRateAdvanced(List<Order> orderList, SearchCondition cond) {
		// 条件の表示
		System.out.println("--------------------");
		String conditionText = CallService.buildConditionText(cond);
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
		DisplayUtil.showRate(total, subCount);
	}

}
