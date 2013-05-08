package is.hotelzargo.negocio.appservices;

import is.hotelzargo.negocio.exception.ServicesAppServicesException;
import is.hotelzargo.negocio.transfer.ServiceTransfer;

public interface ServicesAppServices {

	void addService(ServiceTransfer t)throws ServicesAppServicesException;

	void delService(String id)throws ServicesAppServicesException;;

	void listService()throws ServicesAppServicesException;;

	void modService(ServiceTransfer t)throws ServicesAppServicesException;;

}
