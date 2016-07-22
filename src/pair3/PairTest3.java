package pair3;

import inheritance.Employee;
import inheritance.Manager;
import pair1.Pair;

public class PairTest3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager ceo = new Manager("Gus Greedy",800000,2003,12,15);
		Manager cfo = new Manager("Sid Sneaky",600000,2003,12,15);
		
		Pair<Manager> buddies = new Pair<>(ceo,cfo);
		printBuddies(buddies);
		
		ceo.setBonus(1000000);
		cfo.setBonus(500000);
		
		Manager[] managers = {ceo,cfo};
		
		Pair<Employee> result = new Pair<>();
		minmaxBonus(managers, result);
		System.out.printf("First:%s Second:%s \n",result.getFirst().getName(),result.getSecond().getName());
		maxminBonus(managers, result);
		System.out.printf("First:%s Second:%s \n",result.getFirst().getName(),result.getSecond().getName());
	}
	
	
	public static void printBuddies(Pair<? extends Employee>p)
	{
		Employee first = p.getFirst();
		Employee second = p.getSecond();
		System.out.printf("%s and %s are buddies.\n",first.getName(),second.getName());
	}
	
	public static void minmaxBonus(Manager[] a,Pair<? super Manager>result)
	{
		if(a == null || a.length == 0)
		{
			return;
		}
		Manager min = a[0];
		Manager max = a[0];
		
		for(int i = 0; i < a.length ;i++)
		{
			if(min.getBonus() > a[i].getBonus())
			{
				min = a[i];
			}
			if(max.getBonus() < a[i].getBonus())
			{
				max = a[i];
			}
		}
		result.setFirst(min);
		result.setSecond(max);
	}
	
	public static void maxminBonus(Manager[] a,Pair<? super Manager>result)
	{
		minmaxBonus(a, result);
		PairAlg.swapHelper(result);
	}
}

class PairAlg
{
	public static boolean hasNull(Pair<?> p)
	{
		return p.getFirst() == null || p.getSecond() == null;
	}
	
	public void swap(Pair<?> p)
	{
		swapHelper(p);
	}
	
	public static<T> void swapHelper(Pair<T>p)
	{
		T t = p.getFirst();
		p.setFirst(p.getSecond());
		p.setSecond(p.getFirst());
	}
}
