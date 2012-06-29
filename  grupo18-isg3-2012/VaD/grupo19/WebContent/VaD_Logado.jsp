<%
response.setHeader("Pragma", "No-cache");
response.setDateHeader("Expires", 0);
response.setHeader("Cache-Control", "no-cache");
%>
 <%@page contentType="text/html; charset=windows-1252" pageEncoding="ISO-8859-1" language="java" import="java.io.*,java.sql.*,javax.sql.*,javax.naming.*,java.util.List,java.util.ArrayList,svl.pos.util.*,svl.pos.dominio.*,svl.pos.data.*"%>
<%
	String adonde = request.getParameter("adonde");
	System.out.println("VaD_Logado.jsp...adonde " + adonde);
	String action = "";
		if ("inicio".equals(adonde)){
			action = "FrontController?accion=entrar";
		}else if ("voluntarios".equals(adonde)) {
			action = "FrontController?accion=registrar";
		}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"></meta>
    	<title>VaD - Red Social de Voluntarios a Domicilio</title>
    	<link rel="stylesheet" type="text/css" href="css/vad_estilo.css"></link>
  	
	
	<script language="JavaScript" >
		function enviar(){
				document.formulario.action = <%=action%>;
				document.formulario.submit();
		}
	</script>
	</head>
  	<body leftmargin=0 topmargin=0>
		<div id="cabecera">
       		<div id="bola"></div>
    	</div>
		<form name="formulario" method="POST" action="FrontController?accion=entrar">
		<input name="adonde" id="adonde" type="hidden" value="<%=adonde%>">
		<table width="100%" align=left cellpadding=0 cellspacing=0>
  			<tr><td height=200></td></tr>
			<tr><td align=center>
			    <table align=center width="380" cellspacing="0" cellpadding="0">
					<tr><td class="cabecera_pagina" align=center colspan="2">CONTROL DE ACCESO USUARIOS REGISTRADOS</td></tr>
					<tr><td class="cuerpo_login">
						<table width="100%" align=center cellpadding=0 cellspacing=0>
							<tr>
								<td width="40%" class="etiqueta_login">Usuario&nbsp;</td>
								<td width="60%" align=left><input name="usuario" type="text" class="texto_login" id="usuario" size="30"></td>
							</tr>
							<tr>
								<td width="40%" class="etiqueta_login">Contraseña&nbsp;</td>
								<td width="60%" align=left><input name="clave" type="password" class="texto_login" id="clave" size="30"></td>
							</tr>
						</table>
					</td></tr>
				</table>
			</td></tr>
			<tr><td height=5></td></tr>
			<tr><td align=center>
				<button class="boton_general" type="button" onclick="javascript:document.formulario.submit();">Aceptar</button>
			</td></tr>
			<tr><td height="240"></td></tr>
			<tr><td width="100%" valign="top" colspan="2">
            	<IFRAME src="Pie.htm" scrolling="no" frameborder="0" width="100%" height="25"></IFRAME>
            </td></tr>
		</table>
		</form>
	</body>
</html>