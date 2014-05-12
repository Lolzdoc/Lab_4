package main;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import main.Dot;
import main.Reader;

public class Klass {
	

	public ArrayList<Dot> search(ArrayList<Dot> inputList) {
		int n = inputList.size();
		if (n > 3) {
			
			
			int i = 0;
			ArrayList<Dot> left = new ArrayList<Dot>();
			ArrayList<Dot> right = new ArrayList<Dot>();
			ArrayList<Dot> left_Closest;
			ArrayList<Dot> right_Closest;
			ArrayList<Dot> centerPointList = new ArrayList<Dot>();
			Dot center_Left = inputList.get(n / 2 - 1);
			Dot center_right = inputList.get(n / 2);
			double Delta;
			Iterator<Dot> inputListIterator = inputList.iterator();
			
			while (i < n / 2) {
				left.add(inputList.get(i));			//Delar upp höger och vänster
				i++;
			}
			while (i < n) {
				right.add(inputList.get(i));
				i++;
			}

			left_Closest = search(left);		//Rekursiv anrop
			right_Closest = search(right);

			// Efter rekursivt anrop

			Dot left_1 = left_Closest.get(0);
			Dot left_2 = left_Closest.get(1);
			Dot right_1 = right_Closest.get(0);
			Dot right_2 = right_Closest.get(1);
			Dot last_Dot_Start;
			Dot last_Dot_Finish;

			double distance_Left = left_1.getDistance(left_2);
			double distance_Right = right_1.getDistance(right_2);

			if (distance_Left < distance_Right) {
				Delta = distance_Left;
				last_Dot_Start = left_1;
				last_Dot_Finish = left_2;
			} else {
				Delta = distance_Right;
				last_Dot_Start = right_1;
				last_Dot_Finish = right_2;
			}

			// Här kollas punkterna runt center linjen

			// double center = left_2.getX() + (left_2.getX() - right_1.getX())
			// / 2;
			double midDiff = (center_Left.getX() - center_right.getX()) / 2;
			double center = center_Left.getX() + midDiff;
			inputListIterator = inputList.iterator();
			while (inputListIterator.hasNext()) {
				Dot temp = inputListIterator.next();
				if (temp.getX() > center - Delta
						&& temp.getX() < center + Delta) {
					centerPointList.add(temp);
					// System.out.println("Heh "+temp.toString());
				}
			}

			// Här hittas det närmsta paret i centerPointList

			Dot mid_Point_1 = null;
			Dot mid_point_2 = null;
			double dist = Integer.MAX_VALUE;
			// System.out.println(centerPointList.size());
			if (centerPointList.size() > 1) {
				for (int d = 0; d < centerPointList.size(); d++) {
					for (int j = 0; j < centerPointList.size(); j++) {

						if (!centerPointList.get(j).equals(
								centerPointList.get(d))) {
							double tempDist = centerPointList.get(d)
									.getDistance(centerPointList.get(j));
							if (tempDist < dist) {
								dist = tempDist;
								mid_Point_1 = centerPointList.get(d);
								mid_point_2 = centerPointList.get(j);
							}
						}

					}
				}
			}

			// Här kollas om någon av punkterna vid linjen är närmre varandra en
			// de tidigare funna

			ArrayList<Dot> last = new ArrayList<Dot>();
			if (dist < Delta) {
				last.add(mid_Point_1);
				last.add(mid_point_2);
				// Delta = dist;
				// last.add(mid.get(0));
				// last.add(mid.get(1));

			} else {
				last.add(last_Dot_Start);
				last.add(last_Dot_Finish);
			}
			return last;
		}

		return findClosest(inputList);
	}

    public ArrayList<Dot> findClosest(ArrayList<Dot> lista) {
        if (lista.size() < 3) {
            return lista;
        }

        double delta;
        Dot current_start;
        Dot current_finish;
        ArrayList<Dot> pair = new ArrayList<Dot>();

        Dot dot_1 = lista.get(0);
        Dot dot_2 = lista.get(1);
        Dot dot_3 = lista.get(2);

        delta = dot_1.getDistance(dot_2);
        current_start = dot_1;
        current_finish = dot_2;

        if (dot_1.getDistance(dot_3) < delta) {
            delta = dot_1.getDistance(dot_3);
            current_finish = dot_3;
        }
        if (dot_2.getDistance(dot_3) < delta) {
            delta = dot_2.getDistance(dot_3);
            current_start = dot_2;
            current_finish = dot_3;
        }

        pair.add(current_start);
        pair.add(current_finish);
        Collections.sort(pair);
        return pair;


    }

}
