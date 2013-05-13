package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.ClientIntegrationException;
import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.negocio.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.transfer.RoomTransfer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class RoomDAOImp implements RoomDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public RoomDAOImp(Connection connection){
    	this.connection = connection;
    	try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void createRoom(RoomTransfer t) throws RoomIntegrationException {
		
		/*int room_number = ((RoomTransfer) t).getRoomNumber();
		float price = ((RoomTransfer) t).getRoomPrice();
		int bed_number = ((RoomTransfer) t).getBedNumber();
		
		try {
			
			statement.executeUpdate("INSERT INTO Rooms (room_number, price, bed_number) VALUES " +
					"("+room_number+", "+price+", "+bed_number+");" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new ClientIntegrationException("Problema al crear cliente individual");			
		}*/
		
	}

	@Override
	public void deleteRoom(int id) throws RoomIntegrationException {
		// TODO llamadas a la BBDD
		
	}

	@Override
	public RoomTransfer getRoom(String id) throws RoomIntegrationException {
		// TODO llamadas a la BBDD
		return null;
	}

	@Override
	public Vector<RoomTransfer> listRoom() throws RoomIntegrationException {
		// TODO llamadas a la BBDD
		return null;
	}

	@Override
	public void updateRoom(RoomTransfer t) throws RoomIntegrationException {
		// TODO llamadas a la BBDD
		
	}
	
	@Override
	public boolean searchRoom(int numBeds, int numRoom, float price) throws RoomIntegrationException{
		// TODO llamadas a la BBDD
		return false;
	}
	
	@Override
	public boolean searchRoomByID(int id) throws RoomIntegrationException{
		// TODO llamadas a la BBDD
		return false;
	}

}
