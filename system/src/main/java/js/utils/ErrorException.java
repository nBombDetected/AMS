package js.utils;

public class ErrorException extends Exception{
    protected final ErrorEnum error;

    public ErrorException(){
        this.error = ErrorEnum.UNSPECIFIED;
    }

    public ErrorException(final ErrorEnum error) {
        this.error = error;
    }

    public ErrorEnum getError() {
        return error;
    }
}
