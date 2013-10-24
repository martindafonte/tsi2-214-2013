package serviciosRest;

import java.util.Set;
import java.util.HashSet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import ejb.PruebaWebService;
@ApplicationPath("/Rest")
public class MongoServicios extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public MongoServicios(){
	     singletons.add(new MongoRest());
	     singletons.add(new PushRest());
	     empty.add(PruebaWebService.class);
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
