package co.edu.uco.rapizzas.data.dao.factory.postgresql;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.edu.uco.rapizzas.crosscuting.exception.RapizzasException;
import co.edu.uco.rapizzas.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.rapizzas.data.dao.entity.CategoryDAO;
import co.edu.uco.rapizzas.data.dao.entity.CustomerDAO;
import co.edu.uco.rapizzas.data.dao.entity.CustomerTableDAO;
import co.edu.uco.rapizzas.data.dao.entity.EmployeeDAO;
import co.edu.uco.rapizzas.data.dao.entity.IdentificationTypeDAO;
import co.edu.uco.rapizzas.data.dao.entity.OrderDAO;
import co.edu.uco.rapizzas.data.dao.entity.PaymentDAO;
import co.edu.uco.rapizzas.data.dao.entity.ProductDAO;
import co.edu.uco.rapizzas.data.dao.entity.ProductOrderDAO;
import co.edu.uco.rapizzas.data.dao.entity.SizeDAO;
import co.edu.uco.rapizzas.data.dao.entity.StatusDAO;
import co.edu.uco.rapizzas.data.dao.entity.TableDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.CategoryPostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.CustomerPostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.CustomerTablePostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.EmployeePostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.IdentificationTypePostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.OrderPostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.PaymentPostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.ProductOrderPostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.ProductPostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.SizePostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.StatusPostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.entity.postgresql.TablePostgreSqlDAO;
import co.edu.uco.rapizzas.data.dao.factory.DAOFactory;

public final class PostgreSQLDAOFactory extends DAOFactory {
	
	public PostgreSQLDAOFactory() {
		openConnection();
	}

	@Override
	protected void openConnection() {
		try {
			this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Rapizzas", "postgres", "Toya0727");
		} catch (final SQLException exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_CONNECTION_STATUS.getContent();
			throw RapizzasException.create(userMessage, technicalMessage);
		}catch(final Exception exception) {
			var userMessage = MessagesEnum.USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
			var technicalMessage = MessagesEnum.TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS.getContent();
			throw RapizzasException.create(exception, userMessage, technicalMessage);
		}
		
	}
	@Override
	public CategoryDAO getCategoryDAO() {
		return new CategoryPostgreSqlDAO(connection);
	}

	@Override
	public SizeDAO getSizeDAO() {
		return new SizePostgreSqlDAO(connection);
	}

	@Override
	public StatusDAO getStatusDAO() {
		return new StatusPostgreSqlDAO(connection);
	}

	@Override
	public TableDAO getTableDAO() {
		return new TablePostgreSqlDAO(connection);
	}

	@Override
	public CustomerDAO getCustomerDAO() {
		return new CustomerPostgreSqlDAO(connection);
	}

	@Override
	public CustomerTableDAO getCustomerTableDAO() {
		return new CustomerTablePostgreSqlDAO(connection);
	}

	@Override
	public EmployeeDAO getEmployeeDAO() {
		return new EmployeePostgreSqlDAO(connection);
	}

	@Override
	public IdentificationTypeDAO getIdentificationTypeDAO() {
		return new IdentificationTypePostgreSqlDAO(connection);
	}

	@Override
	public OrderDAO getOrderDAO() {
		return new OrderPostgreSqlDAO(connection);
	}

	@Override
	public PaymentDAO getPaymentDAO() {
		return new PaymentPostgreSqlDAO(connection);
	}

	@Override
	public ProductDAO getProductDAO() {
		return new ProductPostgreSqlDAO(connection);
	}

	@Override
	public ProductOrderDAO getProductOrderDAO() {
		return new ProductOrderPostgreSqlDAO(connection);
	}

}
