package utility.enums;

public enum OpType {
    SAVE("SAVE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String strType;

    OpType(String strType) {
        this.strType = strType;
    }
    public String getStrType(){
        return strType;
    }
}
