public class BestTimeToBuyAndSellStock_121 {

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock_121 cl = new BestTimeToBuyAndSellStock_121();
        cl.maxProfit(new int[]{7,1,5,3,6,4});
        cl.maxProfit(new int[]{7,6,4,3,1});
        cl.maxProfit(new int[]{1,5,1,8,7});
    }

    public int maxProfit(int[] prices) {
        int bestPrice = 0;
        int holdOne = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            bestPrice = Math.max(bestPrice, holdOne + prices[i]);
            holdOne = Math.max(holdOne, -prices[i]);
        }


        System.out.println(bestPrice);
        return bestPrice;
    }
}
