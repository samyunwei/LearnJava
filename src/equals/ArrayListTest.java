package equals;
import java.util.*;
public class ArrayListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Employee> staff = new ArrayList<>();
		staff.add(new Employee("carl", 75000, 1987, 12, 15));
		staff.add(new Employee("Harray", 50000, 1989, 10, 1));
		staff.add(new Employee("Tony", 40000, 1990, 3, 15));
		
		for(Employee e: staff)
		{
			e.raiseSalary(5);
		}
		
		for(Employee e:staff)
		{
			System.out.printf("name = %s salary = %f hireDay = %s \n",e.getName(),e.getSalary(),e.getHireDay());
		}
	}

}
