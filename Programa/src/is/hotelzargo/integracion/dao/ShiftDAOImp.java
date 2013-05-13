package is.hotelzargo.integracion.dao;

import is.hotelzargo.integracion.exception.ShiftIntegrationException;
import is.hotelzargo.negocio.transfer.ShiftTransfer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ShiftDAOImp implements ShiftDAO {
	
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    
    
    public ShiftDAOImp(Connection connection){
    	this.connection = connection;
    	try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
    }

	@Override
	public void createShift(ShiftTransfer t) throws ShiftIntegrationException {
		
		String nameShift =((ShiftTransfer) t).getShift();
		Date entryTime = ((ShiftTransfer) t).getCheckin();
		Date checkOut = ((ShiftTransfer) t).getCheckout();
		
		try {
			
			statement.executeUpdate("INSERT INTO Shifts (nameShift, entryTime, checkOut) VALUES " +
					"("+nameShift+", "+entryTime+", "+checkOut+ ");" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new ShiftIntegrationException("Problema al crear turno "+nameShift);			
		}
		
	}

	@Override
	public void deleteShift(int id) throws ShiftIntegrationException {
		
		String QueryString = "SELECT * FROM Shifts WHERE id="+id+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  //TODO eliminar TODAS las dependencias de este turno
				  //No puede haber empleados con este turno
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al eliminar turno con ID "+id);				
		  }
		
	}

	@Override
	public ShiftTransfer getShift(int id) throws ShiftIntegrationException {
		
		String QueryString = "SELECT * FROM Shifts WHERE id="+id+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  
					String nameShift = rs.getString(1);
					Date entryTime = rs.getDate(2);
					Date checkOut = rs.getDate(3);
					
					ShiftTransfer s = new ShiftTransfer(id,nameShift, entryTime, checkOut);					
					return s;
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al referenciar turno con ID "+id);				
		  }			
		
		return null;
	}
	
	@Override
	public ShiftTransfer getShiftByName(String name) throws ShiftIntegrationException {
		
		String QueryString = "SELECT * FROM Shifts WHERE nameShift="+name+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {
				  
				  	int id = rs.getInt(0);
					Date entryTime = rs.getDate(2);
					Date checkOut = rs.getDate(3);
					
					ShiftTransfer s = new ShiftTransfer(id,name, entryTime, checkOut);					
					return s;
				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al referenciar turno "+name);				
		  }			
		//si no está ese turno se devuelve null
		return null;
	}

	@Override
	public Vector<ShiftTransfer> listShift() throws ShiftIntegrationException {
		
		String QueryString = "SELECT * FROM Shifts;";
		  try {
			rs = statement.executeQuery(QueryString);			
			//recorro turnos, metiendolos en el vector
			Vector<ShiftTransfer> shifts = new Vector<ShiftTransfer>();
			
			  while (rs.next()) {
				  
				  	int id = rs.getInt(0);
				  	String nameShift = rs.getString(1);
					Date entryTime = rs.getDate(2);
					Date checkOut = rs.getDate(3);
					
					ShiftTransfer s = new ShiftTransfer(id,nameShift, entryTime, checkOut);						
					shifts.add(s);
				  
			  }
			  
			  return shifts;
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al referenciar listado turnos");				
		  }			
		
	}

	@Override
	public void updateShift(ShiftTransfer t) throws ShiftIntegrationException {
		
		int id = ((ShiftTransfer) t).getId();
		String nameShift = ((ShiftTransfer) t).getShift();
		Date entryTime = ((ShiftTransfer) t).getCheckin();
		Date checkOut = ((ShiftTransfer) t).getCheckout();
		

		//UPDATE
		String QueryString = "UPDATE Shifts SET nameShift="+nameShift+"," +
				"entryTime="+entryTime+",checkOut="+checkOut+"  WHERE id="+id+";";
		  try {
			  
			rs = statement.executeQuery(QueryString);
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al actualizar turno "+nameShift);				
		  }
		
	}

	@Override
	public boolean searchShift(ShiftTransfer t) throws ShiftIntegrationException {
		
		int id = ((ShiftTransfer) t).getId();
		
		String QueryString = "SELECT * FROM Shifts WHERE id="+id+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al buscar turno "+id);				
		  }			

		return false;
	}
	
	@Override
	public boolean searchShiftByID(int id) throws ShiftIntegrationException {
				
		String QueryString = "SELECT * FROM Shifts WHERE id="+id+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al buscar turno "+id);				
		  }			

		return false;
	}

	@Override
	public boolean employeesWithShift(int id) throws ShiftIntegrationException {
		// TODO Auto-generated method stub
		return false;
	}

}
