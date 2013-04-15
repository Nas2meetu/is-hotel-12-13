package is.hotelzargo.negocio;

import is.hotelzargo.negocio.AppServices.ClientAppServices;
import is.hotelzargo.negocio.AppServices.ClientAppServicesImp;

public class BusinessFactoryImp extends BusinessFactory {

	@Override
	public ClientAppServices getClientAS() {
		return new ClientAppServicesImp();
	}

	@Override
	public Facade getFacade() {
		return new FacadeImp();
	}

	
}
