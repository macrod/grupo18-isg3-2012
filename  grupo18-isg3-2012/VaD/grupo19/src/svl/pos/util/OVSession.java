/********************************************************************************
** @nombre      OVSession.java 
** @fecha       12-08-2011
** @descripcion Clase que controla la session del navegador de aquellos usuarios
**              que est�n conectados
********************************************************************************/	
package svl.pos.util;

import javax.servlet.http.HttpSession;


public class OVSession  
{
  /*******************************************************************************
  ** Atributos de la clase
  *******************************************************************************/
  private HttpSession session;    
  private int minutosMaxInactividad;
  private long ultimoAcceso;

  /*******************************************************************************
  ** Constructor de la clase
  *******************************************************************************/  
  public OVSession() 
  {
    this.ultimoAcceso = System.currentTimeMillis();        
  }

  /*******************************************************************************
  ** M�todo que informa si la sesi�n ha caducado o no
  ** @param session Sesi�n de la que se quiere averiguar si ha caducado o no
  ** @param minutos N�mero de minutos de inactividad que tienen que pasar para que 
  **                caduque una sesi�n
  ** @return boolean Valor que indica si la sesi�n ha caducado o no
  *******************************************************************************/
  public boolean estaCaducada(HttpSession session, int minutosMaxInactividad)
  {
    boolean estaCaducada = false;
    double diferenciaDosUltimosAccesosSession = 0;
        
    this.session = session;
    this.minutosMaxInactividad = minutosMaxInactividad;

    /*******************************************************************************
    ** Se obtienen los segundos que han pasado entre el ultimo acceso y el actual
    *******************************************************************************/    
    diferenciaDosUltimosAccesosSession = (System.currentTimeMillis() - ultimoAcceso)/1000;

    /*******************************************************************************
    ** Se comprueba si se han superado los segundos de caducidad
    *******************************************************************************/    
    if(diferenciaDosUltimosAccesosSession > (new Double(minutosMaxInactividad)).doubleValue())
    {
      estaCaducada = true;
    }
    else
    {
      this.ultimoAcceso = System.currentTimeMillis();                
    }
    
    return estaCaducada;
  }

  /*******************************************************************************
  ** M�todo que devuelve el valor de la propiedad session
  ** @return HttpSession Valor de la propiedad session
  *******************************************************************************/  
  public HttpSession getSession() 
  {
    return this.session;
  }

  /*******************************************************************************
  ** M�todo que devuelve el valor de la propiedad minutosMaxInactividad
  ** @return int Valor de la propiedad minutosMaxInactividad
  *******************************************************************************/
  public int getMinutosMaxInactividad() 
  {
    return this.minutosMaxInactividad;
  }

  /*******************************************************************************
  ** M�todo que devuelve el valor de la propiedad timeUltimoAcceso
  ** @return long Valor de la propiedad timeUltimoAcceso
  *******************************************************************************/
  public long getUltimoAcceso() 
  {
    return this.ultimoAcceso;
  }

  /*******************************************************************************
  ** M�todo que establece el valor de la propiedad session
  ** @param session Valor de la propiedad session
  *******************************************************************************/  
  public void setSession(HttpSession session) 
  {
    this.session = session;
  }

  /*******************************************************************************
  ** M�todo que establece el valor de la propiedad minutosMaxInactividad
  ** @param int Valor de la propiedad minutosMaxInactividad
  *******************************************************************************/    
  public void setMinutosMaxInactividad(int minutosMaxInactividad) 
  {
    this.minutosMaxInactividad = minutosMaxInactividad;
  }
    
  /*******************************************************************************
  ** M�todo que establece el valor de la propiedad ultimoAcceso
  ** @param long Valor de la propiedad ultimoAcceso
  *******************************************************************************/  
  public void setUltimoAcceso(long ultimoAcceso) 
  {
    this.ultimoAcceso = ultimoAcceso;
  }
}
