package models.data;

public class Result {
    /*
        This class will need to be more general. Perhaps a member to hold any throwable
        a member to hold an array of any object, a member to hold the class of the return
        function. Essentially a Generic response class.
     */


    private String errorMessage = "";
    private String authenticationToken;
    private String gameName;
    private boolean isSuccessful;

    public boolean isSuccessful() {
        return this.isSuccessful;
    }

    public void setSuccesful(boolean success) {
        this.isSuccessful = success;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
