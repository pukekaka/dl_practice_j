package poodle;

import poodle.utils.InitData;
import poodle.utils.Learner;

public class Main {
    public static void main(String args[]){
        InitData init = new InitData();
        init.setDefault();

        if(init.getMode().equals("train")){
            Learner learner = new Learner();
            learner.train(init);
        }


    }

}
