<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,java.util.List,java.util.ArrayList,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*"%>
<%


Usuario usuario = (Usuario)session.getAttribute("usuario");
Alerta alerta = (Alerta)session.getAttribute("alerta");

int id = 0;
if (alerta !=null){
	id = alerta.getAler_id();
}

 
//id = (new Integer(request.getParameter("idalerta"))).intValue();
String aler_nombrecontacto ="";
String aler_emailcontacto ="";
int tise_tiseid =0;
int  serv_servid =0;
String aler_descripcion ="";
int tibu_tibuid =0;
int prov_provid =0;
int muni_muniid =0;

String provincia="";
String municipio = "";
String tibubusqueda = "";
String tise = "";
String servicio = "";





if (id != 0) {
	JDBCAlertaDAO jdaoalertas = new JDBCAlertaDAO();
	//JDBCProvinciaDAO jdaoprov = new JDBCProvinciaDAO();
	JDBCMunicipioDAO jdaomuni = new JDBCMunicipioDAO(); 
	JDBCTipoServicioDAO jdaotise = new JDBCTipoServicioDAO();
	
	//Alerta alerta = jdaoalertas.selectAlertaByAlerid(id);
	
	aler_nombrecontacto = alerta.getAler_nombrecontacto();
	aler_emailcontacto =  alerta.getAler_emailcontacto();
	serv_servid = alerta.getServ_servid();
	tise_tiseid = alerta.getTise_tiseid();
	
	//servicio = jdaotise.selectServDescricoByTiseid(tise_tiseid);
	//tise = jdaotise.selectTiseDescricoByServid(serv_servid);
	
	aler_descripcion =  alerta.getAler_descripcion();
	tibu_tibuid =  alerta.getTibu_tibuid();
	prov_provid =  alerta.getProv_provid();
	
	//provincia = jdaoprov.selectProviciaByProvid(prov_provid);
	
	muni_muniid =  alerta.getMuni_muniid();
		
	municipio = jdaomuni.selectMunicipioByMuniid(muni_muniid);
	provincia = jdaomuni.selectProvinciaByMuniid(muni_muniid);
	
	
}
	
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    	<title>SVL - Servicio de Voluntariado Local</title>
    	<link rel="stylesheet" type="text/css" href="css/vad_estilo.css"></link>
    	<script type="text/javascript" src="js/prototype.js"></script>
    	<script type="text/javascript" src="js/reloaders.js"></script>
  	<script language="JavaScript" >
			
		function guardar(){
			window.document.formulario.action = "FrontController?accion=insertarAlerta";
			window.document.formulario.target = "_self";
			window.document.formulario.submit();
		}
		
		function volver(){
			document.formulario.action = "Inicio.jsp";
			document.formulario.submit();
		}
		</script>
  	</head>
  	<body leftmargin=0 topmargin=0>
    	<form action="" name="formulario" method="post" target="_blank">
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
			<tr><td valign="top" colspan="2">
            	<IFRAME src="Cabecera_buscador.jsp" scrolling="no" frameborder="0" width="100%" height="132"></IFRAME>
            </td></tr>
            <tr align="left">
            	<td width="200" align="left" valign="top">
					<IFRAME src="Menu.jsp" scrolling="no" frameborder="0" width="200" height="565"></IFRAME> 
            	</td>
				<td width="1065" align="left" valign="top">
					<table width="99%" border="0" cellpadding="0" cellspacing="0" align="left">
						<tr><td class="cabecera_pagina">ALTA DE ALERTAS</td></tr>
						<tr><td>
							<table width="100%" style="border:1px solid #1a81b6;" cellpadding="2" cellspacing="5">
								<tr>
									<td width="20%" class="etiqueta" nowrap="nowrap">Persona de contacto</td>
									<td width="35%" valign="top" nowrap="nowrap"><input type="text" class="caja_texto" value="<%=aler_nombrecontacto%>" size="45" name="aler_nombrecontacto"></td>
									<td width="20%" class="etiqueta" nowrap="nowrap">Email de contacto </td>
									<td width="25%" valign="top" nowrap="nowrap"><input type="text" class="caja_texto" value="<%=aler_emailcontacto %>" size="45" name="aler_emailcontacto"></td>
								</tr><%
					TipoServicioStore tss = new TipoServicioStore();
					List<TipoServicio> tiposervicio = tss.recuperarTodosLosTipoServicio();
				%>
								<tr>
									<td class="etiqueta" nowrap="nowrap">Tipo de Servicio</td>
									<td valign="top" nowrap="nowrap">
										<select class="caja_texto" size="1" name="tise_tiseid" id="tise_tiseid" onchange="reloadServicios(this);">
											<% for ( TipoServicio tipose : tiposervicio ){ %>
											<option value="<%=tipose.getTise_id()%>" <% if (tise.equals(tipose.getTise_id())) {%> selected<%} %>><%=tipose.getTise_descrico()%></option>
											<%} %>                                                                                                                   
										</select>
									</td>
									<td class="etiqueta" nowrap="nowrap">Nombre Servicio</td>
									<td valign="top" nowrap="nowrap">
									<select class="caja_texto" size="1" name="serv_servid">
											<option value="">(Seleccione un tipo de servicio)</option>
									</select>
									</td>
								</tr>
								<tr>
									<td class="etiqueta" nowrap>Descripción</td>
									<td valign="top" nowrap="nowrap"><input class="caja_texto" size="45" type="text" name="<%=aler_descripcion%>"></td>
									<td class="etiqueta" nowrap="nowrap">Tipo de Búsqueda</td>
									<td valign="top" nowrap="nowrap">
										<select class="caja_texto" size="1" name="tibu_tibuid">
											<option value="0" selected>Todos ...</option>                                                     
											<option value="1">Servicios ofertados</option>
											<option value="2">Servicios demandados</option>
										</select>
									</td>
								</tr>
								<tr>
									<%
					ProvinciaStore pr = new ProvinciaStore();
					List<Provincia> provincias = pr.recuperarTodasLasProvincias();
				%>
									<td class="etiqueta" nowrap="nowrap">Provincia</td>
									<td>
										<select class="caja_texto" name="prov_provid" id="prov_provid" onchange="reloadPoblaciones(this);">
										<option value="0">(Seleccione una provincia)</option>
										<% for ( Provincia pro : provincias ){ %>
											<option value="<%=pro.getProv_id()%>"  <% if (provincia.equals(pro.getProv_id())) {%> selected<%} %>><%=pro.getProv_nombre()%></option>
											<%} %>                                                                                                                   
										</select>
									</td>
									<td class="etiqueta" nowrap="nowrap">Municipio</td>
									<td>
										<select class="caja_texto" name="muni_muniid" id="muni_muniid">
											<option value="">(Seleccione una provincia)</option>
										</select>
									</td>
								</tr>
							</table>
						</td></tr>
						<tr><td>
							<table width="100%" cellpadding="0" cellspacing="0">
								<tr>
									<td align="right"><button class="boton_general" type="button" onclick="javascript:guardar();">Alta de Alerta</button></td>
									<td align="left"><button class="boton_general" type="button" onclick="javascript:volver();">Ir Menu</button></td>
								</tr>
							</table>							
						</td></tr>
					</table>
				</td>
			</tr>
			<tr><td valign="top" colspan="2">
            	<IFRAME src="Pie.htm" scrolling="no" frameborder="0" width="100%" height="25"></IFRAME>
            </td></tr>
		</table>
		</form>
	</body>
</html>