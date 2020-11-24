package me.GoodestEnglish.QoolReport.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.GoodestEnglish.QoolReport.util.BaseEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class LoadJDAListenerEvent extends BaseEvent {

    private List<ListenerAdapter> listenerAdapters = new ArrayList<>();

}
