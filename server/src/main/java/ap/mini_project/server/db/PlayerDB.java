package ap.mini_project.server.db;

import ap.mini_project.shared.model.Player;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class PlayerDB {
    private ObjectMapper objectMapper = new ObjectMapper();
    private String USERS_DIR_PATH = "src/main/resources/db/users";


    public Player get(int id) {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        if (UserDirectoryListing != null) {
            try {
                return objectMapper.readValue(new FileReader(USERS_DIR_PATH + "/" + id + ".json"), new TypeReference<Player>() {
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public LinkedList<Player> all() {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        if (UserDirectoryListing.length != 0) {
            LinkedList<Player> list = new LinkedList<>();
            for (File child : UserDirectoryListing) {
                // Do something with child
                Player player = null;
                try {
                    player = objectMapper.readValue(new FileReader(child.getPath()), new TypeReference<Player>() {
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                list.add(player);
            }
            return list;
        }
        return null;
    }


    public void add(Player player) {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        assert UserDirectoryListing != null;
        if (UserDirectoryListing.length != 0) {
            int ID = Integer.parseInt(FilenameUtils.removeExtension(UserDirectoryListing[UserDirectoryListing.length - 1].getName()));
            player.setID(ID + 1);

        } else {
            player.setID(0);
        }

        File file = new File(USERS_DIR_PATH + "/" + player.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void remove(Player player) {
        File dir = new File(USERS_DIR_PATH);
        File[] UserDirectoryListing = dir.listFiles();
        File temp = null;

        assert UserDirectoryListing != null;
        if (UserDirectoryListing.length != 0) {
            for (File child : UserDirectoryListing) {
                if (child.getName().equals(String.valueOf(player.getID() + ".json")))
                    temp = child;
            }
            temp.delete();
        }

    }


    public void update(Player player) {

        File file = new File(USERS_DIR_PATH + "/" + player.getID() + ".json");
        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new FileWriter(file, false), player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
