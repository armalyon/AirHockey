package utils;

import java.util.Date;



public class Utils {


    public static void timer(int time){
        int t1 = (int) new Date().getTime();
        int t2 = t1;
        while (t2-t1<time){
            t2 = (int) new Date().getTime();
        }
    }



}
