package database;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import rest.MenuItem;

public class FileManager {
    public static void saveMenuToFile(Map<Integer, MenuItem> menuItems, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(menuItems);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public static Map<Integer, MenuItem> loadMenuFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Map<Integer, MenuItem>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
