package svl.pos.data;

import java.util.List;

import svl.pos.dominio.Alerta;

public interface IAlertaDAO {

	public void addAlerta(Alerta alerta);
	public List<Alerta> recuperarTodasLasAlertas();
	public Alerta selectAlertaByAlerid(int aler_id);
	public Alerta selectAlertaByContacto(String aler_nombrecontacto);
	public int selectIDAlertaByNombre(String aler_nombrecontacto);
}
