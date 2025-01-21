package com.mdval.ui.model.cabeceras;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mdval.utils.LiteralesSingleton;
import com.mdval.utils.LogWrapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author federico
 *
 */
@Slf4j
public abstract class Cabecera {

    protected LiteralesSingleton literales;

    @Getter
    protected List<String> columnIdentifiers;

    @Getter
    protected List<Class<?>> columnClasses;

    @Getter
    protected List<Integer> columnSizes;

    /**
     *
     */
    public Cabecera() {
        super();

        initialize();
    }

    /**
     *
     */
    private void initialize() {
        try {
            literales = LiteralesSingleton.getInstance();

            columnIdentifiers = new ArrayList<>();
            columnClasses = new ArrayList<>();
            columnSizes = new ArrayList<>();

            setupCabecera();
        } catch (IOException e) {
            LogWrapper.error(log, "ERROR:", e);
        }
    }

    /**
     *
     */
    public abstract void setupCabecera();

    /**
     * @param index
     * @return
     */
    public String getIdentifierAt(Integer index) {
        return columnIdentifiers.get(index);
    }

    /**
     * @param index
     * @return
     */
    public Class<?> getClassAt(Integer index) {
        return columnClasses.get(index);
    }

    /**
     * @param index
     * @return
     */
    public Integer getSizeColumn(Integer index) {
        return columnSizes.get(index);
    }

    public void addColumnIdentifier(String literal) {
        String codigo = this.getClass().getSimpleName();
        //Se busca el texto específico para la tabla
        String texto = literales.getLiteral(codigo + "." + literal);
        if (texto.isEmpty()) {
            //Si no existe se busca como literal, sin estar vinculado a la tabla
            texto = literales.getLiteral(literal);
            if (texto.isEmpty()) {
                //Si no existe se busca como label
                texto = literales.getLiteral("lbl" + literal);
                if (texto.isEmpty()) {
                    //Si no existe se muestra el literal
                    texto = literal;
                }
            }
        }
        columnIdentifiers.add(texto);
    }

    /**
     * Añade una columna con el ancho definido en el fichero
     * literales.properties las columnas tienen el formato
     * colNombre=ancho;literal
     *
     * @param literal
     * @param anchoDef
     * @param clase
     */
    public void addColumn(String literal, int anchoDef, Class clase) {

        String codigo = this.getClass().getSimpleName();
        //Se busca el texto específico para la tabla
        String texto = literales.getLiteral(codigo + "." + literal);
        if (texto.isEmpty()) {
            //Si no existe se busca como literal, sin estar vinculado a la tabla
            texto = literales.getLiteral(literal);
        }
        String[] col = texto.split(";");
        int ancho = anchoDef;
        String titulo = "lbl" + literal.substring(3);
        // Ancho de la columna
        if (col.length > 0 && !col[0].isEmpty()) {
            try {
                ancho = Integer.parseInt(col[0].trim());
            } catch (NumberFormatException e) {
                LogWrapper.error(log, "ERROR en formato del literal [" + literal + "]:", e);
                ancho = anchoDef; //Nos quedamos con el valor por defecto
            }
        }
        // Título de columna, si no se indica se busca el literal lblNombre
        if (col.length > 1 && !col[1].isEmpty() ) {
            titulo = col[1];
        }
        //Añadir los datos de la columna
        addColumnIdentifier(titulo);
        columnClasses.add(clase);
        columnSizes.add(ancho);
    }
}
