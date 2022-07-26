package com.DBBewerbung.CodeChallenge;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CodeChallengeApplication {

    @Component
    public class DbInit {

        @Autowired
        public BetriebsstelleRepository betriebsstelleRepository;

        //Initialisierung der Datenbank vor Starten der Anwendung  
        @PostConstruct
        private void initialize() throws IOException {
            CSVReader csvReader = new CSVReader();

            //erstelle InputStream zur CSV-Datei
            try ( InputStream is = new FileInputStream("src/main/resources/DBNetz-Betriebsstellenverzeichnis-Stand2021-10.csv");) {
                //söpeichere CSV-Datei in Datenbank
                csvReader.saveInDatabase(is, betriebsstelleRepository);

                //wenn CSV-datei nicht existiert, downloade sie
            } catch (FileNotFoundException fnfe) {
                csvReader.downloadCSV();
                try (
                         InputStream is = new FileInputStream("src/main/resources/"
                                                          + "DBNetz-Betriebsstellenverzeichnis-Stand2021-10.csv");) {
                    csvReader.saveInDatabase(is, betriebsstelleRepository);
                } catch (FileNotFoundException fnfe2) {
                    System.out.println("FileNotFoundException: " + fnfe.getMessage());
                    System.exit(-1);
                }
            }
            System.out.println("\nApplication is started.\n");
        }
    }

    //main-Funktion
    public static void main(String[] args) throws IOException {
        SpringApplication.run(CodeChallengeApplication.class, args);
    }

}
