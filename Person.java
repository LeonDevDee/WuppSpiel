/**
 *
 */
public abstract class Person 
{
    /* Attribute */
    protected Wuerfel wuerfel;
    private int aktErgebnis;
    private int rundengewinne;
    private int spielpunkte;
    
    /*Konstruktor*/
    public Person()
    {
        wuerfel = new Wuerfel();
        rundengewinne = 0;
        spielpunkte = 0;
        aktErgebnis = 0;
    }
    /* Methoden */
    /** 
     * Diese Methode setzt den Attributwert von aktErgebnis 
     *  auf den Wert aus pAktErgebnis
     */
    public void setzeAktErgebnis(int  pAktErgebnis){
        this.aktErgebnis = pAktErgebnis;
    }

    /**
     * Diese Methode setzt die Spiellogik im Spielerobjekt um
     */
    public abstract void spielen();

    public void setzeRundengewinne(int wert)
    {
        this.rundengewinne = wert;
    }
    
    public int gibRundengewinne()
    {
        return this.rundengewinne;
    }
    
    public void setzeSpielpunkte(int wert)
    {
        this.spielpunkte = wert;
    }
    
    public int gibSpielpunkte()
    {
        return this.spielpunkte;
    }
    
    /**
     * Diese Methode gib den aktuellen Attributwert von aktErgebnis aus 
     * @return liefert aktErgebnis 
     */
    public int gibAktErgebnis(){
        return this.aktErgebnis;
    }
    
    

    /**
     * Diese Methode hält den Prozessor für 1 Sekunde lang an, bevor der Prozessor weiter rechnet.
     */
    public void warte()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}//Ende Klasse: Person

