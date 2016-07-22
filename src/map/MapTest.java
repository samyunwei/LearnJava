package map;
import java.util.*;

import inheritance.Employee;
public class MapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Employee>staff = new HashMap<>();
		staff.put("144-25-5464", new Employee("Amylee",1000, 1991,1, 1));
		staff.put("567-24-2546", new Employee("Harry Hacker",1000, 1991,1, 1));
		staff.put("157-62-7935", new Employee("Gary Cooper",1000, 1991,1, 1));
		staff.put("456-62-5527", new Employee("Francesca Cruz",1000, 1991,1, 1));
		
		System.out.println(staff);
		
		staff.remove("567-24-2546");
		
		staff.put("456-62-5527", new Employee("Francesca",1000, 1991,1, 1));
		
		for(Map.Entry<String, Employee> entry : staff.entrySet())
		{
			String key = entry.getKey();
			Employee value = entry.getValue();
			System.out.println("key="+key+",value="+value);
		}
	}

}
