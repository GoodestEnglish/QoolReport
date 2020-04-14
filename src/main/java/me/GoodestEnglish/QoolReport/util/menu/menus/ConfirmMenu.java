package me.GoodestEnglish.QoolReport.util.menu.menus;

import me.GoodestEnglish.QoolReport.util.menu.Button;
import me.GoodestEnglish.QoolReport.util.menu.Menu;
import me.GoodestEnglish.QoolReport.util.menu.TypeCallback;
import me.GoodestEnglish.QoolReport.util.menu.buttons.ConfirmationButton;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ConfirmMenu extends Menu {

	private final String title;
	private final TypeCallback<Boolean> response;
	private final boolean closeAfterResponse;
	private final Button[] centerButtons;

	public ConfirmMenu(final String title, final TypeCallback<Boolean> response, final boolean closeAfter, final Button... centerButtons) {
		this.title = title;
		this.response = response;
		this.closeAfterResponse = closeAfter;
		this.centerButtons = centerButtons;
	}

	@Override
	public Map<Integer, Button> getButtons(final Player player) {
		final HashMap<Integer, Button> buttons = new HashMap<>();

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				buttons.put(getSlot(x, y), new ConfirmationButton(true, response, closeAfterResponse));
				buttons.put(getSlot(8 - x, y), new ConfirmationButton(false, response, closeAfterResponse));
			}
		}

		if (centerButtons != null) {
			for (int i = 0; i < centerButtons.length; i++) {
				if (centerButtons[i] != null) {
					buttons.put(getSlot(4, i), centerButtons[i]);
				}
			}
		}

		return buttons;
	}

	@Override
	public String getTitle(final Player player) {
		return title;
	}

}
