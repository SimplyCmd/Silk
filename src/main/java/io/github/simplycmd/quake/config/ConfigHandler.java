package io.github.simplycmd.quake.config;import net.fabricmc.loader.api.FabricLoader;import java.io.*;import java.nio.file.Files;import java.nio.file.Path;public class ConfigHandler {    @Deprecated    public static Config config = new Config();    private static String path;    private static int tick = 0;    public static void init() {        path = createConfigIfAbsent() + "//quake.config";        loadConfig();    }    public static void saveTick() {        tick++;        if (tick >= 40) {            saveConfig();            tick = 0;        }    }    public static void saveConfig() {        try {            FileOutputStream f = new FileOutputStream(path);            ObjectOutputStream o = new ObjectOutputStream(f);            o.writeObject(config);            o.close();            f.close();        } catch (IOException e) {            e.printStackTrace();        }    }    public static void loadConfig() {        try {            FileInputStream f = new FileInputStream(path);            ObjectInputStream o = new ObjectInputStream(f);            config = (Config) o.readObject();            o.close();            f.close();        } catch (FileNotFoundException | ClassNotFoundException e) {            System.out.println("Did not find Quake config file. Creating now!");            saveConfig();        } catch (IOException e) {            e.printStackTrace();        }    }    private static Path createConfigIfAbsent() {        Path savePath = FabricLoader.getInstance().getGameDir().resolve("config");        if (!Files.exists(savePath)) {            try {                Files.createDirectories(savePath);            } catch (IOException e) {                e.printStackTrace();            }        }        return savePath;    }}