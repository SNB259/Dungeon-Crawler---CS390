import com.game.DungeonDirection;
import com.game.DungeonGenerator;
import com.game.Room;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DungeonGeneratorTest {

    @Test
    public void testExactlyOneEntry(){
        DungeonGenerator gen = new DungeonGenerator(5, 5, 8);
        Room[][] grid = gen.generateDungeon();
        int entryCount = 0;
        for(Room[] row : grid)
            for(Room room : row)
                if(room != null && room.isEntry()) entryCount++;
        assertEquals(1, entryCount);
    }
    @Test
    public void testExactlyOneExit(){
        DungeonGenerator gen = new DungeonGenerator(5, 5, 8);
        Room[][] grid = gen.generateDungeon();
        int exitCount = 0;
        for(Room[] row : grid)
            for(Room room : row)
                if(room != null && room.isExit()) exitCount++;
        assertEquals(1, exitCount);
    }
    @Test
    public void testMinimumRoomCount(){
        DungeonGenerator gen = new DungeonGenerator(5, 5, 8);
        Room[][] grid = gen.generateDungeon();
        int count = 0;
        for(Room[] row : grid)
            for(Room room : row)
                if(room != null) count++;
        assertTrue(count >= 8);
    }
    @Test
    public void testNeighborsAreBidirectional(){
        DungeonGenerator gen = new DungeonGenerator(5, 5, 8);
        Room[][] grid = gen.generateDungeon();
        for(Room[] row : grid){
            for(Room room : row){
                if(room != null){
                    for(DungeonDirection dir : room.getNeighbors().keySet()){
                        Room neighbor = room.getNeighbors().get(dir);
                        assertTrue(neighbor.getNeighbors().containsKey(dir.getOpposite()));
                    }
                }
            }
        }
    }

}