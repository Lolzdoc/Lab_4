package main;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by dat12hri on 07/05/14.
 */
public class Finder {

    public static void main(String[] args) {
        Klass oneSelf = new Klass();
        ArrayList<Dot> list;
        Reader read = new Reader();
        final File folder = new File("lab4");
        StringBuilder build = new StringBuilder();
        try {

            PrintWriter outgang = new PrintWriter(new File("utfile.txt"));
            File[] files;
            files = folder.listFiles();
            List<File> hej = Arrays.asList(files); // Fixa linux dumheter

            Collections.sort(hej);
            for (final File fileEntry : hej) {
                list = read.readFile(fileEntry.getName());
                Collections.sort(list);
                ArrayList<Dot> sista = oneSelf.search(list);
                System.out.println("../data/"+fileEntry.getName()+": "+list.size()+" "+sista.get(0).getDistance(sista.get(1)));
                build.append("../data/" + fileEntry.getName() + ": "
                        + list.size() + " "
                        + sista.get(0).getDistance(sista.get(1)) + "\n");
            }

            outgang.print(build.toString());
            System.out.println(build.toString());
            outgang.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
