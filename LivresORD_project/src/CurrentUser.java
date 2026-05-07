public class CurrentUser {
    private static String username;
    private static String role;

    public static void setRole(String userRole) {
        role = userRole;
    }
    public static void setUsername(String userName) {
        username = userName;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }
}