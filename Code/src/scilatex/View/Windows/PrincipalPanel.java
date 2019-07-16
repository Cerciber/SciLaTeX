package scilatex.View.Windows;

import com.sun.java.swing.plaf.windows.WindowsSplitPaneDivider;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import scilatex.Logic.Controllers.ScriptControllers.ScilabController;
import static scilatex.Data.Constants.Constants.BORDER_COLOR;
import static scilatex.Data.Constants.Constants.COLOR_BAGROUND;
import static scilatex.Data.Constants.Constants.COLOR_TEXT;
import static scilatex.Data.Constants.Constants.LATEX_COLOR_MOVED;
import static scilatex.Data.Constants.Constants.TEXT_FONT;
import scilatex.Logic.Controllers.ScriptControllers.LaTeXController;
import scilatex.Logic.Controllers.ViewControllers.ContentPanelController;
import scilatex.Logic.Controllers.ViewControllers.EditFunctionController;
import scilatex.Logic.Controllers.ViewControllers.InsertConstantController;
import scilatex.Logic.Controllers.ViewControllers.TreePanelController;

// Clase que gestiona la pantalla principal
public final class PrincipalPanel extends javax.swing.JPanel {

    // Controladores graficos
    private ContentPanelController contentPanelController;
    private TreePanelController treeController;
    private InsertConstantController insertConstantController;
    private EditFunctionController editFunctionController;

    // Controladore de codigo
    public ScilabController scilab;
    public LaTeXController laTeX;

    // Inicial panel principal
    public PrincipalPanel() {

        // Iniciar componentes graficos
        initComponents();

        // Asignar controllador de Scilab y abrir conexión
        this.scilab = new ScilabController(); 
        this.scilab.open();       
        
        // Asignar controlador de LaTeX
        this.laTeX = new LaTeXController();
        
        // Asignar controlladores de vista
        contentPanelController = new ContentPanelController(this);
        treeController = new TreePanelController(this);
        insertConstantController = new InsertConstantController(this);
        editFunctionController = new EditFunctionController(this);

        asignAditionalDesignConfiguration();    // Asignar configuraciones de diseño adicionales
        asignPopupmenus();                      // Asignar menus del click derecho
        asignFunctionTree();                    // Asignar arbol de funciones

    }

    // Asignar configuraciones de diseño adicionales
    public void asignAditionalDesignConfiguration() {

        // Asignar color blanco a las barras de movimiento
        jSplitPane1.getComponent(0).setBackground(COLOR_BAGROUND);
        jSplitPane2.getComponent(0).setBackground(COLOR_BAGROUND);
        ((WindowsSplitPaneDivider) jSplitPane1.getComponent(0)).setBorder(null);
        ((WindowsSplitPaneDivider) jSplitPane2.getComponent(0)).setBorder(null);

    }

    // Asignar menus del click derecho
    public void asignPopupmenus() {

        // Ingresar item para ingresar una constante
        containerBoxPaneMenu.add(new JMenuItem(new AbstractAction("Insertar constante") {
            public void actionPerformed(ActionEvent evt) {
                getInsertConstantController().showIinsertConstant(null);
            }
        }));

        // Ingresar item para ingresar una función
        containerBoxPaneMenu.add(new JMenuItem(new AbstractAction("Insertar función") {
            public void actionPerformed(ActionEvent e) {
                getEditFunctionController().showEditNewFunction();
            }
        }));
        
        containerBoxPaneMenu.addSeparator();
        
        // Ingresar item para limpiar pantalla
        containerBoxPaneMenu.add(new JMenuItem(new AbstractAction("Limpiar pantalla") {
            public void actionPerformed(ActionEvent e) {
                getContentPanelController().clean();
            }
        }));

        // Ingresar item para eliminar un nodo
        treeMenu.add(new JMenuItem(new AbstractAction("Eliminar") {
            public void actionPerformed(ActionEvent evt) {
                getTreeController().deleteNode();
            }
        }));

    }
    
    // Asignar aebol de funcioness
    public void asignFunctionTree() {
        treeController.asignNodes();
    }

    public ContentPanelController getContentPanelController() {
        return contentPanelController;
    }

    public void setContentPanelController(ContentPanelController contentPanelController) {
        this.contentPanelController = contentPanelController;
    }

    public TreePanelController getTreeController() {
        return treeController;
    }

    public void setTreeController(TreePanelController treeController) {
        this.treeController = treeController;
    }

    public InsertConstantController getInsertConstantController() {
        return insertConstantController;
    }

    public void setInsertConstantController(InsertConstantController insertConstantController) {
        this.insertConstantController = insertConstantController;
    }

    public EditFunctionController getEditFunctionController() {
        return editFunctionController;
    }

    public void setEditFunctionController(EditFunctionController editFunctionController) {
        this.editFunctionController = editFunctionController;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        containerBoxPaneMenu = new javax.swing.JPopupMenu();
        insertConstantPanel = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        insertFunctionPanel = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jLabel19 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField6 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        treeMenu = new javax.swing.JPopupMenu();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        leftPane = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jSplitPane2 = new javax.swing.JSplitPane();
        centerPane = new javax.swing.JTabbedPane();
        containerBoxPane = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        containerBoxPaneMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                containerBoxPaneMenuMouseClicked(evt);
            }
        });

        insertConstantPanel.setBackground(new java.awt.Color(255, 255, 255));
        insertConstantPanel.setOpaque(false);
        insertConstantPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                insertConstantPanelMouseDragged(evt);
            }
        });
        insertConstantPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                insertConstantPanelMousePressed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField3.setOpaque(false);
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField4.setOpaque(false);
        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jLabel6.setFont(TEXT_FONT);
        jLabel6.setForeground(COLOR_TEXT);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Instrucción Scilab");

        jLabel9.setFont(TEXT_FONT);
        jLabel9.setForeground(COLOR_TEXT);
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Vista LaTeX");

        javax.swing.GroupLayout insertConstantPanelLayout = new javax.swing.GroupLayout(insertConstantPanel);
        insertConstantPanel.setLayout(insertConstantPanelLayout);
        insertConstantPanelLayout.setHorizontalGroup(
            insertConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertConstantPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(insertConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(jTextField3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(insertConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField4)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
        );
        insertConstantPanelLayout.setVerticalGroup(
            insertConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertConstantPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(insertConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(insertConstantPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 1, Short.MAX_VALUE))
        );

        insertFunctionPanel.setBackground(new java.awt.Color(255, 255, 255));
        insertFunctionPanel.setBorder(new javax.swing.border.LineBorder(BORDER_COLOR, 2, true));
        insertFunctionPanel.setMinimumSize(new java.awt.Dimension(320, 400));
        insertFunctionPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                insertFunctionPanelMouseDragged(evt);
            }
        });
        insertFunctionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                insertFunctionPanelMousePressed(evt);
            }
        });

        jTextField5.setFont(TEXT_FONT);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField5.setOpaque(false);
        jTextField5.setPreferredSize(new java.awt.Dimension(6, 15));
        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jTextField7.setFont(TEXT_FONT);
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField7.setOpaque(false);
        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jLabel11.setFont(TEXT_FONT);
        jLabel11.setForeground(COLOR_TEXT);
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Scipt Scilab");

        jScrollPane1.setBorder(new javax.swing.border.LineBorder(BORDER_COLOR, 2, true));

        jTextArea5.setBackground(new java.awt.Color(245, 245, 251));
        jTextArea5.setColumns(20);
        jTextArea5.setRows(3);
        jTextArea5.setTabSize(4);
        jTextArea5.setText("function r = f(a,b,c)\n r = \nendfunction");
        jTextArea5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea5FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextArea5FocusLost(evt);
            }
        });
        jTextArea5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea5KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea5);

        jLabel12.setFont(TEXT_FONT);
        jLabel12.setForeground(COLOR_TEXT);
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Scipt LaTeX In");

        jScrollPane7.setBorder(new javax.swing.border.LineBorder(BORDER_COLOR, 2, true));

        jTextArea6.setBackground(new java.awt.Color(245, 245, 251));
        jTextArea6.setColumns(20);
        jTextArea6.setRows(3);
        jTextArea6.setTabSize(4);
        jTextArea6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextArea6FocusLost(evt);
            }
        });
        jTextArea6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea6KeyReleased(evt);
            }
        });
        jScrollPane7.setViewportView(jTextArea6);

        jTextField8.setFont(TEXT_FONT);
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField8.setOpaque(false);
        jTextField8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField8FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField8FocusLost(evt);
            }
        });
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField8KeyReleased(evt);
            }
        });

        jTextField9.setFont(TEXT_FONT);
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField9.setOpaque(false);
        jTextField9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField9FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField9FocusLost(evt);
            }
        });
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField9KeyReleased(evt);
            }
        });

        jLabel15.setFont(TEXT_FONT);
        jLabel15.setForeground(COLOR_TEXT);
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Nombre función");

        jLabel16.setFont(TEXT_FONT);
        jLabel16.setForeground(COLOR_TEXT);
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Descipción");

        jLabel17.setFont(TEXT_FONT);
        jLabel17.setForeground(COLOR_TEXT);
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Nombre parametros (Separado por comas)");

        jLabel18.setFont(TEXT_FONT);
        jLabel18.setForeground(COLOR_TEXT);
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Descripción parametros (Separado por comas)");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Constantia", 0, 11)); // NOI18N
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setOpaque(true);
        jLabel4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jLabel4MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jLabel4MouseMoved(evt);
            }
        });
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });
        jLabel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel4KeyPressed(evt);
            }
        });

        jLabel13.setFont(TEXT_FONT);
        jLabel13.setForeground(COLOR_TEXT);
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Vista función");

        jLabel14.setFont(TEXT_FONT);
        jLabel14.setForeground(COLOR_TEXT);
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Vista LaTeX In");

        jScrollPane8.setBackground(new java.awt.Color(250, 250, 255));
        jScrollPane8.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));
        jScrollPane8.setOpaque(false);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setOpaque(true);
        jScrollPane8.setViewportView(jLabel10);

        jScrollPane9.setBackground(new java.awt.Color(250, 250, 255));
        jScrollPane9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));
        jScrollPane9.setOpaque(false);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setOpaque(true);
        jScrollPane9.setViewportView(jLabel19);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(TEXT_FONT);
        jButton1.setForeground(COLOR_TEXT);
        jButton1.setText("Aceptar");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));
        jButton1.setContentAreaFilled(false);
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(TEXT_FONT);
        jButton2.setForeground(COLOR_TEXT);
        jButton2.setText("Cancelar");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));
        jButton2.setContentAreaFilled(false);
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(203, 203, 255));
        jSeparator1.setForeground(new java.awt.Color(203, 203, 255));

        jTextField6.setFont(TEXT_FONT);
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField6.setOpaque(false);
        jTextField6.setPreferredSize(new java.awt.Dimension(6, 15));
        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField6FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });

        jLabel20.setFont(TEXT_FONT);
        jLabel20.setForeground(COLOR_TEXT);
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Nombre nodo");

        jLabel21.setFont(TEXT_FONT);
        jLabel21.setForeground(COLOR_TEXT);
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Scipt LaTeX Out");

        jScrollPane11.setBorder(new javax.swing.border.LineBorder(BORDER_COLOR, 2, true));

        jTextArea7.setBackground(new java.awt.Color(245, 245, 251));
        jTextArea7.setColumns(20);
        jTextArea7.setRows(3);
        jTextArea7.setTabSize(4);
        jTextArea7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea7FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextArea7FocusLost(evt);
            }
        });
        jTextArea7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextArea7KeyReleased(evt);
            }
        });
        jScrollPane11.setViewportView(jTextArea7);

        jScrollPane12.setBackground(new java.awt.Color(250, 250, 255));
        jScrollPane12.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));
        jScrollPane12.setOpaque(false);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setOpaque(true);
        jScrollPane12.setViewportView(jLabel22);

        jLabel23.setFont(TEXT_FONT);
        jLabel23.setForeground(COLOR_TEXT);
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Vista LaTeX Out");

        jTextField10.setFont(TEXT_FONT);
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jTextField10.setOpaque(false);
        jTextField10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField10FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField10FocusLost(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField10KeyReleased(evt);
            }
        });

        jLabel24.setFont(TEXT_FONT);
        jLabel24.setForeground(COLOR_TEXT);
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("Tipo parametros (Separado por comas)");

        javax.swing.GroupLayout insertFunctionPanelLayout = new javax.swing.GroupLayout(insertFunctionPanel);
        insertFunctionPanel.setLayout(insertFunctionPanelLayout);
        insertFunctionPanelLayout.setHorizontalGroup(
            insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertFunctionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insertFunctionPanelLayout.createSequentialGroup()
                        .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(insertFunctionPanelLayout.createSequentialGroup()
                                .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField7)
                            .addComponent(jTextField8)
                            .addComponent(jTextField9)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField10)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(insertFunctionPanelLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addComponent(jSeparator1)
        );
        insertFunctionPanelLayout.setVerticalGroup(
            insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(insertFunctionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(insertFunctionPanelLayout.createSequentialGroup()
                        .addGroup(insertFunctionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addContainerGap())
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        treeMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMenuMouseClicked(evt);
            }
        });

        setBackground(COLOR_BAGROUND);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(BORDER_COLOR, 4, true));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));

        jLabel2.setFont(TEXT_FONT);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Herramientas: nuevo proyecto, abrir proyecto, guardar, guardar como, creador de funciones, configuración, acerca de");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jSplitPane1.setDividerLocation(300);
        jSplitPane1.setDividerSize(10);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        leftPane.setBackground(new java.awt.Color(255, 255, 255));
        leftPane.setBorder(new javax.swing.border.LineBorder(BORDER_COLOR, 4, true));

        jScrollPane3.setBorder(null);

        jTree1.setFont(TEXT_FONT);
        jTree1.setToolTipText("");
        jTree1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jTree1MouseDragged(evt);
            }
        });
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTree1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTree1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTree1MouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTree1);

        javax.swing.GroupLayout leftPaneLayout = new javax.swing.GroupLayout(leftPane);
        leftPane.setLayout(leftPaneLayout);
        leftPaneLayout.setHorizontalGroup(
            leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
        leftPaneLayout.setVerticalGroup(
            leftPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(leftPane);

        jSplitPane2.setBackground(new java.awt.Color(255, 255, 255));
        jSplitPane2.setBorder(null);
        jSplitPane2.setDividerLocation(300);
        jSplitPane2.setDividerSize(10);
        jSplitPane2.setForeground(new java.awt.Color(255, 255, 255));
        jSplitPane2.setContinuousLayout(true);

        centerPane.setBackground(new java.awt.Color(255, 255, 255));
        centerPane.setBorder(new javax.swing.border.LineBorder(BORDER_COLOR, 4, true));
        centerPane.setFont(TEXT_FONT);

        containerBoxPane.setBackground(new java.awt.Color(255, 255, 255));
        containerBoxPane.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                containerBoxPaneMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                containerBoxPaneMouseMoved(evt);
            }
        });
        containerBoxPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                containerBoxPaneMouseClicked(evt);
            }
        });
        containerBoxPane.setLayout(null);
        centerPane.addTab("Ventana 1", containerBoxPane);

        jSplitPane2.setLeftComponent(centerPane);
        centerPane.getAccessibleContext().setAccessibleName("tab2");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(BORDER_COLOR, 4, true));

        jScrollPane2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(TEXT_FONT);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Descripción");
        jScrollPane2.setViewportView(jTextArea1);

        jScrollPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(245, 245, 251));
        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setTabSize(3);
        jTextArea2.setText("Script Scilab\n");
        jScrollPane4.setViewportView(jTextArea2);

        jScrollPane5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new java.awt.Color(245, 245, 251));
        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setText("Script LaTeX");
        jScrollPane5.setViewportView(jTextArea3);

        jLabel3.setFont(TEXT_FONT);
        jLabel3.setForeground(COLOR_TEXT);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Script Scilab");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(203, 203, 255)));

        jLabel7.setFont(TEXT_FONT);
        jLabel7.setForeground(COLOR_TEXT);
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Script LaTeX");
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(203, 203, 255)));

        jScrollPane10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(203, 203, 255), 2, true));

        jList1.setFont(TEXT_FONT);
        jList1.setRequestFocusEnabled(false);
        jList1.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jList1.setSelectionForeground(new java.awt.Color(203, 203, 255));
        jList1.setVerifyInputWhenFocusTarget(false);
        jScrollPane10.setViewportView(jList1);

        jLabel8.setFont(TEXT_FONT);
        jLabel8.setForeground(COLOR_TEXT);
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Parametros");
        jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(203, 203, 255)));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, BORDER_COLOR));

        jLabel5.setFont(TEXT_FONT);
        jLabel5.setForeground(COLOR_TEXT);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Nombre");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(203, 203, 255)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        jSplitPane2.setRightComponent(jPanel5);

        jSplitPane1.setRightComponent(jSplitPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSplitPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Evento al mover el mouse sobre el panel principal
    private void containerBoxPaneMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_containerBoxPaneMouseMoved

        // Asignar corrdenadas del mouse en relación al panel principal
        contentPanelController.setMouseX(evt.getX());
        contentPanelController.setMouseY(evt.getY());

        // Evento del panel principal al mover el mouse
        contentPanelController.detectAllShowComplementsAndHighlight();

    }//GEN-LAST:event_containerBoxPaneMouseMoved

    // Evento al arrastrar el mouse sobre el panel principal
    private void containerBoxPaneMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_containerBoxPaneMouseDragged

        // Asignar corrdenadas del mouse en relación al panel principal
        contentPanelController.setMouseX(evt.getX());
        contentPanelController.setMouseY(evt.getY());

        // Evento del panel principal al mover el mouse
        contentPanelController.detectAllShowComplementsAndHighlight();

    }//GEN-LAST:event_containerBoxPaneMouseDragged

    // Evento al cambiar el tramaño de la ventana
    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized

        // Ajustar ancho de los paneles laterales al cambiar tamaño de la ventana
        jSplitPane1.setDividerLocation(300);
        jSplitPane2.setDividerLocation(jSplitPane2.getWidth() - 300);

    }//GEN-LAST:event_formComponentResized

    // Evento al mostrar la ventana
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown

        // Ajustar ancho de los paneles laterales al mostrar la ventana
        jSplitPane1.setDividerLocation(300);
        jSplitPane2.setDividerLocation(jSplitPane2.getWidth() - 300);

    }//GEN-LAST:event_formComponentShown

    // Evento al hacer click sobre el panel principal
    private void containerBoxPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_containerBoxPaneMouseClicked

        // Borrar los paneles de insersión de parametros de las cajas de función
        contentPanelController.deleteAllFunctionInsertParameterPanel();

        // Borrar los bordes de las cajas de función
        contentPanelController.deleteAllFunctionBoxBorders();

        // Evento genral de cambios
        contentPanelController.detectAllShowComplementsAndHighlight();

        // Si se presionó en click izquierdo
        if (evt.getButton() == MouseEvent.BUTTON1) {

            // Si no se está mostrando la caja de ingresar constante
            if (!insertConstantController.showInsertConstant) {

                // Mostrar caja de ingresar constante
                insertConstantController.showIinsertConstant(evt);

            } else {

                // Remover la caja de ingresar constante y función si estan activas
                containerBoxPane.remove(insertConstantPanel);
                insertConstantController.showInsertConstant = false;
                containerBoxPane.updateUI();

            }

            // Si se presionó en click derecho
        } else if (evt.getButton() == MouseEvent.BUTTON3) {

            // Mostrar menu del click derecho del panel contenedor de cajas
            contentPanelController.showContentPanelMenu(evt);

        }

        this.updateUI();

    }//GEN-LAST:event_containerBoxPaneMouseClicked

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    // Evento al presionar una tecla en primer campo de la caja ingresar constante 
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed

        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Insertar constante si es posible
            insertConstantController.insertConstant();

        }

    }//GEN-LAST:event_jTextField3KeyPressed

    // Evento al presionar una tecla en segundo campo de la caja ingresar constante 
    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed

        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Insertar constante si es posible
            insertConstantController.insertConstant();

        }

    }//GEN-LAST:event_jTextField4KeyPressed

    // Evento al presionar el panel de ingresar constante
    private void insertConstantPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertConstantPanelMousePressed

        // Asignar la coordenada donde se presionó
        insertConstantController.constantPressedX = evt.getX();
        insertConstantController.constantPressedY = evt.getY();

    }//GEN-LAST:event_insertConstantPanelMousePressed

    // Evento al arrastrar el mouse en el panel de ingresar constante
    private void insertConstantPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertConstantPanelMouseDragged

        // Cambiar el panel de ingresar constante de acurdo a las coordenadas del mouse
        insertConstantPanel.setLocation(insertConstantPanel.getLocation().x + evt.getX() - insertConstantController.constantPressedX, insertConstantPanel.getLocation().y + evt.getY() - insertConstantController.constantPressedY);

    }//GEN-LAST:event_insertConstantPanelMouseDragged

    private void containerBoxPaneMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_containerBoxPaneMenuMouseClicked

    }//GEN-LAST:event_containerBoxPaneMenuMouseClicked

    private void insertFunctionPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertFunctionPanelMousePressed

        containerBoxPane.add(insertFunctionPanel, 0);
        containerBoxPane.updateUI();

        // Asignar la coordenada donde se presionó
        editFunctionController.functionPressedX = evt.getX();
        editFunctionController.functionPressedY = evt.getY();

    }//GEN-LAST:event_insertFunctionPanelMousePressed

    private void insertFunctionPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_insertFunctionPanelMouseDragged

        insertFunctionPanel.setLocation(insertFunctionPanel.getLocation().x + evt.getX() - editFunctionController.functionPressedX, insertFunctionPanel.getLocation().y + evt.getY() - editFunctionController.functionPressedY);

    }//GEN-LAST:event_insertFunctionPanelMouseDragged

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased

        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Pasar foco
            jTextField7.requestFocus();

        } else {
            
            // Actualizar nombre de la función
            editFunctionController.updateFunctionName();
            
        }

    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased

        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Pasar foco
            jTextField8.requestFocus();

        } else {
            
            // Actualizar descripcion de la función
            editFunctionController.updateDescription();
            
        }
        
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField8KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyReleased

        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Pasar foco
            jTextField9.requestFocus();

        } else {
            
            // Actualizar nombre de los parametros de la función
            editFunctionController.updateNameParameters();
            
        }

    }//GEN-LAST:event_jTextField8KeyReleased

    private void jTextField9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyReleased

        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Pasar foco
            jTextField10.requestFocus();

        } else {
            
            // Actualizar descripción de los parametros de la función
            editFunctionController.updateDescriptionParameters();
            
        }

    }//GEN-LAST:event_jTextField9KeyReleased

    private void jTextArea5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea5KeyReleased

        editFunctionController.updateScriptScilab();

    }//GEN-LAST:event_jTextArea5KeyReleased

    private void jTextArea6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea6KeyReleased

        editFunctionController.updateScriptLaTeXIn();

    }//GEN-LAST:event_jTextArea6KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // Insertar función
        editFunctionController.insertFunction();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        // cancelar insrtar función
        editFunctionController.cancelEditFunction();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel4KeyPressed


    }//GEN-LAST:event_jLabel4KeyPressed

    private void jLabel4MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseDragged

        if (insertFunctionPanel.getPreferredSize().width + jLabel4.getLocation().x + evt.getX() - editFunctionController.functionPressedX > insertFunctionPanel.getMinimumSize().width) {
            insertFunctionPanel.setSize(insertFunctionPanel.getPreferredSize().width + jLabel4.getLocation().x + evt.getX() - editFunctionController.functionPressedX,
                    insertFunctionPanel.getSize().height);
        }

        if (insertFunctionPanel.getPreferredSize().height + jLabel4.getLocation().y + evt.getY() - editFunctionController.functionPressedY > insertFunctionPanel.getMinimumSize().height) {
            insertFunctionPanel.setSize(insertFunctionPanel.getSize().width,
                    insertFunctionPanel.getPreferredSize().height + jLabel4.getLocation().y + evt.getY() - editFunctionController.functionPressedY);
        }

        updateUI();

    }//GEN-LAST:event_jLabel4MouseDragged

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed

        // Asignar la coordenada donde se presionó
        editFunctionController.functionPressedX = jLabel4.getLocation().x + evt.getX();
        editFunctionController.functionPressedY = jLabel4.getLocation().y + evt.getY();

        insertFunctionPanel.setPreferredSize(insertFunctionPanel.getSize());

    }//GEN-LAST:event_jLabel4MousePressed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusGained

        jTextField5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, LATEX_COLOR_MOVED));
        jLabel15.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextField5FocusGained

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost

        jTextField5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jLabel15.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusGained

        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, LATEX_COLOR_MOVED));
        jLabel16.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextField7FocusGained

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost

        jTextField7.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jLabel16.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextField7FocusLost

    private void jTextField8FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusGained

        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, LATEX_COLOR_MOVED));
        jLabel17.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextField8FocusGained

    private void jTextField8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField8FocusLost

        jTextField8.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jLabel17.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextField8FocusLost

    private void jTextField9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusGained

        jTextField9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, LATEX_COLOR_MOVED));
        jLabel18.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextField9FocusGained

    private void jTextField9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField9FocusLost

        jTextField9.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jLabel18.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextField9FocusLost

    private void jTextArea5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea5FocusGained

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, LATEX_COLOR_MOVED));
        jLabel11.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextArea5FocusGained

    private void jTextArea5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea5FocusLost

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, BORDER_COLOR));
        jLabel11.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextArea5FocusLost

    private void jTextArea6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea6FocusGained

        jScrollPane7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, LATEX_COLOR_MOVED));
        jLabel12.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextArea6FocusGained

    private void jTextArea6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea6FocusLost

        jScrollPane7.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, BORDER_COLOR));
        jLabel12.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextArea6FocusLost

    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusGained

        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, LATEX_COLOR_MOVED));
        jLabel6.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextField3FocusGained

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost

        jTextField3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jLabel6.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusGained

        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, LATEX_COLOR_MOVED));
        jLabel9.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextField4FocusGained

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost

        jTextField4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jLabel9.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextField4FocusLost

    private void jLabel4MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseMoved

        jLabel4.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));

    }//GEN-LAST:event_jLabel4MouseMoved

    private void jTree1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseEntered

    }//GEN-LAST:event_jTree1MouseEntered

    private void jTree1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MousePressed

        // Asignar corrdenadas del mouse en relación al panel principal
        contentPanelController.setMouseX(evt.getX() - leftPane.getWidth() - 10);
        contentPanelController.setMouseY(evt.getY() - 27);

        treeController.peressedNode(evt);

    }//GEN-LAST:event_jTree1MousePressed

    private void jTree1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseDragged

        // Asignar corrdenadas del mouse en relación al panel principal
        contentPanelController.setMouseX(evt.getX() - leftPane.getWidth() - 10);
        contentPanelController.setMouseY(evt.getY() - 27);

        treeController.draggerNode(evt);

    }//GEN-LAST:event_jTree1MouseDragged

    private void jTree1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseReleased

        // Asignar corrdenadas del mouse en relación al panel principal
        contentPanelController.setMouseX(evt.getX() - leftPane.getWidth() - 10);
        contentPanelController.setMouseY(evt.getY() - 27);

        treeController.releasedNode();

    }//GEN-LAST:event_jTree1MouseReleased

    private void jTextField6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusGained

        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, LATEX_COLOR_MOVED));
        jLabel20.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextField6FocusGained

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost

        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jLabel20.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextField6FocusLost

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased

        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Pasar foco
            jTextField5.requestFocus();

        } else {
            
            // Actualizar nombre del nodo
            editFunctionController.updateNodeName();
            
        }

    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextArea7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea7FocusGained

        jScrollPane11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, LATEX_COLOR_MOVED));
        jLabel21.setForeground(LATEX_COLOR_MOVED);

    }//GEN-LAST:event_jTextArea7FocusGained

    private void jTextArea7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea7FocusLost

        jScrollPane11.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, BORDER_COLOR));
        jLabel21.setForeground(COLOR_TEXT);

    }//GEN-LAST:event_jTextArea7FocusLost

    private void jTextArea7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextArea7KeyReleased

        // Actualizar script LaTeX de salida de la función
        editFunctionController.updateScriptLaTeXOut();

    }//GEN-LAST:event_jTextArea7KeyReleased

    private void treeMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMenuMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_treeMenuMouseClicked

    private void jTextField10FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusGained
        
        jTextField10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, LATEX_COLOR_MOVED));
        jLabel24.setForeground(LATEX_COLOR_MOVED);
        
    }//GEN-LAST:event_jTextField10FocusGained

    private void jTextField10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField10FocusLost
        
        jTextField10.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, BORDER_COLOR));
        jLabel24.setForeground(COLOR_TEXT);
        
    }//GEN-LAST:event_jTextField10FocusLost

    private void jTextField10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyReleased
        
        // Si se presionó Enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            // Pasar foco
            jTextArea5.requestFocus();

        } else {
            
            // Actualizar nombre del nodo
            editFunctionController.updateTypeParamaters();
            
        }
        
    }//GEN-LAST:event_jTextField10KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane centerPane;
    public javax.swing.JPanel containerBoxPane;
    public javax.swing.JPopupMenu containerBoxPaneMenu;
    public javax.swing.JPanel insertConstantPanel;
    public javax.swing.JPanel insertFunctionPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    public javax.swing.JLabel jLabel19;
    public javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JSplitPane jSplitPane1;
    public javax.swing.JSplitPane jSplitPane2;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextArea jTextArea2;
    public javax.swing.JTextArea jTextArea3;
    public javax.swing.JTextArea jTextArea5;
    public javax.swing.JTextArea jTextArea6;
    public javax.swing.JTextArea jTextArea7;
    public javax.swing.JTextField jTextField10;
    public javax.swing.JTextField jTextField3;
    public javax.swing.JTextField jTextField4;
    public javax.swing.JTextField jTextField5;
    public javax.swing.JTextField jTextField6;
    public javax.swing.JTextField jTextField7;
    public javax.swing.JTextField jTextField8;
    public javax.swing.JTextField jTextField9;
    public javax.swing.JTree jTree1;
    public javax.swing.JPanel leftPane;
    public javax.swing.JPopupMenu treeMenu;
    // End of variables declaration//GEN-END:variables
}
