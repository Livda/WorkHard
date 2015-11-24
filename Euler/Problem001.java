public class Problem001 {

    public static int under(int n){
        int sum = 0;
        int i = 0;
        while (i < n){
            if (i%3 == 0 || i%5 == 0) sum +=i;
            i++;
        }
        return sum;
    }

    public static void main (String[] args){
        int res = under(1000);
        System.out.println("Res = " + res);
    }
}
