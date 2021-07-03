package error;

import daolar.DaoRepositoryImp;
import org.junit.Assert;
import org.junit.Test;
import utility.MyPredicateCreator;
import utility.error.MyErrorHelper;

public class MyErrorHelperTest {
    MyErrorHelper myErrorHelper = new MyErrorHelper();

    @Test
    public void testHataVarsaMesajGosterReturnEt() throws Exception {
        boolean result = myErrorHelper.hataVarsaMesajGosterReturnEt();
        Assert.assertEquals(true, result);
    }


    @Test
    public void testTarihVerisiDogruGirildiMi() throws Exception {
        myErrorHelper.tarihVerisiDogruGirildiMi(null, "fieldDescription");
    }



    @Test
    public void testBoyutSifirOlurAmaFazlaAzOlamaz() throws Exception {
        myErrorHelper.boyutSifirOlurAmaFazlaAzOlamaz("value", "nodeDescription", 0);
    }

    @Test
    public void testBoyutFazlaOlamaz() throws Exception {
        myErrorHelper.boyutFazlaOlamaz("value", "nodeDescription", 0);
    }

    @Test
    public void testUniqueDegerTekrarGirilemez() throws Exception {
        myErrorHelper.uniqueDegerTekrarGirilemez(new DaoRepositoryImp(null), "nodeDescription", "fieldName", "fieldValue", 0);
    }

    @Test
    public void testSinifaOzgunPredicateCalistir() throws Exception {
        myErrorHelper.sinifaOzgunPredicateCalistir(new DaoRepositoryImp(null), "fieldValues", "nodeDescription", new MyPredicateCreator(null, null, null, "value"));
    }

    @Test
    public void testSifirOlamaz() throws Exception {
        myErrorHelper.sifirOlamaz(null, "nodeDescription");
    }


}
