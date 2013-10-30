package serviciosRest;

import java.util.Set;
import java.util.HashSet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@ApplicationPath("/Rest")
public class ApplicationRest extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public ApplicationRest(){
	//     singletons.add(new MongoRest());
	     empty.add(PushRest.class);
	     empty.add(UsersRest.class);
	     empty.add(MongoRest.class);
	}
	
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}
}
