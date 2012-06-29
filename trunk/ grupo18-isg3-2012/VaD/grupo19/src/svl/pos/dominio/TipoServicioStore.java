package svl.pos.dominio;

import java.util.List;

import svl.pos.data.ITipoServicioDAO;
import svl.pos.data.JDBCTipoServicioDAO;

public class TipoServicioStore implements ITipoServicioDAO{

	JDBCTipoServicioDAO dao = new JDBCTipoServicioDAO();

	@Override
	public List<TipoServicio> recuperarTodosLosTipoServicio() {
		// TODO Auto-generated method stub
		return dao.recuperarTodosLosTipoServicio();
	}

	@Override
	public String selectTiseDescricoByTiseid(int tise_id) {
		// TODO Auto-generated method stub
		return dao.selectTiseDescricoByTiseid(tise_id);
	}

	@Override
	public String selectTiseDescricoByServid(int serv_id) {
		// TODO Auto-generated method stub
		return dao.selectTiseDescricoByServid(serv_id);
	}

	@Override
	public String selectServDescricoByTiseid(int tise_id) {
		// TODO Auto-generated method stub
		return dao.selectServDescricoByTiseid(tise_id);
	}
	
}







	