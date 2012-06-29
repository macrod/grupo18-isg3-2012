/********************************************************************************
** @nombre      Request.java 
** @fecha       12-08-2011
** @descripcion Clase Java que almacena todos los par�metros de una llamada de una p�gina HTML.
						Estos par�metros son tanto los del submit, como los introducidos manualmente en la llamada.
						Esta clase es st�tica, no necesita instanciarse.
********************************************************************************/	
package svl.pos.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.http.*;
import java.util.*;

import org.apache.log4j.Logger;
	
  public class Request {

  /** TRATAMIENTO DE PARAMETROS EN DEBUG */

  /** Hashtable con todos los par�metros. Se usa para depuraci�n
	*/
  private static Hashtable parametros=null;

  /*******************************************************************************
  ** Fichero de log
  *******************************************************************************/
  private static Logger logger = Logger.getLogger("sieja.Request.class"); 

  


  /** Crea un Array de String a partir de los elementos de un vector
	* @param valores Vector cuyos elementos son String
	* @return Array de String con los elementos del vector
	*/
  private static String [] construyeArray(Vector valores) {
	if(valores.size()>0) {
	  String [] valoresParametro= new String [valores.size()];
	  for(int i=0; i<valores.size();i++) {
		valoresParametro[i]=(String) valores.elementAt(i);
	  }
	  return valoresParametro;
	}
	else { //el par�metro no tiene valores
	  return null;
	}
  }  
  /** Devuelve a partir de un objeto HttpServletRequest el valor del par�metro solicitado.
	  Si dicho par�metro no existe devuelve null.
	  Decodifica los valores %XX pasados en forma x-www-form-encoded
	  @param request Objeto HttpServletRequest que encapsula la petici�n
	  @param nombreParametro Nombre del parametro solicitado
	  @return Si existe el par�metro solicitado, devuelve su valor, en caso de que
	  no exista devuelve null
	  */
  public static String getParameter(HttpServletRequest request,String nombreParametro)
  {
     
        if (parametros == null) {
          try{
              obtenerParametros(request);
          }catch(Exception e) {}
        }
      	return (String)parametros.get(nombreParametro);
   }
  

  /** Devuelve todos los valores de un par�metro
	  Decodifica los valores %XX pasados en forma x-www-form-encoded
	  @param request Objeto HttpServletRequest que encapsula la petici�n
	  @param nombreParametro Nombre del parametro solicitado
	  @return Si existe el par�metro solicitado, devuelve todos sus valores, en caso de que
	  no exista devuelve null
	  */
  public static String [] getParameterValues(HttpServletRequest request,String nombreParametro) {
	//buscamos tanto por el nombre del par�metro como por el nombre del par�metro codificado
	String nombreParamCodif =URLEncoder.encode(nombreParametro);
	String [] valores= request.getParameterValues(nombreParametro);
	if(valores==null) {
	  valores= request.getParameterValues(nombreParamCodif);
	}
	if(valores==null) {
	  String queryString = "&"+request.getQueryString() + "&";
	  valores = getValoresParametro(queryString, nombreParametro);
	}
	if(valores==null) {
	  String queryString = "&"+request.getQueryString() + "&";
	  valores = getValoresParametro(queryString, nombreParamCodif);
	}
	return valores;
  }  
  /** Devuelve el hashtable con los parametros
  * @return Parametros de la llamada
  */
 public static Hashtable getParametros() {
	return Request.parametros;
  }

/** Devuelve todos los valores de todos los par�mtros contenidos en el
   * request; incluidos los del QueryString
   * @param request Objeto HttpServletRequest que encapsula la petici�n
   * @return una cadena con el formato PARAM=VALOR&... con todos los par�metros
   * y todos los valores de dichos par�metros que est�n en el request
   */
public static String getTodosParametros(HttpServletRequest request) {
	//obtenemos todos los nombres de los par�metros
	String queryString = request.getQueryString(); //parametros que no proceden del SUBMIT()
	java.util.Enumeration parametros = request.getParameterNames();
	StringBuffer listParametros = new StringBuffer();
	while (parametros.hasMoreElements()) {
		String parametro = parametros.nextElement().toString();
    String parametroCodif = URLEncoder.encode(parametro);
		// Se obtiene el contenido del par�metro y se le quitan espacios finales o retornos
		String [] valores = request.getParameterValues(parametro); //tomamos todos los valores del par�metro
    for(int i=0;i<valores.length;i++) {
  		String contenido = valores[i].trim();
      logger.debug("**** PARAMETRO: " + parametro + " CONTENIDO: " + contenido + ".");
	  	//Debug.println("**** PARAMETRO: " + parametro + " CONTENIDO: " + contenido + ".");
		  //listParametros.append(parametro + "=" + request.getParameter(parametro) + "&");
		  String parametroValorSinCodificar = parametro + "=" + contenido;
		  String contenidoCodif = URLEncoder.encode(contenido);
		  String parametroValor = parametroCodif + "=" + contenidoCodif; //Si el parametro=valor esta en la queryString no se mete para
		  //if (queryString.indexOf(parametroValor) == -1 && queryString.indexOf(parametroValorSinCodificar) == -1) {	 // no duplicarlo
      if (!isValorContenido(getValoresParametro("&"+queryString+"&",parametro),contenido)) {	 // no duplicarlo
			  listParametros.append(parametroValor).append("&");
		  }
    }
	}
  logger.debug("*********** LA URL SIN QUERYSTRING: " + listParametros);
  logger.debug("*********** LA QUERYSTRING CON PAR�METROS: " + queryString);
	//Debug.println("*********** LA URL SIN QUERYSTRING: " + listParametros);
	//Debug.println("*********** LA QUERYSTRING CON PAR�METROS: " + queryString);
	listParametros.append(queryString);
  logger.debug("*********** LA URL CON QUERYSTRING: " + listParametros);
	//Debug.println("*********** LA URL CON QUERYSTRING: " + listParametros);
	String urlString = listParametros.toString();
  logger.debug("*********** LA paginaActual: " + urlString);
	//Debug.println("*********** LA paginaActual: " + urlString);
	return urlString;
}
  /** Devuelve cierto si 'valor' est� incluido en la lista de valores
    * @param valoresParams Lista de valores de un par�metro
    * @param valor Valor buscado
    * @return Cierto si 'valor' est� incluido en la lista de valores
    */
  private static boolean isValorContenido(String []valoresParams, String valor) {
    if(valoresParams!=null) {
      for(int i=0;i<valoresParams.length;i++) {
        if(valor.equals(valoresParams[i])) {
          logger.debug("VALOR "+valor+" CONTENIDO EN LA QUERYSTRING");
          //Debug.println("VALOR "+valor+" CONTENIDO EN LA QUERYSTRING");
          return true;
        }
      }
    }
    return false;
  }

  /** Devuelve un array con todos los valores que un par�metro
	* presenta en un QueryString
	* @param queryString String que contiene los parametros de una petici�n
	* @param nombreParametro Nombre del paramertro solicitado
	* @return Si existe el par�metro solicitado, devuelve todos sus valores, en caso de que
	* no exista devuelve null
	*/
  private static String [] getValoresParametro(String queryString, String nombreParametro) {
	Vector valores = new Vector();
	int indComienzoPar = queryString.indexOf("&"+nombreParametro+"=");
	int longQueryString =queryString.length();
	if(indComienzoPar!=-1) { //existe el paramtro
	  String resto = queryString.substring(indComienzoPar,longQueryString);
	  boolean terminado =false;
	  while(!terminado) {
		String valorParametro = getValorParametro(resto,nombreParametro);
		valores.addElement(valorParametro);
		indComienzoPar = queryString.indexOf("&"+nombreParametro+"=",indComienzoPar+1);
		terminado=(indComienzoPar==-1); //el parametro ya no aparece m�s veces en el QueryString
		if(!terminado) {
		  resto = queryString.substring(indComienzoPar,longQueryString);
		}
	  }
	return construyeArray(valores);
	}
	else {
	  return null;
	}
  }
  /** Devuelve el primer valor que se encuentre del par�metro.
	* @param parametros String que contiene los parametros de una petici�n
	* @param nombreParametro Nombre del paramertro solicitado
	* @return Primer valor encontrado del par�metro
	*/
  private static String getValorParametro(String parametros, String nombreParametro) {
	int indComienzoPar = parametros.indexOf("&"+nombreParametro+"=");
	int longNombrePar = nombreParametro.length();
	String parametro = parametros.substring(indComienzoPar+longNombrePar+2,parametros.indexOf('&',indComienzoPar+longNombrePar+2));
	try {
	  parametro = URLDecoder.decode(parametro);
	}
	catch( Exception e ) { /* No se hace nada, como en HttpRequest.getParameter*/ }
	return parametro;
  }  
 public static void main(String args[]) {
  try {
	String [] param = Request.getValoresParametro("&NOMBRE=JUAN&AP=LUQUE&NOMBRE=MANOLO&NOMBRE=PEPE&","NOMBRE");
	Request.obtenerParametros(null);
	Hashtable parametros= Request.getParametros();
	parametros.put("qa", "aq");
	System.out.println(parametros.elements().nextElement());
  }catch(Exception e) {
	System.out.println(e);
  }
 } 
  /** Guarda los par�metros contenidos en el objeto request en el
	* Hashtable
	* @param request Objeto HttpServletRequest de donde se obtienen los par�metros
	*/
  public static void obtenerParametros(HttpServletRequest request)
  throws java.lang.Exception
  {
   
	parametros = new Hashtable();
	if(request!=null) {
	  Enumeration nombreParams = request.getParameterNames();
	  while(nombreParams.hasMoreElements()) {
		String nombreParam = (String)nombreParams.nextElement();
		parametros.put( URLDecoder.decode( nombreParam ),
			  request.getParameter(nombreParam));
	  }
	  String queryString = request.getQueryString();
	  int indInicial =0;
	  int indIgual = queryString.indexOf('=');
	  while(indIgual!=-1) {
		String nombre = queryString.substring(indInicial,indIgual);
		indInicial=indIgual+1;
		int indFinal = queryString.indexOf('&',indInicial);
		if(indFinal==-1) {
		  indFinal = queryString.length();
		}
		String valor = queryString.substring(indInicial,indFinal);
		indInicial=indFinal+1;
		parametros.put(URLDecoder.decode( nombre ),
			  URLDecoder.decode( valor ) );
		indIgual = queryString.indexOf('=',indInicial);
	  }
	}
   }
  
 }  

