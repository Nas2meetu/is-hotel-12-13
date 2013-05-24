package is.hotelzargo.integracion.room.dao;

import is.hotelzargo.integracion.exception.RoomIntegrationException;
import is.hotelzargo.negocio.room.transfer.RoomTransfer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class RoomDAOImp implements RoomDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public RoomDAOImp(){}

	@Override
	public void createRoom(RoomTransfer t) throws RoomIntegrationException {
		
		initDataBase();
		
		int room_number = ((RoomTransfer) t).getnumRoom();
		float price = ((RoomTransfer) t).getPrice();
		int bed_number = ((RoomTransfer) t).getnumBeds();
		
		try {
			
			statement.executeUpdate("INSERT INTO Rooms (room_number, price, bed_number) VALUES " +
					"('"+room_number+"', '"+price+"', '"+bed_number+"');" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new RoomIntegrationException("Problema al crear habitación");			
		}finally{
			closeConnectionDataBase();
		}
		
	}

	@Override
	public void deleteRoom(int id) throws RoomIntegrationException {
		
		initDataBase();
		// Se busca habitacion y se elimina
		String QueryString = "DELETE FROM Rooms WHERE id='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);			
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new RoomIntegrationException("Problema al eliminar  habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
						
	}
	//se trabaja con el numero de habitacion, que suponemos unico
	@Override
	public RoomTransfer getRoom(int id) throws RoomIntegrationException {
		
		initDataBase();
		// Se devuelve habitacion
		String QueryString = "SELECT * FROM Rooms WHERE id='"+id+"';";
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
			throw new RoomIntegrationException("Problema al buscar habitacion ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return null;
	}

	@Override
	public Vector<RoomTransfer> listRoom() throws RoomIntegrationException {
		
		initDataBase();
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
		  }finally{
			  closeConnectionDataBase();
		  }
	}

	@Override
	public void updateRoom(RoomTransfer t) throws RoomIntegrationException {
		
		initDataBase();
		int id = ((RoomTransfer) t).getId();
		int room_number = ((RoomTransfer) t).getnumRoom();
		float price = ((RoomTransfer) t).getPrice();
		int bed_number = ((RoomTransfer) t).getnumBeds();		

		//UPDATE
		String QueryString = "UPDATE Rooms SET room_number='"+room_number+"'," +
				"price='"+price+"',bed_number='"+bed_number+"' WHERE id='"+id+"';";
		  try {
			  
			statement.executeUpdate(QueryString);
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new RoomIntegrationException("Problema al actualizar habitacion");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
	}
	
	@Override
	public boolean searchRoom(int numBeds, int numRoom, float price) throws RoomIntegrationException{
		
		initDataBase();
		// Se busca habitacion llamadas a la BBDD
		String QueryString = "SELECT * FROM Rooms WHERE room_number='"+numRoom+"' AND bed_number='"+numBeds+"' AND price='"+price+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new RoomIntegrationException("Problema al buscar habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return false;
	}
	
	@Override
	public boolean searchRoomByID(int id) throws RoomIntegrationException{
		
		initDataBase();
		// Se busca habitacion llamadas a la BBDD
				String QueryString = "SELECT * FROM Rooms WHERE id='"+id+"';";
				  try {
					rs = statement.executeQuery(QueryString);			
					//solo me devolvera 1 fila
					  while (rs.next()) {				  					
							return true;				  
					  }
					
				  } catch (SQLException e) {
					e.getMessage();
					throw new RoomIntegrationException("Problema al buscar habitación ");				
				  }finally{
					  closeConnectionDataBase();
				  }
				
				return false;
	}
	
	@Override
	public boolean searchRoomByRoomID(int id) throws RoomIntegrationException{
		
		initDataBase();
		// Se busca habitacion llamadas a la BBDD
		String QueryString = "SELECT * FROM Rooms WHERE room_number='"+id+"';";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new RoomIntegrationException("Problema al buscar habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		
		return false;
	}
	
	//comprueba que el numero de habitacion modificado no aparece en otra fila
	@Override
	public boolean checkNumRoom(int id, int numRoom)
			throws RoomIntegrationException {
		initDataBase();
		String QueryString = "SELECT * FROM Rooms WHERE room_number='"+numRoom+"' AND id='"+id+"');";
		  try {
			rs = statement.executeQuery(QueryString);			
			//si hay alguna fila, quiere decir que esta modificando su propia habitacion
			  while (rs.next()) {
				  	//aparece en su propia fila, por lo que puede cambiarla sin problemas
					return false;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new RoomIntegrationException("Problema al checkear habitación ");				
		  }finally{
			  closeConnectionDataBase();
		  }
		  
		  //si llega aqui hay que mirar que no este ya esa habitacion
		  initDataBase();
			String QueryRoom = "SELECT * FROM Rooms WHERE room_number='"+numRoom+"');";
			  try {
				rs = statement.executeQuery(QueryRoom);			
				//si hay alguna fila
				  while (rs.next()) {
					  //no se puede modificar a ese numero de habitacion, porque ya existe
						return true;				  
				  }
				
			  } catch (SQLException e) {
				e.getMessage();
				throw new RoomIntegrationException("Problema al checkear habitación ");				
			  }finally{
				  closeConnectionDataBase();
			  }
		
			 return false;
	}
	
	private void initDataBase() throws RoomIntegrationException {
		
        try
        {
           Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
        	//JOptionPane.showMessageDialog(null, "Connection refused!");
        	throw new RoomIntegrationException("Conexion rechazada");
        }
        
        
        // Se registra el Driver de MySQL
        try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e1) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new RoomIntegrationException("Conexion rechazada");
		}
        
     // Establecemos la conexión con la base de datos.
        try {
        	connection = DriverManager.getConnection ("jdbc:mysql://localhost/test","pma", "password");
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, "Connection refused!");
			throw new RoomIntegrationException("Conexion rechazada");
		}        
		 
        try {
        	statement = connection.createStatement();
		} catch (SQLException e) {
			throw new RoomIntegrationException("Conexion rechazada");
		}
		
	}
	
	private void closeConnectionDataBase() throws RoomIntegrationException {
		try {
			connection.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RoomIntegrationException("Error al desconectar BBDD");
		}
	}

}
