
package scilatex.Data.Models;

// Calse que crea un modelo de datos de la función
public class FunctionModel {
    
    // Atributos
    private String nodeName;                // Nombre de la función
    private String functionName;            // Nombre de la función
    private String description;             // Descripción de la función
    private FunctionModel[] parameters;     // Funciones que se pasan como parametros a la función actual
    private String[] nameParameters;        // Nombre los parametros que se pasan a la función actual
    private String[] descriptionParameters; // Descripción de los parametros que se pasan a la función actual
    private String[] typeParameters;        // tipo de los parametros que se pasan a la función actual (0: funcional, 1: no funcional)
    private String scriptScilab;            // Script de la funcionalidad en formato Scilab (Solo admite la estructua de una función scilab o un numero)
    private String scriptLaTeXIn;           // Scrip de diseño de la función en formato LaTeX
    private String scriptLaTeXOut;          // Scrip de diseño de la salida en formato LaTeX
    private int numDecimals;                // Numero de decimales de los numeros 
    private int laTeXSize;                  // Tamaño del icono LaTeX
    private int showFunctionInScreen;       // Mostrrar u ocultar el nombre de la función en las vistas LaTeX
    private int showParametersInScreen;     // Mostrrar u ocultar los paramatros de la función en las vistas LaTeX
    private int showParameter;              // Mostrrar u ocultar paramatro en los botones
    private int option;                      // Opcion de vista (1: función, 2: entrada LaTeX, 3: salida LaTeX, 4: función resuelta Scilab)

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FunctionModel[] getParameters() {
        return parameters;
    }

    public void setParameters(FunctionModel[] parameters) {
        this.parameters = parameters;
    }

    public FunctionModel getParameter(int i) {
        return parameters[i];
    }

    public void setParameter(FunctionModel parameter, int i) {
        parameters[i] = parameter;
    }
    
    public String[] getNameParameters() {
        return nameParameters;
    }

    public void setNameParameters(String[] nameParameters) {
        this.nameParameters = nameParameters;
    }

    public String[] getDescriptionParameters() {
        return descriptionParameters;
    }

    public void setDescriptionParameters(String[] descriptionParameters) {
        this.descriptionParameters = descriptionParameters;
    }

    public String[] getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(String[] typeParameters) {
        this.typeParameters = typeParameters;
    }

    public String getScriptScilab() {
        return scriptScilab;
    }

    public void setScriptScilab(String scriptScilab) {
        this.scriptScilab = scriptScilab;
    }

    public String getScriptLaTeXIn() {
        return scriptLaTeXIn;
    }

    public void setScriptLaTeXIn(String scriptLaTeXIn) {
        this.scriptLaTeXIn = scriptLaTeXIn;
    }

    public String getScriptLaTeXOut() {
        return scriptLaTeXOut;
    }

    public void setScriptLaTeXOut(String scriptLaTeXOut) {
        this.scriptLaTeXOut = scriptLaTeXOut;
    }

    public int getNumDecimals() {
        return numDecimals;
    }

    public void setNumDecimals(int numDecimals) {
        this.numDecimals = numDecimals;
    }

    public int getLaTeXSize() {
        return laTeXSize;
    }

    public void setLaTeXSize(int laTeXSize) {
        this.laTeXSize = laTeXSize;
    }

    public int getShowFunctionInScreen() {
        return showFunctionInScreen;
    }

    public void setShowFunctionInScreen(int showFunctionInScreen) {
        this.showFunctionInScreen = showFunctionInScreen;
    }

    public int getShowParametersInScreen() {
        return showParametersInScreen;
    }

    public void setShowParametersInScreen(int showParametersInScreen) {
        this.showParametersInScreen = showParametersInScreen;
    }

    public int getShowParameter() {
        return showParameter;
    }

    public void setShowParameter(int showParameter) {
        this.showParameter = showParameter;
    }

    public int getOption() {
        return option;
    }

    public void setOption(int option) {
        this.option = option;
    }
    
    
    
}
