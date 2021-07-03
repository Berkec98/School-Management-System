package listView;

import utility.TableView.Table_View;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

public class Table_ViewTest {
    @InjectMocks
    Table_View table_View;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

@Test
public void observableListTest(){
        table_View.getDataFromDatabase();
}
    @Test
    public void tableViewTest() {
/*        //table_View= new Table_View<>();
        table_View.createTableColumns("grupName");
        List<Groups> grList = new ArrayList<>();
        grList.add(new Groups("Admin"));
        grList.add(new Groups("NormalKull"));
        grList.add(new Groups("TamYetkili"));
        table_View.addEntityListToListView(grList);*/
    }
   /* @Test
    public void testCreateTableColumns() throws Exception {
        table_View.createTableColumns("cols");
    }

    @Test
    public void testAddEntityListToListView() throws Exception {
        table_View.addEntityListToListView(Arrays.<T>asList(new T()));
    }

    @Test
    public void testİnitialize() throws Exception {
        table_View.initialize(null, null);
    }

    @Test
    public void testChangeButtonsDisablingStateTo() throws Exception {
        table_View.changeButtonsDisablingStateTo(true);
    }

    @Test
    public void testShowDialog() throws Exception {
        table_View.showDialog("name");
    }

    @Test
    public void testExecuteCrudRequest() throws Exception {
        boolean result = table_View.executeCrudRequest(new CrudImpSave(), "type");
        Assert.assertEquals(true, result);
    }

    @Test
    public void testChangeState() throws Exception {
        when(entityFactory.createEntity(any())).thenReturn(null);

        table_View.changeState(WPATH.EVENTDATA);
    }

    @Test
    public void testGetDaoRepositoryImp() throws Exception {
        when(entityFactory.createEntity(any())).thenReturn(null);

        DaoRepositoryImp result = table_View.getDaoRepositoryImp();
        Assert.assertEquals(new DaoRepositoryImp(null), result);
    }

    @Test
    public void testGetEntityInterface() throws Exception {
        when(entityFactory.createEntity(any())).thenReturn(null);

        EntityInterface result = table_View.getEntityInterface();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetNewBlankEntity() throws Exception {
        when(entityFactory.createEntity(any())).thenReturn(null);

        EntityInterface result = table_View.getNewBlankEntity();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetEntityFactory() throws Exception {
        EntityFactory result = table_View.getEntityFactory();
        Assert.assertEquals(new EntityFactory(), result);
    }*/
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme