package call_record.repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import call_record.model.Order;

public class CsvRepository {
	
	
	public void save(List<Order> orderList) throws IOException {
			//↓close忘れを防ぐ
		try (FileWriter fw = new FileWriter("data/callrecord.csv")){
			fw.write("month,name,product,subscription\r\n");
			for(Order o: orderList) {
				fw.write(o.getMonth() + ",");
				fw.write(o.getName() + ",");
				fw.write(o.getProduct() + ",");
				fw.write(o.getSub() + "\r\n");
			}			
		}
	}
	
	public List<Order> load(String path) throws IOException{
		List<String> list = new ArrayList<>();
		try(FileReader fr = new FileReader(path);
			BufferedReader br = new BufferedReader(fr);){
			String s;
			while((s = br.readLine()) != null) {
				list.add(s);
			}	
		}
		List<Order> orderList = new ArrayList<>();
		for(int i = 1 ; i < list.size();i++) {
			String[] line = list.get(i).split(",");
			Order o = new Order();
			o.setMonth(line[0]);
			o.setName(line[1]);
			o.setProduct(line[2]);
			o.setSub(Boolean.parseBoolean(line[3]));
			orderList.add(o);
		}
		return orderList;
	}

}
