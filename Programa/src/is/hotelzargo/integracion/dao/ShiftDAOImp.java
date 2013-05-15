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
		Date checkIn = ((ShiftTransfer) t).getCheckin();
		Date checkOut = ((ShiftTransfer) t).getCheckout();
		
		try {
			
			statement.executeUpdate("INSERT INTO Shifts (nameShift, checkIn, checkOut) VALUES " +
					"("+nameShift+", "+checkIn+", "+checkOut+ ");" );					
			
		} catch (SQLException e) {
			e.getMessage();
			throw new ShiftIntegrationException("Problema al crear turno "+nameShift);			
		}
		
	}

	@Override
	public void deleteShift(int id) throws ShiftIntegrationException {
		//Se sabe que si llega aquí no hay empleados con este turno y se puede eliminar
		String QueryString = "DELETE FROM Shifts WHERE id="+id+";";
		  try {
			  
			rs = statement.executeQuery(QueryString);			
			
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
					Date checkIn = rs.getDate(2);
					Date checkOut = rs.getDate(3);
					
					ShiftTransfer s = new ShiftTransfer(id,nameShift, checkIn, checkOut);					
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
					Date checkIn = rs.getDate(2);
					Date checkOut = rs.getDate(3);
					
					ShiftTransfer s = new ShiftTransfer(id,name, checkIn, checkOut);					
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
					Date checkIn = rs.getDate(2);
					Date checkOut = rs.getDate(3);
					
					ShiftTransfer s = new ShiftTransfer(id,nameShift, checkIn, checkOut);						
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
		Date checkIn = ((ShiftTransfer) t).getCheckin();
		Date checkOut = ((ShiftTransfer) t).getCheckout();
		

		//UPDATE
		String QueryString = "UPDATE Shifts SET nameShift="+nameShift+"," +
				"checkIn="+checkIn+",checkOut="+checkOut+"  WHERE id="+id+";";
		  try {
			  
			rs = statement.executeQuery(QueryString);
			
		  } catch (SQLException e) {
			e.printStackTrace();
			throw new ShiftIntegrationException("Problema al actualizar turno "+nameShift);				
		  }
		
	}

	@Override
	public boolean searchShift(int id) throws ShiftIntegrationException {
		
		String QueryString = "SELECT * FROM Shifts WHERE id="+id+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ShiftIntegrationException("Problema al buscar turno "+id);				
		  }			

		return false;
	}
	
	@Override
	public boolean searchShift(String name, Date checkIn, Date checkOut) throws ShiftIntegrationException {
		
		String QueryString = "SELECT * FROM Shifts WHERE nameShift="+name+" AND checkIn="+checkIn+"AND checkOut="+checkOut+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//solo me devolvera 1 fila
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ShiftIntegrationException("Problema al buscar turno ");				
		  }			

		return false;
	}

	@Override
	public boolean employeesWithShift(int id) throws ShiftIntegrationException {
		//Buscamos en la tabla empleados que ninguno tenga este ID turno
		String QueryString = "SELECT * FROM Employees WHERE shiftID="+id+";";
		  try {
			rs = statement.executeQuery(QueryString);			
			//si devuelve alguna fila, este turno tiene empleados asignados
			  while (rs.next()) {				  					
					return true;				  
			  }
			
		  } catch (SQLException e) {
			e.getMessage();
			throw new ShiftIntegrationException("Problema al buscar empleados con cierto turno ");				
		  }		
		
		
		return false;
	}

}
