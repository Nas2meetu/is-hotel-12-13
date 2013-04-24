package is.hotelzargo.negocio;

import is.hotelzargo.negocio.AppServices.ClientAppServices;

public abstract class BusinessFactory {

	private static BusinessFactory instance = null;
	
	public static BusinessFactory getInstance(){
		if(instance == null){
			instance = new BusinessFactoryImp();
		}
		return (BusinessFactoryImp) instance;
	}
	
	public abstract Facade getFacade();
	
	public abstract ClientAppServices getClientAS();
}
