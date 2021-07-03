package utility.TableView;

import java.lang.reflect.Field;


public class DesiredField {

    private Field field;            //kolonun veritabanındaki adı
    private String fieldTitle;          //Kullanıcının göreceği tablodaki ad
    private int minWidth;
    private int maxWidth;
    private String dateTimeFormat;      //verilirse tarih formatı
    private boolean formatOndalik;      //sayının ondalık kısmını düzenler 2.1 --> 2.1     2.0 --> 2
    private boolean hide;               //runtime de kolonun gizlenebilmesi için


    public DesiredField() {
    }

    public boolean isHide() {
        return hide;
    }

    public void setHide(boolean hide) {
        this.hide = hide;
    }

    boolean isFormatOndalik() {
        return formatOndalik;
    }

    public void setFormatOndalik(boolean formatOndalik) {
        this.formatOndalik = formatOndalik;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field fieldName) {
        this.field = fieldName;
    }

    String getFieldTitle() {
        return fieldTitle;
    }

    public void setFieldTitle(String fieldTitle) {
        this.fieldTitle = fieldTitle;
    }

    int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    String getDateTimeFormat() {
        return dateTimeFormat;
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        this.dateTimeFormat = dateTimeFormat;
    }
}
