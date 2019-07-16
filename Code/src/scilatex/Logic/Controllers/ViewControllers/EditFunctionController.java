package scilatex.Logic.Controllers.ViewControllers;

import java.util.Arrays;
import scilatex.Logic.Objects.Function;
import scilatex.View.Components.FunctionBox;
import scilatex.View.Components.Notification;
import scilatex.View.Windows.PrincipalPanel;

// Clase que permite controlar el panel editor de funciones
public class EditFunctionController {

    // Componentes graficos
    public PrincipalPanel principalPanel;   // Panel contenedor
    public FunctionBox editedFunction;      // Caja de función editada
    public FunctionBox nonEditedFunction;   // Caja de función no editada
    
    // Variables auxiliares
    public int functionPressedX;            // Posición del mouse al presionar la caja de ingresar función en x
    public int functionPressedY;            // Posición del mouse al presionar la caja de ingresar función en y
    public boolean showInsertFunction;      // Verifica si es está mostrando la caja de ingresar función

    // Iniciar controlador
    public EditFunctionController(PrincipalPanel principalPanel) {
        this.principalPanel = principalPanel;
    }

    // Mostrar caja para ingresar función
    public void showEditNewFunction() {

        // Mostrar caja en relación a la posición donde se abrió el menu
        principalPanel.containerBoxPane.add(principalPanel.insertFunctionPanel, 0).setBounds(
                principalPanel.getInsertConstantController().constantPressedX,
                principalPanel.getInsertConstantController().constantPressedY,
                principalPanel.insertFunctionPanel.getPreferredSize().width,
                principalPanel.insertFunctionPanel.getPreferredSize().height);

        // Crear caja de función a editar
        editedFunction = new FunctionBox(principalPanel, new Function(principalPanel.scilab));

        // Obtener foco y actualizar UI
        updateInsertFunction();
        principalPanel.jTextField6.requestFocus();
        principalPanel.containerBoxPane.updateUI();
        showInsertFunction = true;

    }

    // Mostrar caja para editar función
    public void showEditFunction(FunctionBox functionBox) {

        // Remover las caja de ingresar constante
        principalPanel.containerBoxPane.remove(functionBox.insertParameterConstantPanel);

        // Verificar que los parametros estén vacios para poder editar la estructura interna
        if (functionBox.getFunction().numAsignParameters() != 0) {

            // Notificar que ya la función no esta vacia
            new Notification(principalPanel.containerBoxPane, "Función no vacia", "La función no puede tener parametros asignados al editar").show();
            return;
        }

        // Mostrar caja en realción a la posición donde se abrió el menu
        principalPanel.containerBoxPane.add(principalPanel.insertFunctionPanel, 0).setBounds(functionBox.getLocation().x, functionBox.getLocation().y,
                principalPanel.insertFunctionPanel.getPreferredSize().width,
                principalPanel.insertFunctionPanel.getPreferredSize().height);

        // Asignar valores de la función
        principalPanel.jTextField6.setText(functionBox.getFunction().getFunctionModel().getNodeName());
        principalPanel.jTextField5.setText(functionBox.getFunction().getFunctionModel().getFunctionName());
        principalPanel.jTextField7.setText(functionBox.getFunction().getFunctionModel().getDescription());
        principalPanel.jTextField8.setText(Arrays.toString(functionBox.getFunction().getFunctionModel().getNameParameters()).substring(1, Arrays.toString(functionBox.getFunction().getFunctionModel().getNameParameters()).length() - 1));
        principalPanel.jTextField9.setText(Arrays.toString(functionBox.getFunction().getFunctionModel().getDescriptionParameters()).substring(1, Arrays.toString(functionBox.getFunction().getFunctionModel().getDescriptionParameters()).length() - 1));
        principalPanel.jTextField10.setText(Arrays.toString(functionBox.getFunction().getFunctionModel().getTypeParameters()).substring(1, Arrays.toString(functionBox.getFunction().getFunctionModel().getTypeParameters()).length() - 1));
        principalPanel.jTextArea5.setText(functionBox.getFunction().getFunctionModel().getScriptScilab());
        principalPanel.jTextArea6.setText(functionBox.getFunction().getFunctionModel().getScriptLaTeXIn());
        principalPanel.jTextArea7.setText(functionBox.getFunction().getFunctionModel().getScriptLaTeXOut());

        // Crear función a editar
        Function f = new Function(principalPanel.scilab,
                functionBox.getFunction().getFunctionModel().getNodeName(),
                functionBox.getFunction().getFunctionModel().getFunctionName(),
                functionBox.getFunction().getFunctionModel().getDescription(),
                functionBox.getFunction().getFunctionModel().getNameParameters(),
                functionBox.getFunction().getFunctionModel().getDescriptionParameters(),
                functionBox.getFunction().getFunctionModel().getTypeParameters(),
                functionBox.getFunction().getFunctionModel().getScriptScilab(),
                functionBox.getFunction().getFunctionModel().getScriptLaTeXIn(),
                functionBox.getFunction().getFunctionModel().getScriptLaTeXOut());

        // Crear caja de función a editar
        principalPanel.getEditFunctionController().editedFunction = new FunctionBox(principalPanel, f);

        // Asignar caja de función original
        principalPanel.getEditFunctionController().nonEditedFunction = functionBox;
        functionBox.setVisible(false);

        // Obtener foco y actualizar UI
        principalPanel.getEditFunctionController().updateInsertFunction();
        principalPanel.jTextField5.requestFocus();
        principalPanel.containerBoxPane.updateUI();
        principalPanel.getEditFunctionController().showInsertFunction = true;

    }

    // Insertar función desde el panel de ingresar función
    public void insertFunction() {

        // Si el nombre está vacio
        if (principalPanel.jTextField5.getText().trim().equals("")) {

            // Notificar que está vacio
            new Notification(principalPanel.containerBoxPane, "Campo vacio", "El nombre de la función está vacio").show();

            return;

        }

        // Si el Script de Scilab está vacio
        if (principalPanel.jTextArea5.getText().trim().equals("")) {

            // Notificar que está vacio
            new Notification(principalPanel.containerBoxPane, "Campo vacio", "El script de Scilab está vacio").show();
            return;

        }

        // Si el Script de LaTeX está vacio
        if (principalPanel.jTextArea6.getText().trim().equals("")) {

            // Notificar que está vacio
            new Notification(principalPanel.containerBoxPane, "Campo vacio", "El script de LaTeX está vacio").show();
            return;

        }

        // Si no hay consistencia en el numero de parametros con el scipt de Scilab y laTeX
        if (!(principalPanel.jTextField8.getText().trim().equals("")
                && Function.numLaTeXParameters(principalPanel.jTextArea6.getText().trim()) == 0
                && Function.numScilabParameters(principalPanel.jTextArea5.getText().trim()) == 0)) {

            if (!(editedFunction.getFunction().numFunctionalParameters() == Function.numLaTeXParameters(principalPanel.jTextArea6.getText().trim())
                    && Function.numLaTeXParameters(principalPanel.jTextArea6.getText().trim()) == Function.numScilabParameters(principalPanel.jTextArea5.getText().trim()))) {

                // Notificar que está vacio
                new Notification(principalPanel.containerBoxPane, "Numero de parametros", "El numero de parametros de los nombres de paramtros y los Scripts no es consistente").show();
                return;

            }

        }

        // Si el Script Scilab no está bien definido
        if (principalPanel.jTextArea5.getText().trim().length() > 7 && principalPanel.jTextArea5.getText().trim().substring(0, 8).equals("function") && !principalPanel.scilab.exec(principalPanel.jTextArea5.getText())) {

            // Notificar que no está bien definido
            new Notification(principalPanel.containerBoxPane, "Error de sintaxis", "La función Scilab no está bien definido").show();
            return;

        }

        // Si se estaba editando una caja cambiar tamaño al original
        if (nonEditedFunction != null) {
            editedFunction.getFunction().getFunctionModel().setLaTeXSize(nonEditedFunction.getFunction().getFunctionModel().getLaTeXSize());
            principalPanel.containerBoxPane.remove(nonEditedFunction);
            nonEditedFunction = null;
        }

        // Ingresar caja de función editada al panel contenedor
        principalPanel.containerBoxPane.add(editedFunction).setLocation(
                principalPanel.insertFunctionPanel.getLocation().x,
                principalPanel.insertFunctionPanel.getLocation().y);

        editedFunction.getFunction().getFunctionModel().setOption(2);
        editedFunction.asignParameterslButtons();
        editedFunction.getFunctionBoxController().asignFunction();
        editedFunction.getPrincipalPanel().containerBoxPane.updateUI();

        // Quitar caja de función editada
        editedFunction = null;

        // Remover ventana de insersión y actualizar UI
        principalPanel.containerBoxPane.remove(principalPanel.insertFunctionPanel);
        principalPanel.containerBoxPane.updateUI();
        showInsertFunction = false;

        // Reiniciar campos
        principalPanel.jTextField6.setText("");
        principalPanel.jTextField5.setText("");
        principalPanel.jTextField7.setText("");
        principalPanel.jTextField8.setText("");
        principalPanel.jTextField9.setText("");
        principalPanel.jTextArea5.setText("function r = f(a,b,c)\n\tr =  \nendfunction");
        principalPanel.jTextArea6.setText("");

    }

    // Cancelar insertar función desde el panel de ingresar función
    public void cancelEditFunction() {

        // Quitar caja de función editada
        editedFunction = null;

        // Si se estaba editando una caja
        if (nonEditedFunction != null) {
            nonEditedFunction.setVisible(true);
            nonEditedFunction.setLocation(principalPanel.insertFunctionPanel.getLocation().x, principalPanel.insertFunctionPanel.getLocation().y);
            nonEditedFunction = null;
        }

        // Remover ventana de insersión y actualizar UI
        principalPanel.containerBoxPane.remove(principalPanel.insertFunctionPanel);
        principalPanel.containerBoxPane.updateUI();
        showInsertFunction = false;

        // Reiniciar campos
        principalPanel.jTextField5.setText("");
        principalPanel.jTextField7.setText("");
        principalPanel.jTextField8.setText("");
        principalPanel.jTextField9.setText("");
        principalPanel.jTextArea5.setText("function r = f(a,b,c)\n   r = \nendfunction");
        principalPanel.jTextArea6.setText("");

    }

    // Actualizar vistas de la caja de insertar función
    public void updateInsertFunction() {

        // Actualizar vista de función
        editedFunction.getFunction().getFunctionModel().setOption(1);
        editedFunction.getFunction().getFunctionModel().setLaTeXSize(-1);
        principalPanel.jLabel10.setIcon(editedFunction.getFunction().getReplaceParametersImage(""));

        // Si los parametros de la vista LaTeX están todos definidos
        if (0 <= Function.numLaTeXParameters(editedFunction.getFunction().getFunctionModel().getScriptLaTeXIn()) && Function.numLaTeXParameters(editedFunction.getFunction().getFunctionModel().getScriptLaTeXIn()) <= principalPanel.jTextField8.getText().split(",").length) {

            // Actualizar vista LaTeX de entrada
            editedFunction.getFunction().getFunctionModel().setOption(2);
            editedFunction.getFunction().getFunctionModel().setLaTeXSize(-1);
            principalPanel.jLabel19.setIcon(editedFunction.getFunction().getReplaceParametersImage(""));

            // Si los parametros de la vista LaTeX no están todos definidos
        } else {

            // Mostrar imagen vacia
            editedFunction.getFunction().getFunctionModel().setOption(2);
            principalPanel.jLabel19.setIcon(null);

        }

        // Actualizar vista LaTeX de salida
        editedFunction.getFunction().setScriptSolveLaTeX(editedFunction.getFunction().asignLaTeXOutParameters(null));
        editedFunction.getFunction().getFunctionModel().setOption(3);
        editedFunction.getFunction().getFunctionModel().setLaTeXSize(-1);
        editedFunction.getFunction().setScriptSolveScilab(null);
        principalPanel.jLabel22.setIcon(editedFunction.getFunction().getReplaceParametersImage(""));

    }

    // Actualizar nombre del nodo
    public void updateNodeName() {

        editedFunction.getFunction().getFunctionModel().setNodeName(principalPanel.jTextField6.getText().trim());
        updateInsertFunction();

    }

    // Actualizar nombre de la función
    public void updateFunctionName() {

        editedFunction.getFunction().getFunctionModel().setFunctionName(principalPanel.jTextField5.getText().trim());
        updateInsertFunction();

    }

    // Actualizar descripcion de la función
    public void updateDescription() {

        editedFunction.getFunction().getFunctionModel().setDescription(principalPanel.jTextField7.getText().trim());
        updateInsertFunction();

    }

    // Actualizar nombre de los parametros de la función
    public void updateNameParameters() {

        if (principalPanel.jTextField8.getText().trim().equals("")) {

            editedFunction.getFunction().getFunctionModel().setNameParameters(new String[0]);
            editedFunction.getFunction().setParameters(new Function[0]);
            editedFunction.getFunction().getFunctionModel().setDescriptionParameters(new String[0]);

        } else {

            editedFunction.getFunction().getFunctionModel().setNameParameters(principalPanel.jTextField8.getText().replace(", ", ",").split(","));
            editedFunction.getFunction().setParameters(new Function[principalPanel.jTextField8.getText().replace(", ", ",").split(",").length]);
            editedFunction.getFunction().getFunctionModel().setDescriptionParameters(new String[principalPanel.jTextField8.getText().replace(", ", ",").split(",").length]);
            editedFunction.getFunction().getFunctionModel().setTypeParameters(new String[principalPanel.jTextField8.getText().replace(", ", ",").split(",").length]);

            String[] descriptionParameters = editedFunction.getFunction().getFunctionModel().getDescriptionParameters();
            for (int i = 0; i < descriptionParameters.length; i++) {
                if (i < principalPanel.jTextField9.getText().split(",").length) {
                    descriptionParameters[i] = principalPanel.jTextField9.getText().replace(", ", ",").split(",")[i];
                } else {
                    descriptionParameters[i] = "";
                }
            }
            editedFunction.getFunction().getFunctionModel().setDescriptionParameters(descriptionParameters);

            String[] typeParameters = editedFunction.getFunction().getFunctionModel().getTypeParameters();
            for (int i = 0; i < typeParameters.length; i++) {
                if (i < principalPanel.jTextField10.getText().split(",").length) {
                    typeParameters[i] = principalPanel.jTextField10.getText().replace(", ", ",").split(",")[i];
                } else {
                    typeParameters[i] = "0";
                }
            }
            editedFunction.getFunction().getFunctionModel().setTypeParameters(typeParameters);

        }

        updateInsertFunction();

    }

    // Actualizar descripción de los parametros de la función
    public void updateDescriptionParameters() {

        String[] descriptionParameters = editedFunction.getFunction().getFunctionModel().getDescriptionParameters();
        for (int i = 0; i < descriptionParameters.length; i++) {
            if (i < principalPanel.jTextField9.getText().replace(", ", ",").split(",").length) {
                descriptionParameters[i] = principalPanel.jTextField9.getText().replace(", ", ",").split(",")[i];
            } else {
                descriptionParameters[i] = "";
            }
        }
        editedFunction.getFunction().getFunctionModel().setDescriptionParameters(descriptionParameters);

        updateInsertFunction();

    }

    // Actualizar tipo de los parametros de la función
    public void updateTypeParamaters() {

        String[] typeParameters = editedFunction.getFunction().getFunctionModel().getTypeParameters();
        for (int i = 0; i < typeParameters.length; i++) {
            if (i < principalPanel.jTextField10.getText().split(",").length) {
                typeParameters[i] = principalPanel.jTextField10.getText().replace(", ", ",").split(",")[i];
            } else {
                typeParameters[i] = "0";
            }
        }
        editedFunction.getFunction().getFunctionModel().setTypeParameters(typeParameters);

        updateInsertFunction();

    }

    // Actualizar script Scilab de la función
    public void updateScriptScilab() {

        editedFunction.getFunction().getFunctionModel().setScriptScilab(principalPanel.jTextArea5.getText());
        updateInsertFunction();

    }

    // Actualizar script LaTeX de entrada de la función
    public void updateScriptLaTeXIn() {

        editedFunction.getFunction().getFunctionModel().setScriptLaTeXIn(principalPanel.jTextArea6.getText());
        updateInsertFunction();

    }

    // Actualizar script LaTeX de salida de la función
    public void updateScriptLaTeXOut() {

        editedFunction.getFunction().getFunctionModel().setScriptLaTeXOut(principalPanel.jTextArea7.getText());
        updateInsertFunction();

    }

}
