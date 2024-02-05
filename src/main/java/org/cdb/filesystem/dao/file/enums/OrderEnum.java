package org.cdb.filesystem.dao.file.enums;

public enum OrderEnum
{
    ASC("ASC"),
    DESC("DESC");

    private final String order;

    OrderEnum(String order)
    {
        this.order = order;
    }

    public static OrderEnum fromString(String text)
    {
        for (OrderEnum order : OrderEnum.values())
        {
            if (order.order.equalsIgnoreCase(text))
            {
                return order;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
