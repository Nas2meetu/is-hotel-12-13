package is.hotelzargo.negocio.room.appservice;

import java.util.Vector;

import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.integracion.factory.DAOFactory;
import is.hotelzargo.integracion.room.dao.RoomDAO;
import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;

public class RoomAppServicesImp implements RoomAppServices {

	@Override
	public void addRoom(RoomTransfer t) throws RoomAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();		
		
		checkData (t);
		
		try {
			if (!dao.searchRoomByRoomID (t.getnumRoom())) {
				dao.createRoom(t);
			}
			else  
				throw new RoomAppServicesException("La habitacion ya existe");
		}	
		catch (RoomIntegrationException e) {
			throw new RoomAppServicesException (e.getMessage());
		}
	}	
	
	@Override
	public void delRoom(int id) throws RoomAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		try {
			if (dao.searchRoomByID(id)){
				if (!dao.existsBooksWithRoom(id)){
					dao.deleteRoom(id);
				}
				else{
					throw new RoomAppServicesException("La habitación a eliminar tiene reservas pendientes");
				}
			}
			else{
				throw new RoomAppServicesException("El ID de la habitación a eliminar no existe");
			}
				
		} catch (RoomIntegrationException e) {
			throw new RoomAppServicesException (e.getMessage());
		}
		
	}

	@Override
	public Vector<RoomTransfer> listRoom() throws RoomAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		try {
			return dao.listRoom();
		} catch (RoomIntegrationException e) {
			throw new RoomAppServicesException (e.getMessage());
		}
		
	}

	@Override
	public void modRoom(RoomTransfer t) throws RoomAppServicesException {
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		checkData(t);
		
		try {
			if (dao.searchRoomByID(t.getId())){				
				if (!dao.checkNumRoom(t.getId(),t.getnumRoom())){
					dao.updateRoom(t);
				}
				else{
					throw new RoomAppServicesException ("la habitacion a modificar intenta cambiar a un número de habitación ya existente");
				}
			}
			else{
				throw new RoomAppServicesException ("la habitacion a modificar no existe");
			}
		} catch (RoomIntegrationException e) {
			throw new RoomAppServicesException (e.getMessage());
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

}
