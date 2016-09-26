package serialClone;
import java.io.*;
import java.util.*;

public class SerialClonetest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Employee harry = new Employee("Harry Hacker",35000, 1989, 10, 1);
		Employee harry2 = (Employee)harry.clone();
		
		harry.raiseSalary(10);
		
		System.out.println(harry);
		System.out.println(harry2);
	}

}


class SerialCloneable implements Cloneable,Serializable
{
	public Object clone()
	{
		try
		{
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(this);
			out.close();
			
			
			ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bin);
			Object ret = in.readObject();
			in.close();
			
			return ret;
		}catch(Exception e)
		{
			return null;
		}
	}
	
}

class Employee extends SerialCloneable
{
	private String name;
	private double salary;
	private Date hireDay;
	public Employee(String n,double s,int year,int month,int day)
	{
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year,month-1,day);
		hireDay = calendar.getTime();
	}
	
	public String getName()
	{
		return name;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public Date getHireDay()
	{
		return hireDay;
	}
	
	public void raiseSalary(double byPercent)
	{
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	
	public String toString()
	{
		return getClass().getName() + "[name=" + name+",salary=" +salary+",hireDay=" + hireDay+"]";
	}
	
}