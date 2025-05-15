package net.postoronnim.mobsoftheunderground.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

public class LivingAmethyst extends Item {
    public static final String LIVING_AMETHYST_ARMOR_MODIFIER = "living_amethyst_armor_modifier";

    public LivingAmethyst(Settings settings) {
        super(settings.maxCount(1).rarity(Rarity.RARE));
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true; // Makes it look enchanted
    }

    @Override
    public boolean onStackClicked(ItemStack stack, Slot slot, ClickType clickType, PlayerEntity player) {
        ItemStack stackClicked = slot.getStack();

        if (clickType.equals(ClickType.LEFT)) {
            if (stackClicked.getItem() instanceof ArmorItem armorItem) {

                Identifier identifier = Identifier.of(LIVING_AMETHYST_ARMOR_MODIFIER + "_" + armorItem.getSlotType().toString().toLowerCase());

                EntityAttributeModifier modifier = new EntityAttributeModifier(identifier, 1d, EntityAttributeModifier.Operation.ADD_VALUE);

                AttributeModifiersComponent component = stackClicked.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, new AttributeModifiersComponent(List.of(), true));

                if (component.modifiers().isEmpty()) {
                    component = armorItem.getAttributeModifiers();
                }

                ArrayList<AttributeModifiersComponent.Entry> modifiers = new ArrayList<>(component.modifiers());

                if (modifiers.removeIf(entry -> entry.modifier().id().equals(identifier))) {
                    return false;
                }

                modifiers.add(new AttributeModifiersComponent.Entry(EntityAttributes.GENERIC_ARMOR, modifier, AttributeModifierSlot.ANY));

                stackClicked.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, new AttributeModifiersComponent(modifiers, true));

                player.playSoundToPlayer(SoundEvents.BLOCK_AMETHYST_BLOCK_PLACE, SoundCategory.PLAYERS, 1f, 1f);

                stack.decrement(1);
                return true;
            }
        }

        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.mobsoftheunderground.living_amethyst.tooltip_1").formatted(Formatting.ITALIC, Formatting.BLUE));
        tooltip.add(Text.translatable("tooltip.mobsoftheunderground.living_amethyst.tooltip_2").formatted(Formatting.ITALIC, Formatting.BLUE));
    }
}
