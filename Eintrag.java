/**
 * Die Klasse Eintrag dient dem Speichern der Highscorepunkte mit Namen
 */
public class Eintrag
{
    private int punkte;
    private String name;

    /**
     * Konstruktor der Klasse Eintrag
     * @param punkte : int
     * @param name : String
     */
    public Eintrag(String name, int punkte)
    {
        this.punkte = punkte;
        this.name = name;
    }

    /**
     * Diese Methode gibt die Punkte des Eintrages zurück
     * 
     * @return punkte : int
     */
    public int gibPunkte()
    {
        return this.punkte;
    }
    
    /**
     * Diese Methode gibt den Namen des Eintrages zurück
     * 
     * @return name : String
     */
    public String gibName()
    {
        return this.name;
    }          
}
