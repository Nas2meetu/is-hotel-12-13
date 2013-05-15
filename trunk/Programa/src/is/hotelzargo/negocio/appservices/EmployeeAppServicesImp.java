package is.hotelzargo.negocio.appservices;

import is.hotelzargo.integracion.DAOFactory;
import is.hotelzargo.integracion.dao.EmployeeDAO;
import is.hotelzargo.integracion.exception.EmployeeIntegrationException;
import is.hotelzargo.negocio.exception.EmployeeAppServicesException;
import is.hotelzargo.negocio.transfer.ClientTransferIndividual;
import is.hotelzargo.negocio.transfer.EmployeeTransfer;
import is.hotelzargo.negocio.transfer.EmployeeTransferAdmin;

public class EmployeeAppServicesImp implements EmployeeAppServices {

	@Override
	public void addEmployee(EmployeeTransfer t)
			throws EmployeeAppServicesException {
		
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		if(t instanceof EmployeeTransferAdmin ) {
			
			checkDataEmployeeAdmin(t);
			
			try{
				if (dao.searchEmployee(t.getDNI()))
						throw new EmployeeAppServicesException("DNI repetido");
				else dao.createEmployeeAdmin(t);
			} catch (EmployeeIntegrationException e) {
				e.getMessage();
			}
					
		} else {
			
			checkDataEmployeeServices(t);
			
			try{
				if (dao.searchEmployee(t.getDNI()))
						throw new EmployeeAppServicesException("DNI repetido");
				else dao.createEmployeeServices(t);
			} catch (EmployeeIntegrationException e) {
				e.getMessage();
			}
		}
	
		
	}

	private void checkDataEmployeeServices(EmployeeTransfer t) throws EmployeeAppServicesException {
		if(!((EmployeeTransferAdmin)t).getDNI().isEmpty()) 
			throw new EmployeeAppServicesException("Sin DNI");
		if(!((EmployeeTransferAdmin)t).getName().isEmpty())
			throw new EmployeeAppServicesException("Sin nombre");
		if	(!((EmployeeTransferAdmin)t).getSurname().isEmpty())
			throw new EmployeeAppServicesException("Sin apellido");
		
	}

	private void checkDataEmployeeAdmin(EmployeeTransfer t) throws EmployeeAppServicesException {
		
		if(!((EmployeeTransferAdmin)t).getDNI().isEmpty()) 
			throw new EmployeeAppServicesException("Sin DNI");
		if(!((EmployeeTransferAdmin)t).getName().isEmpty())
			throw new EmployeeAppServicesException("Sin nombre");
		if(!((EmployeeTransferAdmin)t).getSurname().isEmpty())
			throw new EmployeeAppServicesException("Sin apellido");
		if(!((EmployeeTransferAdmin)t).getPassword().isEmpty())
			throw new EmployeeAppServicesException("Sin contrase�a");
		if(((EmployeeTransferAdmin)t).getTlf().isEmpty())
			throw new EmployeeAppServicesException("Sin tlf");
		
		
	}
	
	
	@Override
	public void delEmployee(int id) throws EmployeeAppServicesException {
		// Borrar empleado
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			if (dao.searchEmployeeByID(id)){
				dao.deleteEmployee(id);
			}
			else{
				throw new EmployeeAppServicesException("Empleado inexistente con ese ID");
			}
		} catch (EmployeeIntegrationException e) {
			throw new EmployeeAppServicesException("Problema al eliminar empleado en BD");
		}
		
	}

	
	
	@Override
	public void listEmployee() throws EmployeeAppServicesException {
		// Listar empleados
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			dao.listEmployee();
		} catch (EmployeeIntegrationException e) {
			throw new EmployeeAppServicesException("Problema al lista empleados en BD");
		}
		
	}

	@Override
	public void modEmployee(EmployeeTransfer t)
			throws EmployeeAppServicesException {
		// Modificar empleado
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			if (dao.searchEmployeeByID(t.getId())){
				if(t instanceof EmployeeTransferAdmin ) {
					dao.updateEmployeeAdmin(t);
				}
				else{
					dao.updateEmployeeServices(t);
				}
			}
			else{
				throw new EmployeeAppServicesException("Empleado a actualizar no encontrado");
			}
		} catch (EmployeeIntegrationException e) {
			throw new EmployeeAppServicesException("Problema al actualizar empleados en BD");
		}
		
	}

}
