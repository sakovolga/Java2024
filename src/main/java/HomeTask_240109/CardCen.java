package HomeTask_240109;

import java.time.LocalDate;
import java.util.Random;

public class CardCen {
    private static int resSum;
    private static final Random random = new Random();

    public static String genUniqueCardNum() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public static boolean isTrue(String cardNum) {
        String[] strings = cardNum.split("");
        int sum1 = 0;
        int sum2 = 0;
        int arg1;
        for (int i = 0; i < strings.length; i++) {
            if (i % 2 == 0) {
                arg1 = Integer.parseInt(strings[i]) * 2;
                if (arg1 > 9) {
                    String[] res = String.valueOf(arg1).split("");
                    int num = Integer.parseInt(res[0]) + Integer.parseInt(res[1]);
                    sum1 += num;
                } else {
                    sum1 += arg1;
                }
            } else {
                sum2 += Integer.parseInt(strings[i]);
            }
        }
        resSum = sum1 + sum2;
        return resSum % 10 == 0;
    }

    public static String fixCardNum(String wrongCard) {
        int delta = resSum % 10;
        int gamma = 10 - delta;
        String[] cardArray = wrongCard.split("");
        if (Integer.parseInt(cardArray[1]) - delta >= 0) {
            cardArray[1] = String.valueOf(Integer.parseInt(cardArray[1]) - delta);
        } else {
            cardArray[1] = String.valueOf(Integer.parseInt(cardArray[1]) + gamma);
        }
        return String.join("", cardArray);
    }

    public static String generateValidity() {
        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();
        int currentYear = currentDate.getYear();
        String year = String.valueOf((random.nextInt(currentYear, currentYear + 6)) - 2000);
        int month;
        if (year.equals(String.valueOf(currentYear - 2000))){
            month = random.nextInt(currentMonth, 13);
        } else if (year.equals(String.valueOf(currentYear + 5 - 2000))) {
            month = random.nextInt(1, currentMonth + 1);
        } else {
            month = random.nextInt(1, 13);
        }
        String correctMonth;
        if (month > 9) {
            correctMonth = String.valueOf(month);
        } else {
            correctMonth = "0" + month;
        }
        return correctMonth + year;
    }

    public static String generateCVV() {
        String[] cvv = new String[3];
        for (int i = 0; i < 3; i++) {
            cvv[i] = String.valueOf(random.nextInt(10));
        }
        return String.join("", cvv);
    }

    public static String generateCard() {
        String cardNum = genUniqueCardNum();
        String correctCardNum;
        if (!isTrue(cardNum)) {
            correctCardNum = fixCardNum(cardNum);
        } else correctCardNum = cardNum;
        String validity = generateValidity();
        String cvv = generateCVV();
        return correctCardNum + validity + cvv;
    }
}
