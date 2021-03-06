import java.util.Scanner;
import java.util.Random;

/**
 *
 */
public class Spiel 
{
    /* Attribute */    
    private Person aktuellerSpieler;
    private Croupier croupier;

    private Spieler spieler;

    private List<Eintrag> highscore;
    private TextdateienVerwaltung tv;

    /*Konstruktor*/
    public Spiel()
    {      
        tv = new TextdateienVerwaltung("wuppSpielHighscores.txt");
        
        if(tv.DateiExistiert()){
            highscore = tv.DateiLesen();
        }else{
            highscore = new List<Eintrag>();
        }
        
        
        starteSpiel();
    }

    /* Methoden */
    /**
     * Diese Methode liefert den aktuellen Spieler als Objekt 
     * @return liefert aktuellerSpieler 
     */
    private Person gibAktuellerSpieler()
    {
        return this.aktuellerSpieler;
    }

    /** 
     * Diese Methode setzt das Attribut aktuellerSpieler auf den 
     * ├╝bergebenen Wert aus pAktuellerSpieler
     * @param pAktuellerSpieler
     */
    private void setzeAktuellerSpieler(Person  pAktuellerSpieler)
    {
        this.aktuellerSpieler = pAktuellerSpieler;
    }

    /**
     * Diese Methode druckt den aktuellen Punktestand auf der Konsole
     */
    private void druckePunktestand()
    {
        System.out.println("-----------------Punktestand nach Spielen-----------------");
        System.out.println("Spieler \t "+spieler.gibRundengewinne()+" : "+croupier.gibRundengewinne()+" \t\t Croupier");
        System.out.println("-----------------Punktestand nach Wertung-----------------");        
        System.out.println("Spieler \t "+spieler.gibSpielpunkte()+" : "+croupier.gibSpielpunkte()+" \t\t Croupier");
        System.out.println("---------------------------------------------");
    }

    /**
     * Diese Methode druckt den aktuellen Punktestand auf der Konsole
     */
    private void druckePunktestand(int sp)
    {
        System.out.println("-----------------Punktestand nach Spielen-----------------");
        System.out.println("Spieler \t "+spieler.gibRundengewinne()+" : "+croupier.gibRundengewinne()+" \t\t Croupier");
        System.out.println("-----------------Punktestand nach Wertung-----------------");        
        System.out.println("Spieler \t "+sp+" : "+croupier.gibSpielpunkte()+" \t\t Croupier");
        System.out.println("---------------------------------------------");
    }

    /**
     * Diese Methode wechselt den aktuellen Spieler auf der 
     * Grundlage der Belegung des Attributes aktuellerSpieler
     */
    private void spielerWechseln()
    {
        if(aktuellerSpieler.equals(croupier))
        {
            aktuellerSpieler = spieler;
        }
        else
        {
            aktuellerSpieler = croupier;
        }
    }

    /**
     * Diese Methode gibt den Gesamtsieger als Objekt zur├╝ck
     * @return Person die gewonnen hat
     */
    private Person ermittleGesamtsieger()
    {
        if(spieler.gibRundengewinne() >= 3)
        {
            return spieler;
        }
        else if(croupier.gibRundengewinne() >= 3)
        {
            return croupier;
        }
        else
        {
            return null;
        }
    }

    /**
     * Diese Methode gibt den Rundensieger als Objekt zur├╝ck
     * @return Person die die Runde gewonnen hat
     */
    private Person ermittleRundensieger()
    {
        if(spieler.gibAktErgebnis() > croupier.gibAktErgebnis() && spieler.gibAktErgebnis() <= 21 || croupier.gibAktErgebnis() > 21 && spieler.gibAktErgebnis() <= 21)
        {
            if(spieler.gibAktErgebnis() == 21) spieler.setzeSpielpunkte(spieler.gibSpielpunkte() + 100 - croupier.gibAktErgebnis());
            if(croupier.gibAktErgebnis() > 21) spieler.setzeSpielpunkte(spieler.gibSpielpunkte() + 200);
            else spieler.setzeSpielpunkte(spieler.gibSpielpunkte() + spieler.gibAktErgebnis() - croupier.gibAktErgebnis());
            return spieler;
        }
        else
        {
            if(croupier.gibAktErgebnis() == 21) croupier.setzeSpielpunkte(croupier.gibSpielpunkte() + 100 - spieler.gibAktErgebnis());
            if(croupier.gibAktErgebnis() == 21 && spieler.gibAktErgebnis() == 21) croupier.setzeSpielpunkte(croupier.gibSpielpunkte() + 500);
            if(croupier.gibAktErgebnis() == spieler.gibAktErgebnis()) croupier.setzeSpielpunkte(croupier.gibSpielpunkte() + 50);
            if(spieler.gibAktErgebnis() > 21) croupier.setzeSpielpunkte(croupier.gibSpielpunkte() + 100);
            else croupier.setzeSpielpunkte(croupier.gibSpielpunkte() + croupier.gibAktErgebnis() - spieler.gibAktErgebnis());
            return croupier;
        }
    }

    /**
     * Diese Methode startet ein neues Spiel. In dieser Methode ist die 
     * Spiellogik implementiert.
     */

    public void starteSpiel()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nennen Sie ihren Namen!");
        String name = sc.next();

        croupier = new Croupier();
        spieler = new Spieler(name);
        
        
        
        this.setzeAktuellerSpieler(spieler);
        while(this.ermittleGesamtsieger() == null)
        {
            this.setzeAktuellerSpieler(spieler);
            this.starteRunde();
            this.druckePunktestand();
            warte(3000);
        }
        this.spielBeenden();
    }

    /**
     * Diese Methode startet eine Runde innerhalb eines Spiels
     */
    private void starteRunde()
    {
        System.out.println("\f");
        druckePunktestand();
        this.gibAktuellerSpieler().spielen();
        this.spielerWechseln();
        this.gibAktuellerSpieler().spielen();
        if(this.ermittleRundensieger() == spieler)
        {
            System.out.println("Sie haben die Runde gewonnen!");
            spieler.setzeRundengewinne(spieler.gibRundengewinne()+1);
        }
        else
        {
            System.out.println("Der Croupier hat die Runde gewonnen!");
            croupier.setzeRundengewinne(croupier.gibRundengewinne()+1);
        }
    }

    /**
     * Diese Methode beendet das Spiel und gibt auf der
     * Konsole ein Gru├čwort aus!
     */

    private void spielBeenden()
    {
        System.out.print("\f");
        if(this.ermittleGesamtsieger() == spieler)
        {
            druckePunktestand();
            System.out.println("Herzlichen Gl├╝ckwunsch! Sie haben das Spiel mit "+spieler.gibRundengewinne()+" : "+croupier.gibRundengewinne()+" gewonnen!");
            System.out.println("Sonderpunkte werden berechnet ....");
            int sonderptmp = spieler.gibSpielpunkte();
            punktberechnungSpielende(spieler.gibRundengewinne(), croupier.gibRundengewinne());
            Random r = new Random();

            int akt = 0;

            for(int i = sonderptmp; i < spieler.gibSpielpunkte(); i = i + 100)
            {
                System.out.print("\f");
                druckePunktestand(sonderptmp);
                System.out.println("Herzlichen Gl├╝ckwunsch! Sie haben das Spiel mit "+spieler.gibRundengewinne()+" : "+croupier.gibRundengewinne()+" gewonnen!");
                System.out.println("Sonderpunkte werden berechnet ....");
                System.out.println(i);
                warte(500);
                akt = i;
            }
            for(int i = akt; i < spieler.gibSpielpunkte(); i = i + 10)
            {
                System.out.print("\f");
                druckePunktestand(sonderptmp);
                System.out.println("Herzlichen Gl├╝ckwunsch! Sie haben das Spiel mit "+spieler.gibRundengewinne()+" : "+croupier.gibRundengewinne()+" gewonnen!");
                System.out.println("Sonderpunkte werden berechnet ....");
                System.out.println(i);
                warte(750);
                akt = i;
            }
            for(int i = akt; i < spieler.gibSpielpunkte(); i = i + 1)
            {
                System.out.print("\f");
                druckePunktestand(sonderptmp);
                System.out.println("Herzlichen Gl├╝ckwunsch! Sie haben das Spiel mit "+spieler.gibRundengewinne()+" : "+croupier.gibRundengewinne()+" gewonnen!");
                System.out.println("Sonderpunkte werden berechnet ....");
                System.out.println(i);
                warte(1000);
            }

            System.out.println("Sie haben "+spieler.gibSpielpunkte()+" erreicht!");
            spielerAbsteigendInListEintragen(spieler.gibName(), spieler.gibSpielpunkte());
        }
        else
        {
            System.out.println("Sie haben das Spiel leider mit "+spieler.gibRundengewinne()+" : "+croupier.gibRundengewinne()+" verloren!");
        }
        
        highscoreAusgeben();
        tv.DateiBeschreiben(highscore);
        starteSpiel();
    }

    /**
     * Die Methode gibt die Highscoreliste, die in der Liste highscore gespeichert ist aus.
     */
    public void highscoreAusgeben()
    {
        System.out.println("---- Highscorelist ----");
        int platz = 1;
        highscore.toFirst();
        while(highscore.hasAccess())
        {
            Eintrag aktuell = highscore.getContent();
            if(aktuell != null && platz <= 10)
            {
                if(aktuell.gibPunkte() > 0)
                {
                    System.out.println(platz+". "+aktuell.gibName()+" | "+aktuell.gibPunkte());
                }
                platz++;
            }
            highscore.next();
        }
    }

    private void spielerAbsteigendInListEintragen(String name, int punkte)
    {
        highscore.toFirst();
       
        while(highscore.hasAccess() && highscore.getContent().gibPunkte() > punkte){
            highscore.next();
        }
        
        if(highscore.hasAccess()){
            highscore.insert(new Eintrag(name,punkte));
        }else{
            highscore.append(new Eintrag(name,punkte));
        }
    }
    
    private int punktberechnungSpielende(int s, int v)
    {
        int erg = 0;
        if(s == 3 && v == 0)
        {
            spieler.setzeSpielpunkte(spieler.gibSpielpunkte() + 1000);
        }
        if(s == 3 && v == 1)
        {
            spieler.setzeSpielpunkte(spieler.gibSpielpunkte() + 500);
        }
        if(s == 3 && v == 2)
        {
            spieler.setzeSpielpunkte(spieler.gibSpielpunkte() + 250);
        }
        if(spieler.gibSpielpunkte() > croupier.gibSpielpunkte())
        {
            spieler.setzeSpielpunkte(spieler.gibSpielpunkte() + croupier.gibSpielpunkte());
        }
        else
        {
            spieler.setzeSpielpunkte(spieler.gibSpielpunkte() - croupier.gibSpielpunkte()/2);
        }
        return erg;
    }

    /**
     * Diese Methode h├Ąlt den Prozessor f├╝r 1 Sekunde lang an, bevor der Prozessor weiter rechnet.
     */
    private void warte(int s)
    {
        try
        {
            Thread.sleep(s);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}//Ende Klasse: Spiel

