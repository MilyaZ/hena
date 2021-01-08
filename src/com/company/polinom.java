package com.company;

public class polinom {
    double[] matrica;
    public static final double ZERO = 1e-24;

    public polinom() {
        matrica = new double[1];
        matrica[0] = 0.0;
    }

    public polinom(double[] m) {
        matrica = m.clone();
    }

    public polinom plus(polinom other) {
        int degree;
        polinom maxP, minP;
        //нашли больший полином(какашка)
        if (other.matrica.length > matrica.length) {
            maxP = other;
            minP = this;
        } else {
            maxP = this;
            minP = other;
        }
        //складываем элементы
        double[] result = new double[maxP.matrica.length];
        for (int i = 0; i < minP.matrica.length; i++) {
            result[i] = maxP.matrica[i] + minP.matrica[i];
        }
        //лишние элементы приписываем
        //откуда, с какой позицииЮ, куда, в какую позицию, сколько
        if (maxP.matrica.length - minP.matrica.length > 0) {
            System.arraycopy(maxP.matrica, minP.matrica.length,
                    result, minP.matrica.length,
                    maxP.matrica.length - minP.matrica.length);
        }
        return new polinom(result);

    }

    public polinom multiplay(double c) {
        double[] result = new double[matrica.length];
        for (int i = 0; i < matrica.length; i++) {
            result[i] = c * matrica[i];

        }
        return new polinom(result);

    }

    public polinom minus(polinom other) {
        return other.multiplay(-1.0).plus(this);
    }

    public polinom div(double a) {
        return this.multiplay(1.0 / a);
    }

    //проверить
    public double y(double a) {
        double degree = matrica.length - 1;
        double result = 0;
        for (int i = 0; i < matrica.length; i++) {
            result += Math.pow(a, degree) * matrica[i];

            degree--;

        }
        return result;
    }

    @Override //переопределение

    public String toString() {
        StringBuilder res = new StringBuilder();
        //Для всех коэффициентов в порядке убывания степеней
        for (int i = matrica.length - 1; i >= 0; i--) {
            //Если коэффициент равен 0...
            //(только не в случае, если это единственный коэффициент)
            if (Math.abs(matrica[i]) < ZERO && (i != 0 || matrica.length != 1)) continue;
            double c = matrica[i];
            if (c < 0) {
                //Для отрицательных значений коэффициентов выводим "-" и далее
                //работаем с модулем коэффициента
                res.append("-");
                c = Math.abs(c);
            } else
                //для положительных коэффициентов выводим "+", если
                //это не первый коэффициент
                if (i < matrica.length - 1)
                    res.append("+");
            //Проверяем, что коэффициент не равен +1 или -1
            //кроме случая, если этот коэффициент при 0й степени
            if (Math.abs(c - 1) > ZERO || i == 0)
                //Выводим сам коэффициент или (если он целый)
                if (Math.abs(c - (long) c) < ZERO)
                    //приводим коэффициент к длинному целому числу
                    res.append((long) c);
                else
                    res.append(c);
            //Если степень 1 или больше, выводим символ "x"
            if (i >= 1) res.append('x');
            //Если степень строго больше 1, выводим ее значение
            if (i > 1) {
                res.append('^');
                res.append(i);
            }
        }
        return res.toString();
    }

}
