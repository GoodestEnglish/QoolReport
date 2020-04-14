package me.GoodestEnglish.QoolReport.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;

@AllArgsConstructor
@Getter
public enum ReportType {

    COMBAT("戰鬥類型外掛 (Combat Hacks)", Material.DIAMOND_SWORD),
    MOVEMENT("移動類型外掛 (Movement Hacks)", Material.GOLD_BOOTS),
    BOOSTING("用不合理方法改善成績 (Boosting)", Material.PAPER),
    SKYBASE("建造天空堡壘 (Skybase)", Material.WOOD),
    CHAT("侮辱性言語 (Insulting / Bad word)", Material.BOOK_AND_QUILL);

    private String readable;
    private Material material;

}
