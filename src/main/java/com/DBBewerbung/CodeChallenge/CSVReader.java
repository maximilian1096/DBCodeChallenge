/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DBBewerbung.CodeChallenge;

import java.io.*;
import java.net.URL;
import java.nio.channels.*;
import org.apache.commons.csv.*;
import org.springframework.stereotype.Service;

/**
 *
 * @author maximilian
 */
@Service
public class CSVReader {

    private static final String CSV_NAME = "src/main/resources/DBNetz-Betriebsstellenverzeichnis-Stand2021-10.csv";
    
    //lade Betriebsstellenverzeichnis herunter
    public void downloadCSV() throws IOException {
        String url_str = "https://download-data.deutschebahn.com/"
                + "static/datasets/betriebsstellen/DBNetz-Betriebsstellenverzeichnis-Stand2021-10.csv";

        //erzeuge stream and channel zu Betriebsstellenverzeichnis
        URL DBwebsite = new URL(url_str);
        ReadableByteChannel readableByteChannel = Channels.newChannel(DBwebsite.openStream());
        
        //erzeuge Stream zur Datei, wo Betriebsstellenverzeichnis auf PC abgespeichert werden soll
        try ( var fos = new FileOutputStream(CSV_NAME)) {
            //übertrage von Betriebsstellenverzeichnis in download-data.deutschebahn.com zu auf PC abgespeicherten CSV-Datei
            fos.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
            fos.close();
        //CSV-Datei wurde nicht gefunden oder generiert
        } catch (FileNotFoundException fnfe) {
            System.out.print(" Can't find, open or create the file. \n");
            
        //Kein Zugriff auf CSV-Datei
        } catch (SecurityException se) {
            System.out.println("System denied write access to file ");
        }

    }
    
    //speichere CSV-Datei in Datenbank
    //Argumente: InputStream zu CSV-Datei und BetriebsstelleRepository von Datenbank
    public void saveInDatabase(InputStream is, BetriebsstelleRepository betriebsstelleRepository) {
        
        //erzeuge BufferedReader und CSVParser zum Auslesen der Datei, wobei Header übersprungen wird 
        try ( BufferedReader fileReader = new BufferedReader(new InputStreamReader(is));  CSVParser csvParser = new CSVParser(fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            
            //csvRecords "enthält" CSV-Datei 
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            //for-Schleife über alle Zeilen der CSV-Datei
            for (CSVRecord csvRecord : csvRecords) {
                
                //erzeuge neue Betirebsstelle, trenne Zeil der CSV-Datei bei ;, weise Werte von betriebsstelle zu und speichere ab
                Betriebsstelle betriebsstelle = new Betriebsstelle();
                String[] val = csvRecord.toString().split(";");
                betriebsstelle.setAbkuerzung(val[1].toLowerCase());
                betriebsstelle.setName(val[2]);
                betriebsstelle.setKurzname(val[3]);
                betriebsstelle.setTyp(val[5]);

                betriebsstelleRepository.save(betriebsstelle);

            }
        //Fehler bei Verbindung
        } catch (IOException e) {
            throw new RuntimeException("Fehler beim Einlesen der CSV-Datei: " + e.getMessage());
        }
    }
}
