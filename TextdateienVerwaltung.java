import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.nio.charset.Charset;

public class TextdateienVerwaltung
{
    // Bezugsobjekte

    // Attribute
    String path  = "U:\\Eigene Dateien\\Informatik\\Dateien\\";
    
    // Konstruktor
    public TextdateienVerwaltung(String filename)
    {
        path = path + filename;
    }
    // Dienste
    
    public boolean DateiExistiert(){
        File file = new File(path);
        return file.exists();
    }
    
    public void DateiBeschreiben(List<Eintrag> list){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));
            
            list.toFirst();
            while(list.hasAccess()){
                bw.write(list.getContent().gibName());
                bw.newLine();
                bw.write(list.getContent().gibPunkte() + "");
                bw.newLine();
                list.next();
            }
            
            bw.close();
        }
        catch(IOException exception){
            exception.printStackTrace();
        }
    }

    public List<Eintrag> DateiLesen(){
        List<Eintrag> list = new List<Eintrag>(); 
        try(BufferedReader br = new BufferedReader(new FileReader(path))) {    


            String name = br.readLine();
            int punkte = 0;
            if(name != null)
                punkte = Integer.parseInt(br.readLine());
            while (name != null) { 
                list.append(new Eintrag(name, punkte)); 
                name = br.readLine(); 
                if(name != null)
                    punkte = Integer.parseInt(br.readLine());
            } 
            
            br.close();
        }catch(IOException exception){
            exception.printStackTrace();
        } 
        return list;
    }
}
