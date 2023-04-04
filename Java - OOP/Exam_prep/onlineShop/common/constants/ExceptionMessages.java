package onlineShop.common.constants;

public class ExceptionMessages {
    public static final String INVALID_PRODUCT_ID = "Id can not be less or equal than 0.";

    public static final String INVALID_MANUFACTURER = "Manufacturer can not be empty.";

    public static final String INVALID_MODEL = "Model can not be empty.";

    public static final String INVALID_PRICE = "Price can not be less or equal than 0.";

    public static final String INVALID_OVERALL_PERFORMANCE = "Overall Performance can not be less or equal than 0.";

    public static final String EXISTING_COMPONENT = "Component %s already exists in %s with Id %d.";

    public static final String EXISTING_PERIPHERAL = "Peripheral %s already exists in %s with Id %d.";

    public static final String NOT_EXISTING_COMPONENT = "Component %s does not exist in %s with Id %d.";

    public static final String NOT_EXISTING_PERIPHERAL = "Peripheral %s does not exist in %s with Id %d.";

    public static final String NOT_EXISTING_COMPUTER_ID = "Computer with this id does not exist.";

    public static final String INVALID_COMPUTER_TYPE = "Computer type is invalid.";

    public static final String EXISTING_COMPUTER_ID = "Computer with this id already exists.";

    public static final String INVALID_PERIPHERAL_TYPE = "Peripheral type is invalid.";

    public static final String EXISTING_PERIPHERAL_ID = "Peripheral with this id already exists.";

    public static final String INVALID_COMPONENT_TYPE = "Component type is invalid.";

    public static final String EXISTING_COMPONENT_ID = "Component with this id already exists.";

    public static final String CAN_NOT_BUY_COMPUTER = "Can't buy a computer with a budget of ${%.2f}.";

}
