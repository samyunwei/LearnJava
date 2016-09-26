package randomAccess;

import java.io.*;
import java.util.*;

import clone.Employee;

public class RandomAccessTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Employee[] staff = new Employee[3];
		
		staff[0] = new Employee("test",75000,1987,12,15);
		staff[1] = new Employee("test1",50000,1989,12,15);
		staff[2] = new Employee("test2",40000,1990,12,15);
		
		try(DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat")))
		{
			for(Employee e:staff)
			{
				writeData(out, e);
			}
		}
		
		try(RandomAccessFile in = new RandomAccessFile("employee.dat","r"))
		{
			int n = (int)(in.length() /Employee.RECORD_SIZE);
			Employee[] newstaff = new Employee[n];
			for(int i = n - 1; i >= 0;i--)
			{
				newstaff[i] = new Employee();
				in.seek(i * Employee.RECORD_SIZE);
				newstaff[i] = readData(in);
			}
			
			for(Employee e:newstaff)
			{
				System.out.println(e);
			}
		}
	}
	
	
	public static void writeData(DataOutput out,Employee e) throws IOException
	{
		DataIO.writeFixedString(e.getName(),Employee.NAME_SIZE, out);
		out.writeDouble(e.getSalary());
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(e.getHireDay());
		out.writeInt(calendar.get(Calendar.YEAR));
		out.writeInt(calendar.get(Calendar.MONTH)+1);
		out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
	}
	
	public static Employee readData(DataInput in) throws IOException
	{
		String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
		double salary = in.readDouble();
		int y = in.readInt();
		int m = in.readInt();
		int d = in.readInt();
		
		
		return new Employee(name, salary, y, m, d);
	}
}
