import com.game.DungeonDirection;
import com.game.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    @Test
    public void testRoomCoordinates(){
        Room room = new Room(2, 3);
        assertEquals(2, room.getGridX());
        assertEquals(3, room.getGridY());
    }
    @Test
    public void testRoomIsEntryByDefault(){
        Room room = new Room(0, 0);
        assertFalse(room.isEntry());
    }
    @Test
    public void testSetEntry(){
        Room room = new Room(0,0);
        room.setEntry(true);
        assertTrue(room.isEntry());
    }
    @Test
    public void testAddNeighbor(){
        Room roomA = new Room(0, 0);
        Room roomB = new Room(0, 1);
        roomA.addNeighbor(DungeonDirection.SOUTH, roomB);
        roomB.addNeighbor(DungeonDirection.NORTH, roomA);

        assertEquals(roomB, roomA.getNeighbors().get(DungeonDirection.SOUTH));
        assertEquals(roomA, roomB.getNeighbors().get(DungeonDirection.NORTH));
    }
}