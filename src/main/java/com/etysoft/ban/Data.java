package com.etysoft.ban;

import org.bukkit.Bukkit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Data {

    public static void save(Set<MBPlayer> pllist)
    {
        Bukkit.getConsoleSender().sendMessage("Saving data file...");
        File file2 = new File(myban.getInstance().getDataFolder(), "players.dat");

        JSONArray arr = new JSONArray();
        for(MBPlayer mbp : pllist) {
            JSONObject pl = new JSONObject();
           pl.put("name", mbp.name);

            pl.put("ismute", mbp.ismute);
            pl.put("tmute", mbp.timemute);
            pl.put("dmute", mbp.datemute);
            pl.put("isban", mbp.isban);
            pl.put("tban", mbp.timeban);
            pl.put("dban", mbp.dateban);
            pl.put("mres", mbp.mreason);
            pl.put("bres", mbp.breason);
            arr.add(pl);
            Bukkit.getConsoleSender().sendMessage("Added " + mbp.name + " to array...");
        }
        try (FileWriter file = new FileWriter(file2)) {
            file.write(arr.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, MBPlayer> load()
    {
        Bukkit.getConsoleSender().sendMessage("Loading data file...");
        Map<String, MBPlayer> players  = new ConcurrentHashMap<>();
        File file2 = new File(myban.getInstance().getDataFolder(), "players.dat");

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(file2)) {

            JSONArray jsonArray = (JSONArray) parser.parse(reader);


            // loop array

            Iterator<JSONObject> iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
              JSONObject jo = iterator.next();
              MBPlayer mbPlayer = new MBPlayer();
              mbPlayer.name = (String) jo.get("name");
              mbPlayer.dateban = (long) jo.get("dban");
              mbPlayer.datemute = (long) jo.get("dmute");
                mbPlayer.isban = (boolean) jo.get("isban");
                mbPlayer.ismute = (boolean) jo.get("ismute");
                mbPlayer.timemute = (long) jo.get("tmute");
                mbPlayer.timeban = (long) jo.get("tban");
                mbPlayer.mreason = (String) jo.get("mres");
                mbPlayer.breason = (String) jo.get("bres");
                players.put(mbPlayer.name, mbPlayer);
                list.add(mbPlayer);
            }

        } catch (IOException e) {
          //  e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Bukkit.getConsoleSender().sendMessage("Loaded data successfully!");
        return players;
    }

    private static Set<MBPlayer> list = new HashSet<MBPlayer>();

    public static Set<MBPlayer> getList()
    {
        return list;
    }

}
