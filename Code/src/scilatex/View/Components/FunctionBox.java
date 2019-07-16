package scilatex.View.Components;

import scilatex.View.Windows.PrincipalPanel;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import scilatex.Data.Constants.Constants;
import scilatex.Logic.Objects.Function;
import static scilatex.Data.Constants.Constants.BORDER_COLOR;
import static scilatex.Data.Constants.Constants.COLOR_TEXT;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_ASIGN_PARAMETER;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_EMPTY_PARAMETER;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_VIEW_PARAMETER;
import static scilatex.Data.Constants.Constants.SIZE_ICON_BUTTON;
import static scilatex.Data.Constants.Constants.TEXT_FONT;
import scilatex.Logic.Controllers.ViewControllers.FunctionBoxController;

// Clase que gestiona la caja que contiene la función
public final class FunctionBox extends javax.swing.JPanel {

    // Controladores graficos
    private FunctionBoxController functionBoxController;    // Controlador de la caja de función
    
    // Componentes graficos
    private PrincipalPanel principalPanel;                  // panel contenedor
    private JButton[] viewButtons;                          // Botones para mostrar los tipos de vista de la función
    private JButton[] parametersButtons;                    // Botones que identifican cada parametro
    
    // Objetos
    private Function function;                              // Función que contiene la caja

    // Iniciar caja con un objeto función
    public FunctionBox(PrincipalPanel panelPrincipal, Function function) {
        
        initComponents();
        this.principalPanel = panelPrincipal;
        this.function = function;

        functionBoxController = new FunctionBoxController(this);
        functionBoxController.outFunction = false;

        // Asignar botones
        asignViewlButtons();
        asignParameterslButtons();

        // Asignar Sliders
        asignDecimalSlider();

        // AsignarFunción
        functionBoxController.asignFunction();

        // Asignar menus del click derecho
        asignPopupmenus();

        // Asignar eventos de la caja
        setEvents();

        // Asignar corchete al pnael de ingresar parametro en la función
        jLabel2.setIcon(principalPanel.laTeX.getImage("\\textcolor{" + Constants.toString(BORDER_COLOR) + "}{\\underbrace{\\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ \\ }}", 25));

    }

    // Asginar los botones verticales
    public void asignViewlButtons() {

        // Instanciar botones 
        viewButtons = new JButton[4];
        verticalPanel.removeAll();      // Eliminar botones actuales

        // Aginar distribucion de linea vertical
        verticalPanel.setLayout(new javax.swing.BoxLayout(verticalPanel, javax.swing.BoxLayout.PAGE_AXIS));

        // Instanciar y agregar botones
        for (int i = 0; i < viewButtons.length; i++) {

            viewButtons[i] = new JButton();
            viewButtons[i].setContentAreaFilled(false);
            viewButtons[i].setFocusable(false);

            // Agregar botones
            verticalPanel.add(viewButtons[i]);

        }

        // Asignar texto LaTeX
        viewButtons[0].setIcon(principalPanel.laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_VIEW_PARAMETER) + "}{[F]}", SIZE_ICON_BUTTON));
        viewButtons[1].setIcon(principalPanel.laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_VIEW_PARAMETER) + "}{[E]}", SIZE_ICON_BUTTON));
        viewButtons[2].setIcon(principalPanel.laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_VIEW_PARAMETER) + "}{[S_l]}", SIZE_ICON_BUTTON));
        viewButtons[3].setIcon(principalPanel.laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_VIEW_PARAMETER) + "}{[S_s]}", SIZE_ICON_BUTTON));

        // Asignar texto del acercaminto del mouse
        viewButtons[0].setToolTipText("F");
        viewButtons[1].setToolTipText("E");
        viewButtons[2].setToolTipText("S_l");
        viewButtons[3].setToolTipText("S_s");

        // Asignar dimensiones del boton
        for (int i = 0; i < viewButtons.length; i++) {

            Dimension dimension = new Dimension(viewButtons[i].getPreferredSize().width - 10, viewButtons[i].getIcon().getIconHeight());
            viewButtons[i].setSize(dimension);
            viewButtons[i].setMinimumSize(dimension);
            viewButtons[i].setMaximumSize(dimension);
            viewButtons[i].setPreferredSize(dimension);

        }

        // Asignar evectos de presionar el boton
        viewButtons[0].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFunctionBoxController().setViewOption(1);
            }
        });
        viewButtons[1].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFunctionBoxController().setViewOption(2);
            }
        });
        viewButtons[2].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFunctionBoxController().setViewOption(3);
            }
        });
        viewButtons[3].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getFunctionBoxController().setViewOption(4);
            }
        });

        // ASignar eventos al mover el muse
        viewButtons[0].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                principalPanel.getContentPanelController().setMouseX(getLocation().x + evt.getComponent().getParent().getX() + evt.getComponent().getX() + evt.getX());
                principalPanel.getContentPanelController().setMouseY(getLocation().y + evt.getComponent().getParent().getY() + evt.getComponent().getY() + evt.getY());
                getPrincipalPanel().getContentPanelController().detectAllShowComplementsAndHighlight();
            }
        });
        viewButtons[1].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                principalPanel.getContentPanelController().setMouseX(getLocation().x + evt.getComponent().getParent().getX() + evt.getComponent().getX() + evt.getX());
                principalPanel.getContentPanelController().setMouseY(getLocation().y + evt.getComponent().getParent().getY() + evt.getComponent().getY() + evt.getY());
                getPrincipalPanel().getContentPanelController().detectAllShowComplementsAndHighlight();
            }
        });
        viewButtons[2].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                principalPanel.getContentPanelController().setMouseX(getLocation().x + evt.getComponent().getParent().getX() + evt.getComponent().getX() + evt.getX());
                principalPanel.getContentPanelController().setMouseY(getLocation().y + evt.getComponent().getParent().getY() + evt.getComponent().getY() + evt.getY());
                getPrincipalPanel().getContentPanelController().detectAllShowComplementsAndHighlight();
            }
        });
        viewButtons[3].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                principalPanel.getContentPanelController().setMouseX(getLocation().x + evt.getComponent().getParent().getX() + evt.getComponent().getX() + evt.getX());
                principalPanel.getContentPanelController().setMouseY(getLocation().y + evt.getComponent().getParent().getY() + evt.getComponent().getY() + evt.getY());
                getPrincipalPanel().getContentPanelController().detectAllShowComplementsAndHighlight();
            }
        });

    }

    // Asignar botones horizontales
    public void asignParameterslButtons() {

        String[] names = function.getAllShowNamesParameters();
        parametersButtons = new JButton[names.length];  // Instanciar arreglo de botones 
        horizontalPanel.removeAll();    // Eliminar botones actuales

        // Aginar distribucion de linea horizontal
        horizontalPanel.setLayout(new javax.swing.BoxLayout(horizontalPanel, javax.swing.BoxLayout.LINE_AXIS));

        // Instanciar y agregar botones 
        for (int i = 0; i < parametersButtons.length; i++) {

            // instanciar boton
            parametersButtons[i] = new JButton();
            parametersButtons[i].setContentAreaFilled(false);
            parametersButtons[i].setFocusable(false);

            // Asignar texto al boton
            // Si el parametro está asignado pintar de negro
            // Si el parametro no está asignado pintar de azul
            if (function.getParameter(names[i]) == null) {
                parametersButtons[i].setIcon(principalPanel.laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_EMPTY_PARAMETER) + "}{[" + names[i] + "]}", SIZE_ICON_BUTTON));
                parametersButtons[i].setToolTipText(names[i]);
            } else {
                parametersButtons[i].setIcon(principalPanel.laTeX.getImage("\\textcolor{" + Constants.toString(LATEX_COLOR_ASIGN_PARAMETER) + "}{[" + names[i] + "]}", SIZE_ICON_BUTTON));
                parametersButtons[i].setToolTipText(names[i]);
            }

            // Asignar dimensiones del boton
            Dimension dimension = new Dimension(parametersButtons[i].getIcon().getIconWidth() - 5, parametersButtons[i].getPreferredSize().height + 10);
            parametersButtons[i].setSize(dimension);
            parametersButtons[i].setMinimumSize(dimension);
            parametersButtons[i].setMaximumSize(dimension);
            parametersButtons[i].setPreferredSize(dimension);

            // Añadir el botón al panel de botones
            horizontalPanel.add(parametersButtons[i]);

            // Asginar eventos al presionar el boton
            parametersButtons[i].addMouseListener(new java.awt.event.MouseAdapter() {
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    parametersButtonsMousePressedEvent(evt);
                }

            });

            // Asignar eventos al mover el mouse
            parametersButtons[i].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
                public void mouseDragged(java.awt.event.MouseEvent evt) {
                    parametersButtonsMouseDraggedEvent(evt);
                }

                public void mouseMoved(java.awt.event.MouseEvent evt) {
                    parametersButtonsMouseMovedEvent(evt);
                }
            });

        }

    }

    // Evento al presionar un boton de parametro
    private void parametersButtonsMousePressedEvent(MouseEvent evt) {

        // Asignar propiedades de la función en el panel derecho
        functionBoxController.asignDataFunction();

        // Si se presionó en click izquierdo
        if (evt.getButton() == MouseEvent.BUTTON1) {
            
            // si el parametro está asignado
            if (function.getParameter(((JButton) evt.getComponent()).getToolTipText()) != null) {

                // Sacar parametro
                functionBoxController.takeOutParamater(evt);

                // Si el parametro no esta asignado
            } else {

                // Mostrar panel para ingresar una constante en el parametro especificado
                functionBoxController.showInsertParameterConstant(((JButton) evt.getComponent()));

            }

            // Si se presionó en click derecho
        } else if (evt.getButton() == MouseEvent.BUTTON3) {

            // Mostrar menu de un parametro
            functionBoxController.showParameterMenu(evt);

        }

    }
    
    // Evento al arrastrar el mouse sobre un boton de parametro
    private void parametersButtonsMouseDraggedEvent(MouseEvent evt) {

        // Asignar coordenadas del mouse en relación al panel contenedor
        principalPanel.getContentPanelController().setMouseX(getLocation().x + evt.getComponent().getParent().getX() + evt.getComponent().getX() + evt.getX());
        principalPanel.getContentPanelController().setMouseY(getLocation().y + evt.getComponent().getParent().getY() + evt.getComponent().getY() + evt.getY());

    }

    // Evento al mover el mouse sobre un boton de parametro
    private void parametersButtonsMouseMovedEvent(MouseEvent evt) {

        // Asignar coordenadas del mouse en relación al panel contenedor
        principalPanel.getContentPanelController().setMouseX(getLocation().x + evt.getComponent().getParent().getX() + evt.getComponent().getX() + evt.getX());
        principalPanel.getContentPanelController().setMouseY(getLocation().y + evt.getComponent().getParent().getY() + evt.getComponent().getY() + evt.getY());

        // Evento del panel principal al mover el mouse
        principalPanel.getContentPanelController().detectAllShowComplementsAndHighlight();

    }

    // Asignar evento al cambiar de numero de decimales en el slider
    public void asignDecimalSlider() {
        decimalSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                getFunctionBoxController().changeDecimalSize();
            }
        });
    }

    // Asignar menus del click derecho
    public void asignPopupmenus() {

        boxPaneMenu.removeAll();

        // Ingresar item para ingresar una constante
        boxPaneMenu.add(new JMenuItem(new AbstractAction("Editar") {
            public void actionPerformed(ActionEvent evt) {

                // Mostrar panel para editar la función
                getPrincipalPanel().getEditFunctionController().showEditFunction(getFunctionBoxController().functionBox);

            }
        }));

        // Ingresar item para ingresar una función
        boxPaneMenu.add(new JMenuItem(new AbstractAction("Eliminar") {
            public void actionPerformed(ActionEvent e) {

                // Borrar caja de función
                getFunctionBoxController().deleteFunction();

            }
        }));
        
        boxPaneMenu.addSeparator();

        // Ingresar item para cambiar nombre de la función
        boxPaneMenu.add(new JMenuItem(new AbstractAction("Cambiar nombre de la función") {
            public void actionPerformed(ActionEvent e) {
                getFunctionBoxController().changeFunctionName();
            }
        }));
        
        boxPaneMenu.addSeparator();

        // Ingresar item para mostrar u ocultar funcion
        if (function.getFunctionModel().getShowFunctionInScreen() == 0) {

            boxPaneMenu.add(new JMenuItem(new AbstractAction("Mostrar función en la vista") {
                public void actionPerformed(ActionEvent e) {
                    getFunctionBoxController().setShowFunctionInScreen(1);
                }
            }));

        } else {

            boxPaneMenu.add(new JMenuItem(new AbstractAction("Ocultar función en la vista") {
                public void actionPerformed(ActionEvent e) {
                    getFunctionBoxController().setShowFunctionInScreen(0);
                }
            }));

        }
        
        // Ingresar item para mostrar u ocultar paramatros
        if (function.getFunctionModel().getShowParametersInScreen() == 0) {

            boxPaneMenu.add(new JMenuItem(new AbstractAction("Mostrar parametros en la vista") {
                public void actionPerformed(ActionEvent e) {
                    getFunctionBoxController().setShowParametersInScreen(1);
                }
            }));

        } else {

            boxPaneMenu.add(new JMenuItem(new AbstractAction("Ocultar parametros en la vista") {
                public void actionPerformed(ActionEvent e) {
                    getFunctionBoxController().setShowParametersInScreen(0);
                }
            }));

        }

        boxPaneMenu.addSeparator();
        
        // Ocultar parametros asignados
        boxPaneMenu.add(new JMenuItem(new AbstractAction("Ocultar parametros asignados") {
            public void actionPerformed(ActionEvent e) {
                getFunctionBoxController().hideAsignParamaters();
            }
        }));

        // Ocultar parametros asignados
        boxPaneMenu.add(new JMenuItem(new AbstractAction("Mostrar parametros asignados") {
            public void actionPerformed(ActionEvent e) {
                getFunctionBoxController().showAsignParamaters();
            }
        }));

    }

    // Asignar menus del click derecho
    public void asignParamaterPopupmenus() {

        boxParameterMenu.removeAll();

        // Ingresar item para ingresar una constante
        boxParameterMenu.add(new JMenuItem(new AbstractAction("Ingresar constante") {
            public void actionPerformed(ActionEvent evt) {

                // Mostrar panel para ingresar una constante en el parametro especificado
                getFunctionBoxController().showInsertParameterConstant(getFunctionBoxController().menuParameterButton);

            }
        }));
        
        // Ingresar item para cambiar vaciar el parametro 
        if (function.getParameter(functionBoxController.menuParameterButton.getToolTipText()) != null) {
            boxParameterMenu.add(new JMenuItem(new AbstractAction("Vaciar parametro") {
                public void actionPerformed(ActionEvent evt) {

                    // Vaciar parametro seleccionado
                    getFunctionBoxController().emptyParameter();

                }
            }));
        }

        // Ingresar item para cambiar ocultar el parametro 
        if (function.getParameter(functionBoxController.menuParameterButton.getToolTipText()) != null) {
            boxParameterMenu.add(new JMenuItem(new AbstractAction("Ocultar parametro") {
                public void actionPerformed(ActionEvent evt) {

                    // Ocultar parametro seleccionado
                    getFunctionBoxController().hideParameter();

                }
            }));
        }
        
        boxParameterMenu.addSeparator();
        
        // Ingresar item para cambiar nombre del parametro 
        boxParameterMenu.add(new JMenuItem(new AbstractAction("Cambiar nombre del parametro") {
            public void actionPerformed(ActionEvent evt) {
                
                // Cambiar nombre del paramatro seleccionado
                getFunctionBoxController().changeParameterName();
                
            }
        }));
        
        // Ingresar item para asignar indicador al paramatro
        boxParameterMenu.add(new JMenuItem(new AbstractAction("Asignar indicador") {
            public void actionPerformed(ActionEvent evt) {
                
                // Asignar indicador del paramatro seleccionado
                getFunctionBoxController().asignParameterIndicator();
                
            }
        }));

        boxParameterMenu.addSeparator();
        
        // Ingresar item para mostrar parametros hijos
        if (function.getParameter(functionBoxController.menuParameterButton.getToolTipText()) != null) {
            boxParameterMenu.add(new JMenuItem(new AbstractAction("Mostrar parametros hijos") {
                public void actionPerformed(ActionEvent evt) {

                    getFunctionBoxController().setShowChildParamaters(1);

                }
            }));
        }

        // Ingresar item para ocultar parametros hijos
        if (function.getParameter(functionBoxController.menuParameterButton.getToolTipText()) != null) {
            boxParameterMenu.add(new JMenuItem(new AbstractAction("Ocultar parametros hijos") {
                public void actionPerformed(ActionEvent evt) {

                    getFunctionBoxController().setShowChildParamaters(0);

                }
            }));
        }

        // Ingresar item para mostrar parametros padre
        boxParameterMenu.add(new JMenuItem(new AbstractAction("Mostrar parametro padre") {
            public void actionPerformed(ActionEvent evt) {

                // Mostrar parametros padre de la función
                getFunctionBoxController().setShowParentParamaters(1);

            }
        }));

        // Ingresar item para ocultar parametros padre
        boxParameterMenu.add(new JMenuItem(new AbstractAction("Ocultar parametro padre") {
            public void actionPerformed(ActionEvent evt) {

                // Ocultar parametros padre de la función
                getFunctionBoxController().setShowParentParamaters(0);

            }
        }));

    }

    // Obtener el ancho de la caja
    public int getBoxWidth() {

        int width = function.getReplaceParametersImage("").getIconWidth() + verticalPanel.getPreferredSize().width;
        int horizontalPanelSize = 0;

        for (int i = 0; i < horizontalPanel.getComponents().length; i++) {
            horizontalPanelSize += horizontalPanel.getComponents()[i].getPreferredSize().width;
        }

        if (width < horizontalPanelSize + verticalPanel.getPreferredSize().width) {
            width = horizontalPanelSize + verticalPanel.getPreferredSize().width;
        }

        if (width < decimalSlider.getPreferredSize().width + verticalPanel.getPreferredSize().width) {
            width = decimalSlider.getPreferredSize().width + verticalPanel.getPreferredSize().width;
        }

        return width + 20;

    }

    // Obtener el alto de la caja
    public int getBoxHeight() {

        int height = function.getReplaceParametersImage("").getIconHeight() + horizontalPanel.getPreferredSize().height + jPanel2.getPreferredSize().height;
        int verticalPanelSize = 0;

        for (int i = 0; i < verticalPanel.getComponents().length; i++) {
            verticalPanelSize += verticalPanel.getComponents()[i].getPreferredSize().height;
        }

        if (height < verticalPanelSize + horizontalPanel.getPreferredSize().height + jPanel2.getPreferredSize().height + 2) {
            height = verticalPanelSize + horizontalPanel.getPreferredSize().height + jPanel2.getPreferredSize().height + 2;
        }

        return height;

    }

    // Asignar eventos de la caja de la función
    public void setEvents() {

        // Eventos al presionar y soltar el mouse
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mousePressedEvent(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mouseReleasedEvent(evt);
            }
        });

        // Eventos al presionar y arrastrar el mouse
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                mouseDraggedEvent(evt);
            }

            public void mouseMoved(java.awt.event.MouseEvent evt) {
                mouseMovedEvent(evt);
            }
        });

    }

    // Evento al presionar el mouse en la caja de la función
    public void mousePressedEvent(MouseEvent evt) {

        // Sobreponer objeto
        principalPanel.containerBoxPane.add(this, 0);

        // Asignar corrdenadas del mouse en relación al panel principal
        functionBoxController.pressedX = evt.getX();
        functionBoxController.pressedY = evt.getY();

        // Activar y desactivar cambio de tamaño
        if (evt.getX() > jPanel5.getLocation().x + jPanel5.getWidth() - 10 && evt.getY() > jPanel5.getLocation().y + jPanel5.getHeight() - 10) {
            functionBoxController.resize = true;
            functionBoxController.widthY = function.getFunctionModel().getLaTeXSize();
        } else {
            functionBoxController.resize = false;
        }

        // Asignar caja que se va a arrastrar
        principalPanel.getContentPanelController().setDraggerFunction(this);

        // crear nodo de arrastre
        principalPanel.getTreeController().pressedExternalNode();

        // Asignar propiedades de la función en el panel derecho
        functionBoxController.asignDataFunction();

        // Si se presionó en click derecho
        if (evt.getButton() == MouseEvent.BUTTON3) {

            // Mostrar menu del click derecho de la caja de función
            functionBoxController.showContentPanelMenu(evt);

        }

        // Evento del panel principal al mover el mouse
        principalPanel.getContentPanelController().detectAllShowComplementsAndHighlight();

    }
    
    // Evento al soltar el mouse en la caja de la función
    public void mouseReleasedEvent(MouseEvent evt) {

        // Si se soltó en click izquierdo
        if (evt.getButton() == MouseEvent.BUTTON1) {
            
            functionBoxController.resize = false;
            
            functionBoxController.insertFunctionInParamater();

            // Evento del panel principal al mover el mouse
            principalPanel.getContentPanelController().detectAllShowComplementsAndHighlight();

            // Reacomodar caja si está afuera de los limites
            try {
                if (this.getLocation().x < 0) {
                    this.setLocation(0, this.getLocation().y);
                }
                if (this.getLocation().x > this.getParent().getWidth() - this.getWidth()) {
                    this.setLocation(this.getParent().getWidth() - this.getWidth(), this.getLocation().y);
                }
                if (this.getLocation().y < 0) {
                    this.setLocation(this.getLocation().x, 0);
                }
                if (this.getLocation().y > this.getParent().getHeight() - this.getHeight()) {
                    this.setLocation(this.getLocation().x, this.getParent().getHeight() - this.getHeight());
                }

            } catch (java.lang.NullPointerException ex) {
            }

            // Agregar elemento al Arbol de funciones
            principalPanel.getTreeController().releasedExternalNode(evt, this);
            
        }

    }

    // Evento al arrastrar el mouse
    public void mouseDraggedEvent(MouseEvent evt) {

        // Asignar coordenadas del mouse en relación al panel contenedor
        principalPanel.getContentPanelController().setMouseX(getLocation().x + evt.getX());
        principalPanel.getContentPanelController().setMouseY(getLocation().y + evt.getY());

        // Cambiar tamaño
        if (functionBoxController.resize == true) {

            // Si el tamaño es mayor al minimo cambiar tamaño
            if (functionBoxController.widthY + (evt.getY() - functionBoxController.pressedY) > 0) {
                function.getFunctionModel().setLaTeXSize(functionBoxController.widthY + (evt.getY() - functionBoxController.pressedY));
                functionBoxController.asignFunction();
            }

            // Cambiar localización
        } else {

            // Cambiar la posición de la caja en relación al mouse
            int x = getLocation().x;
            int y = getLocation().y;
            setLocation(getLocation().x + evt.getX() - functionBoxController.pressedX, getLocation().y + evt.getY() - functionBoxController.pressedY);
            insertParameterConstantPanel.setLocation(insertParameterConstantPanel.getLocation().x + getLocation().x - x, insertParameterConstantPanel.getLocation().y + getLocation().y - y);

            // Evento del panel principal al mover el mouse
            principalPanel.getContentPanelController().detectAllShowComplementsAndHighlight();

        }

        principalPanel.getTreeController().draggerExternalNode();

    }

    // Evento al mover el mouse
    public void mouseMovedEvent(MouseEvent evt) {

        // Asignar coordenadas del mouse en relación al panel contenedor
        principalPanel.getContentPanelController().setMouseX(getLocation().x + evt.getX());
        principalPanel.getContentPanelController().setMouseY(getLocation().y + evt.getY());

        // Cambiar puntero del mouse
        if (evt.getX() > jPanel5.getLocation().x + jPanel5.getWidth() - 10 && evt.getY() > jPanel5.getLocation().y + jPanel5.getHeight() - 10) {
            jPanel5.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));
        } else {
            jPanel5.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        // Evento del panel principal al mover el mouse
        principalPanel.getContentPanelController().detectAllShowComplementsAndHighlight();

    }

    // Pasar al siguiente parametro vacio al ingresar un parametro en la función
    public void nextInsertParameterFocus() {

        for (int i = 0; i < parametersButtons.length - 1; i++) {
            if (parametersButtons[i].getToolTipText().equals(functionBoxController.insertParameterConstantButton.getToolTipText())) {

                for (int j = i + 1; j < parametersButtons.length; j++) {
                    if (function.getParameter(parametersButtons[j].getToolTipText()) == null) {
                        functionBoxController.showInsertParameterConstant(parametersButtons[j]);
                        break;
                    }
                }
                break;

            }
        }

    }

    public FunctionBoxController getFunctionBoxController() {
        return functionBoxController;
    }

    public void setFunctionBoxController(FunctionBoxController functionBoxController) {
        this.functionBoxController = functionBoxController;
    }

    public PrincipalPanel getPrincipalPanel() {
        return principalPanel;
    }

    public void setPrincipalPanel(PrincipalPanel principalPanel) {
        this.principalPanel = principalPanel;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public JButton[] getViewButtons() {
        return viewButtons;
    }

    public void setViewButtons(JButton[] viewButtons) {
        this.viewButtons = viewButtons;
    }

    public JButton[] getParametersButtons() {
        return parametersButtons;
    }

    public void setParametersButtons(JButton[] parametersButtons) {
        this.parametersButtons = parametersButtons;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boxPaneMenu = new javax.swing.JPopupMenu();
        insertParameterConstantPanel = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        boxParameterMenu = new javax.swing.JPopupMenu();
        horizontalPanel = new javax.swing.JPanel();
        verticalPanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        decimalSlider = new javax.swing.JSlider(){
            protected void paintComponent(Graphics g) {
                //super.paintComponent(g);
                repaint();
                int x = 2 + (this.getValue()-this.getMinimum())*(this.getWidth() -11 - 4)/(this.getMaximum()-this.getMinimum());
                this.getValue();
                g.setColor(BORDER_COLOR);
                g.fillRect(7, 10, this.getWidth() - 14, 3);
                g.fillRect(x, 1, 11, 15);

                int x1[] = {x,x+11,(x+5)};
                int y1[] = {16,16,22};
                g.fillPolygon(x1, y1, 3);
            }
        };

        insertParameterConstantPanel.setBackground(new java.awt.Color(255, 255, 255));
        insertParameterConstantPanel.setOpaque(false);
        insertParameterConstantPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                insertParameterConstantPanelMouseDragged(evt);
            }
        });
        insertParameterConstantPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                insertParameterConstantPanelMousePressed(evt);
            }
        });

        jLabel24.setFont(TEXT_FONT);
        jLabel24.setForeground(COLOR_TEXT);
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Instrucción Scilab");

        jTextField13.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField13.setOpaque(false);
        jTextField13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField13FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField13FocusLost(evt);
            }
        });
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField13KeyPressed(evt);
            }
        });

        jLabel25.setFont(TEXT_FONT);
        jLabel25.setForeground(COLOR_TEXT);
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Vista LaTeX");

        jTextField14.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField14.setOpaque(false);
        jTextField14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField14FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField14FocusLost(evt);
            }
        });
        jTextField14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField14ActionPerformed(evt);
            }
        });
        jTextField14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField14KeyPressed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout insertParameterConstantPanelLayout = new javax.swing.GroupLayout(insertParameterConstantPanel);
        insertParameterConstantPanel.setLayout(insertParameterConstantPanelLayout);
        insertParameterConstantPanelLayout.setHorizontalGroup(
            insertParameterConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertParameterConstantPanelLayout.createSequentialGroup()
                .addGroup(insertParameterConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField14)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(insertParameterConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField13)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        insertParameterConstantPanelLayout.setVerticalGroup(
            insertParameterConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertParameterConstantPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(insertParameterConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(insertParameterConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(0, 0));

        horizontalPanel.setMaximumSize(new java.awt.Dimension(2147483647, 26));
        horizontalPanel.setMinimumSize(new java.awt.Dimension(0, 26));
        horizontalPanel.setOpaque(false);
        horizontalPanel.setPreferredSize(new java.awt.Dimension(0, 35));
        horizontalPanel.setLayout(new javax.swing.BoxLayout(horizontalPanel, javax.swing.BoxLayout.LINE_AXIS));

        verticalPanel.setMaximumSize(new java.awt.Dimension(39, 69));
        verticalPanel.setMinimumSize(new java.awt.Dimension(39, 69));
        verticalPanel.setOpaque(false);
        verticalPanel.setPreferredSize(new java.awt.Dimension(39, 73));
        verticalPanel.setLayout(new javax.swing.BoxLayout(verticalPanel, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel5.setOpaque(false);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );

        jPanel2.setMaximumSize(new java.awt.Dimension(175, 26));
        jPanel2.setMinimumSize(new java.awt.Dimension(175, 26));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(185, 26));

        decimalSlider.setForeground(new java.awt.Color(0, 0, 0));
        decimalSlider.setMaximum(25);
        decimalSlider.setPaintLabels(true);
        decimalSlider.setPaintTicks(true);
        decimalSlider.setSnapToTicks(true);
        decimalSlider.setToolTipText("");
        decimalSlider.setValue(4);
        decimalSlider.setOpaque(false);
        decimalSlider.setPreferredSize(new java.awt.Dimension(118, 31));
        decimalSlider.setRequestFocusEnabled(false);
        decimalSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                decimalSliderMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(decimalSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(decimalSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(verticalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(85, 85, 85))
                    .addComponent(horizontalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(horizontalPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(verticalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void decimalSliderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decimalSliderMousePressed

        functionBoxController.asignDataFunction();

    }//GEN-LAST:event_decimalSliderMousePressed

    private void jTextField13FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField13FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13FocusGained

    private void jTextField13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField13FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13FocusLost

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextField13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyPressed

        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Insertar constante si es posible
            functionBoxController.insertParameterConstant();

            // Ceder foco al siguiente parametro
            nextInsertParameterFocus();

            // Evento del panel principal al mover el mouse
            principalPanel.getContentPanelController().detectAllShowComplementsAndHighlight();

        }

    }//GEN-LAST:event_jTextField13KeyPressed

    private void jTextField14FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14FocusGained

    private void jTextField14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField14FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14FocusLost

    private void jTextField14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField14ActionPerformed

    private void jTextField14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField14KeyPressed

        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Insertar constante si es posible
            functionBoxController.insertParameterConstant();

            // Ceder foco al siguiente parametro
            nextInsertParameterFocus();

            // Evento del panel principal al mover el mouse
            principalPanel.getContentPanelController().detectAllShowComplementsAndHighlight();

        }

    }//GEN-LAST:event_jTextField14KeyPressed

    private void insertParameterConstantPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertParameterConstantPanelMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_insertParameterConstantPanelMouseDragged

    private void insertParameterConstantPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertParameterConstantPanelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_insertParameterConstantPanelMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPopupMenu boxPaneMenu;
    public javax.swing.JPopupMenu boxParameterMenu;
    public javax.swing.JSlider decimalSlider;
    private javax.swing.JPanel horizontalPanel;
    public javax.swing.JPanel insertParameterConstantPanel;
    public javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    public javax.swing.JPanel jPanel2;
    public javax.swing.JPanel jPanel5;
    public javax.swing.JTextField jTextField13;
    public javax.swing.JTextField jTextField14;
    public javax.swing.JPanel verticalPanel;
    // End of variables declaration//GEN-END:variables
}
