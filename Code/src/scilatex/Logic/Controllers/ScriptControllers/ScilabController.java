package scilatex.Logic.Controllers.ScriptControllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.types.ScilabDouble;
import org.scilab.modules.types.ScilabType;

// Clase que gestiona el codigo Scilab
public class ScilabController {

    // Objeto que permite gestionar el codigo Scilab
    org.scilab.modules.javasci.Scilab scilab;
    
    // Iniciar objeto Scilab 
    public ScilabController() {
        try {
            scilab = new org.scilab.modules.javasci.Scilab();
        } catch (JavasciException.InitializationException ex) {
            System.out.println("Scilab: Error de instanciación");
        }
    }

    // Abrir conexión
    public void open() {
        try {
            scilab.open();
        } catch (JavasciException ex) {
            System.out.println("Scilab: Error al abrir");
        }
    }

    // Cerrar conexión
    public void close() {
        scilab.close();
    }

    // Ejecutar codigo Scilab
    public boolean exec(String script) {
        
        boolean result = false;
        
        try {

            // Crear objeto de escritura
            FileWriter writer = new FileWriter("exec.sci");

            // Crear objeto de escritura directa
            BufferedWriter br = new BufferedWriter(writer);

            // Escribir texto
            br.write(script);

            // Cerrar objeto escritura
            br.close();
            
            result = scilab.exec(new File("exec.sci"));

        } catch (IOException ex) {
            System.out.println("No se pudo guardar el archivo");
        }
        
        return result;
        
    }

    // Ejecutar una función definida codigo Scilab
    public String execFuncion(String script, String parametros[]) {

        exec(script);       // Crear función 
        
        // si es una función la construye y la ejecuta
        if (script.trim().length() > 7 && script.trim().substring(0, 8).equals("function")) {
            String funcion = getNameFunction(script) + "(";
            for (int i = 0; i < parametros.length; i++) {
                funcion += parametros[i] + ",";
            }
            if (parametros.length > 0) {
                funcion = funcion.substring(0, funcion.length() - 1);
            }
            funcion += ")";

            if (!exec("format('v',25); r = " + funcion)) {
                return "'" + funcion.replace("'", "") + "'";
            }
            
            return get("r").replace("\"", "'");
        }
        
        // Si es un numero, un vetor o un arreglo lo ejecuta
        if (!exec("format('v',25); r = " + script)) {
            return "'" + script.replace("'", "") + "'";
        }
        return get("r").replace("\"", "'");

    }

    // Obtener el nombre de una función definida codigo Scilab
    public String getNameFunction(String script) {
        String firma = "";
        boolean leer = false;
        for (int i = 0; i < script.length(); i++) {
            if (script.charAt(i) == '=') {
                leer = true;
                continue;
            }
            if (script.charAt(i) == '(') {
                leer = false;
                break;
            }
            if (leer) {
                firma += script.charAt(i);
            }
        }
        if ("".equals(firma)) {
            return script;
        } else {
            return firma;
        }
    }

    // Asignar un valor a una variable
    public void set(String variable, String value) {
        exec(variable + " = " + value);
    }

    // Obtener valor de una variable
    public String get(String variable) {
        try {
            ScilabType scilabType = scilab.get(variable);
            return scilabType.toString();
        } catch (JavasciException ex) {
            System.out.println("Scilab: Error al obtener variable");
        }
        return null;
    }

    // Asginar numero a una variable
    public void setValue(String variable, double value) {
        try {
            scilab.put(variable, new ScilabDouble(value));
        } catch (JavasciException ex) {
            System.out.println("Scilab: Error al asignar valor");
        }
    }

    // Obtener valor numerico de una variable
    public Double getValue(String variable) {
        try {
            return ((ScilabDouble) scilab.get(variable)).getRealElement(0, 0);
        } catch (JavasciException ex) {
            System.out.println("Scilab: Error al obtener variable");
        }
        return null;
    }

    // Asignar matriz a una variable
    public void setMatrix(String variable, double[][] values) {
        try {
            scilab.put(variable, new ScilabDouble(values));
        } catch (JavasciException ex) {
            System.out.println("Scilab: Error al asignar matriz");
        }
    }

    // Obtener matriz de una variable
    public double[][] getMatrix(String variable) {
        try {
            return ((ScilabDouble) scilab.get(variable)).getRealPart();
        } catch (JavasciException ex) {
            System.out.println("Scilab: Error al obtener variable");
        }
        return null;
    }

    // Asignar elemento a una matriz en la posicion especificada y asignar a una variable
    public void setMatrixElement(String variable, int i, int j, double value) {
        try {
            ((ScilabDouble) scilab.get(variable)).setRealElement(i, j, value);
        } catch (JavasciException ex) {
            System.out.println("Scilab: Error al asignar matriz");
        }
    }

    // Obtener elemento de una matriz en la posición especificada
    public Double getMatrixElement(String variable, int i, int j) {
        try {
            return ((ScilabDouble) scilab.get(variable)).getRealElement(i, i);
        } catch (JavasciException ex) {
            System.out.println("Scilab: Error al obtener variable");
        }
        return null;
    }

}
