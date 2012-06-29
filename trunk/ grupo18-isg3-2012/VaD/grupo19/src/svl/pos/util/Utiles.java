/*******************************************************************************
** @nombre Utiles.java
** @autor  Carlos Pompa
** @fecha  03-02-2011
** @descripcion Clase que recoge un conjunto de m�todos que pueden usarse por  
**              otras clases
*******************************************************************************/
package svl.pos.util;



import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.math.BigDecimal;

import java.util.Vector;

public class Utiles 
{
  /*******************************************************************************
  ** M�todo que completa una cadena con espacios en blanco hasta una longitud 
  ** determinada
  ** @param valor Cadena a completar
  ** @param longitud Longitud m�xima de la cadena
  ** @return String Nueva cadena con los espacios en blanco
  *******************************************************************************/
  public static String completarValor(String valor,int longitud)
  {
    String valorCompleto = "";

    if(valor != null)
    {
      valorCompleto = valor;
      while(valorCompleto.length() < longitud)
      {
        valorCompleto += " ";
      }
    }

    return valorCompleto;
  }

  /*******************************************************************************
  ** M�todo que obtiene la fecha del sistema en formato dd-mm-aa
  ** @return String Cadena con la fecha actual 
  *******************************************************************************/
  public static String obtenerFechaActual()
  {
    String fechaActual;
    
    Calendar g = new GregorianCalendar();
    
    String dia  = Integer.toString(g.get(Calendar.DATE));
    String mes  = Integer.toString(g.get(Calendar.MONTH)+1);
    String anyo = Integer.toString(g.get(Calendar.YEAR));
    
    if(g.get(Calendar.DATE) < 10)
    {
      dia = "0" + dia; 
    }
    
    if((g.get(Calendar.MONTH) + 1) < 10)
    {
      mes = "0" + mes;
    }
     anyo = anyo.substring(2);
     
     fechaActual = dia + "/" + mes + "/" + anyo;
     
     return fechaActual;
  }
  
  
  
  /*******************************************************************************
  ** M�todo que obtiene la hora del sistema en formato hhmmss
  ** @return String Cadena con la hora actual 
  *******************************************************************************/
  public static String obtenerHoraActual()
  {
    String horaActual;
    
    Calendar g = new GregorianCalendar();
    
    String hora     = Integer.toString(g.get(Calendar.HOUR));
    String minutos  = Integer.toString(g.get(Calendar.MINUTE));
    String segundos = Integer.toString(g.get(Calendar.SECOND));
    
    if(g.get(Calendar.HOUR) < 10)
    {
      hora = "0" + hora; 
    }
    
    if(g.get(Calendar.MINUTE) < 10)
    {
      minutos = "0" + minutos;
    }
    
    if(g.get(Calendar.SECOND) < 10)
    {
      segundos = "0" + segundos;
    }
     
    horaActual = hora + minutos + segundos;
     
    return horaActual;
  }

  /*******************************************************************************
  ** M�todo que obtiene la fecha del sistema en formato aaaammdd
  ** @return String Cadena con la fecha actual 
  *******************************************************************************/
  public static String obtenerFechaNatural()
  {
    String fechaActual;
    
    Calendar g = new GregorianCalendar();
    
    String dia  = Integer.toString(g.get(Calendar.DATE));
    String mes  = Integer.toString(g.get(Calendar.MONTH) + 1);
    String anyo = Integer.toString(g.get(Calendar.YEAR));
    
    if(g.get(Calendar.DATE) < 10)
    {
      dia = "0" + dia; 
    }
    
    if((g.get(Calendar.MONTH) + 1) < 10)
    {
      mes = "0" + mes;
    }
     
    fechaActual = anyo + mes + dia;
     
    return fechaActual;
  }

  /*******************************************************************************
  ** M�todo que transforma una fecha con dos d�gitos en el a�o a una fecha con 
  ** cuatro d�gitos en el a�o 
  ** @param fecha Par�metro que se va a transformar
  ** @return String Devuelve la nueva fecha
  *******************************************************************************/
  public static String transformarFecha(String fecha) 
  {
    String fechaNueva = "";
    
    if(fecha != null && !fecha.equals(""))
    {
      int i = fecha.lastIndexOf("-");
    
      if(Integer.parseInt(fecha.substring(i+1))>=70)
        fechaNueva = fecha.substring(0,i+1) + "19" + fecha.substring(i+1);
      else
        fechaNueva = fecha.substring(0,i+1) + "20" + fecha.substring(i+1);

      fechaNueva = fechaNueva.substring(6) + fechaNueva.substring(3,5) + fechaNueva.substring(0,2);
    }
    
    return fechaNueva;
  }

  /*******************************************************************************
  ** M�todo que elimina los espacios en blanco por detras de una cadena
  ** @param entrada Par�metro que se va a transformar
  ** @return String Devuelve la nueva cadena
  *******************************************************************************/
  public static String EliminarBlancos (String entrada)
  {
    int longitud = entrada.length();

    while (entrada.substring(longitud-1).equals(" ") && longitud != 1)
    {
       entrada = entrada.substring(0,longitud-1);
       longitud = (entrada.substring(0,longitud-1)).length();
    }
    if (longitud == 1 && entrada.substring(0).equals(" "))
       entrada = "";
    return entrada;
  }

  /*******************************************************************************
  ** M�todo que transforma una fecha ccon formato AAAAMMDD a otro DD-MM-AA
  ** @param fecha Par�metro que se va a transformar
  ** @return String Devuelve la nueva fecha
  *******************************************************************************/
  public static String presentarFecha(String fecha) 
  {
    String fechaNueva = "";

    if(fecha != null && !fecha.trim().equals("") && fecha.length()>7)
    {
      fechaNueva = fecha.substring(6) + "-" + fecha.substring(4,6) + "-"  + fecha.substring(2,4);
    }
    
    return fechaNueva;
  }

 
  
    /*******************************************************************************
  ** M�todo para convertir una cadena a formato aa,00
  ** @param cadena Valor de la cadena a transformar
  ** @return String Valor de la cadena con formato correcto
  *******************************************************************************/
  public static String CadenaConFormato(String cadena)
  {
    int indice = cadena.indexOf(".");
    
    if(indice != -1)
    {
      String subcad = cadena.substring(indice);
      cadena = cadena.replace('.',',');
      if(subcad.length()==2)
      {
        cadena = cadena + "0";
      }
    }
    else
    {
      indice = cadena.indexOf(",");
      if(indice != -1)
      {
        String subcad = cadena.substring(indice);
        if(subcad.length()==2)
        {
          cadena = cadena + "0";
        }
      }
      else
      {
        cadena = cadena + ",00";
      }
    }
    
    return cadena;
  }
  /*******************************************************************************
  ** M�todo que completa una cadena con la cadena que se desee hasta una longitud 
  ** determinada
  ** @param cadena Cadena de partida
  ** @param valor Valor con el que se desea completar la cadena
  ** @param longitud Longitud m�xima de la cadena
  ** @param direccion Indicador de la concatenacion (izq=delante,der=detras)
  ** @return String Nueva cadena con los espacios en blanco
  *******************************************************************************/
  public static String completarValorCadena(String cadena, String valor,int longitud,String direccion)
  {
    String valorCompleto = cadena;

    if(valorCompleto == null)
    {
      valorCompleto = " ";
    }
    
    while(valorCompleto.length() < longitud)
    {
      if(direccion != null && direccion.equals("izq"))
      {
        valorCompleto = valor + valorCompleto;
      }
      else if(direccion != null && direccion.equals("der"))
      {
        valorCompleto += valor;
      }
    }

    return valorCompleto;
  }

  /************************************************************************************
  ** M�todo que transforma un importe con formato #.## a otro #,## y devuelve una
  ** cadena vacia si el importe es 0.00, nulo o vacio.
  ** @param importe Par�metro que se va a transformar
  ** @return String Devuelve el importe vacio, transformado o igual seg�n corresponda
  *************************************************************************************/
  public static String presentarImportes(String importe) 
  {
    String importeNuevo = "";

    if(importe != null && !importe.trim().equals("") && !importe.equals("0.00"))
    { 
      importeNuevo = importe.replace('.',',');
      /*  importe = importe.trim();
        //obtenemos la posicion donde se encuentra el punto decimal
        int i = importe.indexOf(".");
        //si encontramos el punto lo cambiamos por la coma, si no, dejamos el importe igual
        if ( i != -1)
        {
          importeNuevo = importe.substring(0,i) + "," + importe.substring(i+1); 
        }
        else
        {
          importeNuevo = importe;
        }
        */
    }
    return importeNuevo;
  } 

  /************************************************************************************
  ** M�todo que transforma un importe con formato ### a otro #,## 
  ** @param importe Par�metro que se va a transformar
  ** @return String Devuelve el importe transformado
  *************************************************************************************/
  public static String transformarNumerico(String importe) 
  {
    String importeNuevo = "";

    if(importe != null && !importe.trim().equals(""))
    { 
      //importeNuevo = importe.substring(0,importe.length()-2) + "," + importe.substring(importe.length()-2);
      importeNuevo = importe;
    }
    return importeNuevo;
  } 

  /*******************************************************************************
  ** M�todo que obtiene la cabecera de la pagina del subsistema
  ** @param nombrePro Valor con el nombre del proceso
  ** @return String Nombre del directorio de ayuda
  *******************************************************************************/
  public static String obtenerCabecera(String nombrePro)
  {
    String dirCabecera = "";

    if(nombrePro != null && nombrePro.equals("admin"))
    {
      dirCabecera = "Cabecera_admin.htm";
    }
    else if(nombrePro != null && nombrePro.equals("admin_serv"))
    {
      dirCabecera = "Cabecera_admin_serv.htm";
    } 
    else if(nombrePro != null && nombrePro.equals("alertas"))
    {
      dirCabecera = "Cabecera_alertas.htm";
    }
    else if(nombrePro != null && nombrePro.equals("buscador"))
    {
      dirCabecera = "Cabecera_buscador.htm";
    }
    else if(nombrePro != null && nombrePro.equals("misdatos"))
    {
      dirCabecera = "Cabecera_misdatos.htm";
    }
    
    
    return dirCabecera;
  }

  /*******************************************************************************
  ** M�todo que carga una cadena en una tabla
  ** @param cadena Valor de la cadena
  ** @param longitud Tama�o de los elementos de la tabla
  ** @param elem N�mero de elementos de la tabla
  ** @return String [] Tabla de cadenas
  *******************************************************************************/
  public static String [] cargarATabla(String cadena,int longitud,int elem)
  {
    String [] retorno       = new String [elem];
    String subcadenaOrigen  = cadena;
    String subcadenaDestino = "";

    for(int i=0;i<elem;i++)
    {
      if(subcadenaOrigen.length()>longitud)
      {
        subcadenaDestino = subcadenaOrigen.substring(0,longitud);
        subcadenaOrigen  = subcadenaOrigen.substring(longitud);
        retorno[i] = subcadenaDestino;
      }
      else
      {
        retorno[i] = subcadenaOrigen;
        break;
      }
    }
    return retorno;
  }

  /*******************************************************************************
  ** M�todo que carga una cadena en una tabla
  ** @param cadenas Tabla de cadenas a transformar
  ** @return BigDecimal [] Tabla de cadenas pasadas a num�ricas
  *******************************************************************************/
  public static BigDecimal [] pasarATablaNumerica(String [] cadenas)
  {
    BigDecimal [] retorno = new BigDecimal[cadenas.length];

    for(int i=0;i<cadenas.length;i++)
    {
      if(cadenas[i] != null && !cadenas[i].trim().equals(""))
      {
        retorno[i] = new BigDecimal(cadenas[i].trim().replace(',','.'));
      }
    }

    return retorno;
  }

  /*******************************************************************************
  ** M�todo que transforma una fecha con formato DD-MM-AA a AAMMDD o DD-MM-AAAA a AAAAMMDD
  ** @param fecha Par�metro que se va a transformar
  ** @return String Devuelve la nueva fecha
  *******************************************************************************/
  public static String convertirFecha(String fecha) 
  {
    String fechaNueva = "";

    if(fecha != null && !fecha.trim().equals("") && fecha.length()>7 && fecha.length()<10)
    {
      fechaNueva = fecha.substring(6) + fecha.substring(3,5) + fecha.substring(0,2);
    }
    
    return fechaNueva;
  }  
}