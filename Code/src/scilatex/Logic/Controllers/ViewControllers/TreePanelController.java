package scilatex.Logic.Controllers.ViewControllers;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import static scilatex.Data.Constants.Constants.TEXT_FONT;
import scilatex.Logic.Objects.Function;
import scilatex.View.Components.FunctionBox;
import scilatex.View.Components.Notification;
import scilatex.View.Windows.PrincipalPanel;

// Clase que permite controlar el arbol de funciones
public final class TreePanelController {
    
    // Componentes graficos
    public PrincipalPanel principalPanel;   // Panel contenedor
    public JTree draggerNode;               // Nodo de la caja de función arrastrada
    
    // Variables auxiliares
    public File selectedFile;               // Ruta del nodo seleccionado
    public int treePressedX;                // Localización del puntero al presionar el arbol de funciones en x
    public int treePressedY;                // Localización del puntero al presionar el arbol de funciones en y

    // Iniciar controlador
    public TreePanelController(PrincipalPanel principalPanel) {
        
        this.principalPanel = principalPanel;
        
        customizedIconNode();
        
    }
    
    // Personalizar icono y texto
    public void customizedIconNode(){
        
        principalPanel.jTree1.setCellRenderer(new DefaultTreeCellRenderer() {

            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value,
                    boolean selected, boolean expanded, boolean isLeaf, int row,
                    boolean focused) {

                Component c = super.getTreeCellRendererComponent(tree, value, selected,
                        expanded, isLeaf, row, focused);

                Object[] obejct = (Object[]) ((DefaultMutableTreeNode) value).getUserObject();
                boolean type = (boolean) obejct[0];
                String name = (String) obejct[1];

                if (type) {
                    setIcon(getLeafIcon());
                } else {
                    setIcon(getOpenIcon());
                }
                setText(name);

                return c;
            }

        });
        
    }

    // Asignar valores al arbol de funciones
    public void asignNodes() {
        
        // Crear raiz del arbol
        File file = new File("Funciones");
        Object[] obejct = {false, file.getName(), file};
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(obejct);
        DefaultTreeModel model = new DefaultTreeModel(node);
        principalPanel.jTree1.setModel(model);

        // Asignar funciones y carpetas al arbol
        asignNodes(model, node, file);
        
        principalPanel.leftPane.updateUI();

    }
    
    // Asignar nodos al arbol recursivamente
    private void asignNodes(DefaultTreeModel model, DefaultMutableTreeNode parentNode, File file) {

        // Si existen rutas o archivos contendidos
        if (file.listFiles() != null) {

            // Para cada archivo
            for (int i = 0; i < file.listFiles().length; i++) {

                // Si es una carpeta
                if (file.listFiles()[i].isDirectory()) {

                    // Nodo a agregar
                    DefaultMutableTreeNode childNode = null;

                    // Si la carpeta es una función
                    if (isNode(file.listFiles()[i])) {

                        try {

                            // Abrir carpeta
                            FileReader reader = new FileReader(file.listFiles()[i] + "\\" + "Atributes.txt");
                            BufferedReader br = new BufferedReader(reader);

                            // Agregar nombre de la función al nodo con el icono de función
                            Object[] obejct = {true, br.readLine().trim(), file.listFiles()[i]};
                            childNode = new DefaultMutableTreeNode(obejct);

                        } catch (FileNotFoundException ex) {
                            System.out.println("No se pudo abrir el archivo");
                        } catch (IOException ex) {
                            System.out.println("No se pudo leer el archivo");
                        }

                        // Si la carpeta es una carpeta de funciones
                    } else {

                        // Agregar nombre de la carpeta al nodo con el icono de carpeta
                        Object[] obejct = {false, file.listFiles()[i].getName(), file.listFiles()[i]};
                        childNode = new DefaultMutableTreeNode(obejct);
                    }

                    // Agregar hijo al arbol
                    model.insertNodeInto(childNode, parentNode, i);

                    // Agregar siguiente nivel de hijos
                    asignNodes(model, childNode, file.listFiles()[i]);

                }

            }

        }

    }
    
    // Borrar nodo seleccionado
    public void deleteNode() {

        // Borrar la ruta especificada
        try {
            Files.walk(selectedFile.toPath())
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        selectedFile = null;
        asignNodes();

    }
    
    // Verificar si una ruta contiene una función
    public boolean isNode(File file) {

        for (int i = 0; i < file.listFiles().length; i++) {
            if (file.listFiles()[i].isFile()) {
                return true;
            }
        }

        return false;

    }

    // Crear elemento del Arbol de funciones
    public void peressedNode(MouseEvent evt) {

        treePressedX = evt.getX() - principalPanel.leftPane.getWidth() - 10;
        treePressedY = evt.getY() - 27;

        try {

            try {

                // Obtener nodo por localización
                TreePath tp = principalPanel.jTree1.getPathForLocation(evt.getX(), evt.getY());
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tp.getLastPathComponent();

                // Si se presionó en click izquierdo
                if (evt.getButton() == MouseEvent.BUTTON1) {

                    // Si la ruta es de una función
                    if (isNode(((File) ((Object[]) node.getUserObject())[2]))) {
                        
                        // Crear función a arrastrar con la ruta obtenida
                        Function f = new Function(principalPanel.scilab, ((File) ((Object[]) node.getUserObject())[2]));
                        
                        principalPanel.getContentPanelController().setDraggerFunction(new FunctionBox(principalPanel, f));
                        principalPanel.containerBoxPane.add(principalPanel.getContentPanelController().getDraggerFunction(), 0).setLocation(
                                principalPanel.getContentPanelController().getMouseX() - principalPanel.getContentPanelController().getDraggerFunction().getBoxWidth() / 2,
                                principalPanel.getContentPanelController().getMouseY() - principalPanel.getContentPanelController().getDraggerFunction().getBoxHeight() / 2);
                        
                        // crear nodo de arrastre
                        draggerNode = new JTree();
                        draggerNode.setOpaque(false);
                        draggerNode.setFont(TEXT_FONT);
                        draggerNode.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(principalPanel.getContentPanelController().getDraggerFunction().getFunction().getFunctionModel().getNodeName())));
                        principalPanel.leftPane.add(draggerNode, 0).setBounds(evt.getX() + 8, evt.getY() + 8, draggerNode.getPreferredSize().width, draggerNode.getPreferredSize().height);

                        // Asignar propiedades de la función en el panel derecho
                        principalPanel.getContentPanelController().getDraggerFunction().getFunctionBoxController().asignDataFunction();

                    }

                    // Si se presionó en click derecho
                } else if (evt.getButton() == MouseEvent.BUTTON3) {

                    // Mostrar menu del arbol
                    selectedFile = ((File) ((Object[]) node.getUserObject())[2]);
                    principalPanel.treeMenu.show(principalPanel.leftPane, evt.getX(), evt.getY());

                }

            } catch (java.lang.ClassCastException ex) {
            }

        } catch (java.lang.NullPointerException ex) {
        }

    }

    // Arrastrar elemento en el arbol de funciones
    public void draggerNode(MouseEvent evt) {

        if (principalPanel.getContentPanelController().getDraggerFunction() != null) {

            // Cambiar la posición de la caja en relación al mouse
            principalPanel.getContentPanelController().getDraggerFunction().setLocation(
                    principalPanel.getContentPanelController().getMouseX() - principalPanel.getContentPanelController().getDraggerFunction().getBoxWidth() / 2,
                    principalPanel.getContentPanelController().getMouseY() - principalPanel.getContentPanelController().getDraggerFunction().getBoxHeight() / 2);
            draggerNode.setLocation(evt.getX() + 8, evt.getY() + 8);

        }

    }

    // Agregar elemento al Arbol de funciones
    public void releasedNode() {

        if (draggerNode != null) {

            principalPanel.leftPane.remove(draggerNode);

            if (principalPanel.getContentPanelController().getMouseX() > 0 && principalPanel.getContentPanelController().getMouseX() < principalPanel.containerBoxPane.getWidth()
                    && principalPanel.getContentPanelController().getMouseY() > 0 && principalPanel.getContentPanelController().getMouseY() < principalPanel.containerBoxPane.getHeight()) {

                // Reacomodar caja si está afuera de los limites
                try {
                    if (principalPanel.getContentPanelController().getDraggerFunction().getLocation().x < 0) {
                        principalPanel.getContentPanelController().getDraggerFunction().setLocation(
                                0,
                                principalPanel.getContentPanelController().getDraggerFunction().getLocation().y);
                    }
                    if (principalPanel.getContentPanelController().getDraggerFunction().getLocation().x > principalPanel.getContentPanelController().getDraggerFunction().getParent().getWidth() - principalPanel.getContentPanelController().getDraggerFunction().getWidth()) {
                        principalPanel.getContentPanelController().getDraggerFunction().setLocation(
                                principalPanel.getContentPanelController().getDraggerFunction().getParent().getWidth() - principalPanel.getContentPanelController().getDraggerFunction().getWidth(),
                                principalPanel.getContentPanelController().getDraggerFunction().getLocation().y);
                    }
                    if (principalPanel.getContentPanelController().getDraggerFunction().getLocation().y < 0) {
                        principalPanel.getContentPanelController().getDraggerFunction().setLocation(
                                principalPanel.getContentPanelController().getDraggerFunction().getLocation().x,
                                0);
                    }
                    if (principalPanel.getContentPanelController().getDraggerFunction().getLocation().y > principalPanel.getContentPanelController().getDraggerFunction().getParent().getHeight() - principalPanel.getContentPanelController().getDraggerFunction().getHeight()) {
                        principalPanel.getContentPanelController().getDraggerFunction().setLocation(
                                principalPanel.getContentPanelController().getDraggerFunction().getLocation().x,
                                principalPanel.getContentPanelController().getDraggerFunction().getParent().getHeight() - principalPanel.getContentPanelController().getDraggerFunction().getHeight());
                    }

                } catch (java.lang.NullPointerException ex) {
                }

            } else {

                principalPanel.containerBoxPane.remove(principalPanel.getContentPanelController().getDraggerFunction());

            }

            // Desasignar la caja y el nodo que se está arrastrando
            principalPanel.getContentPanelController().setDraggerFunction(null);
            draggerNode = null;

            principalPanel.updateUI();

        }

    }

    // Crear elemento del Arbol de funciones desde una caja de función
    public void pressedExternalNode() {

        // crear nodo de arrastre
        draggerNode = new JTree();
        draggerNode.setOpaque(false);
        draggerNode.setFont(TEXT_FONT);
        draggerNode.setModel(new DefaultTreeModel(new DefaultMutableTreeNode(principalPanel.getContentPanelController().getDraggerFunction().getFunction().getFunctionModel().getNodeName())));
        principalPanel.leftPane.add(draggerNode, 0).setBounds(
                principalPanel.getContentPanelController().getMouseX() + principalPanel.leftPane.getWidth() + 10,
                principalPanel.getContentPanelController().getMouseY() + 25,
                principalPanel.getTreeController().draggerNode.getPreferredSize().width,
                principalPanel.getTreeController().draggerNode.getPreferredSize().height);

    }

    // Arrastrar elemento en el arbol de funciones desde una caja de función
    public void draggerExternalNode() {

        if (draggerNode != null) {
            draggerNode.setLocation(principalPanel.getContentPanelController().getMouseX() + principalPanel.leftPane.getWidth() + 10, principalPanel.getContentPanelController().getMouseY() + 25);
        }

    }

    // Agregar elemento al Arbol de funciones desde una caja de función
    public void releasedExternalNode(MouseEvent evt, FunctionBox functionBox) {

        if (draggerNode != null) {

            try {

                TreePath tp = principalPanel.jTree1.getPathForLocation(principalPanel.getContentPanelController().getMouseX() + principalPanel.leftPane.getWidth() + 10, principalPanel.getContentPanelController().getMouseY() + 20);
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tp.getLastPathComponent();

                try {

                    try {

                        // Si la ruta ya existe
                        if (new File(((File) ((Object[]) node.getUserObject())[2]).getCanonicalFile() + "\\" + functionBox.getFunction().getFunctionModel().getNodeName().trim().replace("\\", "")).exists()) {

                            new Notification(principalPanel.containerBoxPane, "Función repetida", "La función a guardar ya existe en la ruta especificada").show();

                            // Si la ruta no es una función
                        } else if (!isNode((File) ((Object[]) node.getUserObject())[2])) {

                            functionBox.getFunction().functionRepository.saveFunctionModel(functionBox.getFunction().getFunctionModel(), ((File) ((Object[]) node.getUserObject())[2]).getCanonicalFile());
                            asignNodes();

                        }

                    } catch (IOException ex) {
                    }

                } catch (java.lang.ClassCastException ex) {
                }

            } catch (java.lang.NullPointerException ex) {
            }

            principalPanel.leftPane.remove(draggerNode);
            draggerNode = null;

        }

        principalPanel.updateUI();

    }

}
