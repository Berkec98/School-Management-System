package Crud;

import org.junit.Test;

public class LogsITest {
    LogsI logsI = new LogsI();

/*    @Test
    public void testGetEventType() throws Exception {
        EventType result = logsI.getEventType(new EventType("name"));
        Assert.assertEquals(new EventType("name"), result);
    }*/

/*    @Test
    public void testGetEventData() throws Exception {
        Groups grp= new Groups("Test Grubu");
        EventData resultCurrentNull = logsI.getEventData(grp, null);
        EventData resultOldNull = logsI.getEventData(null,grp);
        EventData resultAllNull = logsI.getEventData(null, null);
        EventData resultAllFull = logsI.getEventData(grp, grp);
        System.out.println("resultCurrentNull="+resultCurrentNull);
        System.out.println("resultOldNull="+resultOldNull);
        System.out.println("resultAllNull="+resultAllNull);
        System.out.println("resultAllFull="+resultAllFull);
    }*/

/*    @Test
    public void testGetEventTableName() throws Exception {
       EventTableDesc result1 = logsI.get("Groups");
        EventTableDesc result = logsI.getEventTableName("Groups");
        System.out.println("result:"+result);
        System.out.println("result1:"+result1);
    }*/



    @Test
    public void testVeriTabaninaLogKaydet() throws Exception {
       // logsI.veriTabaninaLogKaydet(new T(), new T(), new EventType("name"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme