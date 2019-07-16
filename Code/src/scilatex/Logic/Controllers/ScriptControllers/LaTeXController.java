package scilatex.Logic.Controllers.ScriptControllers;

import javax.swing.Icon;
import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;
import static scilatex.Data.Constants.Constants.SIZE_DEFAULT_LATEX;

// Clase que ejecuta la sintaxis de LaTeX
public class LaTeXController {
    
    // Obtener el icono de la formula LaTeX especificada con el tamaño especificado
    public Icon getImage(String formula, int size) {

        try {

            TeXIcon teXIcon = new TeXFormula(formula).createTeXIcon(TeXConstants.STYLE_DISPLAY, SIZE_DEFAULT_LATEX);
            
            if (size != -1) {
                teXIcon = new TeXFormula(formula).createTeXIcon(TeXConstants.STYLE_DISPLAY, SIZE_DEFAULT_LATEX * size / teXIcon.getIconHeight());
            }
            
            return teXIcon;
        } catch (org.scilab.forge.jlatexmath.ParseException ex) {
            return new TeXFormula("").createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
        }
    }

    // Obtener el icono del texto en formato LaTeX
    public Icon getTextImage(String text, int size) {

        try {
            return new TeXFormula(text.replace(" ", "\\ ")).createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
        } catch (org.scilab.forge.jlatexmath.ParseException ex) {
            return new TeXFormula("").createTeXIcon(TeXConstants.STYLE_DISPLAY, size);
        }
    }

}
