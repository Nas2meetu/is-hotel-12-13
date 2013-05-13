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
				if (!dao.searchEmployee(t.getDNI()))
						throw new EmployeeAppServicesException("DNI repetido");
				else dao.createEmployee(t);
			} catch (EmployeeIntegrationException e) {
				e.getMessage();
			}
					
		} else {
			
			checkDataEmployeeServices(t);
			
			try{
				if (!dao.searchEmployee(t.getDNI()))
						throw new EmployeeAppServicesException("DNI repetido");
				else dao.createEmployee(t);
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
			throw new EmployeeAppServicesException("Sin contraseña");
		if(((EmployeeTransferAdmin)t).getTlf() == 0)
			throw new EmployeeAppServicesException("Sin tlf");
		
		
	}
	
	
	@Override
	public void delEmployee(String id) throws EmployeeAppServicesException {
		// TODO borrar empleado
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			dao.deleteEmployee(id);
		} catch (EmployeeIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	
	
	@Override
	public void listEmployee() throws EmployeeAppServicesException {
		// TODO listar empleados
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			dao.listEmployee();
		} catch (EmployeeIntegrationException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void modEmployee(EmployeeTransfer t)
			throws EmployeeAppServicesException {
		// TODO modificar empleado
		DAOFactory fac = DAOFactory.getInstance();
		EmployeeDAO dao = fac.getEmployeeDAO();
		
		try {
			dao.updateEmployee(t);
		} catch (EmployeeIntegrationException e) {
			e.printStackTrace();
		}
		
	}

}
