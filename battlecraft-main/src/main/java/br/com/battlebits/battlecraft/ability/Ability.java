package br.com.battlebits.battlecraft.ability;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public interface Ability extends Listener {

    String getName();

    ItemStack getIcon();

    String getLorePrefix();

    String getPermission();

    int getPrice();

    void onReceiveItems(Player player);

    boolean isUsing(Player player);

    boolean hasCooldown(Player player);

    long getCooldown(Player player);

}
