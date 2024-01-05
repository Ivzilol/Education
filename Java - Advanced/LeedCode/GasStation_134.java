public class GasStation_134 {

    public static void main(String[] args) {
        GasStation_134 cl = new GasStation_134();
        cl.canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2});
        cl.canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3});
        cl.canCompleteCircuit(new int[]{5,1,2,3,4}, new int[]{4,4,1,5,1});
        cl.canCompleteCircuit(new int[]{5,8,2,8}, new int[]{6,5,6,6});
        cl.canCompleteCircuit(new int[]{4,5,3,1,4}, new int[]{5,4,3,4,2});
        cl.canCompleteCircuit(new int[]{2}, new int[]{2});
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int tank = 0;
        for (int i = 0; i < gas.length; i++) {
            if (gas[i] >= cost[i] && tank == 0) {
                tank += gas[i] - cost[i];
                start = i;
                continue;
            }
            if (checkTankOverZero(tank)) {
                tank += gas[i] - cost[i];
            } else {
                tank = 0;
                start = -1;
            }
            if (checkTankEmpty(tank)) {
                tank = 0;
            }
        }
        if (start != 0) {
            for (int i = 0; i < start; i++) {
                if (checkTankOverZero(tank)) {
                    tank += gas[i] - cost[i];
                } else {
                    start = -1;
                    break;
                }
            }
        }
        if (checkTankEmpty(tank)) {
            start = -1;
        }
        return start;
    }

    public boolean checkTankOverZero(int tank) {
        return tank > 0;
    }

    public boolean checkTankEmpty(int tank) {
        return tank < 0;
    }
}
