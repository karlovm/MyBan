package com.etysoft.ban;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;

public class MuteSystem {

    public static void mutePlayer(String name, long minutes, CommandSender cs, String reason)
    {
        if(!Sets.canOffline())
        {
            if(!myban.isOnline(name))
            {
                cs.sendMessage(Sets.cantOffline());
                return;
            }
        }
       if(!myban.players.containsKey(name))
       {
           MBPlayer mbp = new MBPlayer();
           mbp.name = name;
           mbp.ismute = false;
           mbp.timemute = minutes;
           mbp.mreason = reason;
           Timestamp t = new Timestamp(System.currentTimeMillis());
           mbp.datemute = (Long) t.getTime();
           myban.players.put(name, mbp);
           myban.listplayers.add(mbp);
           try {
               Player p = Bukkit.getPlayer(name);
               p.sendMessage(Sets.mute(minutes + "", reason));
               cs.sendMessage(Sets.mute(minutes + "", reason, name));
           }
           catch (Exception e)
           {
               cs.sendMessage(Sets.mute(minutes + "", reason, name));
           }
       }
       else
       {
           MBPlayer mbp = myban.players.get(name);
           myban.players.remove(mbp.name);
           myban.listplayers.remove(mbp);
           mbp.ismute = true;
           mbp.mreason = reason;
           mbp.timemute = minutes;
           Timestamp t = new Timestamp(System.currentTimeMillis());
           mbp.datemute = (Long) t.getTime();
           myban.players.put(name, mbp);
           myban.listplayers.add(mbp);

           try {
               Player p = Bukkit.getPlayer(name);
               p.sendMessage(Sets.mute(minutes + "", reason));
               cs.sendMessage(Sets.mute(minutes + "", reason, name));
           }
           catch (Exception e)
           {
               cs.sendMessage(Sets.mute(minutes + "", reason, name));
           }
       }
    }
    public static boolean unMute(String s)
    {
        if(!Sets.canOffline())
        {
            if(!myban.isOnline(s))
            {

                return false;
            }
        }

        if(myban.players.containsKey(s))
        {
            MBPlayer mbPlayer = myban.players.get(s);

            myban.listplayers.remove(mbPlayer);
            mbPlayer.ismute = false;
            if(mbPlayer.isban) {
                myban.players.get(s).ismute = false;
                myban.listplayers.add(mbPlayer);
            }
            else
            {
                myban.players.remove(mbPlayer.name);
            }
            return true;
        }
        else
        {
            return false;
        }
    }

}
