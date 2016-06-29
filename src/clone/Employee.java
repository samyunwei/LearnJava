package clone;
import java.util.Date;
import java.util.GregorianCalendar;
public class Employee implements Cloneable
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
	
	public Employee clone() throws CloneNotSupportedException
	{
		Employee cloned = (Employee) super.clone();
		cloned.hireDay = (Date) hireDay.clone();
		
		return cloned;
	}
	
	public void setHireDay(int year,int month,int day)
	{
		GregorianCalendar calendar = new GregorianCalendar(year,month-1,day);
		hireDay = calendar.getTime();
	}
	
	public String toString()
	{
		return getClass().getName() + "[name=" + name+",salary=" +salary+",hireDay=" + hireDay+"]";
	}
}
