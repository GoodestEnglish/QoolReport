package me.GoodestEnglish.QoolReport.util;

import me.GoodestEnglish.QoolReport.QoolReport;
import org.bukkit.Bukkit;

public class AsyncUtils {

	public static void runAsync(final Runnable runnable) {
		/*final ExecutorService executor = Executors.newSingleThreadExecutor(new ThreadFactoryBuilder().setNameFormat("ShitPractice-" + runnable.hashCode()).build());
		executor.submit(runnable);*/
        Bukkit.getScheduler().runTaskAsynchronously(QoolReport.get(), runnable);

	}

	/*public static BukkitTask runTaskTimer(final BukkitRunnable runnable, final long delay, final long time, final boolean async) {
		if (async)
			return runnable.runTaskTimerAsynchronously(ShitPractice.getInstance(), delay, time);
		return runnable.runTaskTimer(ShitPractice.getInstance(), delay, time);
	}

	public static BukkitTask runTaskLater(final Runnable runnable, final long delay, final boolean async) {
		if (async)
			return Bukkit.getScheduler().runTaskLaterAsynchronously(ShitPractice.getInstance(), runnable, delay);
		return Bukkit.getScheduler().runTaskLater(ShitPractice.getInstance(), runnable, delay);
	}*/
}