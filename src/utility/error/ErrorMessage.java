package utility.error;


import utility.mesajlar.MyAlert;

public enum ErrorMessage {

    NO_ERROR(0, "... error checking before database operations; THE ERROR IS NOT ENCOUNTERED..."),
    NULL_ENTITY(1, "The object is NULL. We had to cancel the requested transaction..."),
    NULL_COL(2, "The specified Column is NULL. We had to cancel the requested transaction...."),
    MAX_LENGTH(3, "The specified column exceeds the MAX value. We had to cancel the requested transaction...."),
    RUNTIMEEXCEPTION(5, " RuntimeException error occurred while processing "),
    ROLLBACKEXCEPTION(6, "Rollback Exception error occurred during ROLLBACK "),
    ID_ZERO(7, " The ID has a value of NULL or 0."),
    IO_FORM_LOAD(10, "An input output error occurred while trying to open the form."),
    FACTORY_CLASS_ERROR(12, "Entity specified in Factory class is Not Encoded"),
    EMPTY(18, "Expected data / data were not entered. You have entered blank records or some of the data missing."),
    NO_ACTIVE_STUDENT(19, "No Active Person Selected..."),
    RELATED(20, "The data you want to be deleted cannot be deleted since there are records connected to it...."),
    ANOTHER_ERROR(21, "An unidentified error occurred while processing.");


    private final int errorNumber;
    private final String errorMessages;

    ErrorMessage(int errorNumber, String errorMessages) {
        this.errorNumber = errorNumber;
        this.errorMessages = errorMessages;

    }

    public boolean isError() {
        return this.getErrorNumber() > 0;
    }

    public int getErrorNumber() {
        return errorNumber;
    }

    public String getErrorMessages() {
        return errorMessages;
    }


    public void printErrorMessages() {
        new MyAlert().showErrorAlert( errorMessages, "Error");
    }


    public void printErrorMessages(String exMessage) {
        new MyAlert().showErrorAlert(errorMessages+"\n" +exMessage,"Error occured");
    }


    public void printErrorMessages(String exMessage,Exception e) {
        new MyAlert().showErrorAlert(errorMessages+"\n" +exMessage+"\n"+e.getMessage(),"Error occured");
    }
}