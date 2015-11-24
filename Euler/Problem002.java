public class Problem002 {

    public static long fibo(){
        long pred1 = 1;
        long pred2 = 1;
        long sum = 0;
        long i = 0;
        while (i < 4000000){
            i = pred1 + pred2;
            if (i%2 == 0) sum +=i;
            pred1 = pred2;
            pred2 = i;
            System.out.println(i);
        }
        return sum;
    }

    public static void main (String[] args){
        long res = fibo();
        System.out.println("Res = " + res);
    }
}
