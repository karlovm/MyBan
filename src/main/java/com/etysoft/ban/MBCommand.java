package com.etysoft.ban;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class MBCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if((s.equals("mb") || s.equals("myban") ) && strings.length == 0)
        {
            commandSender.sendMessage(Sets.cstring("Running &6MyBan " + myban.getInstance().getDescription().getVersion() + " &7by &bkarlov_m"));
            return true;
        }
        else if(s.equals("rban") && strings.length != 0)
        {
            if(commandSender.hasPermission("myban.ban")) {
                if (strings.length > 1) {


                        String reas = "";
                        int i = 0;
                        for (String a :
                                strings) {
                            if (i > 1) {
                                reas = reas + a +  " ";
                            } else {

                            }
                            i++;
                        }

                        if(isNumeric(strings[1]))
                        {
                            long min = Long.parseLong(strings[1]);
                            long max = getPlayersLimit(commandSender, "myban.ban");
                            if(max >= min || commandSender.hasPermission("myban.admin"))
                            {
                                BanSystem.ban(strings[0], min, commandSender, reas);
                            }
                            else
                            {
                                commandSender.sendMessage(Sets.timeMax(max + ""));
                            }


                        }
                        else
                        {
                            commandSender.sendMessage(Sets.NaN());
                        }


                } else {

                    commandSender.sendMessage(Sets.infoBan());
                }
            }
            else
            {
                commandSender.sendMessage(Sets.noPerm());
            }
            return true;
        }
        else if(s.equals("rmute") && strings.length != 0)
        {
            if(commandSender.hasPermission("myban.mute")) {
                if (strings.length > 1) {
                    try {

                        String reas = "";
                        int i = 0;
                        for (String a :
                                strings) {
                            if (i > 1) {
                                reas = reas  + a +  " ";
                            } else {

                            }
                            i++;
                        }
                        if (isNumeric(strings[1])) {
                            long min = Long.parseLong(strings[1]);
                            long max = getPlayersLimit(commandSender, "myban.mute");
                            if (max >= min || commandSender.hasPermission("myban.admin")) {
                                MuteSystem.mutePlayer(strings[0], min, commandSender, reas);
                            } else {
                                commandSender.sendMessage(Sets.timeMax(max + ""));
                            }


                        } else {
                            commandSender.sendMessage(Sets.NaN());
                        }

                    } catch (Exception e) {
                        commandSender.sendMessage(Sets.NaN());
                    }
                } else {
                    commandSender.sendMessage(Sets.infoMute());
                }
            }
            else
            {
                commandSender.sendMessage(Sets.noPerm());
            }
            return true;
        }
        else if(s.equals("runban") && strings.length != 0)
        {
            if(commandSender.hasPermission("myban.unban")) {
                if (BanSystem.unBan(strings[0])) {
                    commandSender.sendMessage(Sets.unBan(strings[0]));
                } else {
                    commandSender.sendMessage(Sets.cantUnban());
                }
            }
            else
            {
                commandSender.sendMessage(Sets.noPerm());
            }
            return true;
        }
        else if(s.equals("runmute") && strings.length != 0)
        {
            if(commandSender.hasPermission("myban.unmute")) {
                if (BanSystem.unBan(strings[0])) {
                    commandSender.sendMessage(Sets.unMute(strings[0]));
                } else {
                    commandSender.sendMessage(Sets.cantUnmute());
                }
            }
            else
            {
                commandSender.sendMessage(Sets.noPerm());
            }
            return true;
        }
        if(s.equals("mb") && strings.length != 0)
        {
            if(commandSender.hasPermission("myban.admin")) {
                if (strings[0].equals("save")) {
                    Data.save(myban.listplayers);
                }
                if (strings[0].equals("reload")) {
                   myban.getInstance().reloadConfig();
                   commandSender.sendMessage(Sets.cstring("&f[&6MyBan&f] &aConfig of &bMyBan " + myban.getInstance().getDescription().getVersion() + " &areloaded!"));
                }
            }
            else
            {
                commandSender.sendMessage(Sets.noPerm());
            }
            return true;
        }
        commandSender.sendMessage(Sets.Args());
     return true;
    }

    public static long getPlayersLimit(CommandSender player, String permission) {
        String perm;
        for (PermissionAttachmentInfo pio : player.getEffectivePermissions()) {
            perm = pio.getPermission();

            if (perm.startsWith(permission)) {
                String ending = perm.substring(perm.lastIndexOf("."));
                if (isNumeric(ending.replace(".", ""))) {
                    return Long.parseLong(ending.replace(".", ""));
                }
            }
        }
        return 0;
    }

    private static boolean isNumeric(String ending) {
        try
        {
            long a = Long.parseLong(ending);
            if(a > 0)
            {
                return  true;
            }
            else
            {
                return  false;
            }
        }
        catch (Exception e)
        {
         //   Bukkit.getConsoleSender().sendMessage(ending + " NaN!");
            return  false;
        }
    }
}
