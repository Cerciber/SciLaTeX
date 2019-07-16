package scilatex.Data.Constants;

import java.awt.Color;
import java.awt.Font;

// Clase que permite obtener constantes del sistema
public class Constants {

    // Constantes
    public static int SIZE_ICON_BUTTON = 25;                                                // Tamaño del icono de los botones de la función
    public static int SIZE_DEFAULT_LATEX = 27;                                              // Tamaño por defecto de LaTeX
    public static Color BORDER_COLOR = new java.awt.Color(203, 203, 255);                   // Color del borde
    public static Color LATEX_COLOR_EMPTY_PARAMETER = new java.awt.Color(100, 100, 255);    // Color del parmaetro sin asignar
    public static Color LATEX_COLOR_ASIGN_PARAMETER = Color.BLACK;                          // Color del parmaetro asignado
    public static Color LATEX_COLOR_VIEW_PARAMETER = new java.awt.Color(100, 100, 255);     // Color de la vista del parametro
    public static Color LATEX_COLOR_MOVED = new java.awt.Color(203, 203, 255);              // Color al mover el mouse sobre un parametro
    public static Color LATEX_COLOR_ERROR = Color.red;                                      // Color del mensaje de error
    public static Color COLOR_TEXT = new java.awt.Color(100, 100, 255);                     // Color del texto de los labels
    public static Color COLOR_BAGROUND = new java.awt.Color(245, 245, 251);                 // Color de fondo
    public static Font TEXT_FONT = new java.awt.Font("Constantia", 0, 14);                  // Fuente del texto

    // Covertir un color a String (Separados por comas)
    public static String toString(Color color) {
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
    }

}
