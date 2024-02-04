package org.cdb.filesystem.dto.file.enums;

public enum Order
{
    ASC("ASC"),
    DESC("DESC");

    private final String order;

    Order(String order)
    {
        this.order = order;
    }

    public static Order fromString(String text)
    {
        for (Order order : Order.values())
        {
            if (order.order.equalsIgnoreCase(text))
            {
                return order;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
