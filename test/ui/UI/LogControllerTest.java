package ui.UI;


import controller.log.LogController;
import factory.EntityFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;



public class LogControllerTest {

    @InjectMocks
    LogController logController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

   @Test
   public void tableviewTest(){
        logController=new LogController();
        System.out.println(logController.getDataFromDatabase());
   }

    @Test
    public void testGetEntityFactory() throws Exception {
        EntityFactory result = logController.getEntityFactory();
        Assert.assertEquals(new EntityFactory(), result);
    }
}