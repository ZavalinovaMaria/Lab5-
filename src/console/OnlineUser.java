package console;

public class OnlineUser {
     private String userName;



    public OnlineUser(String userName) {
        this.userName = userName;
    }

    public OnlineUser() {
    }


    /**
     * Gets username.
     *
     * @return the username
     */

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Sets user name.
     *
     * @param userName the user name
     * @return the user name
     */
    /*
    public CurrentUserManager setUserName(String userName) {
        this.userName = userName;
        return this;
    }

     */
}
