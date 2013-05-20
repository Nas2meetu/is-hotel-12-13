package is.hotelzargo.negocio.appservices;

import java.util.Vector;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.RoomDAO;
import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.negocio.exception.RoomAppServicesException;
import is.hotelzargo.negocio.transfer.RoomTransfer;

public class RoomAppServicesImp implements RoomAppServices {

	@Override
	public void addRoom(RoomTransfer t) throws RoomAppServicesException {
		//Crear habitacion
		
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
	
	
	@Override
	public void delRoom(int id) throws RoomAppServicesException {
		//Borrar habitacion
		
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		try {
			if (dao.searchRoomByID(id)){
				dao.deleteRoom(id);
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
		//Listar habitaciones
		
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
		//Modificar habitaciones
		
		DAOFactory fac = DAOFactory.getInstance();
		RoomDAO dao = fac.getRoomDAO();
		
		try {
			if (dao.searchRoomByID(t.getId())){
				dao.updateRoom(t);
			}
			else{
				throw new RoomAppServicesException ("la habitacion a modificar no existe");
			}
		} catch (RoomIntegrationException e) {
			throw new RoomAppServicesException (e.getMessage());
		}
		
	}

}
