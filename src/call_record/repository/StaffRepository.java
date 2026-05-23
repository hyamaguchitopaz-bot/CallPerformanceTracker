package call_record.repository;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import call_record.model.Order;

public class StaffRepository {
	
	public static Set<String> staffSet(List<Order> orderList){
		Set<String> staffSet = new TreeSet<>();
		for (Order o : orderList) {
			staffSet.add(o.getName());
		}
		return staffSet;
		
	}

}
