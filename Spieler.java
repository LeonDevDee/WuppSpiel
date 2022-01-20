 import java.util.Scanner;
/**
 *
 */
public class Spieler extends Person 
{
    /*Konstruktor*/
    private String name;
    
    public Spieler(String name)
    {
        this.name = name;
    }
    /* Methoden */
    /**
     * Diese Methode gibt zunächst eine entscheidungsfrage auf der 
     * Konsole aus und gibt anschließend die Benutzereingabe zurück
     * @return entscheidung
     */
    public boolean entscheide(){
        if(this.gibAktErgebnis()>=21)
        {
            System.out.println("Sie haben eine "+wuerfel.gibAktWert()+" gewürfelt. Ihr aktueller Würfelwert beträgt "+this.gibAktErgebnis()+", der Croupier ist an der Reihe!");
            return false;
        }
        else
        {
            Scanner sc = new Scanner(System.in);
            String eingabe = sc.next();

            if(eingabe.equals("j"))
            {
                return true;
            }
            else
            {
                return false;
            }
        }
    }

    public void spielen()
    {
        this.setzeAktErgebnis(0);
        for(int i  = 0; i < 3; i = i + 1)
        {
            wuerfel.wuerfeln();
            this.setzeAktErgebnis(this.gibAktErgebnis()+wuerfel.gibAktWert());
            if(i < 2) System.out.println("Sie haben eine "+wuerfel.gibAktWert()+" gewürfelt. Ihr aktueller Würfelwert beträgt "+this.gibAktErgebnis()+", sie würfeln weiter...");
            else System.out.println("Sie haben eine "+wuerfel.gibAktWert()+" gewürfelt. Ihr aktueller Würfelwert beträgt "+this.gibAktErgebnis()+", möchten Sie weiter würfeln?");
            warte();
        }
        while(entscheide() == true)
        {
            wuerfel.wuerfeln();
            this.setzeAktErgebnis(this.gibAktErgebnis()+wuerfel.gibAktWert());
            if(this.gibAktErgebnis() < 21) System.out.println("Sie haben eine "+wuerfel.gibAktWert()+" gewürfelt. Ihr aktueller Würfelwert beträgt "+this.gibAktErgebnis()+", möchten Sie weiterspielen?");
        }
    }
    
    public String gibName()
    {
        return this.name;
    }
}//Ende Klasse: Spieler

