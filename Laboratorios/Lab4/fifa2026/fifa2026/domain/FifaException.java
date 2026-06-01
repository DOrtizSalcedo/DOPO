package domain;

public class FifaException extends Exception {
    public static final String MINUTES_UNKNOWN = "Minutos desconocidos.";
    public static final String VALUE_UNKNOWN = "Valor desconocido.";
    public static final String IMPOSSIBLE = "La operación es imposible de realizar.";
    public static final String PLAYER_ALREADY_EXISTS = "El jugador ya existe.";
    public static final String CLUB_UNKNOWN = "El club es desconocido.";
    public static final String PLAYER_UNKNOWN = "El jugador no existe.";
    public static final String INVALID_NUMBER = "El valor númerico no es entero.";
    public static final String INVALID_NAME = "Este nombre no es válido.";
    public static final String CLUB_ALREADY_EXISTS = "El nombre de este club ya existe.";
    
    public FifaException(String message) {
        super(message);
    }
}