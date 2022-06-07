package Arrays.Power;

public class PowerWithModulo {

    public double power(double x, int n, int m) {
        // Calculate (x^n) % m
        double power = 1;

        long N = n;
        if (n < 0) N = -1 *N;

        while (N != 0){

            if (N % 2 ==0){
                x = ((x % m) * (x % m)) % m;
                N /= 2;
            }
            else{
                power = (power * (x % m)) % m;
                N--;
            }
        }

        return power % m;
    }
}
