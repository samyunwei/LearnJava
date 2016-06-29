package clone;

public class CloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			Employee original = new Employee("test", 500, 1999, 1, 1);
			Employee copy = original.clone();
			copy.raiseSalary(10);
			copy.setHireDay(2002, 12, 31);
			System.out.println("original =" + original);
			System.out.println("copy="+ copy);
			
		}
		catch(CloneNotSupportedException e)
		{
			e.printStackTrace();
		}
	}

}
