package com.me.master.itemcreator.loader;

import com.google.gson.JsonObject;
import com.me.master.itemcreator.ItemCreator;
import com.me.master.itemcreator.config.ConfigValidator;
import com.me.master.itemcreator.types.ItemType;
import com.me.master.itemcreator.item.*;
import com.me.master.itemcreator.options.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Mod.EventBusSubscriber(modid = ItemCreator.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Loader {
    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void registerItems(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.ITEMS, helper -> {
            List<JsonObject> configs = ResourceLoader.loadItemConfigs();

            for (JsonObject config : configs) {
                try {
                    // VALIDACIÓN AGREGADA
                    if (!ConfigValidator.isValid(config, LOGGER)) {
                        LOGGER.warn("Skipping invalid item configuration");
                        continue;
                    }

                    String name = config.get("name").getAsString();
                    String typeStr = config.get("type").getAsString().toLowerCase();

                    // USAR ITEMTYPE ENUM
                    ItemType type = ItemType.fromString(typeStr);
                    Item item = createItem(type, config);

                    if (item != null) {
                        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ItemCreator.MOD_ID, name);
                        helper.register(id, item);
                        LOGGER.info("✓ Registered item: {} (type: {})", name, type.getName());
                    }
                } catch (Exception e) {
                    LOGGER.error("Failed to register item from config: {}", config, e);
                }
            }
        });
    }

    // CAMBIAR A ITEMTYPE EN LUGAR DE STRING
    private static Item createItem(ItemType type, JsonObject config) {
        return switch (type) {
            case PICKAXE -> createPickaxe(config);
            case AXE -> createAxe(config);
            case TRIDENT -> createTrident(config);
            case CROSSBOW -> createCrossbow(config);
            case SHIELD -> createShield(config);
            case ARMOR -> createArmor(config);
            case TOTEM -> createTotem(config);
            case CONSUMABLE -> createConsumable(config);
            case DRINK -> createDrink(config);
            default -> createBasicItem(config);
        };
    }

    private static AdvancedPickaxe createPickaxe(JsonObject config) {
        Tier material = ToolMaterialFactory.fromJson(config);
        ToolOptions options = ToolOptions.builder()
                .material(material)
                .attackDamage(config.get("attackDamage").getAsInt())
                .attackSpeed(config.get("attackSpeed").getAsFloat())
                .build();

        return new AdvancedPickaxe(material, options, new Item.Properties());
    }

    private static AdvancedTrident createTrident(JsonObject config) {
        ToolOptions options = ToolOptions.builder()
                .attackDamage(config.get("attackDamage").getAsInt())
                .attackSpeed(config.get("attackSpeed").getAsFloat())
                .build();

        return new AdvancedTrident(new Item.Properties(), options);
    }

    private static AdvancedAxe createAxe(JsonObject config) {
        Tier material = ToolMaterialFactory.fromJson(config);
        ToolOptions options = ToolOptions.builder()
                .material(material)
                .attackDamage(config.get("attackDamage").getAsInt())
                .attackSpeed(config.get("attackSpeed").getAsFloat())
                .build();

        return new AdvancedAxe(material, options, new Item.Properties());
    }

    private static AdvancedCrossbow createCrossbow(JsonObject config) {
        ItemOptions options = ItemOptionsFactory.fromJson(config);
        return new AdvancedCrossbow(new Item.Properties(), options);
    }

    private static AdvancedShield createShield(JsonObject config) {
        ItemOptions options = ItemOptionsFactory.fromJson(config);
        return new AdvancedShield(new Item.Properties(), options);
    }

    private static AdvancedArmor createArmor(JsonObject config) {
        ArmorMaterial material = ArmorMaterialFactory.fromJson(config);
        Type armorType = Type.valueOf(config.get("slot").getAsString().toUpperCase());
        EquipmentSlot slot = mapTypeToSlot(armorType);

        ArmorOptions options = ArmorOptions.builder()
                .material(material)
                .slot(slot)
                .defense(config.get("defense").getAsInt())
                .toughness(config.get("toughness").getAsFloat())
                .knockbackResistance(config.get("knockbackResistance").getAsFloat())
                .build();

        return new AdvancedArmor(material, armorType, new Item.Properties(), options);
    }

    private static EquipmentSlot mapTypeToSlot(Type type) {
        return switch (type) {
            case HELMET -> EquipmentSlot.HEAD;
            case CHESTPLATE -> EquipmentSlot.CHEST;
            case LEGGINGS -> EquipmentSlot.LEGS;
            case BOOTS -> EquipmentSlot.FEET;
        };
    }

    private static AdvancedTotem createTotem(JsonObject config) {
        TotemOptions options = TotemOptions.builder()
                .destroyOnUse(config.has("destroyOnUse") ? config.get("destroyOnUse").getAsBoolean() : true)
                .build();

        return new AdvancedTotem(new Item.Properties(), options);
    }

    private static Item createConsumable(JsonObject config) {
        ConsumableItemOptions options = ItemOptionsFactory.consumableFromJson(config);
        return new Item(options.getSettings()) {};
    }

    private static Item createDrink(JsonObject config) {
        DrinkItemOptions options = DrinkItemOptions.builder()
                .foodProperties(new net.minecraft.world.food.FoodProperties.Builder().build())
                .build();
        return new Item(new Item.Properties()) {};
    }

    private static Item createBasicItem(JsonObject config) {
        ItemOptions options = ItemOptionsFactory.fromJson(config);
        return new Item(options.getSettings());
    }
}
