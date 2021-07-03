package utility.TableView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFHelper {
    private Object clazz1;
    private DesiredField[] df;


    public DFHelper(Object clazz) {
        this.clazz1 = clazz;
    }

    public DesiredField[] buFieldleriOlustur(String... fieldName) {
        Field[] fields;
        if (fieldName.length==0) {
            System.out.println("fieldName null... no field is specified, therefore all fields of the class are created");
            fields= tumFieldleriGetirKalitimDahil(clazz1.getClass()).toArray(new Field[0]);
        }
        else{
            fields = stringDiziyiFieldDiziyeCevir(fieldName);
        }
        df = new DesiredField[fields.length];
        for (int i = 0; i < df.length; i++) {
            df[i] = new DesiredField();
            df[i].setField(fields[i]);
        }
        return df;
    }



    public DesiredField[] fieldBasliklariSunlarOlsun(String... fieldTitle) {
        if (df == null) {
            System.out.println("No headers will be added to null table columns in the DF array.");
            return null;
        }
        if (df.length < fieldTitle.length) {
            System.out.println("The DF array has more elements than the specified columns. Processing continued but there will be columns not shown");
        }
        if (df.length > fieldTitle.length) {
            System.out.println("An ArrayIndexOutOfBoundsException error will occur because DF array is less than the specified columns. That's why we canceled adding titles...");
            return null;
        }

        for (int i = 0; i < df.length; i++) {
            df[i].setFieldTitle(fieldTitle[i]);
        }
        return df;
    }



    private Field[] stringDiziyiFieldDiziyeCevir(String... istenenKolonlar) {
        final int kolonAdedi = istenenKolonlar.length;
        Field[] desiredFields = new Field[kolonAdedi];
        List<Field> inheritedFieldsAll = tumFieldleriGetirKalitimDahil(clazz1.getClass());
        for (int i = 0; i < kolonAdedi; i++) {
            desiredFields[i] = findField(inheritedFieldsAll, istenenKolonlar[i]);
        }
        return desiredFields;
    }



    private List<Field> tumFieldleriGetirKalitimDahil(Class<?> type) {
        List<Field> result = new ArrayList<>();
        Class<?> i = type;
        while (i != null && i != Object.class) {
            Collections.addAll(result, i.getDeclaredFields());
            i = i.getSuperclass();
        }
        return result;
    }



    private Field findField(List<Field> fields, String name) {
        for (Field field : fields) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        System.out.println("ERROR: The specified field entity '" + name + "' does not exist. Table View Helper class findField method");
        return null;
    }
}