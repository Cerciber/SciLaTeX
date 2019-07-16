package scilatex.Logic.Controllers.ViewControllers;

import java.awt.event.MouseEvent;
import scilatex.View.Components.FunctionBox;
import scilatex.View.Windows.PrincipalPanel;

// Clase que permite controlar el panel contenedor de funciones
public class ContentPanelController {

    // Componentes graficos
    private PrincipalPanel principalPanel;  // Panel contenedor
    private FunctionBox draggerFunction;    // Caja de la función arrastrada
    
    // Variables auxiliares
    private int mouseX;                     // Posicion del mouse relativa al panel contenedor en X
    private int mouseY;                     // Posicion del mouse relativa al panel contenedor en Y

    // Iniciar controlador
    public ContentPanelController(PrincipalPanel principalPanel) {

        this.principalPanel = principalPanel;

    }
    
    // Limpiar panel contenedor
    public void clean() {

        principalPanel.containerBoxPane.removeAll();
        principalPanel.containerBoxPane.updateUI();

    }
    
    // Detectar si se debe mostrar complementos o resaltado de todas las cajas de función
    public void detectAllShowComplementsAndHighlight() {
        
        // Recorrer cada caja de función dentro del rectangulo del panel contenedor
        for (int i = 0; i < principalPanel.containerBoxPane.getComponents().length; i++) {

            try {

                // Obtener caja de función
                FunctionBox functionBox = (FunctionBox) principalPanel.containerBoxPane.getComponents()[i];
                
                // Si el mouse ingresa dentro de la caja de función
                if (functionBox.getFunctionBoxController().isMouseIn()) {

                    // Mostrar complementos de la caja de función
                    functionBox.getFunctionBoxController().showFunctionBoxComplements(true);

                    // Verificar resaltado de parametros
                    functionBox.getFunctionBoxController().paramatersHighlight();

                    // Verificar resaltado de los botones de vista
                    functionBox.getFunctionBoxController().viewButtonsHighlight();

                    // Si el mouse no ingresa dentro de la caja de función
                } else {

                    // Ocultar complementos de la caja de función
                    functionBox.getFunctionBoxController().showFunctionBoxComplements(false);

                }

            } catch (java.lang.ClassCastException ex) {
            }

        }
        
    }
    
    // Borrar los bordes de todas las cajas de función
    public void deleteAllFunctionBoxBorders() {

        for (int i = 0; i < principalPanel.containerBoxPane.getComponents().length; i++) {
            try {
                ((FunctionBox) principalPanel.containerBoxPane.getComponents()[i]).jPanel5.setBorder(null);
            } catch (java.lang.ClassCastException ex) {
            }
        }

    }
    
    // Borrar los paneles de insersión de parametros de todas las cajas de función
    public void deleteAllFunctionInsertParameterPanel() {

        for (int i = 0; i < principalPanel.containerBoxPane.getComponents().length; i++) {
            try {

                // Reiniciar campos
                ((FunctionBox) principalPanel.containerBoxPane.getComponents()[i]).jTextField14.setText("");
                ((FunctionBox) principalPanel.containerBoxPane.getComponents()[i]).jTextField13.setText("");
                
                // Remover las caja de ingresar constante
                principalPanel.containerBoxPane.remove(((FunctionBox) principalPanel.containerBoxPane.getComponents()[i]).insertParameterConstantPanel);

            } catch (java.lang.ClassCastException ex) {
            } catch (java.lang.NullPointerException ex) {
            }
        }

    }

    // Mostrar menu del panel conendor de cajas
    public void showContentPanelMenu(MouseEvent evt) {

        // Remover las caja de ingresar constante y función si estan activas
        principalPanel.containerBoxPane.remove(principalPanel.insertConstantPanel);
        principalPanel.getInsertConstantController().showInsertConstant = false;
        principalPanel.containerBoxPane.updateUI();

        // Asignar coordenadas donde se hizo click
        principalPanel.getInsertConstantController().constantPressedX = evt.getX();
        principalPanel.getInsertConstantController().constantPressedY = evt.getY();

        // Mostrar menu
        principalPanel.containerBoxPaneMenu.show(principalPanel.containerBoxPane, evt.getX(), evt.getY());

    }

    public PrincipalPanel getPrincipalPanel() {
        return principalPanel;
    }

    public void setPrincipalPanel(PrincipalPanel principalPanel) {
        this.principalPanel = principalPanel;
    }

    public int getMouseX() {
        return mouseX;
    }

    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    public FunctionBox getDraggerFunction() {
        return draggerFunction;
    }

    public void setDraggerFunction(FunctionBox draggerFunction) {
        this.draggerFunction = draggerFunction;
    }
    
}
