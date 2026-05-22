import com.game.DungeonDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DungeonDirectionTest{
    @Test
    public void testNorthOppositeIsSouth(){
        assertEquals(DungeonDirection.SOUTH, DungeonDirection.NORTH.getOpposite());
    }
    @Test
    public void testSouthOppositeIsNorth(){
        assertEquals(DungeonDirection.NORTH, DungeonDirection.SOUTH.getOpposite());
    }
    @Test
    public void testWestOppositeIsEast(){
        assertEquals(DungeonDirection.EAST, DungeonDirection.WEST.getOpposite());
    }
    @Test
    public void testEastOppositeIsWest(){
        assertEquals(DungeonDirection.WEST, DungeonDirection.EAST.getOpposite());
    }
    @Test
    public void testNorthOffsets(){
        assertEquals(0,DungeonDirection.NORTH.getDx());
        assertEquals(-1, DungeonDirection.NORTH.getDy());
    }
    @Test
    public void testSouthOffsets(){
        assertEquals(0,DungeonDirection.SOUTH.getDx());
        assertEquals(1, DungeonDirection.SOUTH.getDy());
    }
    @Test
    public void testEastOffsets(){
        assertEquals(1,DungeonDirection.EAST.getDx());
        assertEquals(0, DungeonDirection.EAST.getDy());
    }
    @Test
    public void testWestOffsets(){
        assertEquals(-1,DungeonDirection.WEST.getDx());
        assertEquals(0, DungeonDirection.WEST.getDy());
    }
}
