/********************************************************************************
** @nombre      Log4JInit.java 
** @fecha       12-08-2011
** @descripcion Clase que implementa un servlet que se utiliza para configurar
**              el log de la aplicaci�n con LOG4J
********************************************************************************/	
package svl.pos.util;

import java.io.IOException;
 
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Log4JInit extends HttpServlet 
{
  /*******************************************************************************
  ** M�todo que ser� invocado en la creaci�n del servlet
  ** @param config Objeto que contiene la configuraci�n del servlet
  ** @exception ServletException Se produce cuando la petici�n no puede ser atendida
  *******************************************************************************/ 
  public void init () throws ServletException 
  {    
    String nombreFichero = getInitParameter("fichero_configuracion");

    if(nombreFichero != null) 
    {
      DOMConfigurator.configureAndWatch(nombreFichero);
    }
  }

  /*******************************************************************************
  ** M�todo que procesa la petici�n HTTP con el m�todo Get
  ** @param request  Objeto que contiene la petici�n
  ** @param response Objeto que almacenar� la respuesta
  ** @exception ServletException Se produce cuando la petici�n no puede ser atendida
  ** @exception IOException Se produce cuando se detecta un error de entrada/salida
  **                        mientras el servlet atiende la petici�n
  *******************************************************************************/
  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {}

  /*******************************************************************************
  ** M�todo que procesa la petici�n HTTP con el m�todo Post
  ** @param request  Objeto que contiene la petici�n
  ** @param response Objeto que almacenar� la respuesta
  ** @exception ServletException Se produce cuando la petici�n no puede ser atendida
  ** @exception IOException Se produce cuando se detecta un error de entrada/salida
  **                        mientras el servlet atiende la petici�n
  *******************************************************************************/
  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {}

  /*******************************************************************************
  ** M�todo que ser� invocado en la destrucci�n del servlet
  *******************************************************************************/  
  public void destroy(){}

}
