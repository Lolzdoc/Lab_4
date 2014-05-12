package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by hansr_000 on 2014-05-06.
 */
public class Reader {

    public Reader() {

    }

    public ArrayList<Dot> readFile(String fileName) {

        ArrayList<Dot> list = new ArrayList<Dot>();
        try {
            BufferedReader reader;
            reader = new BufferedReader(new FileReader("lab4/"+fileName));
            String a = reader.readLine();
            while (a != null) {
                String[] split_Line;
                a= a.trim();
                if (a.matches(".+\\s+\\d+\\s+\\d+")||a.matches("\\s{0,3}\\d+\\s+.?\\d+\\.?.*\\s+.?\\d+\\.?.*")) {
                    split_Line = a.trim().split(" +");
                    list.add(new Dot(split_Line[0], Double.parseDouble(split_Line[1]), Double.parseDouble(split_Line[2])));
                }
                a = reader.readLine();
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;


    }




}
