package svl.pos.data;

import java.util.List;


import svl.pos.dominio.Usuario;
import svl.pos.dominio.Voluntario;
import svl.pos.dominio.Servicio;

public interface IVoluntarioDAO {
	
	
	Usuario consultarMisdatos(String usua_id);
	void addAlerta(String usua_usuario, String usua_email);
    void insertarVoluntario(Usuario usuario);
    int recuperarVoluidByUsuaID(int usua_id);
	
}
