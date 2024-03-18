package rest.spellchecking.enumVariable;

public enum SortType {
    ORDER_BY_DATE(3),
    ORDER_BY_TITLE(6),
    ORDER_BY_LINK(9);

    private final int value;

    SortType(int value) {
        this.value = value;
    }


    public int getValue() {
        return value;
    }

    public static SortType fromValue(int value) {
        for (SortType type : SortType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
