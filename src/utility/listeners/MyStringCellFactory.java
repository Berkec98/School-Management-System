package utility.listeners;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;


public class MyStringCellFactory<T> implements Callback<TableColumn<T, Double>, TableCell<T, Double>> {

    public MyStringCellFactory() {
    }

    @Override
    public TableCell<T, Double> call(TableColumn<T, Double> param) {
        return new TableCell<T, Double>() {
            @Override
            public void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(doubleNormalGosterim(item));
                }
            }
        };
    }


    /**
     * Double olan sayının ondalık kısmı eğer 0 ise atar değilse kalır ÖR:      15.0 ==> 15       15.167 ==> 15.167  şeklinde
     *
     * @param d value of cell
     * @return text
     */

    public String doubleNormalGosterim(final Double d) {
        if (d == null) return "0";
        final String value = String.valueOf(d);
        final String tamSayi = value.substring(0, value.indexOf("."));
        final String ondalik = value.substring(value.indexOf(".") + 1);
        return ondalik.equals("0")?tamSayi:value;
    }
}
