package error;

import model.user.Groups;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import utility.error.ErrorCheck;
import utility.error.ErrorMessage;

public class ErrorCheckTest {

    //ErrorMessages errorMsg=new ErrorMessages();
    @InjectMocks
    ErrorCheck errorCheck;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testIsErrorCheckAll()  {
        Groups ei = new Groups("Admin");
        ErrorMessage result = errorCheck.checkIsErrorCheckAll(ei, ei.getGrupName(), 20, true, true);
        result.printErrorMessages();
        Assert.assertEquals(4, result.getErrorNumber());
    }

    @Test
    public void testIsErrorCheckAllOver20()  {
        Groups ei = new Groups("Admin444444444444444444444444444444444444444444444444444444444444444");//length 0 olduğundan test etmeyecek
        ErrorMessage result = errorCheck.checkIsErrorCheckAll(ei, ei.getGrupName(), 0, true, true);
        result.printErrorMessages();
        Assert.assertEquals(0, result.getErrorNumber());
    }

    @Test
    public void testIsErrorCheckAllLeng4() throws Exception {
        Groups ei = new Groups("AAAAA");//length 0 olduğundan test etmeyecek
        ErrorMessage result = errorCheck.checkIsErrorCheckAll(ei, ei.getGrupName(), 4, true, true);
        result.printErrorMessages();
        Assert.assertEquals(3, result.getErrorNumber());
    }

    @Test
    public void testIsErrorCheckAllUniqueFALSE()  {
        Groups ei = new Groups("Admin");//Admin aslında var ama unique false
        ErrorMessage result = errorCheck.checkIsErrorCheckAll(ei, ei.getGrupName(), 20, true, false);
        result.printErrorMessages();
        Assert.assertEquals(0, result.getErrorNumber());
    }

    @Test
    public void testIsErrorCheckAll_SORUNSUZ()  {
        // when(errorMsg.getErrorMessage(anyInt())).thenReturn("getErrorMessageResponse");
        Groups ei = new Groups("Deneme");
        ErrorMessage result = errorCheck.checkIsErrorCheckAll(ei, ei.getGrupName(), 20, true, true);
        result.printErrorMessages();
        Assert.assertEquals(0, result.getErrorNumber());
    }

    @Test
    public void testIsErrorCheckAll_SORUNSUZ_TAM20()  {
        // when(errorMsg.getErrorMessage(anyInt())).thenReturn("getErrorMessageResponse");
        Groups ei = new Groups("12345678911234567890");
        ErrorMessage result = errorCheck.checkIsErrorCheckAll(ei, ei.getGrupName(), 20, true, true);
        result.printErrorMessages();
        Assert.assertEquals(0, result.getErrorNumber());
    }

    @Test
    public void testIsErrorCheckAll_ANOTHER()  {
        // when(errorMsg.getErrorMessage(anyInt())).thenReturn("getErrorMessageResponse");
        Groups ei = new Groups("12345678911234567890");
        ErrorMessage result = errorCheck.checkIsErrorCheckAll(ei, ei.getGrupName(), 0, false, false);
        result.printErrorMessages();
        Assert.assertEquals(0, result.getErrorNumber());
    }

    @Test
    public void testCheckEmtyButNotNullEntity(){
        Groups grupEntity = new Groups(); //DİKKAT bu boş entity ama null değil string kolonları null int kolonları ise 0 olur
        ErrorMessage result = errorCheck.checkIsNullEntity(grupEntity);
        result.printErrorMessages();
        Assert.assertEquals(0, result.getErrorNumber());
    }
    @Test
    public void testcheckIsIdZero() {
        Groups grupEntity1 = new Groups();
        ErrorMessage result = errorCheck.checkIsIdZero(grupEntity1.getId());
        result.printErrorMessages();
        Assert.assertEquals(7, result.getErrorNumber());
    }
    @Test
    public void testCheckIsNullEntity() {
        Groups grupEntity1 = null;
        ErrorMessage result = errorCheck.checkIsNullEntity(grupEntity1);
        //true sonuç hatalı olduğunu işlemin yapılamayacağını belirtir
        result.printErrorMessages();
        Assert.assertEquals(1, result.getErrorNumber());
    }

    @Test
    public void testCheckIsNullColumn()  {
        Groups groups = new Groups();
        ErrorMessage result = errorCheck.checkIsNullColumn(groups.getGrupName());
        result.printErrorMessages();
        Assert.assertEquals(2, result.getErrorNumber());
    }

    @Test
    public void testIsOverColumnLength()  {
        Groups groups = new Groups("11111111111111111111111111111111111111111111111111111111111111111");
        ErrorMessage result = errorCheck.lengthOverflow(groups.getGrupName(), 20);
        result.printErrorMessages();
        Assert.assertEquals(3, result.getErrorNumber());
    }

/*    @Test
    public void testIsViolateUnique() throws Exception {
        Groups groups = new Groups("Admin");
       // ErrorMessage result = errorCheck.checkIsViolateUnique(groups.getGrupName(),groups);
        result.printErrorMessages();
        Assert.assertEquals(4, result.getErrorNumber());
    }*/
}