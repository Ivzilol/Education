package Workshop_03;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class VisitCounter {
    public static void main(String[] args) {
        UserStats userStats = new UserStats(Optional.of(3L));
        VisitCounter cl = new VisitCounter();
        HashMap<String, UserStats> first = new HashMap<>();
        first.put("1", userStats);
        first.put("2", userStats);
        first.put("3", userStats);
        cl.count(first);
    }

    Map<Long, Long> count(Map<String, UserStats>... visits) {
        Map<Long, Long> userVisits = new HashMap<>();
        if (visits == null) {
            return userVisits;
        }
        for (Map<String, UserStats> visit : visits) {
            if (visit != null) {
                for (Map.Entry<String, UserStats> entry : visit.entrySet()) {
                    String userIdString = entry.getKey();
                    UserStats stats = entry.getValue();
                    Long userId = null;
                    try {
                        userId = Long.parseLong(userIdString);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    if (stats != null && stats.visitCount().isPresent()) {
                        Long visitCount = stats.visitCount().get();
                        userVisits.put(userId, userVisits.getOrDefault(userId, 0L) + visitCount);
                    }
                }
            }
        }
        return userVisits;
    }
}

record UserStats(Optional<Long> visitCount) {
}
