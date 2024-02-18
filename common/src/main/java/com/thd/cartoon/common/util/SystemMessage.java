package com.thd.cartoon.common.util;

public class SystemMessage {
    //message properties
    public static final String SUCCESS = "cartoon.message.success";
    public static final String BAD_REQUEST = "cartoon.message.badRequest";
    public static final String USER_IS_DISABLE = "cartoon.message.userIsDisabled";
    public static final String USER_IS_EXPIRED = "cartoon.message.userIsExpired";
    public static final String USER_CREDENTIALS_IS_EXPIRED = "cartoon.message.userCredentialsIsExpired";
    public static final String USER_IS_LOCKED = "cartoon.message.userIsLocked";
    public static final String USER_BAD_CREDENTIALS = "cartoon.message.usernamePasswordIsInvalid";
    public static final String JWT_IS_EXPIRED = "cartoon.message.jwtIsExpired";
    public static final String JWT_IS_INVALID = "cartoon.message.jwtIsInvalid";
    public static final String ACCESS_DENIED = "cartoon.message.accessDenied";
    public static final String UNAUTHORIZED = "cartoon.message.unauthorized";
    public static final String FORBIDDEN = "cartoon.message.forbidden";
    public static final String VALUE_EXIST = "cartoon.message.valueExist";
    public static final String REGISTER_SUCCESS = "cartoon.message.registerSuccess";
    public static final String SEND_MAIL_ERROR = "cartoon.message.sendMailError";
    public static final String TOKEN_INVALID = "cartoon.message.tokenInvalid";
    public static final String TOKEN_EXPIRED = "cartoon.message.tokenExpired";
    public static final String VERIFY_SUCCESS = "cartoon.message.verifySuccess";
    public static final String NOT_FOUND = "cartoon.message.notFound";
    public static final String ACTIVATED = "cartoon.message.activated";
    public static final String GENERATE_TOKEN_SUCCESS = "cartoon.message.generateTokenSuccess";
    public static final String TWO_FIELD_NOT_MATCH = "cartoon.message.twoFieldNotMatch";
    public static final String CONTENT_SUCCESS_FORGOT_PASS= "cartoon.message.successForgotPass";
    public static final String ALREADY_SEND_FORGOT_PASS= "cartoon.message.alreadySendForgotPass";
    public static final String LINK_EXPIRED= "cartoon.message.linkAlreadyExpired";
    public static final String ACTIVE_NEW_PASS_SUCCESS= "cartoon.message.activeNewPassSuccess";
    public static final String FILE_NAME_INVALID = "cartoon.message.fileNameInvalid";
    public static final String WRITE_FILE_ERROR = "cartoon.message.writeFileError";

    // validation
    public static final String VALIDATION_NOTNULL = "{cartoon.validation.NotNull}";
    public static final String VALIDATION_NOT_BLANK = "{cartoon.validation.NotBlank}";
    public static final String VALIDATION_EMAIL = "{cartoon.validation.email}";
    public static final String VALIDATION_MIN_LENGTH = "{cartoon.validation.MinLength}";
    public static final String VALIDATION_USERNAME_PATTERN = "{cartoon.validation.usernamePattern}";
    public static final String VALIDATION_FIELD_MATCH = "{cartoon.validation.fieldMatch}";

    // message application
    public static final String CONTENT_MAIL_REGISTER = "Thanks for registering your account. Link activity will be expired after 5 minutes";
    public static final String SUBJECT_MAIL_REGISTER = "Verify your email address";
    public static final String SUBJECT_MAIL_FORGOT = "Reset your password";
    public static final String CONTENT_MAIL_FORGOT = "Please active your new password. Link activity will be expired after 5 minutes";
}
