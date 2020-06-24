package com.etysoft.ban;

import org.bukkit.ChatColor;

public class Sets {

    public static String cstring(String text)
    {
        try
        {
            return  ChatColor.translateAlternateColorCodes('&', text);
        }
        catch (Exception e)
        {
            return text;
        }
    }

    public static String unBan(String name)
    {
        return cstring(myban.getInstance().getConfig().getString("unban").replace("%s", name));
    }

    public static String unMute(String name)
    {
        return cstring(myban.getInstance().getConfig().getString("unmute").replace("%s", name));
    }

    public static String noPerm()
    {
        return cstring(myban.getInstance().getConfig().getString("no-perm"));
    }

    public static String cantUnban()
    {
        return cstring(myban.getInstance().getConfig().getString("cunban"));
    }

    public static String cantUnmute()
    {
        return cstring(myban.getInstance().getConfig().getString("cunmute"));
    }
    public static String NaN()
    {
        return cstring(myban.getInstance().getConfig().getString("nan"));
    }

    public static String Args()
    {
        return cstring(myban.getInstance().getConfig().getString("args"));
    }

    public static String infoBan()
    {
        return cstring(myban.getInstance().getConfig().getString("whatban"));
    }

    public static String infoMute()
    {
        return cstring(myban.getInstance().getConfig().getString("whatmute"));
    }

    public static String timeMax(String max)
    {
        return cstring(myban.getInstance().getConfig().getString("time").replace("%t", max));
    }

    public static String ban(String minutes, String reason)
    {
        return cstring(myban.getInstance().getConfig().getString("ban").replace("%s", minutes).replace("%z", reason));
    }

    public static String ban(String minutes, String reason, String name)
    {
        return cstring(myban.getInstance().getConfig().getString("banh").replace("%s", name).replace("%z", reason).replace("%k", minutes));

    }

    public static String mute(String minutes, String reason)
    {
        return cstring(myban.getInstance().getConfig().getString("mute").replace("%s", minutes).replace("%z", reason));
    }

    public static String mute(String minutes, String reason, String name)
    {
        return cstring(myban.getInstance().getConfig().getString("muteh").replace("%s", name).replace("%z", reason).replace("%k", minutes));

    }

    public static String cantOffline()
    {
        return cstring(myban.getInstance().getConfig().getString("oof"));
    }

    public static boolean canOffline()
    {
        return myban.getInstance().getConfig().getBoolean("offline-players");
    }

}
