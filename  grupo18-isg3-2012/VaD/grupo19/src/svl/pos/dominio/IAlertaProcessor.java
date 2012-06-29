package svl.pos.dominio;


import java.util.List;

public interface IAlertaProcessor {

	void registrarAlerta(String aler_nombrecontacto,String aler_emailcontacto,int prov_id, int muni_id, int serv_id, int tibu_id, int tise_id);
	void registrarAlerta(Alerta alerta);
	void realizarBusqueda(Alerta alerta);
	//void mandarCorreo(Busqueda busqueda);
	
}
