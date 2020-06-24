package com.etysoft.ban;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;

public class BanSystem {

    public static void ban(String name, long minutes, CommandSender cs, String reason)
    {
        if(!Sets.canOffline())
        {
            if(!myban.isOnline(name))
            {
                cs.sendMessage(Sets.cantOffline());
                return;
            }
        }

        if(!myban.players.containsKey(name)) {
            MBPlayer mbp = new MBPlayer();
            mbp.name = name;
            mbp.isban = true;
            mbp.timeban = minutes;
            mbp.breason = reason;
            Timestamp t = new Timestamp(System.currentTimeMillis());
            mbp.dateban = (Long) t.getTime();
            myban.players.put(name, mbp);

            myban.listplayers.add(mbp);
            try {
                Player p = Bukkit.getPlayer(name);
                if(!p.hasPermission("myban.banbypass")) {
                    p.kickPlayer(Sets.ban(minutes + "", reason));
                    cs.sendMessage(Sets.ban(minutes + "", reason, name));
                }
                else
                {
                    cs.sendMessage(Sets.noPerm());
                }

            }
            catch (Exception e)
            {
                cs.sendMessage(Sets.ban(minutes + "", reason, name));
            }

        }
        else
        {
            MBPlayer mbp = myban.players.get(name);
            myban.players.remove(mbp.name);
            myban.listplayers.remove(mbp);
            mbp.isban = true;
            mbp.timeban = minutes;
            mbp.breason = reason;
            Timestamp t = new Timestamp(System.currentTimeMillis());
            mbp.dateban = (Long) t.getTime();
            myban.players.put(name, mbp);
            myban.listplayers.add(mbp);
            try {
                Player p = Bukkit.getPlayer(name);
                if(!p.hasPermission("myban.banbypass")) {
                    p.kickPlayer(Sets.ban(minutes + "", reason));
                    cs.sendMessage(Sets.ban(minutes + "", reason, name));
                }
                else
                {
                    cs.sendMessage(Sets.noPerm());
                }
            }
            catch (Exception e)
            {
                cs.sendMessage(Sets.ban(minutes + "", reason, name));
            }
        }

    }



    public static boolean unBan(String s)
    {
        if(myban.players.containsKey(s))
        {
            MBPlayer mbPlayer = myban.players.get(s);

            myban.listplayers.remove(mbPlayer);
            mbPlayer.isban = false;
            if(mbPlayer.ismute)
            {
                myban.players.get(s).isban = false;
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

    public static boolean isBan(String s)
    {
        if(myban.players.containsKey(s))
        {
            return myban.players.get(s).isban;
        }
        else
        {
            return false;
        }
    }
}
