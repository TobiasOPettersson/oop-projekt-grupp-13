package test.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import src.Model.AMap;
import src.Model.MapOne;
import src.Model.TileAvailability;

public class MapOneTest {

    @Test
    public void createGridAndMakeEverythingFree(){
        AMap map = new MapOne();
        assertEquals(TileAvailability.Free, map.grid[0][0]);
    }
}
