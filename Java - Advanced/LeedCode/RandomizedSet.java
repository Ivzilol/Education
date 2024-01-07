import java.util.*;

public class RandomizedSet {

    ArrayList<Integer> nums;
    HashMap<Integer, Integer> locs;
    java.util.Random random = new java.util.Random();

    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        locs = new HashMap<Integer, Integer>();
    }

    public boolean insert(int val) {
        boolean isContain = locs.containsKey(val);
        if (!isContain) {
            locs.put(val, nums.size());
            nums.add(val);
            return true;
        } else {
            return false;
        }
    }

    public boolean remove(int val) {
        boolean isContain = locs.containsKey(val);
        if (isContain) {
            int location = locs.get(val);
            if (location < nums.size() - 1) {
                int lastOne = nums.get(nums.size() - 1);
                nums.set(location, lastOne);
                locs.put(lastOne, location);
            }
            locs.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        } else {
            return false;
        }
    }

    public int getRandom() {
        return nums.get(random.nextInt(nums.size()) );
    }
}
