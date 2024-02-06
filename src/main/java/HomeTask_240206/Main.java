package HomeTask_240206;

public class Main {
    //    Сложение (add): Принимает два числа и возвращает их сумму.
    public static int getSum(int a, int b) {
        return a + b;
    }

    //    Вычитание (subtract): Принимает два числа и возвращает результат их вычитания.
    public static int getSub(int a, int b) {
        return a - b;
    }

    //    Умножение (multiply): Принимает два числа и возвращает их произведение.
    public static int getMult(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return a * b;
    }

    //            Деление (divide): Принимает два числа и возвращает результат деления
//            первого на второе. Необходимо обработать случай деления на ноль.
    public static int getDiv(int a, int b) {
        if (b == 0) throw new ArithmeticException();
        return a / b;
    }

    //    Возведение в степень (power): Принимает основание и показатель степени,
//    возвращает результат возведения числа в степень.
    public static double getPower(double base, int power) {
        if (power == 0) return 1;
        double result = 1;
        for (int i = 0; i < power; i++) {
            result *= base;
        }
        return result;
    }

    //    Факториал (factorial): Принимает число и возвращает его факториал.
//    Должна быть обработка отрицательных чисел.
    public static int getFactorial(int a) {
        if (a < 0) throw new ArithmeticException();
        if (a == 0 || a == 1) return 1;
        int result = 1;
        for (int i = 2; i <= a; i++) {
            result *= i;
        }
        return result;
    }

    //   Модуль числа (absolute): Принимает число и возвращает его абсолютное значение.
    public static double getAbsolute(double a) {
        if (a < 0) return -a;
        else return a;
    }

    //   Квадратный корень (sqrt): Принимает число и возвращает квадратный корень числа.
//       Необходимо учесть обработку отрицательных чисел.
    public static double sqrt(int number) {
        if (number == 0) return 0;
        if (number == 1) return 1;
        double epsilon = 1e-15; // Точность
        double x = number; // Начальное приближение
        while (getAbsolute(x - number / x) > epsilon * x) {
            x = (x + number / x) / 2;
        }
        return x;
    }

    //   Логарифм (logarithm): Принимает основание логарифма и число,
//     для которого необходимо вычислить логарифм.
    public static double logarithm(double base, double x) {
        if (base <= 0 || base == 1 || x <= 0) {
            throw new IllegalArgumentException("Base and argument must be positive and base must not be 1");
        }

        // Находим логарифм
        double result = 0;
        while (x >= base) {
            x /= base;
            result++;
        }

        // Если x стал меньше базы, то приближаемся к решению
        double fraction = x - 1; // Остаток
        double power = 1; // Степень базы
        double term = fraction; // Текущий член ряда
        int sign = 1; // Знак члена ряда
        while (Math.abs(term) > 1e-10) { // Задаем точность вычисления
            power *= fraction; // Увеличиваем степень базы
            term = sign * power / (double) ++result; // Вычисляем следующий член ряда
            sign = -sign; // Меняем знак следующего члена
            result += term; // Добавляем член к результату
        }

        return result;
    }

    //            Синус (sin): Принимает угол в радианах и возвращает его синус.
    public static double sin(double connerRadian) {
        //double x = Math.PI / 4; // угол, для которого нужно вычислить синус
        int n = 10; // количество членов ряда Маклорена

        double result = 0;
        for (int i = 0; i < n; i++) {
            result += getPower(-1, i) * getPower(connerRadian, 2 * i + 1) / getFactorial(2 * i + 1);
        }
        return result;

    }
}
