package domain;  
 
import java.util.ArrayList;

public class Team extends Participant{
   
    private String manager;
    private String uniform;
    
    private ArrayList<Player> players;
    
    /**
     * Constructs a new Team
     * @param name
     * @param type
     */
    public Team(String name, int minutes, char position, String manager, String uniform){
        super(name, minutes, position);
        this.manager=manager;
        this.uniform=uniform;
        players= new ArrayList<Player>();
    }


     /**
     * Add a new Player
     * @param c
     */   
    public void addPlayer(Player c){
        players.add(c);
    }
       
 
   public int marketValue() throws FifaException{ 
        for(Player p: players) {
            int minutos = p.minutes();
            if(minutos <= 0) {
                throw new FifaException(FifaException.MINUTES_UNKNOWN);
            }
        }
        
        int total = 0;
        for(Player p: players) {
            int valor = p.marketValue();
            if(valor < 0) {
                throw new FifaException(FifaException.VALUE_UNKNOWN);
            }
            
            int minutos = p.minutes();
            if(minutos < 0) {
                throw new FifaException(FifaException.IMPOSSIBLE);
            }
            total += valor;
        }
        return total;
    }

   /**
     * Returns the expectet Market Value 
     * @return
     * @throws FifaException, if any marker value or minutes is unknown
     */
    //If more than half of the players have no recorded minutes, the total number of players is used to average. 
    //Otherwise, the average minutes played by known players is used for those whose minutes are unknown.
    
    public int expectedMarketValue() throws FifaException{
        for(Player p: players) {
            int minutos = p.minutes();
            if(minutos < 0) {
                throw new FifaException(FifaException.MINUTES_UNKNOWN);
            }
        }
        
        int total = 0;
        for(Player p: players) {
            int valor = p.marketValue();
            if(valor < 0) {
                throw new FifaException(FifaException.VALUE_UNKNOWN);
            }
            
            if(valor > 50) {
                try {
                    total += p.minutes();
                } catch (FifaException e) {
                    throw new FifaException(FifaException.IMPOSSIBLE);
                }
            }
        }
        return total;
    }
    
    
    /**
     * Returns the Marked Value using default values 
     * @return
     * @throws FifaException, if the resistance cannot be calculate
     */
    //If a player's market value or minutes played are unknown, default values ​​are used.
    public int defaultMarkedValue(int defaultMarketValue, int defaultMinutes) throws FifaException{
        int total = 0;
        for(Player p: players) {
            int valor = p.marketValue();
            int minutos = p.minutes();
            
            if(valor < 0 && minutos < 0) {
                throw new FifaException(FifaException.IMPOSSIBLE);
            }
            
            if(valor < 0) {
                valor = defaultMarketValue;
            }
            
            if(minutos < 0) {
                minutos = defaultMinutes;
            }
            
            if(valor > 50) {
                total += minutos;
            }
        }
        return total;
    }
    
    /**
     * 
     */
    public int bestMarkedValue() throws FifaException {
        for(Player p: players) {
            int minutos = p.minutes();
            if(minutos < 0) {
                throw new FifaException(FifaException.MINUTES_UNKNOWN);
            }
            
        }
        
        int mejorValor = 0;
        for(Player p: players) {
            int valor = p.marketValue();
            if(valor < 0) {
                throw new FifaException(FifaException.VALUE_UNKNOWN);
            }
            if(valor > mejorValor) {
                mejorValor = valor;
            }
        }
        return mejorValor;
    }
    
    @Override
    public String data() throws FifaException{
        StringBuffer answer=new StringBuffer();
        answer.append(name+".\t Grupo: "+position+".\t Valor Promedio:" +marketValue());
        for(Player p: players) {
            answer.append("\n\t"+p.data());
        }
        return answer.toString();
    } 
    

}
