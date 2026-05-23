package call_record.main;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import call_record.model.Order;
import call_record.repository.CsvRepository;
import call_record.repository.StaffRepository;
import call_record.service.CallService;
import call_record.service.RankingService;
import call_record.service.SearchRateService;
import call_record.util.DisplayUtil;
import call_record.util.InputUtil;

public class CallRecord {
	public static void main(String[] args) throws IOException {
		
		
		CsvRepository repo = new CsvRepository();
		List<Order> orderList = repo.load("callrecord.csv");

		// 全受注件数
		CallService.handleShowList(orderList);

		// 個人のリスト化
		Set<String> staffSet = StaffRepository.staffSet(orderList);

		Scanner sc = new Scanner(System.in);
		
		while (true) {
			DisplayUtil.showMenu();
			int choice = InputUtil.inputNum(sc);
			System.out.println("--------------------");
			if (choice == 5) {
				break;
			}
			switch (choice) {
			case 1:
				// データの追加
				CallService.handleAdd(sc, staffSet, orderList);
				break;
			case 2:
				// 成績管理（定期率）
				SearchRateService.handleSearch(sc, orderList);
				break;
			case 3:
				// 成績ランキング
				RankingService.handleRanking(sc, staffSet, orderList);
				break;
			case 4:
				// 受注一覧
				CallService.handleShowList(orderList);
				break;

			default:
				System.out.println("入力をやり直してください");
				break;
			}
		}
		//ファイルを作る
		repo.save(orderList);
		
		System.out.println("** 保存して終了します **");
		sc.close();
	}
}
