package scilatex.Logic.Controllers.ViewControllers;

import java.awt.event.MouseEvent;
import scilatex.Logic.Objects.Function;
import scilatex.View.Components.FunctionBox;
import scilatex.View.Windows.PrincipalPanel;

// Clase que permite controlar la caja de insertar constante
public class InsertConstantController {

    // Componentes graficos
    PrincipalPanel principalPanel;          // Panel contenedor
    
    // Variables auxiliares
    public int constantPressedX;            // Posición del mouse al presionar la caja de ingresar constante en x
    public int constantPressedY;            // Posición del mouse al presionar la caja de ingresar constante en y
    public boolean showInsertConstant;      // Verifica si es está mostrando la caja de ingresar constante  

    // Iniciar controlador
    public InsertConstantController(PrincipalPanel principalPanel) {
        this.principalPanel = principalPanel;
    }
    
    // Mostrar caja de ingresar constante
    public void showIinsertConstant(MouseEvent evt) {

        // Si la insersión es por click
        if (evt != null) {

            // Mostrar caja en relación a las cordenadas del click
            principalPanel.containerBoxPane.add(principalPanel.insertConstantPanel, 0).setBounds(
                    evt.getX() - principalPanel.insertConstantPanel.getPreferredSize().width / 2,
                    evt.getY() - principalPanel.insertConstantPanel.getPreferredSize().height / 2,
                    principalPanel.insertConstantPanel.getPreferredSize().width,
                    principalPanel.insertConstantPanel.getPreferredSize().height);

            // Si la insersión es por menu
        } else {

            // Mostrar caja en realción a la posición donde se abrió el menu
            principalPanel.containerBoxPane.add(principalPanel.insertConstantPanel, 0).setBounds(
                    principalPanel.getInsertConstantController().constantPressedX,
                    principalPanel.getInsertConstantController().constantPressedY,
                    principalPanel.insertConstantPanel.getPreferredSize().width,
                    principalPanel.insertConstantPanel.getPreferredSize().height);

        }

        // Obtener foco y actualizar UI
        principalPanel.jTextField3.requestFocus();
        principalPanel.containerBoxPane.updateUI();
        showInsertConstant = true;

    }

    // Insertar constante desde el panel de ingresar constante
    public void insertConstant() {

        // Remover las caja de ingresar constante y función si estan activas
        principalPanel.containerBoxPane.remove(principalPanel.insertConstantPanel);
        principalPanel.getInsertConstantController().showInsertConstant = false;
        principalPanel.containerBoxPane.updateUI();

        if (!principalPanel.jTextField3.getText().trim().equals("")) {

            // Si el scrib de LaTeX está vacio
            if (principalPanel.jTextField4.getText().trim().equals("")) {

                // Asignar al script de LaTeX el Script de Scilab
                // Se elimina "%" para evitar pantalla blanca con constantes
                principalPanel.jTextField4.setText(principalPanel.jTextField3.getText().trim().replace("%", "").replace("*", "\\cdot "));
            }

            // Ingresar constante en una nueva caja de función
            Function f = new Function(principalPanel.scilab, principalPanel.jTextField3.getText().trim(), principalPanel.jTextField4.getText().trim());
            FunctionBox functionBox = new FunctionBox(principalPanel, f);
            principalPanel.containerBoxPane.add(functionBox).setLocation(
                    principalPanel.insertConstantPanel.getLocation().x + principalPanel.insertConstantPanel.getWidth() / 2 - functionBox.getBoxWidth() / 2, 
                    principalPanel.insertConstantPanel.getLocation().y + principalPanel.insertConstantPanel.getHeight() / 2 - functionBox.getBoxHeight() / 2);
            principalPanel.containerBoxPane.updateUI();

            // Reiniciar campos
            principalPanel.jTextField3.setText("");
            principalPanel.jTextField4.setText("");

            principalPanel.requestFocus();

        }

    }
    
}
