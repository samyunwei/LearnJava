package eventTracer;
import java.awt.*;
import java.beans.*;
import java.lang.reflect.*;

public class EventTracer {
	private InvocationHandler handler;

	public EventTracer() {
		handler = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println(method + ":" + args[0]);
				return null;
			}
		};
	}
	
	public void add(Component c)
	{
		try
		{
			BeanInfo info = Introspector.getBeanInfo(c.getClass());
			
			EventSetDescriptor[] eventSets = info.getEventSetDescriptors();
			for(EventSetDescriptor eventSet : eventSets)
			{
				addlistener(c, eventSet);
			}
		}
		catch(IntrospectionException e)
		{
			
		}
		
		if (c instanceof Container) {
			for (Component comp : ((Container) c).getComponents()) {
				add(comp);
			}
		}
	}
	
	
	public void addlistener(Component c,EventSetDescriptor eventSet)
	{
		Object proxy = Proxy.newProxyInstance(null,new Class[]{eventSet.getListenerType()}, handler);
		
		Method addListenerMethod = eventSet.getAddListenerMethod();
		try
		{
			addListenerMethod.invoke(c, proxy);
		}
		catch(ReflectiveOperationException e)
		{
			
		}
	}
}
