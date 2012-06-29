package svl.test;

import svl.pos.data.*;
import svl.pos.dominio.*;


public class Tests {

	/**
	 * @param args
	 */
	public static void main() {
		MunicipioStore me = new MunicipioStore();
		JDBCUsuarioDAO jdbcuserdao = new JDBCUsuarioDAO();
		UsuarioStore us = new UsuarioStore();
		Usuario usuario = us.recuperarUsuarioByIdUsuario(2);
		System.out.println("Usuario : " + usuario.getUsua_nombre());
		
		String nombreus = usuario.getUsua_usuario();
		String claveus = usuario.getUsua_clave();
		System.out.println("Nombre : " + nombreus);
		System.out.println("Clave : " + claveus);
		String municipio = me.selectMunicipioByMuniid(2345);
		System.out.println("Municipio nº 2345 :" + municipio);
		
		//int id = jdbcuserdao.obtenerUsuaId(nombreus, claveus);
		//System.out.println("Id del usuario:  " + id);
		
		JDBCAlertaDAO jdaoalertas = new JDBCAlertaDAO();
		//JDBCProvinciaDAO jdaoprov = new JDBCProvinciaDAO();
		//JDBCMunicipioDAO jdaomuni = new JDBCMunicipioDAO(); 
		JDBCTipoServicioDAO jdaotise = new JDBCTipoServicioDAO();
		
		Alerta alerta = jdaoalertas.selectAlertaByAlerid(1);
		
		String aler_nombrecontacto = alerta.getAler_nombrecontacto();
		System.out.println("Nombrecontacto de la Alerta:  " + aler_nombrecontacto);
		String aler_emailcontacto =  alerta.getAler_emailcontacto();
		System.out.println("Email de Contacto de la Alerta : " + aler_emailcontacto);
		int serv_servid = alerta.getServ_servid();
		System.out.println("Servicio:  " + serv_servid);
		int tise_tiseid = alerta.getTise_tiseid();
		System.out.println("Tipo Servicio:  " + tise_tiseid);
		String servicio = jdaotise.selectServDescricoByTiseid(1);
		System.out.println("Nombre Servicio:  " + servicio);
		String tise = jdaotise.selectTiseDescricoByServid(serv_servid);
		System.out.println("Nombre Tipo ervicio: " + tise);
		String aler_descripcion =  alerta.getAler_descripcion();
		System.out.println("Descripción Alerta: " + aler_descripcion);
		
		
		int prov_provid =  alerta.getProv_provid();
		System.out.println("Provincia de la  Alerta: " + aler_descripcion);
	}

}
