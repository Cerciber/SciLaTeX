
package scilatex.Data.Repositories.LocalRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import scilatex.Data.Models.FunctionModel;

// Clase que permite guardar y recuperar una función desde una dirección local
public class FunctionRepository {
    
    public FunctionModel readFunctionModel(File directory){
        
        FunctionModel functionModel = null;
        
        try {

            // Asignar atributos
            FileReader reader = new FileReader(directory + "\\" + "Atributes.txt");
            BufferedReader br = new BufferedReader(reader);

            // Asignar modelo
            functionModel = new FunctionModel();
            
            // Asignar parametros
            functionModel.setNodeName(br.readLine().trim());
            functionModel.setFunctionName(br.readLine().trim());
            functionModel.setDescription(br.readLine().trim());
            
            String nameParameters = br.readLine();
            if (nameParameters.trim().equals("")) {
                functionModel.setNameParameters(new String[0]);
            } else {
                functionModel.setNameParameters(nameParameters.replace(", ", ",").split(","));
            }
            
            String descriptionParameters = br.readLine();
            if (descriptionParameters.trim().equals("")) {
                functionModel.setDescriptionParameters(new String[0]);
            } else {
                functionModel.setDescriptionParameters(descriptionParameters.replace(", ", ",").split(","));
            }
            
            String typeParameters = br.readLine();
            if (typeParameters.trim().equals("")) {
                functionModel.setTypeParameters(new String[0]);
            } else {
                functionModel.setTypeParameters(typeParameters.replace(", ", ",").split(","));
            }
            
            functionModel.setOption(Integer.parseInt(br.readLine().trim()));
            functionModel.setNumDecimals(Integer.parseInt(br.readLine().trim()));
            functionModel.setLaTeXSize(Integer.parseInt(br.readLine().trim()));
            functionModel.setShowFunctionInScreen(Integer.parseInt(br.readLine().trim()));
            functionModel.setShowParametersInScreen(Integer.parseInt(br.readLine().trim()));
            functionModel.setShowParameter(Integer.parseInt(br.readLine().trim()));
            functionModel.setParameters(new FunctionModel[functionModel.getNameParameters().length]);
            reader.close();

            // Asignar script Scilab
            reader = new FileReader(directory + "\\" + "Scilab.txt");
            br = new BufferedReader(reader);
            String scriptScilab = "";
            while (true) {
                String valor = br.readLine();
                if (valor == null) {
                    break;
                } else {
                    scriptScilab += valor + "\n";
                }
            }
            reader.close();
            functionModel.setScriptScilab(scriptScilab);

            // Asignar script LaTeX de entrada
            reader = new FileReader(directory + "\\" + "LaTeXIn.txt");
            br = new BufferedReader(reader);
            String scriptLaTeXIn = "";
            while (true) {
                String valor = br.readLine();
                if (valor == null) {
                    break;
                } else {
                    scriptLaTeXIn += valor + "\n";
                }
            }
            reader.close();
            functionModel.setScriptLaTeXIn(scriptLaTeXIn);

            // Asignar script LaTeX de salida
            reader = new FileReader(directory + "\\" + "LaTeXOut.txt");
            br = new BufferedReader(reader);
            String scriptLaTeXOut = "";
            while (true) {
                String valor = br.readLine();
                if (valor == null) {
                    break;
                } else {
                    scriptLaTeXOut += valor + "\n";
                }
            }
            reader.close();
            functionModel.setScriptLaTeXOut(scriptLaTeXOut);
            
            // Leer parametros
            readFunctionModel(functionModel, directory);

        } catch (IOException ex) {
            System.out.println("No se pudo leer la función: " + directory);
        }

        return functionModel;
        
    }
    
    // Asignar paramatros guardados en el arbol de archivos
    private void readFunctionModel(FunctionModel functionModel, File directory) {

        // Si existen rutas o archivos contendidos
        if (directory.listFiles() != null) {

            // Para cada archivo
            for (int i = 0; i < directory.listFiles().length; i++) {

                // Si es una carpeta
                if (directory.listFiles()[i].isDirectory()) {
                    
                    functionModel.getParameters()[Integer.parseInt(directory.listFiles()[i].getName())] = readFunctionModel(directory.listFiles()[i]);
                    
                }

            }

        }

    }
    
    // Guardar función en la ruta especificada
    public void saveFunctionModel(FunctionModel functionModel, File directory){
        saveFunctionModel(-1, functionModel, directory);
    }
    
    // Guardar función en la ruta especificada recursivamente
    private void saveFunctionModel(int numParamater, FunctionModel functionModel, File directory) {

        // Crear ruta
        String nameFile = null;
        if (numParamater == -1) {
            nameFile = functionModel.getNodeName().trim();
        } else {
            nameFile = numParamater + "";
        }
        File file = new File(directory + "\\" + nameFile.trim());

        // Crear carpeta de la función
        file.mkdir();

        // Escribir función
        try {

            // Escribir atributos
            FileWriter writer = new FileWriter(directory + "\\" + nameFile + "\\" + "Atributes.txt");
            BufferedWriter br = new BufferedWriter(writer);
            br.write(functionModel.getNodeName() + "\n");
            br.write(functionModel.getFunctionName() + "\n");
            br.write(functionModel.getDescription() + "\n");
            br.write(Arrays.toString(functionModel.getNameParameters()).substring(1, Arrays.toString(functionModel.getNameParameters()).length() - 1) + "\n");
            br.write(Arrays.toString(functionModel.getDescriptionParameters()).substring(1, Arrays.toString(functionModel.getDescriptionParameters()).length() - 1) + "\n");
            br.write(Arrays.toString(functionModel.getTypeParameters()).substring(1, Arrays.toString(functionModel.getTypeParameters()).length() - 1) + "\n");
            br.write(functionModel.getOption() + "\n");
            br.write(functionModel.getNumDecimals() + "\n");
            br.write(functionModel.getLaTeXSize() + "\n");
            br.write(functionModel.getShowFunctionInScreen() + "\n");
            br.write(functionModel.getShowParametersInScreen() + "\n");
            br.write(functionModel.getShowParameter() + "\n");
            br.close();

            // Escribir codigo Scilab
            writer = new FileWriter(directory + "\\" + nameFile + "\\" + "Scilab.txt");
            br = new BufferedWriter(writer);
            br.write(functionModel.getScriptScilab());
            br.close();

            // Escribir codigo LaTeX de entrada
            writer = new FileWriter(directory + "\\" + nameFile + "\\" + "LaTeXIn.txt");
            br = new BufferedWriter(writer);
            br.write(functionModel.getScriptLaTeXIn());
            br.close();

            // Escribir codigo LaTeX de salida
            writer = new FileWriter(directory + "\\" + nameFile + "\\" + "LaTeXOut.txt");
            br = new BufferedWriter(writer);
            br.write(functionModel.getScriptLaTeXOut());
            br.close();

            // Guardar parametros
            for (int i = 0; i < functionModel.getParameters().length; i++) {
                if (functionModel.getParameters()[i] != null) {
                    saveFunctionModel(i, functionModel.getParameters()[i], file);
                }

            }

        } catch (IOException ex) {
            System.out.println("No se pudo escribir la función");
        }

    }
    
}
