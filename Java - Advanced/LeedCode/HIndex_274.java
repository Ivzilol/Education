public class HIndex_274 {

    public static void main(String[] args) {
        HIndex_274 cv = new HIndex_274();
        cv.hIndex(new int[]{3,0,6,1,5});
        cv.hIndex(new int[]{1,3,1});
    }

    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        for(int c : citations) {
            if(c >= n) {
                buckets[n]++;
            } else {
                buckets[c]++;
            }
        }
        int count = 0;
        for(int i = n; i >= 0; i--) {
            count += buckets[i];
            if(count >= i) {
                return i;
            }
        }
        System.out.println();
        return 0;
    }
}
