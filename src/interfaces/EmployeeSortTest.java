package interfaces;

import java.util.*;

public class EmployeeSortTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("test1", 35000);
		staff[1] = new Employee("test2",75000);
		staff[2] = new Employee("test3",38000);
		
		Arrays.sort(staff);
		
		for(Employee e:staff)
		{
			System.out.printf("name=%s,salary=%f\n",e.getName(),e.getSalary());
		}
	}

}
