package me.GoodestEnglish.QoolReport.util;

import me.GoodestEnglish.QoolReport.QoolReport;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BaseEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public void call() {
		QoolReport.get().getServer().getPluginManager().callEvent(this);
	}

}
