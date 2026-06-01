import java.lang.Math;
/**
 * Estructura de datos que contiene datos heterogéneos.
 * @autor: Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version: 21/02/2026
 */

public class DataFrame {
    private String[][] data;
    private String[] columns;
    private int[] shape;
    
    // Invariante
    // data.length == columns.length
    // shape[0] >= 0
    // shape[1] > 0
    
    /**
     * Constructor para la estructura de datos.
     * @param data Son las filas que contienen información.
     * @param columns Son las columnas que contienen información.
     */
    public DataFrame(String [][] data, String [] columns){
            this.columns = columns;
            int helper = 0;
            for(String[] fila : data) {
                if(fila.length == columns.length) {
                    helper++;
            }
        }
        this.data = new String[helper][columns.length];
        int secondHelper = 0;
        for(int i = 0; i < data.length; i++) {
            if(data[i].length == columns.length) {
                this.data[secondHelper] = data[i];
                secondHelper++;
            }
        }
        this.shape = new int[]{helper, columns.length};
    } // Cierre del constructor.

    
    /**
     * Método que concatena el DataFrame actual con otros DataFrames de forma simple.
     * @param dfs Arreglo de DataFrames a concatenar (usaremos el primero).
     * @param axis Eje de concatenación: 0 para filas, 1 para columnas.
     * @return Un nuevo DataFrame con los datos unidos.
     */
    public DataFrame concat(DataFrame[] dfs, byte axis) {
            DataFrame other = dfs[0]; // Asumimos que el arreglo siempre trae al menos un DataFrame
            
            if (axis == 0) { // Concatenar por FILAS (una debajo de otra)
                // Calculamos el nuevo tamaño sumando las filas
                String[][] newData = new String[this.data.length + other.data.length][this.columns.length];
                
                // Copiamos la tabla original
                for (int i = 0; i < this.data.length; i++) {
                    newData[i] = this.data[i];
                }
                // Copiamos la segunda tabla justo debajo
                for (int i = 0; i < other.data.length; i++) {
                    newData[this.data.length + i] = other.data[i];
                }
                
                return new DataFrame(newData, this.columns);
                
            } else { // Si no es 0, asumimos que es 1 -> Concatenar por COLUMNAS (una al lado de otra)
                // Unimos los nombres de las columnas
                String[] newCols = new String[this.columns.length + other.columns.length];
                System.arraycopy(this.columns, 0, newCols, 0, this.columns.length);
                System.arraycopy(other.columns, 0, newCols, this.columns.length, other.columns.length);
                
                // Unimos los datos fila por fila
                String[][] newData = new String[this.data.length][newCols.length];
                for (int i = 0; i < this.data.length; i++) {
                    // Mitad izquierda (tabla original)
                    System.arraycopy(this.data[i], 0, newData[i], 0, this.columns.length);
                    // Mitad derecha (tabla a agregar)
                    System.arraycopy(other.data[i], 0, newData[i], this.columns.length, other.columns.length);
                }
                
                return new DataFrame(newData, newCols);
            }
        } // Cierre del método.
    
    /**
     * Retorna el tamaño completo de la tabla.
     * @return Un HashMap que dice el tamaño completo de la tabla.
     */
    public int [] shape(){
            return this.shape;
    } // Cierre del método.
    
   
    // The columns are aligned, separated by three spaces, and include the index.
    //     Nombre   Edad    Profesion
    // 0    Lucía     28    Ingeniero
    // 1   Carlos     35     Profesor
    // 2      Ana     42       Doctor
    // 3    Jorge     30   Arquitecto
    // 4    Elena     25    Diseñador
    /**
     * Método que retorna las primeras filas de una tabla.
     * @param rows El parámetro rows indica las filas de la tabla.
     */
    public String head(int rows) {
        StringBuilder encabezado = new StringBuilder();
        for(int j = 0; j < columns.length; j++) {
            encabezado.append(columns[j]);
            encabezado.append("   ");
        }
        encabezado.append("\n");
            
        int limite = Math.min(rows, data.length);
        for(int i = 0; i < limite; i++) {
            encabezado.append(i);
            encabezado.append("   ");
            for(int k = 0; k < columns.length; k++) {
                   encabezado.append(data[i][k]);
                   encabezado.append("   ");
                }
                encabezado.append("\n");
            }
            String resultado = encabezado.toString();
            return resultado;
        } // Cierre del método.
    
    /**
     * Verifica si el DataFrame es igual.
     * @param df El parámetro df es un DataFrame para verificar si es igual a otro.
     * @return Un booleano dependiendo si es igual o no.
     */
    public boolean equals(DataFrame df){
        return false;
    } // Cierre del método.
    
    /**
     * Verifica si el DataFrame es igual.
     * @param o El parámetro o es un Objeto que verifica si es igual a otro objeto.
     * @return Un booleano dependiendo si es igual o no.
     */
    public boolean equals(Object o){
        return equals((DataFrame)o);
    } // Cierre del método.
    
    /**
     * Método que selecciona filas específicas según sus índices.
     * @param rows Índices de las filas a seleccionar.
     * @return Un nuevo DataFrame con las filas seleccionadas.
     */
    public DataFrame selectRows(int[] rows) {
        String[][] newData = new String[rows.length][this.columns.length];
        for (int i = 0; i < rows.length; i++) {
            int rowIndex = rows[i];
            if (rowIndex >= 0 && rowIndex < this.data.length) {
                newData[i] = this.data[rowIndex];
            } else {
                throw new IllegalArgumentException("Índice de fila fuera de rango: " + rowIndex);
            }
        }
        return new DataFrame(newData, this.columns);
    } // Cierre del método.

    /**
     * Método que selecciona columnas específicas según sus nombres.
     * @param colNames Nombres de las columnas a seleccionar.
     * @return Un nuevo DataFrame con las columnas seleccionadas.
     */
    public DataFrame selectColumns(String[] colNames) {
        int[] colIndices = new int[colNames.length];
        // Encontrar los índices de las columnas solicitadas
        for (int i = 0; i < colNames.length; i++) {
            colIndices[i] = -1;
            for (int j = 0; j < this.columns.length; j++) {
                if (this.columns[j].equals(colNames[i])) {
                    colIndices[i] = j;
                    break;
                }
            }
            if (colIndices[i] == -1) {
                throw new IllegalArgumentException("Columna no encontrada: " + colNames[i]);
            }
        }

        // Extraer los datos de esas columnas
        String[][] newData = new String[this.data.length][colNames.length];
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < colNames.length; j++) {
                newData[i][j] = this.data[i][colIndices[j]];
            }
        }
        return new DataFrame(newData, colNames);
    } // Cierre del método.

    /**
     * Método que filtra las filas que cumplen una condición exacta (columna == valor).
     * @param colName Nombre de la columna a evaluar.
     * @param value Valor que debe tener la columna.
     * @return Un nuevo DataFrame con las filas filtradas.
     */
    public DataFrame filterCondition(String colName, String value) {
        int colIndex = -1;
        for (int j = 0; j < this.columns.length; j++) {
            if (this.columns[j].equals(colName)) {
                colIndex = j;
                break;
            }
        }
        if (colIndex == -1) throw new IllegalArgumentException("Columna no encontrada: " + colName);

        // Contar cuántas filas cumplen la condición para definir el tamaño de la matriz
        int count = 0;
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i][colIndex].equals(value)) {
                count++;
            }
        }

        // Llenar la nueva matriz
        String[][] newData = new String[count][this.columns.length];
        int helper = 0;
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i][colIndex].equals(value)) {
                newData[helper] = this.data[i];
                helper++;
            }
        }
        return new DataFrame(newData, this.columns);
    } // Cierre del método.
}
