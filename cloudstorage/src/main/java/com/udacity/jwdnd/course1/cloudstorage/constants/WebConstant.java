package com.udacity.jwdnd.course1.cloudstorage.constants;

/**
 * WebConstant.
 *
 * @author NguyenT4.
 * @since 2023/09/25.
 */
public class WebConstant {

    /**
     * MAX_SIZE_MB.
     */
    public final static int MAX_SIZE_MB = 15000000;

    /**
     * TabPattern.
     */
    public class TabPattern {

        /**
         * TAB_DEFAULT.
         */
        public final static String TAB_DEFAULT = "tab";

        /**
         * TAB_SELECTION.
         */
        public final static String TAB_SELECTION = "nav-{0}-tab";
    }


    /**
     * TableName.
     */
    public class TableName {

        /**
         * CREDENTIAL_MODEL.
         */
        public static final String CREDENTIAL_MODEL = "credentialModel";

        /**
         * NOTE_MODEL.
         */
        public static final String NOTE_MODEL = "noteModel";

        /**
         * NOTES.
         */
        public static final String NOTES = "notes";

        /**
         * CREDENTIALS.
         */
        public static final String CREDENTIALS = "credentials";

    }

    /**
     * MessageResponse.
     */
    public class MessageResponse {

        /**
         * MESSAGE_ERROR.
         */
        public static final String MESSAGE_ERROR = "messageError";

        /**
         * MESSAGE_SUCCESS.
         */
        public static final String MESSAGE_SUCCESS = "messageSuccess";


        /**
         * LOGIN_RESPONSE.
         */
        public static final String LOGIN_RESPONSE = "Login success !";

        /**
         * LOGOUT_RESPONSE.
         */
        public static final String LOGOUT_RESPONSE = "redirect:/login?logout";

        /**
         * SIGNUP_RESPONSE.
         */
        public static final String SIGNUP_RESPONSE = "Sign Up view !";

        /**
         * USERNAME_ALREADY_EXISTS.
         */
        public static final String USERNAME_ALREADY_EXISTS = "The username already exists.";

        /**
         * SIGNING_ERROR.
         */
        public static final String SIGNING_ERROR = "Signing error. Please try again.";

        /**
         * SIGNING_SUCCESS.
         */
        public static final String SIGNING_SUCCESS = "Signing Success !";

        /**
         * INSERT_DATA_TABLE_SUCCESS.
         */
        public static final String INSERT_DATA_TABLE_SUCCESS = "Insert {0} success !";

        /**
         * UPDATE_DATA_TABLE_SUCCESS.
         */
        public static final String UPDATE_DATA_TABLE_SUCCESS = "Update {0} success !";

        /**
         * INSERT_DATA_TABLE_ERROR.
         */
        public static final String INSERT_DATA_TABLE_ERROR = "Insert {0} error !";

        /**
         * UPDATE_DATA_TABLE_ERROR.
         */
        public static final String UPDATE_DATA_TABLE_ERROR = "Update {0} error !";

        /**
         * DELETE_DATA_TABLE_SUCCESS.
         */
        public static final String DELETE_DATA_TABLE_SUCCESS = "Delete {0} success !";

        /**
         * DELETE_DATA_TABLE_ERROR.
         */
        public static final String DELETE_DATA_TABLE_ERROR = "Delete {0} error !";
    }


    /**
     * UserTable.
     */
    public class UserTable {
        /**
         * USER_ID.
         */
        public static final String USER_ID = "userId";
    }

    /**
     * FileTable.
     */
    public class FileTable {

        /**
         * USER_ID.
         */
        public static final String FILE_ID = "fileId";

        /**
         * USER_ID.
         */
        public static final String FILE_NAME = "fileName";

        /**
         * FILE_UPLOAD.
         */
        public static final String FILE_UPLOAD = "fileUpload";

    }

    /**
     * CredentialTable.
     */
    public class CredentialTable {
        /**
         * CREDENTIAL_ID.
         */
        public static final String CREDENTIAL_ID = "credentialid";
    }

    /**
     * NoteTable.
     */
    public class NoteTable {
        /**
         * CREDENTIAL_ID.
         */
        public static final String NOTE_ID = "noteId";
    }

    /**
     * GetDataFromTable.
     */
    public class GetDataFromTable {
        /**
         * SELECT_USER_BY_USER_NAME.
         */
        public static final String SELECT_USER_BY_USER_NAME = "SELECT * FROM USERS WHERE username = #{username}";

        /**
         * SELECT_USER_BY_USER_ID.
         */
        public static final String SELECT_USER_BY_USER_ID = "SELECT * FROM USERS WHERE userid = #{userId}";

        /**
         * SELECT_NOTE_BY_USER_ID.
         */
        public static final String SELECT_NOTE_BY_USER_ID = "SELECT * FROM NOTES WHERE userid = #{userId}";

        /**
         * SELECT_FILE_BY_FILE_ID.
         */
        public static final String SELECT_FILE_BY_FILE_ID = "SELECT * FROM FILES WHERE fileid = #{fileId}";

        /**
         * SELECT_FILE_BY_USER_ID.
         */
        public static final String SELECT_FILE_BY_USER_ID = "SELECT * FROM FILES WHERE userid = #{userId}";

        /**
         * SELECT_FILE_BY_FILE_NAME.
         */
        public static final String SELECT_FILE_BY_FILE_NAME = "SELECT * FROM FILES WHERE filename = #{fileName}";


        /**
         * SELECT_CREDENTIAL_BY_USER_ID.
         */
        public static final String SELECT_CREDENTIAL_BY_USER_ID = "SELECT * FROM CREDENTIALS WHERE userid = #{userId}";

    }

    /**
     * InsertDataToTable.
     */
    public class InsertDataToTable {
        /**
         * INSERT_USER.
         */
        public static final String INSERT_USER = "INSERT INTO USERS (username, salt, password, firstname, lastname) " + "VALUES(#{username}, #{salt}, #{password}, #{firstName}, #{lastName})";

        /**
         * INSERT_FILE.
         */
        public static final String INSERT_FILE = "INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " + "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})";

    }

    /**
     * DeleteDataFromTable.
     */
    public class DeleteDataFromTable {
        /**
         * DELETE_FILE.
         */
        public static final String DELETE_FILE = "DELETE FROM FILES WHERE fileId = #{fileId}";
    }

    /**
     * ControllerUrl
     */
    public class ControllerUrl {

        /**
         * URL_HOME.
         */
        public static final String URL_HOME = "/home";

        /**
         * URL_LOGIN.
         */
        public static final String URL_LOGIN = "/login";

        /**
         * URL_LOGOUT.
         */
        public static final String URL_LOGOUT = "/logout";


        /**
         * URL_SIGNUP.
         */
        public static final String URL_SIGNUP = "/signup";

        /**
         * URL_GET_FILE_BY_FILE_ID.
         */
        public static final String URL_GET_FILE_BY_FILE_ID = "/files/get-file-by-file-id/{fileId}";

        /**
         * URL_DELETE_FILE_BY_FILE_ID.
         */
        public static final String URL_DELETE_FILE_BY_FILE_ID = "/files/delete/{fileId}";

        /**
         * URL_ADD_OR_UPDATE_CREDENTIAL.
         */
        public static final String URL_ADD_OR_UPDATE_CREDENTIAL = "/credentials/add-new-or-update-credential";

        /**
         * URL_DELETE_CREDENTIAL.
         */
        public static final String URL_DELETE_CREDENTIAL = "/credentials/delete-note-by-credential-id/{credentialid}";

        /**
         * URL_DELETE_NOTE.
         */
        public static final String URL_DELETE_NOTE = "/notes/delete-note-by-note-id/{noteId}";

        /**
         * URL_ADD_OR_UPDATE_NOTE.
         */
        public static final String URL_ADD_OR_UPDATE_NOTE = "/notes/add-new-or-update-note";


        /**
         * URL_REDIRECT_HOME.
         */
        public static final String URL_REDIRECT_HOME = "redirect:/home";

        /**
         * URL_UPLOAD_FILE.
         */
        public static final String URL_UPLOAD_FILE = "/files/uploadFile";

    }
}
