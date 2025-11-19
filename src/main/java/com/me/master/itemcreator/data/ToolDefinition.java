package com.me.master.itemcreator.data;

public record ToolDefinition(
    String material,
    int attackDamage,
    float attackSpeed,
    int durability
) {
    public static ToolDefinition defaults() {
        return new ToolDefinition("iron", 1, -2.4f, 250);
    }
}
