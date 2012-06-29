package svl.pos.data;

import java.util.List;

import svl.pos.dominio.TipoServicio;

public interface ITipoServicioDAO {

	public List<TipoServicio> recuperarTodosLosTipoServicio();
	public String selectTiseDescricoByTiseid(int tise_id);
	public String selectServDescricoByTiseid(int tise_id);
	public String selectTiseDescricoByServid(int serv_id);
}
