package com.etysoft.ban;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class myban extends JavaPlugin {

   private static myban mb;
  public static Map<String, MBPlayer> players;
    public static Set<MBPlayer> listplayers = new HashSet<MBPlayer>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        mb = this;
        saveDefaultConfig();
        reloadConfig();
        getCommand("myban").setExecutor(new MBCommand());
        getServer().getPluginManager().registerEvents(new MBEvents(), this);
            players = Data.load();
            listplayers = Data.getList();
        Bukkit.getConsoleSender().sendMessage("Successfully enabled MyBan by karlov_m!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Data.save(listplayers);
        Bukkit.getConsoleSender().sendMessage("Successfully disabled MyBan by karlov_m!");
    }

    public static myban getInstance()
    {
        return mb;
    }

    public static boolean isOnline(String name)
    {
        try
        {
            Player p = Bukkit.getPlayer(name);
            if(p != null)
            {
                return true;
            }
            else
            {
                return  false;
            }
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
