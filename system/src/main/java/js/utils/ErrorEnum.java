package js.utils;

public enum ErrorEnum {
    UNSPECIFIED(-1, "系统错误"),
    UNAUTHORIZED(401, "令牌、用户名或密码错误，请再次尝试登录"),
    FORBIDDEN(403, "权限不足，访问被禁止");

    private final int code;
    private final String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    private ErrorEnum(final int code, final String description){
        this.code = code;
        this.description = description;
    }

    public static ErrorEnum geError(int code) {

        for(ErrorEnum value: ErrorEnum.values()) {
            if(code == value.getCode()) return value;
        }
        return UNSPECIFIED;
    }
}
