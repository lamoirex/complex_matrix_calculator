package src;
public class Complex {
    private final double re;
    private final double im;

    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public Complex plus(Complex other) {
        return new Complex(re + other.re, im + other.im);
    }

    public Complex minus(Complex other) {
        return new Complex(re - other.re, im - other.im);
    }

    public Complex multiply(Complex other) {
        double newRe = re * other.re - im * other.im;
        double newIm = re * other.im + im * other.re;
        return new Complex(newRe, newIm);
    }

    public Complex divide(Complex other) {
        double denominator = other.re * other.re + other.im * other.im;
        if (denominator == 0) {
            throw new ArithmeticException("Деление на ноль!");
        }
        double newRe = (re * other.re + im * other.im) / denominator;
        double newIm = (im * other.re - re * other.im) / denominator;
        return new Complex(newRe, newIm);
    }

    public boolean isZero() {
        return Math.abs(re) < 1e-9 && Math.abs(im) < 1e-9;
    }

    @Override 
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Complex)) return false;
        Complex other = (Complex) o;
        return re == other.re && im == other.im;
    }

    private double round(double x) {
        return Math.round(x * 10000) / 10000.0;
    }

    @Override 
    public String toString() {
        double shownRe = round(re);
        double shownIm = round(im);
        if (shownIm >= 0) {
            return shownRe + "+" + shownIm + "i";
        } else {
            return shownRe + "-" + (-shownIm) + "i";
        }
    }
}
