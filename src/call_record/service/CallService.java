package call_record.service;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import call_record.condition.SearchCondition;
import call_record.model.Order;
import call_record.util.DisplayUtil;
import call_record.util.InputUtil;

public class CallService {

	// 定期率の計算
	public static double calcRate(int total, int subCount) {
		if (total == 0) {
			return 0;
		}
		return (double) subCount / total * 100;
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
	
	// case1のデータの追加
		public static void handleAdd(Scanner sc, Set<String> staffSet, List<Order> orderList) {
			System.out.println("＜＜データを追加します＞＞");
			Order o = InputUtil.inputOrder(sc);
			orderList.add(o);
			staffSet.add(o.getName());
			System.out.println("\nデータを追加しました。");
			DisplayUtil.showOrder(o);
			System.out.println("--------------------");
		}

	// case4の受注一覧
	public static void handleShowList(List<Order> orderList) {
		System.out.println("＜＜受注一覧＞＞");
		System.out.println(orderList.size() + "件");
		// 全受注の担当者表示
		for (Order o : orderList) {
			DisplayUtil.showOrder(o);
		}
		System.out.println("----------------------------------------");
	}
}
