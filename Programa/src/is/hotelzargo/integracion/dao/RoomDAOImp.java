package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.RoomIntegrationException;
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
		
		int room_number = ((RoomTransfer) t).getnumRoom();
		float price = ((RoomTransfer) t).getPrice();
		int bed_number = ((RoomTransfer) t).getnumBeds();
		
		try {
			
			statement.executeUpdate("INSERT INTO Rooms (room_number, price, bed_number) VALUES " +
					"("+room_number+", "+price+", "+bed_number+");" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new RoomIntegrationException("Problema al crear habitación");			
		}
		
	}

	@Override
	public void deleteRoom(int id) throws RoomIntegrationException {
		// Se busca habitacion y se elimina
		String QueryString = "DELETE FROM Rooms WHERE id="+id+";";
				  try {
					  
					rs = statement.executeQuery(QueryString);			
					
				  } catch (SQLException e) {
					e.getMessage();
					throw new RoomIntegrationException("Problema al eliminar  habitación ");				
				  }			
						
	}

	@Override
	public RoomTransfer getRoom(int id) throws RoomIntegrationException {
		// Se devuelve habitacion
		String QueryString = "SELECT * FROM Rooms WHERE id="+id+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  
				  	int room_number = rs.getInt(2);
				  	float price = rs.getFloat(3);
				  	int bed_number = rs.getInt(4);
					
				  	RoomTransfer r = new RoomTransfer(id,bed_number, room_number, price);					
					return r;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new RoomIntegrationException("Problema al buscar habitación ");				
		  }			
		
		return null;
	}

	@Override
	public Vector<RoomTransfer> listRoom() throws RoomIntegrationException {
		String QueryString = "SELECT * FROM Rooms;";
		  try {
			rs = statement.executeQuery(QueryString);			
			//recorro habitaciones, metiendolas en el vector
			Vector<RoomTransfer> rooms = new Vector<RoomTransfer>();
			
			  while (rs.next()) {
				  
				  	int id = rs.getInt(1);
				  	int room_number = rs.getInt(2);
					float price = rs.getFloat(3);
					int bed_number = rs.getInt(4);
					
					RoomTransfer r = new RoomTransfer(id,bed_number, room_number, price);						
					rooms.add(r);
				  
			  }
			  
			  return rooms;
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new RoomIntegrationException("Problema al referenciar listado habitaciones");				
		  }	
	}

	@Override
	public void updateRoom(RoomTransfer t) throws RoomIntegrationException {
		int id = ((RoomTransfer) t).getId();
		int room_number = ((RoomTransfer) t).getnumRoom();
		float price = ((RoomTransfer) t).getPrice();
		int bed_number = ((RoomTransfer) t).getnumBeds();
		

		//UPDATE
		String QueryString = "UPDATE Rooms SET room_number="+room_number+"," +
				"price="+price+",bed_number="+bed_number+"  WHERE id="+id+";";
		  try {
			  
			rs = statement.executeQuery(QueryString);
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new RoomIntegrationException("Problema al actualizar habitacion");				
		  }
		
	}
	
	@Override
	public boolean searchRoom(int numBeds, int numRoom, float price) throws RoomIntegrationException{
		// Se busca habitacion llamadas a la BBDD
		String QueryString = "SELECT * FROM Rooms WHERE room_number="+numRoom+" AND bed_number="+numBeds+"AND price="+price+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new RoomIntegrationException("Problema al buscar habitación ");				
		  }			
		
		return false;
	}
	
	@Override
	public boolean searchRoomByID(int id) throws RoomIntegrationException{
		// Se busca habitacion llamadas a la BBDD
				String QueryString = "SELECT * FROM Rooms WHERE id="+id+";";
				  try {
					rs = statement.executeQuery(QueryString);			
					//solo me devolvera 1 fila
					  while (rs.next()) {				  					
							return true;				  
					  }
					
				  } catch (SQLException e) {
					e.getMessage();
					throw new RoomIntegrationException("Problema al buscar habitación ");				
				  }			
				
				return false;
	}

}
