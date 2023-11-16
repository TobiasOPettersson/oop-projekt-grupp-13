package test.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import src.Model.AMap;
import src.Model.EnemyDirection;
import src.Model.MapOne;
import src.Model.TileAvailability;
import src.Model.TileTerrain;

public class MapOneTest {

    @Test
    public void createGridAndMakeEverythingFree(){
        AMap map = new MapOne();
        assertEquals(false, map.getTile(0, 4).getPlaceable());
    }

    @Test
    public void testDirectionOfEveryPath(){
        AMap map = new MapOne();
        assertEquals(EnemyDirection.RIGHT, map.getPathDirections().get(7));
    }
}
