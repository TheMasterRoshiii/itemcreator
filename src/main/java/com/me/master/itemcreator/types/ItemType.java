package com.me.master.itemcreator.types;

public enum ItemType {
    BASIC("basic"),
    PICKAXE("pickaxe"),
    AXE("axe"),
    SHOVEL("shovel"),
    HOE("hoe"),
    SWORD("sword"),
    TRIDENT("trident"),
    CROSSBOW("crossbow"),
    BOW("bow"),
    SHIELD("shield"),
    ARMOR("armor"),
    TOTEM("totem"),
    CONSUMABLE("consumable"),
    DRINK("drink");
    
    private final String name;
    
    ItemType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static ItemType fromString(String name) {
        for (ItemType type : values()) {
            if (type.name.equalsIgnoreCase(name)) {
                return type;
            }
        }
        return BASIC;
    }
}
