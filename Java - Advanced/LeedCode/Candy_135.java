import java.util.Arrays;

public class Candy_135 {
    public static void main(String[] args) {
        Candy_135 cl = new Candy_135();
        cl.candy(new int[]{1,0,2});
        cl.candy(new int[]{1,2,2});
        cl.candy(new int[]{1,2,87,87,87,2,1});
        cl.candy(new int[]{29,51,87,87,72,12});
    }


    public int candy(int[] ratings) {

        int n = ratings.length;
        int[] candiesPerChild = new int[n];
        Arrays.fill(candiesPerChild, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candiesPerChild[i] = candiesPerChild[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candiesPerChild[i] = Math.max(candiesPerChild[i], candiesPerChild[i + 1] + 1);
            }
        }

        int candies = 0;
        for (int candy : candiesPerChild) {
            candies += candy;
        }
        System.out.println(candies);
        return candies;
    }
}
