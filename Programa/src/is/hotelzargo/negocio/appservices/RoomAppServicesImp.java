package is.hotelzargo.negocio.appservices;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.RoomDAO;
import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.transfer.RoomTransfer;

public class RoomAppServicesImp implements RoomAppServices {

	@Override
	public void addRoom(RoomTransfer t) throws RoomAppServicesException {
		// TODO crear habitacion
		
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		
		checkData (t);
		
		try {
			if (!dao.searchRoom (t.getnumRoom(), t.getnumBeds(), t.getPrice())) {
				dao.createRoom(t);
			}
			else  
				throw new RoomAppServicesException("La habitacion ya existe");
		}	
		catch (RoomIntegrationException e) {
			e.getMessage();
		}
}
		

	private void checkData (RoomTransfer t) throws RoomAppServicesException {
		int numRoom = t.getnumRoom();
		int numBeds = t.getnumBeds();
		float price = t.getPrice();
		
		if (price<0)
			throw new RoomAppServicesException("Precio invalido");
		if (numBeds<=0)
			throw new RoomAppServicesException("Numero camas invalido");
		if (numRoom<=0)
			throw new RoomAppServicesException("Numero habitacion invalido");
	}
	
	
	@Override
	public void delRoom(int id) throws RoomAppServicesException {
		// TODO borrar habitacion
		
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		try {
				dao.deleteRoom(id);
				
		} catch (RoomIntegrationException e) {
			throw new RoomAppServicesException (e.getMessage());
		}
		
	}

	@Override
	public void listRoom() throws RoomAppServicesException {
		// TODO listar habitaciones
		
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		try {
			dao.listRoom();
		} catch (RoomIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void modRoom(RoomTransfer t) throws RoomAppServicesException {
		// TODO modificar habitaciones
		
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		try {
			dao.updateRoom(t);
		} catch (RoomIntegrationException e) {
			e.printStackTrace();
		}
		
	}

}
