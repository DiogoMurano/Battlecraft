package br.com.battlebits.battlecraft.inventory;

import br.com.battlebits.battlecraft.Battlecraft;
import br.com.battlebits.battlecraft.manager.TeleportManager;
import br.com.battlebits.battlecraft.manager.WarpManager;
import br.com.battlebits.battlecraft.warp.Warp;
import br.com.battlebits.commons.bukkit.api.item.ItemBuilder;
import br.com.battlebits.commons.bukkit.api.menu.MenuInventory;
import br.com.battlebits.commons.bukkit.api.menu.MenuItem;
import br.com.battlebits.commons.translate.Language;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static br.com.battlebits.battlecraft.translate.BattlecraftTranslateTag.*;
import static br.com.battlebits.commons.translate.TranslationCommon.tl;

public class WarpSelector extends MenuInventory {

    public WarpSelector(Language l, Warp currentWarp) {
        super(l.tl(WARPS_MENU_TITLE), 1);

        WarpManager manager = Battlecraft.getInstance().getWarpManager();
        ItemBuilder builder;
        MenuItem menuItem;
        int slot = 0;
        List<Warp> warpList = new ArrayList<>(manager.getWarps());

        warpList.sort(Comparator.comparing(Warp::getId));

        for (Warp warp : manager.getWarps()) {
//
            builder =
                    ItemBuilder.create(warp.getMaterial()).name(ChatColor.AQUA + "" + ChatColor.BOLD + tl(l,
                            valueOf("WARP_" + warp.getId().toUpperCase() + "_NAME"))).lore(ChatColor.GRAY + tl(l,
                            valueOf("WARP_" + warp.getId().toUpperCase() + "_LORE")));
            if (currentWarp == warp) {
                builder = builder.glow().lore(ChatColor.GRAY + tl(l,
                        valueOf("WARP_" + warp.getId().toUpperCase() + "_LORE")), "", tl(l,
                        CURRENTLY_HERE));
                setItem(slot++, builder.build());
                continue;
            }
            menuItem = new MenuItem(builder.build(), (player, inventory, clickType, itemStack, i) -> {
                player.closeInventory();
                TeleportManager.teleport(player, warp);
            });
            setItem(slot++, menuItem);
        }
    }
}
