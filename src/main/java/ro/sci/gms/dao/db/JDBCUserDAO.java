package ro.sci.gms.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ro.sci.gms.dao.UserDAO;
import ro.sci.gms.domain.Appointment;
import ro.sci.gms.domain.Doctor;
import ro.sci.gms.domain.User;
import ro.sci.gms.temp.Li;

/**
 * Pure JDBC implementation for {@link EmployeeDAO}.
 * 
 * @author sebi
 *
 */
@Repository
public class JDBCUserDAO implements UserDAO<User> {
	private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUserDAO.class);

	private String host;
	private String port;
	private String dbName;
	private String userName;
	private String pass;

	public JDBCUserDAO() {
	} // seems not to be working without this default constructor

	public JDBCUserDAO(String host, String port, String dbName, String userName, String pass) {
		this.host = host;
		this.userName = userName;
		this.pass = pass;
		this.port = port;
		this.dbName = dbName;

	}

	public User update(User user) {
		Li.st("Saving User to DB started.");

		Connection connection = newConnection();

		update(connection, user);

		try {
			connection.commit();
			connection.close();
		} catch (Exception ex) {
			throw new RuntimeException("Error commiting or closing while writing user to DB. (91)", ex);
		}
		return user;

	}

	protected User update(Connection connection, User user) {
		try {
			PreparedStatement ps = null;
			if (user.getId() > 0) {
				ps = connection.prepareStatement(
						"update users set first_name=?, last_name=?, user_name=?, password=?, address=?, phone= ?, email= ?, role=? "
								+ "where id = ? returning id");
			} else {

				ps = connection.prepareStatement(
						"insert into users (first_name, last_name, user_name, password, address, phone, email, role) "
								+ "values (?, ?, ?, ?, ?, ?, ?, ?) returning id");
			}

			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUserName());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getAdress());
			ps.setString(6, user.getPhone());
			ps.setString(7, user.getEmail());
			ps.setString(8, user.getRole().toString());

			if (user.getId() > 0) {
				ps.setLong(9, user.getId());
			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getLong(1));
			}
			rs.close();

		} catch (SQLException ex) {
			throw new RuntimeException("Error saving user to DB. (91)", ex);
		}

		return user;

	}

	@Override
	public Collection<User> getAll() {
		Connection connection = newConnection();

		Collection<User> result = new LinkedList<>();

		// try (ResultSet rs = connection.createStatement().executeQuery("select
		// * from employee")) {
		//
		// while (rs.next()) {
		// result.add(extractAppointment(rs));
		// }
		// connection.commit();
		// } catch (SQLException ex) {
		//
		// throw new RuntimeException("Error getting employees.", ex);
		// } finally {
		// try {
		// connection.close();
		// } catch (Exception ex) {
		//
		// }
		// }

		return result;
	}

	@Override
	public User findById(Long id) {
		Connection connection = newConnection();

		List<Appointment> result = new LinkedList<>();

		try (ResultSet rs = connection.createStatement().executeQuery("select * from employee where id = " + id)) {

			while (rs.next()) {
				result.add(extractAppointment(rs));
			}
			connection.commit();
		} catch (SQLException ex) {

			throw new RuntimeException("Error getting employees.", ex);
		} finally {
			try {
				connection.close();
			} catch (Exception ex) {

			}
		}

		if (result.size() > 1) {
			throw new IllegalStateException("Multiple Employees for id: " + id);
		}
		// return result.isEmpty() ? null : result.get(0);
		return null;
	}

	public boolean delete(User user) {
		boolean result = false;
		// Connection connection = newConnection();
		// try {
		// Statement statement = connection.createStatement();
		// result = statement.execute("delete from employee where id = " +
		// user.getId());
		// connection.commit();
		// } catch (SQLException ex) {
		//
		// throw new RuntimeException("Error updating employees.", ex);
		// } finally {
		// try {
		// connection.close();
		// } catch (Exception ex) {
		//
		// }
		// }
		return result;

	}

	/*
	 * @Override public Collection<Appointment> searchByName(String query) { if
	 * (query == null) { query = ""; } else { query = query.trim(); }
	 * 
	 * Connection connection = newConnection();
	 * 
	 * Collection<Employee> result = new LinkedList<>();
	 * 
	 * try (ResultSet rs = connection.createStatement() .executeQuery(
	 * "select * from employee where lower(first_name || ' ' || last_name) like '%"
	 * + query.toLowerCase() + "%'")) {
	 * 
	 * while (rs.next()) { result.add(extractAppointment(rs)); }
	 * connection.commit(); } catch (SQLException ex) {
	 * 
	 * throw new RuntimeException("Error getting employees.", ex); }
	 * 
	 * return result; }
	 */

	protected Connection newConnection() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();

			String url = new StringBuilder()//
					.append("jdbc:")//
					.append("postgresql")//
					.append("://")//
					.append(host)//
					.append(":")//
					.append(port)//
					.append("/")//
					.append(dbName)//
					.append("?user=")//
					.append(userName)//
					.append("&password=")//
					.append(pass).toString();
			Connection result = DriverManager.getConnection(url);
			result.setAutoCommit(false);

			return result;
		} catch (Exception ex) {
			throw new RuntimeException("Error getting DB connection. (91)", ex);
		}

	}

	private Appointment extractAppointment(ResultSet rs) throws SQLException {
		Appointment appointment = new Appointment();
		// appointment.setId(rs.getLong("id"));
		// appointment.setFirstName(rs.getString("first_name"));
		// appointment.setLastName(rs.getString("last_name"));
		// appointment.setJobTitle(rs.getString("job_title"));
		// appointment.setBirthDate(new
		// Date(rs.getTimestamp("birth_date").getTime()));
		// appointment.setEmploymentDate(new
		// Date(rs.getTimestamp("employment_date").getTime()));
		// appointment.setGender(Gender.valueOf(rs.getString("gender")));
		return appointment;

	}

	@Override
	public Collection<User> searchById(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
