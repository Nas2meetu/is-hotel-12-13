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
		
		try {
			dao.createRoom(t);
		} catch (RoomIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delRoom(String id) throws RoomAppServicesException {
		// TODO borrar habitacion
		
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		try {
			dao.deleteRoom(id);
		} catch (RoomIntegrationException e) {
			e.printStackTrace();
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
