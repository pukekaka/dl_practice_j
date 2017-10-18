package dogtest.test;

import java.util.List;
import java.util.ArrayList;

public class coursetest {
    public static void main(String[] args){

        List<Integer> front = new ArrayList<Integer>();

        List<Integer> back = new ArrayList<Integer>();
        List<String> operator = new ArrayList<String>();

        operator.add("*");
        operator.add("+");
        operator.add("-");
        operator.add("/");

        for (int i=1; i<=1000; i++){
            front.add(i);
        }

        for (int j=1; j<=4; j++){
            back.add(j);
        }

        int count = 0;
        int result = 0;
        int total = 0;
        int hcount = 0;

        for (int loop=0; loop<front.size(); loop++){

            //System.out.println(count);


            if(operator.get(count).equals("*")){
                result = front.get(loop) * back.get(count);
            }

            if(operator.get(count).equals("+")){
                result = front.get(loop) + back.get(count);
            }

            if(operator.get(count).equals("-")){
                result = front.get(loop) - back.get(count);
            }

            if(operator.get(count).equals("/")){
                result = front.get(loop) / back.get(count);
            }

            //System.out.println(total);

            total = result + total;


            if(total>100){
                total = 0;
                //System.out.println(total + ", " + count +", "+hcount);
                hcount++;
            }


            if(count == 3){
                count = 0;
            }
            else{
                count++;
            }
        }

        System.out.println(hcount);

    }
}
