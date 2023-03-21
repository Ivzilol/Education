package shopAndGoods;

import javax.naming.OperationNotSupportedException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Shop {
    private Map<String, Goods> shelves;

    public Shop() {
        this.shelves = new LinkedHashMap<>();
        this.shelves.put("Shelves1", null);
        this.shelves.put("Shelves2", null);
        this.shelves.put("Shelves3", null);
        this.shelves.put("Shelves4", null);
        this.shelves.put("Shelves5", null);
        this.shelves.put("Shelves6", null);
        this.shelves.put("Shelves7", null);
        this.shelves.put("Shelves8", null);
        this.shelves.put("Shelves9", null);
        this.shelves.put("Shelves10", null);
        this.shelves.put("Shelves11", null);
        this.shelves.put("Shelves12", null);
    }

    public Map<String, Goods> getShelves() {
        return Collections.unmodifiableMap(this.shelves);
    }

    public String addGoods(String shelf, Goods goods) throws OperationNotSupportedException, IllegalArgumentException{
        if (!this.shelves.containsKey(shelf)) {
            throw new IllegalArgumentException("The shelf doesn't exist!");
        }

        if (this.shelves.get(shelf) != null) {
            throw new IllegalArgumentException("The shelf is already taken!");
        }
        boolean itemExist = getShelves().containsValue(goods);

        if (itemExist) {
            throw new OperationNotSupportedException("Goods is already in shelf!");
        }

        this.shelves.put(shelf, goods);
        return String.format("Goods: %s is placed successfully!", goods.getGoodsCode());
    }

    public String removeGoods (String shelf, Goods goods) {
        if (!this.shelves.containsKey(shelf)) {
            throw new IllegalArgumentException("The shelf doesn't exist!");
        }
        if (this.shelves.get(shelf) != goods) {
            throw new IllegalArgumentException("Goods in that shelf doesn't exists!");
        }

        this.shelves.put(shelf, null);

        return String.format("Goods: %s is removed successfully!", goods.getGoodsCode());
    }
}
