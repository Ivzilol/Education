public class BestTimeToBuyAndSellStock_2_122 {


    public static void main(String[] args) {
        BestTimeToBuyAndSellStock_2_122 cl = new BestTimeToBuyAndSellStock_2_122();
        cl.maxProfit(new int[]{7,1,5,3,6,4});
//        cl.maxProfit(new int[]{1,2,3,4,5});
//        cl.maxProfit(new int[]{7,6,4,3,1});
    }

    public int maxProfit(int[] prices) {
        int bestProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            int oldDay = prices[i - 1];
            int currentDay = prices[i];
            if (oldDay < currentDay) {
                bestProfit += currentDay - oldDay;
            }
        }
        System.out.println(bestProfit);
        return bestProfit;
    }
}
