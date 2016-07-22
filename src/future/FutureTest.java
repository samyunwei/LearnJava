package future;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;


public class FutureTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory(e.g/usr/local/jdk1.6.0/src);");
		String directory = in.nextLine();
		System.out.print("EnterKeyword:(e.g volatile)");
		String keyword = in.nextLine();
		
		MathCounter counter = new MathCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<>(counter);
		Thread t = new Thread(task);
		t.start();
		try
		{
			System.out.println(task.get() + "Mathching files.");
		}catch(ExecutionException e)
		{
			e.printStackTrace();
		}catch(InterruptedException e)
		{
		}
	}

}



class MathCounter implements Callable<Integer>
{
	private File directory;
	private String keyword;
	private int count;
	
	
	public MathCounter(File directory,String keyword)
	{
		this.directory = directory;
		this.keyword = keyword;
	}


	@Override
	public Integer call()
	{
		// TODO Auto-generated method stub
		count = 0;
		try
		{
			File[] files = directory.listFiles();
			List<Future<Integer>> results = new ArrayList<>();
			for(File file: files)
			{
				if(file.isDirectory())
				{
					MathCounter counter = new MathCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<>(counter);
					results.add(task);
					Thread t = new Thread(task);
					t.start();
				}
				else
				{
					if(search(file))
					{
						count++;
					}
				}
			}
			
			for(Future<Integer> result: results)
			{
				try{
					count += result.get();
				}catch(ExecutionException e)
				{
					e.printStackTrace();
				}
			}
		}catch(InterruptedException e)
		{
			
		}
		return count;
	}
	
	
	public boolean search(File file)
	{
		try
		{
			try(Scanner in = new Scanner(file))
			{
				boolean found = false;
				while(!found && in.hasNextLine())
				{
					String line = in.nextLine();
					if(line.contains(keyword))
					{
						found = true;
					}
				}
				return found;
			}
		}catch(IOException e)
		{
			return false;
		}
	}
}