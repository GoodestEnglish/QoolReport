package me.GoodestEnglish.QoolReport.menu;

import lombok.RequiredArgsConstructor;
import me.GoodestEnglish.QoolReport.QoolReport;
import me.GoodestEnglish.QoolReport.enums.ReportType;
import me.GoodestEnglish.QoolReport.util.ItemBuilder;
import me.GoodestEnglish.QoolReport.util.menu.Button;
import me.GoodestEnglish.QoolReport.util.menu.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ReportMenu extends Menu {

    private final Player target;

    @Override
    public String getTitle(Player player) {
        return "&e正在舉報: &c" + target.getName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        for (ReportType type : ReportType.values()) {
            buttons.put(buttons.size(), new Button() {
                @Override
                public ItemStack getButtonItem(Player player) {
                    return new ItemBuilder(type.getMaterial()).name("&6" + type.getReadable()).build();
                }

                @Override
                public void clicked(final Player player, final int i, final ClickType clickType, final int hb) {
                    QoolReport.get().getReportManager().sendReport(player, target, type);
                    player.closeInventory();
                }
            });
        }

        return buttons;
    }

}
