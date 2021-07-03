package utility.error;

import model.base.EntityInterface;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

import java.util.Objects;

public  class ErrorCheck {


    /*
     @param nodes null kontrolü yapılacak nodeler combobox olmak zorunda
     @return eğer null ise true döner sorun yok ise false döner
     */
    public static boolean checkNullNodes(Node... nodes) {
        for (Node n : nodes) {
            if (n instanceof ComboBox) if (((ComboBox) n).getValue() == null) {
                ErrorMessage.EMPTY.printErrorMessages();
                return true;
            }
        }
        return false;
    }


    //bu metodlar hata kontrolu yapar ve herhangi bir sorun yoksa ErrorMessage.NO_ERROR eğer sakıncalı bir durum varsa
    // ilgili hata mesajını byte cinsinden döndürür ör:ErrorMessage.NULL_ENTITY (ErrorMessages clasında utility.mesajlar var)

    public ErrorMessage checkIsErrorCheckAll(EntityInterface entityInterface, String value, int length, boolean checkColumnNull, boolean checkUnique) {
        if (checkIsNullEntity(entityInterface) != ErrorMessage.NO_ERROR)
            return ErrorMessage.NULL_ENTITY;//error message number 1
        if (checkColumnNull) if (checkIsNullColumn(value) != ErrorMessage.NO_ERROR) return ErrorMessage.NULL_COL;
        if (length > 0)
            if (lengthOverflow(value, length) != ErrorMessage.NO_ERROR) return ErrorMessage.MAX_LENGTH;
        return ErrorMessage.NO_ERROR;
    }

    public ErrorMessage checkIsNullEntity(EntityInterface entity) {
        if (Objects.isNull(entity)) {
            return ErrorMessage.NULL_ENTITY;
        } else return ErrorMessage.NO_ERROR;
    }

    public ErrorMessage checkIsIdZero(int id) {
        if (id < 1) return ErrorMessage.ID_ZERO;
        return ErrorMessage.NO_ERROR;
    }

    public ErrorMessage checkIsNullColumn(String value) {
        if (Objects.isNull(value)) {
            return ErrorMessage.NULL_COL;
        } else return ErrorMessage.NO_ERROR;
    }

    public ErrorMessage lengthOverflow(String value, int length) {
        if (value.length() > length) {
            return ErrorMessage.MAX_LENGTH;
        } else return ErrorMessage.NO_ERROR;
    }
}
