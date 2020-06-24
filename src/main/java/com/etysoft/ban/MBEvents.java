package com.etysoft.ban;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Timestamp;

public class MBEvents implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent p)
    {
        if(!p.getPlayer().hasPermission("myban.banbypass")) {
            if (BanSystem.isBan(p.getPlayer().getName())) {
                MBPlayer mbPlayer = myban.players.get(p.getPlayer().getName());
                Timestamp t = new Timestamp(System.currentTimeMillis());
                if (TimeConvert.msToMin(mbPlayer.dateban) + mbPlayer.timeban < TimeConvert.msToMin(t.getTime())) {
                    BanSystem.unBan(p.getPlayer().getName());
                } else {
                    p.getPlayer().kickPlayer(Sets.ban(mbPlayer.timeban + "", mbPlayer.breason));
                }

            }
        }
    }

    @EventHandler
    public void sendm(AsyncPlayerChatEvent e)
    {
        if(!e.getPlayer().hasPermission("myban.mutebypass")) {
            if (myban.players.containsKey(e.getPlayer().getName())) {
                MBPlayer mbPlayer = myban.players.get(e.getPlayer().getName());
                Timestamp t = new Timestamp(System.currentTimeMillis());
                if (TimeConvert.msToMin(mbPlayer.datemute) + mbPlayer.timemute < TimeConvert.msToMin(t.getTime())) {
                   MuteSystem.unMute(e.getPlayer().getName());
                } else {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(Sets.mute(mbPlayer.timemute + "", mbPlayer.mreason));
                }

            }
        }
    }
}
