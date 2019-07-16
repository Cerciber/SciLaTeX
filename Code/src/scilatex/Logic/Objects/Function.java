package scilatex.Logic.Objects;

import scilatex.Logic.Controllers.ScriptControllers.ScilabController;
import scilatex.Logic.Controllers.ScriptControllers.LaTeXController;
import scilatex.Data.Constants.Constants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Icon;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_EMPTY_PARAMETER;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_MOVED;
import scilatex.Data.Models.FunctionModel;
import scilatex.Data.Repositories.LocalRepository.FunctionRepository;

// Clase que gestiona las operaciones de la función y sus parametros
public final class Function {

    // Controladores de codigo
    private ScilabController scilab;
    private LaTeXController laTeX;

    // Modelos
    private FunctionModel functionModel;
    
    // Repositorios
    public FunctionRepository functionRepository;

    // Atributos
    private Function[] parameters;          // Funciones que se pasan como parametros a la función actual

    // Atributos generados
    private String scriptSolveScilab;       // Scrip de diseño de la función solucionada en formato LaTeX
    private String scriptSolveLaTeX;        // Scrip de diseño de la función solucionada en formato LaTeX

    // Instanciar clase como constante
    public Function(ScilabController scilab, String scriptScilab, String scriptLaTeX) {

        // Asignar parametros
        this(scilab, scriptScilab, scriptLaTeX, scriptScilab, scriptScilab, scriptLaTeX, "");

    }

    // Instanciar clase como una función con nombres de parametros por defecto
    public Function(ScilabController scilab, String name, String nameFunction, String description, String scriptScilab, String scriptLaTeXIn, String scriptLaTeXOut) {

        // Asignar parametros
        this(scilab, name, nameFunction, description, defineDefaultParametersNames(scriptLaTeXIn), defineDefaultParametersNames(scriptLaTeXIn), defineDefaultTypesParameters(scriptLaTeXIn), scriptScilab, scriptLaTeXIn, scriptLaTeXOut);

    }

    // Instanciar clase como una función con valores vacios
    public Function(ScilabController scilab) {

        // Asignar parametros
        this(scilab, "", "", "", new String[0], new String[0], new String[0], "function r = f(a,b,c)\n\tr = \nendfunction", "", "");

    }

    // Instanciar clase como una función con nombres de parametros definidos
    public Function(ScilabController scilab, String name, String nameFunction, String description, String[] nameEachParameter, String[] descriptionParameters, String[] typeParameters, String scriptScilab, String scriptLaTeXIn, String scriptLaTeXOut) {

        // Asignar repositorio
        functionRepository = new FunctionRepository();
        
        // Asignar modelo
        functionModel = new FunctionModel();
        
        // Asignar parametros
        functionModel.setNodeName(name.trim());
        functionModel.setFunctionName(nameFunction.trim());
        functionModel.setDescription(description.trim());
        functionModel.setScriptScilab(scriptScilab.trim());
        functionModel.setScriptLaTeXIn(scriptLaTeXIn.trim());
        functionModel.setScriptLaTeXOut(scriptLaTeXOut.trim());
        functionModel.setNumDecimals(4);
        functionModel.setOption(2);
        functionModel.setLaTeXSize(-1);
        functionModel.setParameters(new FunctionModel[nameEachParameter.length]);
        functionModel.setNameParameters(nameEachParameter);
        functionModel.setDescriptionParameters(descriptionParameters);
        functionModel.setTypeParameters(typeParameters);
        functionModel.setShowFunctionInScreen(0);
        functionModel.setShowParametersInScreen(1);
        functionModel.setShowParameter(1);

        // Asginar controlador de Scilab
        this.scilab = scilab;
        this.parameters = new Function[nameEachParameter.length];

        // Asignar controlador de LaTeX
        this.laTeX = new LaTeXController();

        // Solucionar función
        solve();

    }

    // Instanciar clase como una función desde una carpeta
    public Function(ScilabController scilab, File directory) {

        // Asignar repositorio
        functionRepository = new FunctionRepository();
        
        // Asignar modelo
        functionModel = functionRepository.readFunctionModel(directory);

        // Iniciar parametros
        this.parameters = new Function[functionModel.getNameParameters().length];

        // Asginar controladores de codigo
        this.scilab = scilab;
        this.laTeX = new LaTeXController();

        // Asignar parametros guardados
        asignSavedParameters(directory);

        // Solucionar función
        solve();
        
    }

    // Contar el numero de parametros sin resolver en un script LaTeX
    public int numFunctionalParameters() {

        int cont = 0;
        for (int i = 0; i < getFunctionModel().getTypeParameters().length; i++) {
            if (getFunctionModel().getTypeParameters()[i].equals("0")) {
                cont++;
            }
        }
        return cont;

    }

    // Contar el numero de parametros sin resolver en un script LaTeX
    public static int numLaTeXParameters(String script) {

        try {

            String[] separatedScript = script.split("#");      // Arreglo que almacena el script de LaTeX separado por el simbolo "#" 
            int max = 0;

            // Recorrer los parametros de la función
            for (int i = 0; i < separatedScript.length; i++) {

                if (i % 2 != 0 && max < Integer.parseInt(separatedScript[i])) {
                    max = Integer.parseInt(separatedScript[i]);
                }

            }

            // Retornar el numeor maximo de los parmetros
            return max;

        } catch (java.lang.NumberFormatException ex) {
            return -1;
        }

    }

    // Contar el numero de parametros sin resolver en un script Scilab
    public static int numScilabParameters(String script) {

        if (script.trim().length() > 7 && script.trim().substring(0, 8).equals("function")) {

            if (!script.split("\\(")[1].split("\\)")[0].trim().equals("")) {
                return script.split("\\(")[1].split("\\)")[0].split(",").length;
            }

        }

        return 0;

    }

    // Definir nombre de cada parametro por defecto
    public static String[] defineDefaultParametersNames(String scriptLaTeX) {

        String[] defaultParametersName = new String[numLaTeXParameters(scriptLaTeX)];
        for (int i = 0; i < defaultParametersName.length; i++) {
            defaultParametersName[i] = ((char) ('A' + (i))) + "";
        }
        return defaultParametersName;

    }

    // Definir nombre de cada parametro por defecto
    public static String[] defineDefaultTypesParameters(String scriptLaTeX) {

        String[] defaultTypeParametersName = new String[numLaTeXParameters(scriptLaTeX)];
        for (int i = 0; i < defaultTypeParametersName.length; i++) {
            defaultTypeParametersName[i] = "0";
        }
        return defaultTypeParametersName;

    }

    // Asignar paramatros guardados en el arbol de archivos
    public void asignSavedParameters(File directory) {

        // Si existen rutas o archivos contendidos
        if (directory.listFiles() != null) {

            // Para cada archivo
            for (int i = 0; i < directory.listFiles().length; i++) {

                // Si es una carpeta
                if (directory.listFiles()[i].isDirectory()) {

                    getParameters()[Integer.parseInt(directory.listFiles()[i].getName())] = new Function(scilab, directory.listFiles()[i]);
                    functionModel.getParameters()[Integer.parseInt(directory.listFiles()[i].getName())] = getParameters()[Integer.parseInt(directory.listFiles()[i].getName())].getFunctionModel();
                    
                }

            }

        }

    }

    // verificar si se resolvió la función
    public boolean isSolved() {

        // Si el resultado tiene parametros sin resolver o si no es numero, vector ni arreglo
        if (numNoAsignParameters() > 0 || (!isVectorOfOne(scriptSolveScilab) && !isVector(scriptSolveScilab) && !isMatrix(scriptSolveScilab))) {
            return false;
        }
        return true;

    }

    // Resolver funcion 
    // - Si se puede resolver retorna la respuesta como numero, vector o matriz de numeros
    // - Si no se han asignado todos los parametros retorna la formula LaTeX con los parametros ya asignados resueltos
    // - Si no se puede resolver por inconsistencias matematicas retorna la función hasta donde se logró resolver en rojo
    public String solve() {

        String[] s = solve2();
        setScriptSolveLaTeX(s[0]);
        setScriptSolveScilab(s[1]);
        return getScriptSolveScilab();

    }

    // Resolver funcion conservando el formato LateX y el Scilab
    private String[] solve2() {

        String[][] results = new String[2][getParameters().length];                          // Arreglo que almacena los resultados de cada parametro 
        String[] separatedScript = getFunctionModel().getScriptLaTeXIn().split("#");         // Arreglo que almacena el script de LaTeX separado por el simbolo "#" 
        String solvedScript = "";                                                       // Cadena que almacena el script de LaTeX con los parametros reemplazados
        String returns[] = new String[2];

        // Recorrer los parametros de la función
        for (int i = 0; i < getParameters().length; i++) {

            if (getParameters()[i] != null) {

                // Si el parametro está asignado resolverlo
                String[] solved = getParameters()[i].solve2();
                results[0][i] = solved[0];
                results[1][i] = solved[1];

            } else {

                // Si es parametro no esta resulto asignar parametro vacio
                results[0][i] = getFunctionModel().getNameParameters()[i];
                results[1][i] = "";
            }

        }

        // Recorrer los espacios de los parametros de la función
        for (int i = 0; i < separatedScript.length; i++) {

            // Si se encuenta dentro del espacio de un parametro
            if (i % 2 == 1) {

                // Obtener numero del parametro
                int numParameter = Integer.parseInt(separatedScript[i]) - 1;

                if (getParameters()[numParameter] != null) {

                    // Si el parametro está asignado remplazarlo en el script
                    separatedScript[i] = results[0][numParameter];

                } else {

                    // Si el parametro no está asignado asignarle el nombre del parametro 
                    separatedScript[i] = "\\textcolor{" + Constants.toString(LATEX_COLOR_EMPTY_PARAMETER) + "}{" + getFunctionModel().getNameParameters()[numParameter] + "}";

                }

            }

        }

        // Unir las partes del script de LaTeX con los parametros reemplazados
        for (int i = 0; i < separatedScript.length; i++) {
            solvedScript += separatedScript[i];
        }

        // Si existen parametros sin asginar retornar el script LaTeX reemplazado
        if (numNoAsignParameters() > 0) {

            // asignar resultado LaTeX
            returns[0] = solvedScript;

            // asignar resultado Scilab
            returns[1] = "";

            // Retornar resultados
            return returns;

        }

        // Si no existen paramentros sin asignar obtener la solución de la función Scilab
        String result = scilab.execFuncion(getFunctionModel().getScriptScilab(), results[1]);

        //result = setResultScilabFormat(result);
        returns[0] = result.replace("'", "");
        returns[1] = result;

        return returns;

    }

    // Asignar las representaciones de los parametros resaltado un parametro
    // Se resalta el parametro especificado (Si es "" no se realta ninguno)
    private String asignParameters(String parameterHighlight) {

        if (getFunctionModel().getShowFunctionInScreen() == 0) {

            switch (getFunctionModel().getOption()) {
                case 1:
                    return asignFunctionParameters(parameterHighlight);
                case 2:
                    return asignLaTeXInParameters(parameterHighlight);
                case 3:
                    return asignLaTeXOutParameters(getScriptSolveScilab());
                case 4:
                    return getScriptSolveLaTeX();
                default:
                    return null;
            }

        } else {

            switch (getFunctionModel().getOption()) {
                case 1:
                    return asignFunctionParameters(parameterHighlight);
                case 2:
                    return asignFunctionParameters(parameterHighlight) + "=" + asignLaTeXInParameters(parameterHighlight);
                case 3:
                    return asignFunctionParameters(parameterHighlight) + "=" + asignLaTeXOutParameters(getScriptSolveScilab());
                case 4:
                    return asignFunctionParameters(parameterHighlight) + "=" + getScriptSolveLaTeX();
                default:
                    return null;
            }

        }

    }

    private String asignFunctionParameters(String parameterHighlight) {

        // si el script es una función y si se deben mostrar los paramatros
        if (getFunctionModel().getScriptScilab().trim().length() > 7 && getFunctionModel().getScriptScilab().trim().substring(0, 8).equals("function") && getFunctionModel().getShowParametersInScreen() == 1) {

            boolean showAnyParamater = false;
            String nameScript = getFunctionModel().getFunctionName() + "(";
            for (int i = 0; i < getFunctionModel().getNameParameters().length; i++) {

                // Si se debe mostrar el parametro
                if (getParameters()[i] == null || getParameters()[i].getFunctionModel().getShowParameter() == 1) {

                    // Si el parametro está asignado
                    if (getParameters()[i] != null) {

                        // Si el parametro es el parametro a resaltar resaltarlo en el script
                        if (parameterHighlight.equals(getFunctionModel().getNameParameters()[i])) {
                            nameScript += "\\textcolor{" + Constants.toString(LATEX_COLOR_MOVED) + "}{" + getParameters()[i].asignParameters(parameterHighlight) + "}";
                        } else {
                            nameScript += getParameters()[i].asignParameters(parameterHighlight);
                        }

                        // Si el parametro no está asignado enumerarlo 
                    } else {

                        // Si el parametro es el parametro a resaltar resaltarlo en el script
                        if (parameterHighlight.equals(getFunctionModel().getNameParameters()[i])) {
                            nameScript += "\\textcolor{" + Constants.toString(LATEX_COLOR_MOVED) + "}{" + getFunctionModel().getNameParameters()[i] + "}";
                        } else {
                            nameScript += "\\textcolor{" + Constants.toString(LATEX_COLOR_EMPTY_PARAMETER) + "}{" + getFunctionModel().getNameParameters()[i] + "}";
                        }

                    }

                    // Separar parametros por comas
                    nameScript += ",";
                    showAnyParamater = true;

                }

            }

            // Eliminar coma sobrante si hay parametros
            if (showAnyParamater) {
                nameScript = nameScript.substring(0, nameScript.length() - 1);
            }

            nameScript = nameScript + ")";

            return nameScript;

        } else {
            return getFunctionModel().getFunctionName();
        }

    }

    private String asignLaTeXInParameters(String parameterHighlight) {

        String[] separatedScript = getFunctionModel().getScriptLaTeXIn().split("#");      // Arreglo que almacena el script de LaTeX separado por el simbolo "#" 
        String solvedScript = "";                               // Cadena que almacena el script de LaTeX con los parametros reemplazados

        // Recorrer los parametros de la función
        for (int i = 0; i < separatedScript.length; i++) {

            // Si se encuenta dentro del espacio de un parametro
            if (i % 2 == 1) {

                // Obtener numero del parametro
                int numParameter = Integer.parseInt(separatedScript[i]) - 1;

                if (getParameters().length > numParameter) {

                    // Si el parametro está asignado
                    if (getParameters()[numParameter] != null) {

                        // Si el parametro es el parametro a resaltar resaltarlo en el script
                        if (parameterHighlight.equals(getFunctionModel().getNameParameters()[numParameter])) {
                            separatedScript[i] = "\\textcolor{" + Constants.toString(LATEX_COLOR_MOVED) + "}{" + getParameters()[numParameter].asignParameters(parameterHighlight) + "}";
                        } else {
                            separatedScript[i] = "{" + getParameters()[numParameter].asignParameters(parameterHighlight) + "}";
                        }

                        // Si el parametro no está asignado enumerarlo 
                    } else {

                        // Si el parametro es el parametro a resaltar resaltarlo en el script
                        if (parameterHighlight.equals(getFunctionModel().getNameParameters()[numParameter])) {
                            separatedScript[i] = "\\textcolor{" + Constants.toString(LATEX_COLOR_MOVED) + "}{" + getFunctionModel().getNameParameters()[numParameter] + "}";
                        } else {
                            separatedScript[i] = "\\textcolor{" + Constants.toString(LATEX_COLOR_EMPTY_PARAMETER) + "}{" + getFunctionModel().getNameParameters()[numParameter] + "}";
                        }

                    }

                } else {

                    return "";

                }

            }

        }

        // Unir las partes del script de LaTeX con los parametros reemplazados
        for (int i = 0; i < separatedScript.length; i++) {
            solvedScript += separatedScript[i];
        }

        // Retornar el script LaTeX reemplazado
        return solvedScript;

    }

    public String asignLaTeXOutParameters(String scriptSolveScilab) {

        // Si la función no está completamente asignada
        if (!isAsigned()) {
            scriptSolveScilab = null;
        }

        if (getFunctionModel().getScriptLaTeXOut().trim().isEmpty()) {
            if (scriptSolveScilab == null) {
                return "";
            }
            return scriptSolveScilab.replace("'", "");
        }

        String[] results = null;

        if (scriptSolveScilab != null) {
            results = scriptSolveScilab.replace("[", "").replace("]", "").split("[,;]");
        }

        String[] separatedScript = getFunctionModel().getScriptLaTeXOut().split("#");      // Arreglo que almacena el script de LaTeX separado por el simbolo "#" 
        String solvedScript = "";                               // Cadena que almacena el script de LaTeX con los parametros reemplazados

        // Recorrer los parametros de la función
        for (int i = 0; i < separatedScript.length; i++) {

            // Si se encuenta dentro del espacio de un parametro
            if (i % 2 == 1) {

                try {
                    // Obtener numero del parametro 
                    int numParameter = Integer.parseInt(separatedScript[i]) - 1;

                    try {

                        if (scriptSolveScilab != null) {
                            separatedScript[i] = "[" + results[numParameter].replace("'", "") + "]";
                        } else {
                            separatedScript[i] = "\\textcolor{" + Constants.toString(LATEX_COLOR_EMPTY_PARAMETER) + "}{\\Box_" + (numParameter + 1) + "}";
                        }

                    } catch (ArrayIndexOutOfBoundsException ex) {
                        separatedScript[i] = "\\textcolor{" + Constants.toString(LATEX_COLOR_EMPTY_PARAMETER) + "}{[\\Box_" + (numParameter + 1) + "]}";
                    }
                } catch (java.lang.NumberFormatException ex) {
                    separatedScript[i] = "";
                }

            }

        }

        // Unir las partes del script de LaTeX con los parametros reemplazados
        for (int i = 0; i < separatedScript.length; i++) {
            solvedScript += separatedScript[i];
        }

        // Retornar el script LaTeX reemplazado
        return solvedScript;

    }

    // Contar el numero de parametros remplazados en la función actual
    public int numAsignParameters() {

        int sum = 0;
        // Recorrer los parametros de la función
        for (int i = 0; i < getParameters().length; i++) {

            if (getParameters()[i] != null) {
                sum++;
            }

        }
        return sum;

    }

    // Contar el numero de parametros sin remplazar en la función actual
    public int numNoAsignParameters() {

        int sum = 0;
        // Recorrer los parametros de la función
        for (int i = 0; i < getParameters().length; i++) {

            if (getParameters()[i] == null) {
                sum++;
            }

        }
        return sum;

    }

    // Contar el numero de parametros totales (reemplazados y no reemplasados)
    public int numParameters() {

        // Contar los parametros exteriores 
        int cont = getParameters().length;

        // Recorrer los parametros de la función
        for (int i = 0; i < getParameters().length; i++) {
            if (getParameters()[i] != null) {
                cont += getParameters()[i].numParameters();      // Contar los parametros internos
            }
        }

        // Retornar numero de parametros
        return cont;

    }

    // Verificar si la función tiene todos sus parametros internos asignados
    public boolean isAsigned() {

        // Recorrer los parametros de la función
        for (int i = 0; i < getParameters().length; i++) {
            if (getParameters()[i] == null) {
                return false;
            } else {
                if (!parameters[i].isAsigned()) {
                    return false;
                }
            }
        }
        return true;

    }

    // Asginar un parametro en la posición vacia especificada
    public void setParameter(Function parameter, String name) {

        // Recorrer parametros
        for (int i = 0; i < parameters.length; i++) {

            if (parameters[i] != null) {

                // Si el parametro está asignado recorrere los parametros interior contando los parametros no asignados interiores
                parameters[i].setParameter(parameter, name);

            }

            if (getFunctionModel().getNameParameters()[i].equals(name)) {

                // Si se llega al parametro vacio especificado asignar parametro e incrementar el contador
                parameters[i] = parameter;
                functionModel.setParameter(parameters[i].functionModel, i);

            }

        }

    }

    // Obtener y borrar el parametro en la posición especificada
    public Function getAndDeleteParameter(String name) {

        Function function = null;

        // Recorrer parametros
        for (int i = 0; i < getParameters().length; i++) {

            if (getFunctionModel().getNameParameters()[i].equals(name)) {

                // Si se llega al parametro vacio especificado asignar parametro e incrementar el contador
                function = getParameters()[i];
                getParameters()[i] = null;
                functionModel.getParameters()[i] = null;

            }

            if (getParameters()[i] != null) {

                // Si el parametro está asignado recorrer los parametros interior contando los parametros no asignados interiores
                Function function2 = getParameters()[i].getAndDeleteParameter(name);

                if (function2 != null) {
                    function = function2;
                }

            }
        }

        return function;

    }

    // Obtener el parametro en la posición especificada
    public Function getParameter(String name) {

        Function function = null;

        // Recorrer parametros
        for (int i = 0; i < getParameters().length; i++) {

            if (getFunctionModel().getNameParameters()[i].equals(name)) {

                // Si se llega al parametro vacio especificado asignar parametro e incrementar el contador
                function = getParameters()[i];
                break;

            }

            if (getParameters()[i] != null) {

                // Si el parametro está asignado recorrer los parametros interior contando los parametros no asignados interiores
                Function function2 = getParameters()[i].getParameter(name);

                if (function2 != null) {
                    function = function2;
                    break;
                }

            }
        }

        return function;

    }

    // Obtener el parametro en la posición especificada
    public Function getParentParameter(String name) {

        Function function = null;

        // Recorrer parametros
        for (int i = 0; i < getParameters().length; i++) {

            if (getFunctionModel().getNameParameters()[i].equals(name)) {

                // Si se llega al parametro vacio especificado asignar parametro e incrementar el contador
                function = this;
                break;

            }

            if (getParameters()[i] != null) {

                // Si el parametro está asignado recorrer los parametros interior contando los parametros no asignados interiores
                Function function2 = getParameters()[i].getParentParameter(name);

                if (function2 != null) {
                    function = function2;
                    break;
                }

            }
        }

        return function;

    }

    // Obtener un arreglo de todos los nombres de las variables contenidos
    public String[] getAllNamesParameters() {

        ArrayList<String> names = new ArrayList<>();
        names = getAllNameParameters(names);

        if (names.isEmpty()) {
            return new String[0];
        }
        return names.toArray(new String[names.size()]);
    }

    // Obtener un arreglo de todos los nombres de las variables contenidos
    private ArrayList<String> getAllNameParameters(ArrayList<String> names) {

        // Recorrer parametros
        for (int i = 0; i < getParameters().length; i++) {

            if (!names.contains(functionModel.getNameParameters()[i])) {

                // Si se llega al parametro vacio especificado asignar parametro e incrementar el contador
                names.add(getFunctionModel().getNameParameters()[i]);

            }

            if (getParameters()[i] != null) {

                // Si el parametro está asignado recorrer los parametros interior contando los parametros no asignados interiores
                names = getParameters()[i].getAllNameParameters(names);

            }
        }

        return names;

    }

    // Obtener un arreglo de todos los nombres de las variables contenidos que se deben mostrar
    public String[] getAllShowNamesParameters() {

        ArrayList<String> names = new ArrayList<>();
        names = getAllShowNamesParameters(names);

        if (names.isEmpty()) {
            return new String[0];
        }
        return names.toArray(new String[names.size()]);
    }

    // Obtener un arreglo de todos los nombres de las variables contenidos que se deben mostrar
    private ArrayList<String> getAllShowNamesParameters(ArrayList<String> names) {

        // Recorrer parametros
        for (int i = 0; i < getParameters().length; i++) {

            if (!names.contains(functionModel.getNameParameters()[i]) && (getParameter(getFunctionModel().getNameParameters()[i]) == null || getParameter(getFunctionModel().getNameParameters()[i]).getFunctionModel().getShowParameter() == 1)) {

                // Si se llega al parametro vacio especificado asignar parametro e incrementar el contador
                names.add(getFunctionModel().getNameParameters()[i]);

            }

            if (getParameters()[i] != null && getFunctionModel().getTypeParameters()[i].trim().equals("0")) {

                // Si el parametro está asignado recorrer los parametros interior contando los parametros no asignados interiores
                names = getParameters()[i].getAllShowNamesParameters(names);

            }
        }

        return names;

    }

    // Obtener el nombre del parametro en la posición especificada
    public void setNameParameter(String name, String oldName) {

        // Recorrer parametros
        for (int i = 0; i < getParameters().length; i++) {

            if (getFunctionModel().getNameParameters()[i].equals(oldName)) {
                // Si se llega al parametro vacio especificado asignar parametro e incrementar el contador
                functionModel.getNameParameters()[i] = name;

            }

            if (getParameters()[i] != null) {

                // Si el parametro está asignado recorrer los parametros interior contando los parametros no asignados interiores
                getParameters()[i].setNameParameter(name, oldName);

            }
        }

    }

    // Asignar indicador a todos los nombres de parametros
    public void setNameParameterIndicator(String name, String indicator) {

        // Recorrer parametros
        for (int i = 0; i < getParameters().length; i++) {

            if (getFunctionModel().getNameParameters()[i].equals(name)) {
                functionModel.getNameParameters()[i] += "_{" + indicator + "}";
            }

            if (getParameters()[i] != null) {

                // Si el parametro está asignado recorrer los parametros interior contando los parametros no asignados interiores
                getParameters()[i].setNameParameterIndicator(name, indicator);

            }
        }

    }

    // Ocultar todos los parametros
    public void hideAllParameters() {

        // Recorrer parametros
        for (int i = 0; i < getParameters().length; i++) {

            if (getParameters()[i] != null) {

                getParameters()[i].getFunctionModel().setShowParameter(0);

                // Si el parametro está asignado recorrer los parametros interior contando los parametros no asignados interiores
                getParameters()[i].hideAllParameters();

            }
        }

    }

    // Ocultar todos los parametros
    public void showAllParameters() {

        // Recorrer parametros
        for (int i = 0; i < getParameters().length; i++) {

            if (getParameters()[i] != null) {

                getParameters()[i].getFunctionModel().setShowParameter(1);

                // Si el parametro está asignado recorrer los parametros interior contando los parametros no asignados interiores
                getParameters()[i].hideAllParameters();

            }
        }

    }

    // Verificar si la cadena es un numero
    public boolean isNumber(String in) {
        try {
            new BigDecimal(in.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Verificar si la cadena es un vector de un elemento
    public boolean isVectorOfOne(String in) {
        if (in.length() < 2 || in.charAt(0) != '[' || in.charAt(in.length() - 1) != ']' || in.contains(",")) {
            return false;
        }
        return true;
    }

    // Verificar si la cadena es un vector
    public boolean isVector(String in) {

        // Verificar si la cadena esta limitada por parentesis cuadradados
        if (in.length() <= 2 || in.charAt(0) != '[' || in.charAt(in.length() - 1) != ']' || in.contains(";")) {
            return false;
        }

        // La cadena es un vector
        return true;

    }

    // Verificar si la cadena es una matriz
    public boolean isMatrix(String in) {

        // Verificar si la cadena esta limitada por parentesis cuadradados
        if (in.length() <= 2 || in.charAt(0) != '[' || in.charAt(in.length() - 1) != ']') {
            return false;
        }

        // Eliminar los parentesis cuadradados
        in = in.substring(1, in.length() - 1);

        // Separar las filas en un arreglo
        String[] separatedFills = in.split(";");

        // Verificar si cada fila es un vector y si todas las filas tienen el mismo tamaño
        int size = separatedFills[0].split(",").length;
        for (int i = 0; i < separatedFills.length; i++) {
            if (!isVector("[" + separatedFills[i] + "]") || separatedFills[i].split(",").length != size) {
                return false;
            }
        }

        // La cadena es una matriz
        return true;

    }

    // Obtener imagen LaTeX de la formula especificada
    private Icon getImage(String scriptLaTeX) {

        String[] separatedScript = scriptLaTeX.split("#");      // Arreglo que almacena el script de LaTeX separado por el simbolo "#" 
        String solvedScript = "";                               // Cadena que almacena el script de LaTeX con los parametros reemplazados

        // Recorrer los parametros de la función
        for (int i = 0; i < separatedScript.length; i++) {

            if (i % 2 == 1) {

                int numParameter = Integer.parseInt(separatedScript[i]) - 1;
                separatedScript[i] = "\\textcolor{" + Constants.toString(LATEX_COLOR_EMPTY_PARAMETER) + "}{" + getFunctionModel().getNameParameters()[numParameter] + "}";

            }

        }

        // Unir las partes del script de LaTeX con los parametros reemplazados
        for (int i = 0; i < separatedScript.length; i++) {
            solvedScript += separatedScript[i];
        }

        // Remplazar parentesis por la formula de parentesis LaTeX que se ajuste al tamaño de sus elementos internos
        solvedScript = solvedScript.replace("(", "\\left(").replace(")", "\\right)");

        // Remplazar matrices por la formula de matriz de LaTeX
        solvedScript = setResultLatexFormat(solvedScript);

        // Retornar imagen de la formula LaTeX transformada con tamaño por defecto
        if (getFunctionModel().getLaTeXSize() == -1) {
            Icon icon = laTeX.getImage(solvedScript, getFunctionModel().getLaTeXSize());
            getFunctionModel().setLaTeXSize(icon.getIconHeight());
            return icon;
        }

        // Retornar imagen de la formula LaTeX transformada
        return laTeX.getImage(solvedScript, getFunctionModel().getLaTeXSize());

    }

    // Obtener imagen LaTeX de la formula especificada con los paramentros reemplazados
    public Icon getReplaceParametersImage(String parameterHighlight) {
        return getImage(asignParameters(parameterHighlight));
    }

    // obtener representación LaTeX de un numero
    public String getNumberDisplay(String num) {

        try {

            num = new BigDecimal(num).toString();
            num = num.trim();

            // Elimar ceros a la izquierda
            int pos = 0;
            for (int i = 0; i < num.split("\\.")[0].length() - 1; i++) {
                if (num.split("\\.")[0].charAt(i) == '0') {
                    pos++;
                } else {
                    break;
                }
            }
            num = num.substring(pos);

            // Redondear
            if (num.split("\\.")[0].equals("0")) {
                num = new BigDecimal(num).add(BigDecimal.ONE).round(new MathContext(num.split("\\.")[0].replace("-", "").length() + getFunctionModel().getNumDecimals())).add(BigDecimal.ONE.negate()).toString();
            } else if (num.split("\\.")[0].equals("-0")) {
                num = new BigDecimal(num).add(BigDecimal.ONE.negate()).round(new MathContext(num.split("\\.")[0].replace("-", "").length() + getFunctionModel().getNumDecimals())).add(BigDecimal.ONE).toString();
            } else {
                num = new BigDecimal(num).round(new MathContext(num.split("\\.")[0].replace("-", "").length() + getFunctionModel().getNumDecimals())).toString();
            }

            // Elimar ceros decimales a la derecha
            if (num.split("\\.").length == 2) {
                pos = num.split("\\.")[1].length();
                for (int i = num.split("\\.")[1].length() - 1; i >= 0; i--) {
                    if (num.split("\\.")[1].charAt(i) == '0') {
                        pos--;
                    } else {
                        break;
                    }
                }
                num = num.substring(0, num.split("\\.")[0].length() + pos + 1);
            }

            // Eliminar punto si no hay parte decimal
            if (num.charAt(num.length() - 1) == '.') {
                num = num.substring(0, num.length() - 1);
            }

        } catch (java.lang.NumberFormatException ex) {
        }

        return num;

    }

    // obtener representación LaTeX de un vector
    public String getVectorDisplay(String vector) {

        String vectorDisplay = "(";
        vector = vector.replace("[", "").replace("]", "");      // Eliminar corchetes
        String[] elements = vector.split(",");                  // Separar elementos

        // asginar valores al script separados por "," 
        for (int i = 0; i < elements.length; i++) {
            vectorDisplay += getNumberDisplay(elements[i]) + ",";
        }

        // Asignar final del vector LaTeX
        vectorDisplay = vectorDisplay.substring(0, vectorDisplay.length() - 1) + ")";

        // Retornar vector en formato LaTeX
        return vectorDisplay;

    }

    // obtener representación LaTeX de un vector de un elemento
    public String getVectorOfOneDisplay(String vector) {

        // Retornar vector en formato LaTeX
        return getNumberDisplay(vector.substring(1, vector.length() - 1));

    }

    // Obtener representación LaTeX de una matriz
    public String getMatrixDisplay(String matrix) {

        String matrizDisplay = "\\begin{pmatrix}";              // Asignnar inicio de la matriz LaTeX
        matrix = matrix.replace("[", "").replace("]", "");      // Eliminar corchetes
        String[] fills = matrix.split(";");                     // separar filas

        // Asginar valores al script separados por "&" en cada elemento y "\\" por cada fila
        for (int i = 0; i < fills.length; i++) {
            String[] columnas = matrix.split(";")[i].split(",");
            for (int j = 0; j < columnas.length; j++) {
                matrizDisplay += getNumberDisplay(columnas[j].trim()) + "&";
            }
            matrizDisplay = matrizDisplay.substring(0, matrizDisplay.length() - 1) + "\\\\";
        }

        // Asignar final de la matriz LaTeX
        matrizDisplay += "\\end{pmatrix}";

        // retornar matriz en formato LaTeX
        return matrizDisplay;

    }

    // Asignar formato LaTeX a los numeros, vectores y matrices de un script 
    public String setResultLatexFormat(String solvedScript) {

        int state = 0;
        int initSquareBracket = 0;
        for (int i = 0; i < solvedScript.length(); i++) {

            if (state == 0 && solvedScript.charAt(i) == '[') {

                initSquareBracket = i;
                state = 1;

            } else if (state == 1 && solvedScript.charAt(i) == ']') {

                if (isVectorOfOne(solvedScript.substring(initSquareBracket, i + 1))) {

                    solvedScript = solvedScript.substring(0, initSquareBracket)
                            + getVectorOfOneDisplay(solvedScript.substring(initSquareBracket, i + 1))
                            + solvedScript.substring(i + 1, solvedScript.length());

                } else if (isVector(solvedScript.substring(initSquareBracket, i + 1))) {

                    solvedScript = solvedScript.substring(0, initSquareBracket)
                            + getVectorDisplay(solvedScript.substring(initSquareBracket, i + 1))
                            + solvedScript.substring(i + 1, solvedScript.length());

                } else if (isMatrix(solvedScript.substring(initSquareBracket, i + 1))) {

                    solvedScript = solvedScript.substring(0, initSquareBracket)
                            + getMatrixDisplay(solvedScript.substring(initSquareBracket, i + 1))
                            + solvedScript.substring(i + 1, solvedScript.length());

                }

                i = initSquareBracket - 1;
                state = 0;

            }

        }

        return solvedScript;

    }

    public String setResultScilabFormat(String result) {

        if (result.isEmpty()) {
            return "['" + result + "']";
        }

        String scilabFormat = "[";              // Asignnar inicio de la matriz LaTeX

        if (result.trim().charAt(0) == '[') {
            result = result.trim().substring(1);
        }

        if (result.trim().charAt(result.trim().length() - 1) == ']') {
            result = result.trim().substring(0, result.trim().length() - 1);
        }

        String[] fills = result.split(";");                     // separar filas

        // Asginar valores al script separados por "&" en cada elemento y "\\" por cada fila
        for (int i = 0; i < fills.length; i++) {
            String[] columnas = result.split(";")[i].split(",");

            boolean existsNotNumber = false;
            for (int j = 0; j < columnas.length; j++) {
                if (!isNumber(columnas[j].trim())) {
                    existsNotNumber = true;
                }
            }

            if (existsNotNumber) {

                for (int j = 0; j < columnas.length; j++) {
                    scilabFormat += "'" + columnas[j].trim().replace("'", "") + "'" + ",";
                }

            } else {

                for (int j = 0; j < columnas.length; j++) {
                    scilabFormat += getNumberDisplay(columnas[j].trim()) + ",";
                }

            }

            scilabFormat = scilabFormat.substring(0, scilabFormat.length() - 1) + ";";
        }

        // Asignar final de la matriz LaTeX
        scilabFormat = scilabFormat.substring(0, scilabFormat.length() - 1) + "]";

        // retornar matriz en formato LaTeX
        return scilabFormat;

    }

    // Asignar formato LaTeX a los numeros, vectores y matrices de un script 
    public int getDefaultIconHeight() {

        int laTeXSize = getFunctionModel().getLaTeXSize();
        getFunctionModel().setLaTeXSize(-1);
        int height = getReplaceParametersImage("").getIconHeight();
        getFunctionModel().setLaTeXSize(laTeXSize);
        return height;

    }

    // Obtener modelo de la función
    public FunctionModel getFunctionModel() {
        return functionModel;
    }

    // Asignar modelo de la función
    public void setFunctionModel(FunctionModel functionModel) {
        this.functionModel = functionModel;
    }

    // Obtener parametros
    public Function[] getParameters() {
        return parameters;
    }

    // Asignar parametros
    public void setParameters(Function[] parameters) {
        this.parameters = parameters;
    }

    // Obtener solución Scilab
    public String getScriptSolveScilab() {
        return scriptSolveScilab;
    }

    // Asignar solución Scilab
    public void setScriptSolveScilab(String scriptSolveScilab) {
        this.scriptSolveScilab = scriptSolveScilab;
    }

    // Obtener solución LaTeX
    public String getScriptSolveLaTeX() {
        return scriptSolveLaTeX;
    }

    // Asignar solución LaTeX
    public void setScriptSolveLaTeX(String scriptSolveLaTeX) {
        this.scriptSolveLaTeX = scriptSolveLaTeX;
    }

}
