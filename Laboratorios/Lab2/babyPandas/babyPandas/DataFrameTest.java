import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Permite realizar casos para comprobar que el código funciona.
 * @autor: Juan Pablo Cuervo Contreras, David Felipe Ortiz Salcedo
 * @version: 21/02/2026
 */

public class DataFrameTest{
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp(){
        
    }
    
    /**
     * Verifica que se pueda crear un DataFrame pequeño.
     */
    @Test
    public void shouldCreateSmallestDataFrame(){
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data={};
        int [] shape={0,3};
        DataFrame df=new DataFrame(data,columns);
        assertArrayEquals(shape, df.shape());     
    } // Cierre del caso de prueba.
    
    /**
     * Verifica que se pueda crear un DataFrame completo.
     */
    @Test
    public void shouldCreateOtherDataFrame(){
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data = {{"Carlos", "35", "Profesor"}, 
        {"Ana", "42", "Doctor"}, 
        {"Jorge", "30", "Arquitecto"},
        {"Elena", "25", "Diseñador"}};
        int [] shape={4,3};
        DataFrame df=new DataFrame(data,columns);
        assertArrayEquals(shape, df.shape());   
    } // Cierre del caso de prueba.
    
    /**
     * Verifica que no se pueda crear un DataFrame incompleto.
     */
    @Test
    public void shouldNotCreateBadDataFrame(){
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data = {{"Carlos", "35"}, 
        {"Ana", "42", "Doctor"}, 
        {"30", "Arquitecto"},
        {"Elena", "25", "Diseñador"}};
        int [] shape={2,3};
        DataFrame df=new DataFrame(data,columns);
        assertArrayEquals(shape, df.shape());   
    } // Cierre del caso de prueba.  
    
    /**
     * Verifica que 15 = 10 + 5.
     */
    @Test
    public void shouldPass() {
        assertEquals(15, 10 + 5);
    }
    
    /**
     * Verifica que si 4 = (10 / 2) -> 5.
     */
    @Test
    public void shouldFail() {
        assertEquals(4, 10 / 2);
    }
    
    /**
     * Verifica si la suma es mayor a 9 (8 > 9).
     */
    @Test
    public void shouldErr() {
        int suma =  5 + 3;
        try {
          boolean operacionAFallar = suma > 9;
          fail("8 no es mayor a 9.");
        } catch(Exception e) {
            boolean operacionIgual = suma == 8;
            System.out.println("¡Exacto!");
        }
    }
    
    @Test
    (expected = IllegalArgumentException.class)
    public void shouldHaveAName() {
        BabyPandas baby = new BabyPandas();
        baby.define("");
    }
    
    @Test
    (expected = IllegalArgumentException.class)
    public void shouldNotNameNull() {
        BabyPandas baby = new BabyPandas();
        baby.define(null);
    }
    
    @Test
    (expected = NullPointerException.class)
    public void shouldNotAssignNull() {
        BabyPandas baby = new BabyPandas();
        baby.assign(null, null, null);
    }
    
    /**
     * Verifica que las filas seleccionadas sean las correctas.
     */
    @Test
    public void shouldSelectRowsCorrectly() {
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data = {
            {"Carlos", "35", "Profesor"}, 
            {"Ana", "42", "Doctor"}, 
            {"Jorge", "30", "Arquitecto"}
        };
        DataFrame df = new DataFrame(data, columns);
        
        // Seleccionamos la fila 0 y 2
        DataFrame subset = df.selectRows(new int[]{0, 2});
        int [] expectedShape = {2, 3}; // Quedan 2 filas y 3 columnas
        assertArrayEquals(expectedShape, subset.shape());   
    } // Cierre del caso de prueba.
    
    /**
     * Verifica que las columnas seleccionadas sean las correctas.
     */
    @Test
    public void shouldSelectColumnsCorrectly() {
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data = {
            {"Carlos", "35", "Profesor"}, 
            {"Ana", "42", "Doctor"}
        };
        DataFrame df = new DataFrame(data, columns);
        
        // Seleccionamos solo la columna "Nombre" y "Profesión"
        DataFrame subset = df.selectColumns(new String[]{"Nombre", "Profesión"});
        int [] expectedShape = {2, 2}; // Quedan 2 filas y 2 columnas
        assertArrayEquals(expectedShape, subset.shape());   
    } // Cierre del caso de prueba.
    
    /**
     * Filtra por una condición correcta.
     */
    @Test
    public void shouldFilterByConditionCorrectly() {
        String [] columns = {"Nombre", "Edad", "Profesión"};
        String [][] data = {
            {"Carlos", "35", "Profesor"}, 
            {"Ana", "42", "Doctor"},
            {"Luis", "35", "Ingeniero"}
        };
        DataFrame df = new DataFrame(data, columns);
        
        // Filtramos donde la Edad sea "35"
        DataFrame subset = df.filterCondition("Edad", "35");
        int [] expectedShape = {2, 3}; // Deben quedar Carlos y Luis (2 filas)
        assertArrayEquals(expectedShape, subset.shape());   
    } // Cierre del caso de prueba.
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
