import com.lqb.thoughtworks.trains.server.exception.SystemException;
import com.lqb.thoughtworks.trains.server.service.IStationCenterService;
import com.lqb.thoughtworks.trains.server.service.StationCenterServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author liqibo
 * @description StationService测试
 * @date 2019/9/16 14:39
 **/
public class StationServiceTest {

    private IStationCenterService stationCenterService;

    @Before
    public void before() {
        stationCenterService = new StationCenterServiceImpl();
        stationCenterService.reload("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7", true);
    }

    @Test
    public void testGetDistance() {
        Assert.assertEquals(9, stationCenterService.getDistance("A", "B", "C"));
        Assert.assertEquals(13, stationCenterService.getDistance("A", "D", "C"));
    }

    @Test
    public void testGetLeastStops() {
        Assert.assertEquals(2, stationCenterService.getLeastStops("C", "C"));

        String errMsg = "";
        try {
            stationCenterService.getDistance("B", "A");
        } catch (SystemException e) {
            errMsg = e.getMessage();
        }
        Assert.assertEquals("NO SUCH ROUTE", errMsg);

        Assert.assertEquals(2, stationCenterService.getLeastStops("D", "B"));
    }

    @Test
    public void testGetLinesNumOfStops() {
        Assert.assertEquals(3, stationCenterService.getLinesNumOfStops("A", "C", 4));
        Assert.assertEquals(1, stationCenterService.getLinesNumOfStops("A", "B", 3));
        Assert.assertEquals(2, stationCenterService.getLinesNumOfStops("A", "D", 3));
        Assert.assertEquals(4, stationCenterService.getLinesNumOfStops("A", "E", 5));
    }

    @Test
    public void testGetLeastDistance() {
        Assert.assertEquals(9, stationCenterService.getLeastDistance("A",  "C"));
        Assert.assertEquals(9, stationCenterService.getLeastDistance("B",  "B"));
        Assert.assertEquals(6, stationCenterService.getLeastDistance("B", "E"));
        Assert.assertEquals(12, stationCenterService.getLeastDistance("B", "D"));

        String errMsg = "";
        try {
            stationCenterService.getLeastDistance("A", "A");
        } catch (SystemException e) {
            errMsg = e.getMessage();
        }
        Assert.assertEquals("NO SUCH ROUTE BETWEEN A AND A", errMsg);
    }

    @Test
    public void testGetMostLinesNumInDistance() {
        Assert.assertEquals(7, stationCenterService.getMostLinesNumInDistance("C",  "C", 30));
        Assert.assertEquals(0, stationCenterService.getMostLinesNumInDistance("A", "A", 30));
        Assert.assertEquals(1, stationCenterService.getMostLinesNumInDistance("B", "B", 10));
        Assert.assertEquals(2, stationCenterService.getMostLinesNumInDistance("B", "B", 20));
        Assert.assertEquals(4, stationCenterService.getMostLinesNumInDistance("B", "B", 26));
    }
}
