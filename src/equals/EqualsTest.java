package equals;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class EqualsTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee test1 = new Employee("test1", 75000, 1987, 12, 15);
		Employee test2 = test1;
		
		Employee test3 = new Employee("test2", 75000, 1987, 12, 15);
		
		Employee testt1 = new Employee("test4", 50000, 1989, 10, 1);
		
		System.out.println("test1 == test2:" + (test1 == test2));
		
		System.out.println("test1 == test3:" + (test1 == test3));
		
		System.out.println("test1.equals(test3):" + test1.equals(test3));
		
		System.out.println("test1.equals(testt1):" + test1.equals(testt1));
		
		System.out.println("testt1.toString:"+ testt1);
		
		Manager carl  = new Manager("Cal Cracker", 8000, 1987, 12, 15);
		Manager boss = new Manager("Cal Cracker", 8000, 1987, 12, 15);
		
		boss.setBonus(5000);
		
		System.out.println("boss.tostring:" + boss);
		System.out.println("carl.equals(boss):" + carl.equals(boss));
		System.out.println("tes1.hashcode():" + test1.hashcode());
		System.out.println("test3.hashcode()" + test3.hashcode());
		
		System.out.println("boss.hashcode():"+ boss);
		System.out.println("carl.hashcode():" + carl);;
	}

}
