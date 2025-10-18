package labproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Database {

    private ArrayList<Record> records = new ArrayList<>();
    private String filename;

    public Database(String filename) {
        this.filename = filename;
    }

    public abstract Record createRecordFrom(String line);

    public void readFromFile() {
        records.clear();

        File data = new File(filename);

        try (Scanner s = new Scanner(data)) {

            while (s.hasNextLine()) {
                String dataLine = s.nextLine().trim();

                Record dataRecorded = createRecordFrom(dataLine);

                if (dataRecorded != null) {
                    records.add(dataRecorded);
                }

            }
        } catch (Exception e) {
            System.out.println("Error reading" + filename);
        }
    }

    public ArrayList<Record> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (Record r : records) {
            if (r.getSearchKey().equals(key)) {
                return true;
            }
        }

        return false;
    }

    public Record getRecord(String key) {
        for (Record r : records) {
            if (r.getSearchKey().equals(key)) {
                return r;
            }
        }

        return null;
    }

    public void insertRecord(Record record) {
        records.add(record);
    }

    public void deleteRecord(String key) {
        Record toBeDeleted = null;
        for (Record r : records) {
            if (r.getSearchKey().equals(key)) {
                toBeDeleted = r;
                break;
            }
        }
        if (toBeDeleted != null) {
            records.remove(toBeDeleted);
        }
    }

    public void saveToFile() {

        try (PrintWriter out = new PrintWriter(new FileWriter(filename, false))) {
            for (Record r : records) {
                out.println(r.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error writing file" + filename);
        }
    }

}