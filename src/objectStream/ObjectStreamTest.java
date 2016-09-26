package objectStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import inheritance.Employee;
import inheritance.Manager;

public class ObjectStreamTest {

	public static void main(String[] args) throws IOException,ClassCastException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		Employee harray = new Employee("Harray",50000,1989,10,1);
		Manager carl = new Manager("Carl Cracker",80000,1987,12,15);
		carl.setSecreatary(harray);
		Manager tony = new Manager("Tony Tester",40000,1990,3,15);
		tony.setSecreatary(harray);
		
		Employee[] staff = new Employee[3];
		staff[0] = carl;
		staff[1] = harray;
		staff[2] = tony;
		
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat")))
		{
			out.writeObject(staff);
		}
		
		
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat")))
		{
			Employee[] newstaff = (Employee[])in.readObject();
			
			newstaff[1].raiseSalary(10);
			
			for(Employee e:newstaff)
			{
				System.out.println(e);
			}
		}
	}

}
