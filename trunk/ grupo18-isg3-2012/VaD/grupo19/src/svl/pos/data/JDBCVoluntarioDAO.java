package svl.pos.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import svl.pos.util.UIDGenerator;
import svl.pos.util.Utiles;


import svl.pos.data.IUsuarioDAO;
import svl.pos.data.JDBCUsuarioDAO;
import svl.pos.dominio.Usuario;
import svl.pos.dominio.Voluntario;
import svl.pos.dominio.Servicio;

public class JDBCVoluntarioDAO implements IVoluntarioDAO{

	IVoluntarioDAO vdao;
	private JDBCUsuarioDAO jdbcUdao = new JDBCUsuarioDAO();
	
	/**
	 * Atributo que crea la conexion a la bd
	 */
	private Connection conn;
	
	/**
	 * Constructor de la clase
	 */
	public JDBCVoluntarioDAO(){
		conn = (Connection) ConnectionManager.getInstance().checkOut();
	}
	

	@Override
	public Usuario consultarMisdatos(String usua_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAlerta(String usua_usuario, String usua_email) {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void insertarVoluntario(Usuario usuario) {
		System.out.println("Estamos en insertarVoluntario()   ");
		
		String sql = "INSERT INTO voluntarios (volu_cod, usua_usuaid, volu_falta) VALUES (?,?, ?) ";
		String nombre = usuario.getUsua_nombre();
		int usua_id = usuario.getUsua_id();
		String falta = Utiles.obtenerFechaActual();
		PreparedStatement stmt = null;
		
		try {
			stmt =  conn.prepareStatement(sql);
	
			stmt.setString(1, nombre);
			stmt.setInt(2, usua_id);
			stmt.setString(3, falta);
			
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Message: insertarVoluntario" + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("ErrorCode: " + e.getErrorCode());
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
			}
		}
		System.out.println("Salimos de insertarVoluntario()   ");	
	}
		
	
	public int recuperarVoluidByUsuaID(int usua_id) {
		
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM voluntarios WHERE usua_usuaid = ?";
		ResultSet result = null;
		int id =  0;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, usua_id);
			result = stmt.executeQuery();
			result.next();
			id = result.getInt("volu_id");
        } catch (SQLException e) {
            System.out.println("Message: recuperarVoluidByUsuaID: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("ErrorCode: " + e.getErrorCode());
        } finally {
        	ConnectionManager.getInstance().checkIn(conn);
            try {
            	if (result != null)
            		result.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
            }
        }	
        return id;
	}
}
	
	
	
	
	

	
			


	
