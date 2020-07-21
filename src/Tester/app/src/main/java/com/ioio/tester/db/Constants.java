package com.ioio.tester.db;

import com.ioio.tester.db.model.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Constants {

    public static String USERNAME_KEY = "USERNAME_KEY";
    public static String RIGHT_ANSWERS_KEY = "RIGHT_ANSWERS_KEY";
    public static Question[] generateQuestions(){
        Question[] result = new Question[5];
        // 0
        result[0] = new Question();
        ArrayList<String> answers = new ArrayList<>();
        answers.add("2");
        answers.add("3");
        answers.add("7");
        answers.add("1");
        String rightAnswer = "2";
        result[0].answers.addAll(answers);
        result[0].rightAnswer = rightAnswer;
        result[0].question = "5 свечей горело. Две потухли. Сколько свечей осталось?";
        result[0].number = 0;

        // 1
        result[1] = new Question();
        ArrayList<String> answer1 = new ArrayList<>();
        answer1.add("4");
        answer1.add("10");
        answer1.add("1");
        answer1.add("25");
        String rightAnswer1 = "25";
        result[1].answers.addAll(answer1);
        result[1].rightAnswer = rightAnswer1;
        result[1].question = "На двух руках 10 пальцев. Сколько пальцев на 5 руках?";
        result[1].number = 1;

        // 2
        result[2] = new Question();
        ArrayList<String> answer2 = new ArrayList<>();
        answer2.add("7");
        answer2.add("2");
        answer2.add("6");
        answer2.add("8");
        String rightAnswer2 = "6";
        result[2].answers.addAll(answer2);
        result[2].rightAnswer = rightAnswer2;
        result[2].question = "Сколько будет два плюс два умноженное на два?";
        result[2].number = 2;

        // 3
        result[3] = new Question();
        ArrayList<String> answer3 = new ArrayList<>();
        answer3.add("9");
        answer3.add("10");
        answer3.add("11");
        answer3.add("8");
        String rightAnswer3 = "9";
        result[3].answers.addAll(answer3);
        result[3].rightAnswer = rightAnswer3;
        result[3].question = "Бревно нужно распилить на 10 частей, сколько надо сделать распилов?";
        result[3].number = 3;

        // 4
        result[4] = new Question();
        ArrayList<String> answer4 = new ArrayList<>();
        answer4.add("90");
        answer4.add("60");
        answer4.add("30");
        answer4.add("0");
        String rightAnswer4 = "60";
        result[4].answers.addAll(answer4);
        result[4].rightAnswer = rightAnswer4;
        result[4].question = "Укол делают каждые полчаса, сколько нужно минут для трех уколов?";
        result[4].number = 4;

        return result;
    }
}
