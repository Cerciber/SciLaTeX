package scilatex.Logic.Controllers.ViewControllers;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import scilatex.Data.Constants.Constants;
import static scilatex.Data.Constants.Constants.BORDER_COLOR;
import static scilatex.Data.Constants.Constants.COLOR_TEXT;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_ASIGN_PARAMETER;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_EMPTY_PARAMETER;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_MOVED;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_VIEW_PARAMETER;
import static scilatex.Data.Constants.Constants.SIZE_ICON_BUTTON;
import scilatex.Logic.Objects.Function;
import scilatex.View.Components.FunctionBox;
import scilatex.View.Components.InsertValue;
import scilatex.View.Components.Notification;

// Clase que permite controlar una caja de función
public class FunctionBoxController {

    // Componentes graficos
    public FunctionBox functionBox;                 // Caja de función asociada
    public JButton menuParameterButton;             // Boton del parametro en el cual se proyecta el menu
    public JButton insertParameterConstantButton;   // Boton del parametro en el cual se ingresará una constante
    
    // Variables auxiliares
    public boolean outFunction;                     // Sacar una función de la caja
    public boolean resize;                          // Redimencionando la función
    public int widthY;                              // Ancho de la función al presionar el panel
    public int pressedX;                            // Localización del puntero al presionar el panel en x
    public int pressedY;                            // Localización del puntero al presionar el panel en y

    // Iniciar controlador
    public FunctionBoxController(FunctionBox functionBox) {
        this.functionBox = functionBox;
    }

    // Mostar u ocultar complementos de la caja de función
    public void showFunctionBoxComplements(boolean state) {

        // Si se esta mostrando la caja de insersión
        if (functionBox.getPrincipalPanel().containerBoxPane.getComponentZOrder(functionBox.insertParameterConstantPanel) != -1) {
            state = true;
        }

        // Mostrar barra de tamaño y de decimales
        functionBox.decimalSlider.setVisible(state);

        // Mostrar botones verticales
        for (int j = 0; j < functionBox.getViewButtons().length; j++) {
            functionBox.getViewButtons()[j].setVisible(state);
        }

        // Mostrar botones horizontales
        for (int j = 0; j < functionBox.getParametersButtons().length; j++) {
            functionBox.getParametersButtons()[j].setVisible(state);
        }

    }

    // Asignar resaltado de parametros en realción a la posición del mouse
    public void paramatersHighlight() {

        // Verifica si el mouse está en algun botón
        boolean mouseInAnyButton = false;

        // Almacena la posicion en x actual del boton de parametro
        int buttonLocationX = 0;

        // Recorrer cada parametro de cada función
        for (int j = 0; j < functionBox.getParametersButtons().length; j++) {

            // Asignar botón
            JButton button = functionBox.getParametersButtons()[j];

            // Si el mouse ingresa dentro del rectangulo del boton o si el panel de inserción de parametros está activo
            if (isMouseInParameterButton(button, buttonLocationX)) {
                // Resaltar boton e icono del parametro de la función en verde
                button.setIcon(functionBox.getPrincipalPanel().laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_MOVED) + "}{[" + button.getToolTipText() + "]}", SIZE_ICON_BUTTON));
                functionBox.jLabel1.setIcon(functionBox.getFunction().getReplaceParametersImage(button.getToolTipText()));
                mouseInAnyButton = true;

            } else {

                // Colorear boton del parametro de la función en azul si no está asginado
                if (functionBox.getFunction().getParameter(button.getToolTipText()) == null) {

                    button.setIcon(functionBox.getPrincipalPanel().laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_EMPTY_PARAMETER) + "}{[" + button.getToolTipText() + "]}", SIZE_ICON_BUTTON));

                    // Colorear boton del parametro de la función en negro si ya está asginado
                } else {
                    button.setIcon(functionBox.getPrincipalPanel().laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_ASIGN_PARAMETER) + "}{[" + button.getToolTipText() + "]}", SIZE_ICON_BUTTON));
                }

            }

            // Asignar la posición en x del siguiente boton de parametro
            buttonLocationX += button.getPreferredSize().width;

        }

        // Si el mouse no está sobre el rectangulo de ningun botón
        if (!mouseInAnyButton) {

            // Eliminar resaltado
            functionBox.jLabel1.setIcon(functionBox.getFunction().getReplaceParametersImage(""));

        }

        if ((functionBox.getPrincipalPanel().containerBoxPane.getComponentZOrder(functionBox.insertParameterConstantPanel) != -1)) {

            // Resaltar boton e icono del parametro de la función en verde
            insertParameterConstantButton.setIcon(functionBox.getPrincipalPanel().laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_MOVED) + "}{[" + insertParameterConstantButton.getToolTipText() + "]}", SIZE_ICON_BUTTON));
            functionBox.jLabel1.setIcon(functionBox.getFunction().getReplaceParametersImage(insertParameterConstantButton.getToolTipText()));
            mouseInAnyButton = true;

        }

    }

    // Asignar resaltado de los botones de vista en realción a la posición del mouse
    public void viewButtonsHighlight() {

        // Almacena la posicion en y actual del boton de vista
        int buttonLocationY = 0;

        // Recorrer cada boton de vista
        for (int j = 0; j < functionBox.getViewButtons().length; j++) {

            // Asignar botón
            JButton button = functionBox.getViewButtons()[j];

            // Si el mouse ingresa dentro del rectangulo del boton de vista
            if (isMouseInViewButton(button, buttonLocationY)) {

                // Resaltar boton del prametro de la función en verde
                button.setIcon(functionBox.getPrincipalPanel().laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_MOVED) + "}{[" + button.getToolTipText() + "]}", SIZE_ICON_BUTTON));

                // Si el mouse no ingresa dentro del rectangulo del boton de vista
            } else {

                // Colorear boton del prametro de la función en azul
                button.setIcon(functionBox.getPrincipalPanel().laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_VIEW_PARAMETER) + "}{[" + button.getToolTipText() + "]}", SIZE_ICON_BUTTON));

            }

            // Asignar la posición en y del siguiente boton de vista
            buttonLocationY += button.getPreferredSize().height;

        }

    }

    // Verificar si el mouse esta dentro del la caja de función
    public boolean isMouseIn() {

        return functionBox.getPrincipalPanel().getContentPanelController().getMouseX() > functionBox.getLocation().x
                && functionBox.getPrincipalPanel().getContentPanelController().getMouseX() < functionBox.getLocation().x + functionBox.getWidth()
                && functionBox.getPrincipalPanel().getContentPanelController().getMouseY() > functionBox.getLocation().y
                && functionBox.getPrincipalPanel().getContentPanelController().getMouseY() < functionBox.getLocation().y + functionBox.getHeight();

    }
    
    // Verificar si el mouse esta dentro de un boton de parametro especificado
    public boolean isMouseInParameterButton(JButton button, int buttonLocationX) {

        return functionBox.getPrincipalPanel().getContentPanelController().getMouseX() > buttonLocationX + button.getParent().getLocation().x
                + button.getParent().getParent().getLocation().x + button.getParent().getParent().getParent().getLocation().x
                && functionBox.getPrincipalPanel().getContentPanelController().getMouseX() < button.getPreferredSize().width + buttonLocationX + button.getParent().getLocation().x
                + button.getParent().getParent().getLocation().x + button.getParent().getParent().getParent().getLocation().x
                && functionBox.getPrincipalPanel().getContentPanelController().getMouseY() > -button.getPreferredSize().height + button.getParent().getLocation().y
                + button.getParent().getParent().getLocation().y + button.getParent().getParent().getParent().getLocation().y
                && functionBox.getPrincipalPanel().getContentPanelController().getMouseY() < button.getParent().getLocation().y + button.getParent().getParent().getLocation().y
                + button.getParent().getParent().getParent().getLocation().y;

    }
    
    // Verificar si el mouse esta dentro de un boton de parametro especificado
    public boolean isMouseInViewButton(JButton button, int buttonLocationY) {

        return functionBox.getPrincipalPanel().getContentPanelController().getMouseX() > button.getParent().getLocation().x + button.getParent().getParent().getLocation().x
                + button.getParent().getParent().getParent().getLocation().x
                && functionBox.getPrincipalPanel().getContentPanelController().getMouseX() < button.getSize().width + button.getParent().getLocation().x
                + button.getParent().getParent().getLocation().x + button.getParent().getParent().getParent().getLocation().x
                && functionBox.getPrincipalPanel().getContentPanelController().getMouseY() > buttonLocationY - button.getPreferredSize().height
                + button.getParent().getLocation().y + button.getParent().getParent().getLocation().y + button.getParent().getParent().getParent().getLocation().y
                && functionBox.getPrincipalPanel().getContentPanelController().getMouseY() < buttonLocationY + button.getParent().getLocation().y
                + button.getParent().getParent().getLocation().y + button.getParent().getParent().getParent().getLocation().y;

    }

    // Asignar vista segun las opciones de la función
    public void setViewOption(int option) {
        int height1 = functionBox.getFunction().getDefaultIconHeight();
        functionBox.getFunction().getFunctionModel().setOption(option);
        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }
        asignDataFunction();
        functionBox.getFunctionBoxController().asignFunction();
    }

    // Asignar propiedades de la función en el panel derecho
    public void asignDataFunction() {

        // Asignar propiedades de la función en el panel derecho
        functionBox.getPrincipalPanel().jLabel5.setText(functionBox.getFunction().getFunctionModel().getNodeName());
        functionBox.getPrincipalPanel().jLabel1.setIcon(functionBox.getPrincipalPanel().laTeX.getImage("\\textcolor{" + Constants.toString(COLOR_TEXT) + "}{" + functionBox.getFunction().getFunctionModel().getFunctionName() + "}", 40));
        functionBox.getPrincipalPanel().jTextArea1.setText(functionBox.getFunction().getFunctionModel().getDescription());
        functionBox.getPrincipalPanel().jTextArea2.setText(functionBox.getFunction().getFunctionModel().getScriptScilab());
        functionBox.getPrincipalPanel().jTextArea3.setText(functionBox.getFunction().getFunctionModel().getScriptLaTeXIn());

        String[] s = new String[functionBox.getFunction().getFunctionModel().getNameParameters().length];
        for (int i = 0; i < functionBox.getFunction().getFunctionModel().getNameParameters().length; i++) {
            s[i] = functionBox.getFunction().getFunctionModel().getNameParameters()[i] + " : " + functionBox.getFunction().getFunctionModel().getDescriptionParameters()[i];
        }
        functionBox.getPrincipalPanel().jList1.setListData(s);

        // Eliminar bordes de todos los function box
        functionBox.getPrincipalPanel().getContentPanelController().deleteAllFunctionBoxBorders();

        // Asignar borde al icono de la caja
        functionBox.jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(BORDER_COLOR));

        // Remover el panel de ingresar constante
        functionBox.getPrincipalPanel().containerBoxPane.remove(functionBox.getPrincipalPanel().insertConstantPanel);
        functionBox.getPrincipalPanel().getInsertConstantController().showInsertConstant = false;

        // Actualizar interfaz
        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }

    // Sacar parametro de una caja de función
    public void takeOutParamater(MouseEvent evt) {

        int height1 = functionBox.getFunction().getDefaultIconHeight();

        // Obtener función del parametro 
        Function f = functionBox.getFunction().getAndDeleteParameter(((JButton) evt.getComponent()).getToolTipText());

        // Añadir al panel contenedor una nueva caja de función con la función obtenida
        functionBox.getPrincipalPanel().getContentPanelController().setDraggerFunction(new FunctionBox(functionBox.getPrincipalPanel(), f));
        functionBox.getPrincipalPanel().containerBoxPane.add(functionBox.getPrincipalPanel().getContentPanelController().getDraggerFunction(), 0).setLocation((int) (evt.getComponent().getParent().getParent().getLocation().getX() + evt.getComponent().getParent().getLocation().getX() + evt.getComponent().getLocation().getX() + evt.getX() - (functionBox.getPrincipalPanel().getContentPanelController().getDraggerFunction().getBoxWidth() / 2)),
                (int) (evt.getComponent().getParent().getParent().getLocation().getY() + evt.getComponent().getParent().getLocation().getY() + evt.getComponent().getLocation().getY() + evt.getY() - (functionBox.getPrincipalPanel().getContentPanelController().getDraggerFunction().getBoxHeight() / 2)));

        // Reasignar botones, función y actualizar panel
        functionBox.getFunction().solve();
        functionBox.asignParameterslButtons();

        int height2 = functionBox.getFunction().getDefaultIconHeight();

        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }

        functionBox.getFunctionBoxController().asignFunction();

        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

        // Presionar virtualmente la nueva caja de función para activar el arrastre
        try {
            Robot bot = new Robot();
            bot.mouseMove(MouseInfo.getPointerInfo().getLocation().x, MouseInfo.getPointerInfo().getLocation().y);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException ex) {
            System.out.println("No se pudo presionar la caja de la función");
        }

    }

    // Mostrar panel para ingresar una constante en el parametro especificado
    public void showInsertParameterConstant(JButton button) {

        if (functionBox.getPrincipalPanel().containerBoxPane.getComponentZOrder(functionBox.insertParameterConstantPanel) == -1 || !insertParameterConstantButton.getToolTipText().equals(button.getToolTipText())) {

            // Asignar nombre del parametro a asignar
            insertParameterConstantButton = button;

            // Obtener posición x en relación al panel contenedor
            int buttonLocationX = 0;
            for (int j = 0; j < functionBox.getParametersButtons().length; j++) {
                if (button.equals(functionBox.getParametersButtons()[j])) {
                    break;
                }
                buttonLocationX += functionBox.getParametersButtons()[j].getPreferredSize().width;
            }

            // Ubicar el apuntador al boton especificado
            buttonLocationX += button.getWidth() / 3 - functionBox.insertParameterConstantPanel.getPreferredSize().width / 2;

            // Añadir panel de inserción  al panel contenedor
            functionBox.getPrincipalPanel().containerBoxPane.add(functionBox.insertParameterConstantPanel, 0).setBounds(buttonLocationX + button.getParent().getLocation().x + button.getParent().getParent().getLocation().x + button.getParent().getParent().getParent().getLocation().x,
                    functionBox.getLocation().y - functionBox.insertParameterConstantPanel.getPreferredSize().height + 5, functionBox.insertParameterConstantPanel.getPreferredSize().width, functionBox.insertParameterConstantPanel.getPreferredSize().height);
            functionBox.getPrincipalPanel().containerBoxPane.updateUI();

            functionBox.jTextField14.requestFocus();

        } else {

            // Remover las caja de ingresar constante
            functionBox.getPrincipalPanel().containerBoxPane.remove(functionBox.insertParameterConstantPanel);
            insertParameterConstantButton = null;

        }

    }

    // Mostrar menu de un parametro
    public void showParameterMenu(MouseEvent evt) {

        menuParameterButton = (JButton) evt.getComponent();
        functionBox.asignParamaterPopupmenus();
        functionBox.boxParameterMenu.show(evt.getComponent(), evt.getX(), evt.getY());

    }

    // Insertar constante en el parametro
    public void insertParameterConstant() {

        // Remover las caja de ingresar constante
        functionBox.getPrincipalPanel().containerBoxPane.remove(functionBox.insertParameterConstantPanel);

        // Si el scrib de LaTeX está vacio
        if (functionBox.jTextField13.getText().trim().equals("")) {

            // Asignar al script de LaTeX el Script de Scilab
            // Se elimina "%" para evitar pantalla blanca con constantes
            functionBox.jTextField13.setText(functionBox.jTextField14.getText().trim().replace("%", ""));
        }

        // Ingresar constante en una nueva caja de función
        Function f = new Function(functionBox.getPrincipalPanel().scilab, functionBox.jTextField14.getText().trim(), functionBox.jTextField13.getText().trim());

        int height1 = functionBox.getFunction().getDefaultIconHeight();

        // Asignar parametro a la función
        functionBox.getFunction().setParameter(f, insertParameterConstantButton.getToolTipText());

        // Remover la función graficamente
        functionBox.getFunction().solve();
        functionBox.asignParameterslButtons();

        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }

        functionBox.getFunctionBoxController().asignFunction();

        // Reiniciar campos
        functionBox.jTextField14.setText("");
        functionBox.jTextField13.setText("");

        functionBox.requestFocus();

        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }

    // Cambiar numero de decimales del resultado
    public void changeDecimalSize() {

        functionBox.getFunction().getFunctionModel().setNumDecimals(functionBox.decimalSlider.getValue());
        functionBox.getFunctionBoxController().asignFunction();

    }

    // Asignar icono de la función reemplazada
    public void asignFunction() {
        functionBox.jLabel1.setIcon(functionBox.getFunction().getReplaceParametersImage(""));
        functionBox.setSize(functionBox.getBoxWidth(), functionBox.getBoxHeight());
    }

    // Borrar caja de función
    public void deleteFunction() {

        functionBox.getPrincipalPanel().containerBoxPane.remove(functionBox);
        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }

    // Cambiar numero de decimales del resultado
    public void changeFunctionName() {

        String name = new InsertValue(functionBox.getPrincipalPanel(), "Cambiar nombre de la función", "Ingrese el nombre nuevo").getField();
        if (name != null) {
            functionBox.getFunction().getFunctionModel().setFunctionName(name);
        }

    }

    // Mostrar u ocultar nombre de la función en la vista
    public void setShowFunctionInScreen(int option) {
        int height1 = functionBox.getFunction().getDefaultIconHeight();
        functionBox.getFunction().getFunctionModel().setShowFunctionInScreen(option);
        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }
        asignFunction();
        functionBox.asignPopupmenus();
    }

    // Mostrar u ocultar  parametros de la función en la vista
    public void setShowParametersInScreen(int option) {
        int height1 = functionBox.getFunction().getDefaultIconHeight();
        functionBox.getFunction().getFunctionModel().setShowParametersInScreen(option);
        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }
        asignFunction();
        functionBox.asignPopupmenus();
    }

    // Ocultar parametros de la función
    public void hideAsignParamaters() {

        int height1 = functionBox.getFunction().getDefaultIconHeight();
        functionBox.getFunction().hideAllParameters();
        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }
        functionBox.asignParameterslButtons();
        asignFunction();
        functionBox.asignPopupmenus();
        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }

    // Mostrar parametros de la función
    public void showAsignParamaters() {

        int height1 = functionBox.getFunction().getDefaultIconHeight();
        functionBox.getFunction().showAllParameters();
        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }
        functionBox.asignParameterslButtons();
        asignFunction();
        functionBox.asignPopupmenus();
        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }

    // Vaciar parametro seleccionado
    public void emptyParameter() {

        int height1 = functionBox.getFunction().getDefaultIconHeight();
        functionBox.getFunction().getAndDeleteParameter(menuParameterButton.getToolTipText());
        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }
        functionBox.asignParameterslButtons();
        functionBox.getFunctionBoxController().asignFunction();
        functionBox.asignPopupmenus();
        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }

    // Ocultar parametro seleccionado
    public void hideParameter() {

        int height1 = functionBox.getFunction().getDefaultIconHeight();
        functionBox.getFunction().getParameter(menuParameterButton.getToolTipText()).getFunctionModel().setShowParameter(0);
        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }
        functionBox.asignParameterslButtons();
        functionBox.getFunctionBoxController().asignFunction();
        functionBox.asignPopupmenus();
        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }

    // Cambiar nombre del paramatro seleccionado
    public void changeParameterName() {

        String name = new InsertValue(functionBox.getPrincipalPanel(), "Cambiar nombre del parametro", "Ingrese el nombre nuevo").getField();
        if (name != null) {
            functionBox.getFunction().setNameParameter(name, menuParameterButton.getToolTipText());
            functionBox.asignParameterslButtons();
        }

    }

    // Asignar indicador del paramatro seleccionado
    public void asignParameterIndicator() {

        String indicator = new InsertValue(functionBox.getPrincipalPanel(), "Asignar indicador al nombre del parametro", "Ingrese el indicador").getField();
        if (indicator != null) {
            functionBox.getFunction().setNameParameter(menuParameterButton.getToolTipText() + "_{" + indicator + "}", menuParameterButton.getToolTipText());
            functionBox.asignParameterslButtons();
        }

    }

    // Mostrar u ocultar parametros hijos de la función
    public void setShowChildParamaters(int option) {

        int height1 = functionBox.getFunction().getDefaultIconHeight();

        Function f = functionBox.getFunction().getParameter(menuParameterButton.getToolTipText());
        for (int i = 0; i < f.getParameters().length; i++) {
            if (f.getParameters()[i] != null) {
                f.getParameters()[i].getFunctionModel().setShowParameter(option);
            }
        }

        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }
        functionBox.asignParameterslButtons();
        asignFunction();
        functionBox.asignPopupmenus();
        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }

    // Mostrar u ocultar parametros padre de la función
    public void setShowParentParamaters(int option) {

        int height1 = functionBox.getFunction().getDefaultIconHeight();

        Function f = functionBox.getFunction().getParentParameter(menuParameterButton.getToolTipText());
        if (f != null) {
            f.getFunctionModel().setShowParameter(option);
        }

        int height2 = functionBox.getFunction().getDefaultIconHeight();
        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }
        functionBox.asignParameterslButtons();
        asignFunction();
        functionBox.asignPopupmenus();
        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }

    // Asignar insersión de función en parametro (Recorrer cada caja de función)
    public void insertFunctionInParamater() {

        // Si se esta arrastrando un la caja
        if (functionBox.getPrincipalPanel().getContentPanelController().getDraggerFunction() != null) {

            // Recorrer cada caja de función dentro del panel contenedor
            for (int i = 0; i < functionBox.getPrincipalPanel().containerBoxPane.getComponents().length; i++) {

                try {

                    // Evento de soltar el mouse dentro del rectangulo de cada caja de función 
                    insertFunctionInParamater((FunctionBox) functionBox.getPrincipalPanel().containerBoxPane.getComponents()[i]);

                } catch (java.lang.ClassCastException ex) {
                }

            }

            // Desasignar la caja que se está arrastrando
            functionBox.getPrincipalPanel().getContentPanelController().setDraggerFunction(null);

        }

    }

    // Asignar insersión de función en parametro (Recorrer cada parametro)
    private void insertFunctionInParamater(FunctionBox functionBox) {

        // Localización inicial en x del boton
        int buttonLocationX = 0;

        // Recorrer cada parametro de cada función
        for (int j = 0; j < functionBox.getParametersButtons().length; j++) {

            insertFunctionInParamater(functionBox, functionBox.getParametersButtons()[j], buttonLocationX);

            // Asignar la posición en x del siguiente boton de parametro
            buttonLocationX += functionBox.getParametersButtons()[j].getPreferredSize().width;

        }

    }

    // Asignar insersión de función en parametro
    private void insertFunctionInParamater(FunctionBox functionBox, JButton parameter, int buttonLocationX) {

        // Si el mouse no ingresa dentro del rectangulo del boton del parametro
        if (!this.isMouseInParameterButton(parameter, buttonLocationX)) {
            return;
        }

        // Si el parametro ya ha sido asignado
        if (functionBox.getFunction().getParameter(parameter.getToolTipText()) != null) {
            return;
        }

        // Verificar si hay un error de recursividad
        if (loopError(parameter.getToolTipText())) {

            // Mostrar mensaje de error de recursividad
            new Notification(this.functionBox.getPrincipalPanel().containerBoxPane, "Recursividad de variable", "Los parametros de la función a combinar no pueden tener el mismo nombre del parametro contenerdor").show();
            return;

        }

        // Verificar si hay un error de ambiguedad
        if (ambigueError(functionBox)) {

            // Mostrar mensaje de error de ambiguedad
            new Notification(this.functionBox.getPrincipalPanel().containerBoxPane, "Ambiguedad de variables", "Los parametros a combinar deben ser el mismo parametro o estar vacios").show();
            return;

        }

        // Resolver la función entes de combinar
        this.functionBox.getPrincipalPanel().getContentPanelController().getDraggerFunction().getFunction().solve();

        int height1 = functionBox.getFunction().getDefaultIconHeight();

        // Asignar parametro a la función
        functionBox.getFunction().setParameter(this.functionBox.getPrincipalPanel().getContentPanelController().getDraggerFunction().getFunction(), parameter.getToolTipText());
        
        // Remover la función graficamente
        functionBox.getPrincipalPanel().containerBoxPane.remove(this.functionBox.getPrincipalPanel().getContentPanelController().getDraggerFunction());
        functionBox.getFunction().solve();
        functionBox.asignParameterslButtons();

        int height2 = functionBox.getFunction().getDefaultIconHeight();

        if (height2 != 0 && height1 != 0) {
            functionBox.getFunction().getFunctionModel().setLaTeXSize(functionBox.getFunction().getFunctionModel().getLaTeXSize() * height2 / height1);
        }

        functionBox.getFunctionBoxController().asignFunction();

        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

    }
    
    // Verificar que el nombre del parametro a asignar sea diferente a los nombres de los parametros de la función a ingresar para evitar bloqueo por recursividad
    public boolean loopError(String parameterButton) {
        for (int k = 0; k < functionBox.getParametersButtons().length; k++) {
            if (functionBox.getParametersButtons()[k].getToolTipText().equals(parameterButton)) {
                return true;
            }
        }
        return false;
    }
    
    // Verificar que los parametros a combinar esten vacios o sean iguales para evitar ambiguedades
    public boolean ambigueError(FunctionBox functionBox) {

        // Comparar cada parametro de la función con cada parametro de la función especificada
        for (int k = 0; k < this.functionBox.getParametersButtons().length; k++) {
            for (int l = 0; l < functionBox.getParametersButtons().length; l++) {

                // Si el nombre de los parametros coincide
                if (this.functionBox.getParametersButtons()[k].getToolTipText().equals(functionBox.getParametersButtons()[l].getToolTipText())) {

                    // Si ambos parametros están asignados
                    if (this.functionBox.getFunction().getParameter(this.functionBox.getParametersButtons()[k].getToolTipText()) != null
                            && functionBox.getFunction().getParameter(functionBox.getParametersButtons()[l].getToolTipText()) != null) {

                        // Si las funciones no son iguales
                        if (!this.functionBox.getFunction().getParameter(this.functionBox.getParametersButtons()[k].getToolTipText()).equals(
                                functionBox.getFunction().getParameter(functionBox.getParametersButtons()[l].getToolTipText()))) {

                            return true;

                        }

                        // Si alguna función es nula pero no ambas
                    } else if (!(this.functionBox.getFunction().getParameter(this.functionBox.getParametersButtons()[k].getToolTipText()) == null
                            && functionBox.getFunction().getParameter(functionBox.getParametersButtons()[l].getToolTipText()) == null)) {

                        return true;

                    }

                }
            }
        }

        return false;

    }

    // Mostrar menu de la caja de función
    public void showContentPanelMenu(MouseEvent evt) {

        // Remover las caja de ingresar constante y función si estan activas
        functionBox.getPrincipalPanel().containerBoxPane.remove(functionBox.getPrincipalPanel().insertConstantPanel);
        functionBox.getPrincipalPanel().containerBoxPane.remove(functionBox.getPrincipalPanel().insertFunctionPanel);
        functionBox.getPrincipalPanel().getInsertConstantController().showInsertConstant = false;
        functionBox.getPrincipalPanel().getEditFunctionController().showInsertFunction = false;
        functionBox.getPrincipalPanel().containerBoxPane.updateUI();

        // Mostrar menu
        functionBox.boxPaneMenu.show(functionBox, evt.getX(), evt.getY());

    }
    
}
