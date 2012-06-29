package svl.pos.data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import svl.pos.dominio.Alerta;
import svl.pos.dominio.Usuario;
import svl.pos.util.*;

import com.mysql.jdbc.Connection;

public class JDBCAlertaDAO implements IAlertaDAO{

	private Connection conn;
	
	
	public JDBCAlertaDAO(){
		conn =  (Connection) ConnectionManager.getInstance().checkOut();
	}

	@Override
	public void addAlerta(Alerta alerta) {
		String sql = "INSERT INTO alertas (aler_nombrecontacto, aler_emailcontacto, tise_tiseid, serv_servid,aler_descripcion, tibu_tibuid, prov_provid, muni_muniid,aler_isregistrado) VALUES (?,?,?,?,?,?,?,?,?) ";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
	
			stmt.setString(1, alerta.getAler_nombrecontacto());
			stmt.setString(2, alerta.getAler_emailcontacto());
			stmt.setInt(3, alerta.getTise_tiseid());
			stmt.setInt(4, alerta.getServ_servid());
			stmt.setString(5, alerta.getAler_descripcion());
			stmt.setInt(6, alerta.getTibu_tibuid());
			stmt.setInt(7, alerta.getProv_provid());
			stmt.setInt(8, alerta.getMuni_muniid());
			stmt.setInt(9, 1);
			
			
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("Message: addAlerta" + e.getMessage());
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
	}

		
	
	@Override
	public List<Alerta> recuperarTodasLasAlertas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alerta selectAlertaByAlerid(int aler_id) {
		 {
				
				PreparedStatement stmt = null;
				String sql = "SELECT * FROM Alertas WHERE aler_id = ?   ";
				ResultSet result = null;
				Alerta alerta = new Alerta();
				try{
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, aler_id);
					result = stmt.executeQuery();
					result.next();
					
					
					alerta.setAler_id(result.getInt("aler_id"));
					alerta.setAler_nombrecontacto(result.getString("aler_nombrecontacto"));
					
					alerta.setAler_emailcontacto(result.getString("aler_emailcontacto"));
					alerta.setTise_tiseid(result.getInt("tise_tiseid"));
					alerta.setServ_servid(result.getInt("serv_servid"));
					alerta.setAler_descripcion(result.getString("aler_descripcion"));
					alerta.setTibu_tibuid(result.getInt("tibu_tibuid"));
					
					alerta.setProv_provid(result.getInt("prov_provid"));
					alerta.setMuni_muniid(result.getInt("muni_muniid"));
					
					
		        } catch (SQLException e) {
		            System.out.println("Message: selectUserById: " + e.getMessage());
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
		        return alerta;
		 }
	}

	@Override
	public Alerta selectAlertaByContacto(String aler_nombrecontacto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectIDAlertaByNombre(String aler_nombrecontacto) {
		PreparedStatement stmt = null;
		String sql = "SELECT * FROM Alertas WHERE aler_nombrecontacto = ?";
		ResultSet result = null;
		int id =  0;
		try{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, aler_nombrecontacto);
			result = stmt.executeQuery();
			result.next();
			id = result.getInt("aler_id");
        } catch (SQLException e) {
            System.out.println("Message: selectIDAlertaByNombre: " + e.getMessage());
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