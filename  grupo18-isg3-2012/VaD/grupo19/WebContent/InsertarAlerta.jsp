<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=iso-8859-1" pageEncoding="ISO-8859-1" language="java" import="java.sql.*,javax.sql.*,javax.naming.*,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<body>
<%

Usuario usuario = (Usuario)session.getAttribute("usuario");
Alerta alerta = new Alerta();

System.out.println("aler_nombrecontacto: " + request.getParameter("aler_nombrecontacto"));

String aler_nombrecontacto = request.getParameter("aler_nombrecontacto");
alerta.setAler_nombrecontacto(aler_nombrecontacto);

String aler_emailcontacto = request.getParameter("aler_emailcontacto");
alerta.setAler_emailcontacto(aler_emailcontacto);

int tise_tiseid = Integer.parseInt(request.getParameter("tise_tiseid"));
System.out.println("tise_tiseid: " + tise_tiseid);

alerta.setTise_tiseid(tise_tiseid);

/*
alerta.setTise_tiseid((new Integer(request.getParameter("tise_tiseid"))).intValue());
alerta.setServ_servid((new Integer(request.getParameter("serv_servid"))).intValue());
alerta.setAler_descripcion(request.getParameter("aler_descripcion"));
alerta.setTibu_tibuid((new Integer(request.getParameter("tibu_tibuid"))).intValue());	
*/

int prov_provid = Integer.parseInt(request.getParameter("prov_provid"));
alerta.setProv_provid(prov_provid);	

int muni_muniid = Integer.parseInt(request.getParameter("muni_muniid"));
alerta.setMuni_muniid(muni_muniid);	

JDBCAlertaDAO jdaoalertas = new JDBCAlertaDAO();


jdaoalertas.addAlerta(alerta);
int idalerta = jdaoalertas.selectIDAlertaByNombre(request.getParameter("aler_nombrecontacto"));

session.setAttribute("alerta",alerta);
session.setAttribute("idalerta",idalerta);
System.out.println("idalerta: " + idalerta);
response.sendRedirect("SVL_Alerta_1.jsp?"+idalerta);

%>
</body>
</html>