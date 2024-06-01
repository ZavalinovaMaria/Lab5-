package console;

import subjects.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;


public class AuthenticationManager {
    private final UserDAO userDatabaseProvider;
    private User currentUser;

    public AuthenticationManager() {
        this.userDatabaseProvider = new UserDAO();
    }

    public boolean login(String username, String password) {
        User user = userDatabaseProvider.getUserByUsername(username);
        if (user != null) {
            String hashedInput = hashPassword(password);
            if (hashedInput != null && hashedInput.equals(user.getPasswordHash())) {
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean register(String username, String password) {
        String hashedPassword = hashPassword(password);
        User newUser = new User(username, hashedPassword);
        return userDatabaseProvider.addUser(newUser);
    }

    public List<String> getUsernames() {
        return userDatabaseProvider.getAllUsernames();
    }


    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found");
        }
    }
}
