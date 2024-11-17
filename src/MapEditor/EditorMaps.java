package MapEditor;

import Level.Map;
import Maps.TestMap;
import Maps.TitleScreenMap;
import Maps.Map1;
import Maps.Map2;
import Maps.Map3;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("Map1");
            add("Map2");
            add("Map3");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "Map1":
                return new Map1();
            case "Map2":
                return new Map2();
            case "Map3":
                return new Map3();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
