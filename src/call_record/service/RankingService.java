package call_record.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import call_record.model.Order;
import call_record.model.Result;
import call_record.model.SearchCondition;
import call_record.util.DisplayUtil;
import call_record.util.InputUtil;

public class RankingService {

	// case3の成績ランキング
	public static void handleRanking(Scanner sc, Set<String> staffSet, List<Order> orderList) {
		System.out.println("＜＜ランキングを調べます＞＞");
		System.out.print("指定する月を入力してください→");
		String targetMonth = InputUtil.inputTarget(sc);
		System.out.print("指定する商品を入力してください→");
		String targetPro = InputUtil.inputTarget(sc);
		SearchCondition cond = new SearchCondition(targetMonth, null, targetPro);
		// 定期率ランキング（条件選択可）
		rankRateAdvanced(staffSet, orderList, cond);
	}

	// 定期率ランキング（条件選択可）
	public static void rankRateAdvanced(Set<String> staffSet, List<Order> orderList, SearchCondition cond) {
		List<Result> resultList = new ArrayList<>();
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
			double rate = CallService.calcRate(total, subCount);
			resultList.add(new Result(staff, rate, total, subCount));
		}
		// 並べ替え
		sortResult(resultList);
		// 表示
		DisplayUtil.showRankForm(resultList, cond);
	}

	// 降順に並べ替え
	public static void sortResult(List<Result> resultList) {
		//まだ理解できてない、あとで調べる
		Collections.sort(resultList, (a, b) -> Double.compare(b.getRate(), a.getRate()));
	}

}
