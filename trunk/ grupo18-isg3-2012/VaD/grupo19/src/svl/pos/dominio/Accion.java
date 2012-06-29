/********************************************************************************
** @nombre      Accion.java 
** @fecha       12-08-2011
** @descripcion Interfaz de los objetos que la clase FactoriaAccion crea
********************************************************************************/					
package svl.pos.dominio;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public interface Accion 
{
  /*******************************************************************************
  ** M�todo que ejecuta una accion
  ** @param request  Objeto que contiene la petici�n
  ** @param response Objeto que almacenar� la respuesta
  ** @param aplicacion  Objeto que contiene el contexto del servlet
  ** @exception ServletException Se produce cuando la petici�n no puede ser atendida
  ** @exception IOException Se produce cuando se detecta error de entrada/salida
  **                        mientras el servlet atiende la petici�n
  *******************************************************************************/
  public void execute(HttpServletRequest request,HttpServletResponse response, ServletContext config) throws ServletException,IOException;

  /*******************************************************************************
  ** M�todo que obtiene el valor del atributo vista
  ** @return String Devuelve el valor del atributo vista
  *******************************************************************************/
  public String getVista();

  /*******************************************************************************
  ** M�todo que obtiene el valor del atributo modelo
  ** @return Object Devuelve el valor del atributo modelo
  *******************************************************************************/
  public Object getModelo();
  
  /*******************************************************************************
  ** M�todo que configura el valor del atributo vista
  ** @param vista Nuevo valor del atributo vista
  *******************************************************************************/
  public void setVista(String vista);
  
  /*******************************************************************************
  ** M�todo que configura el valor del atributo modelo
  ** @param modelo Nuevo valor del atributo modelo
  *******************************************************************************/
  public void setModelo(Object modelo);
}