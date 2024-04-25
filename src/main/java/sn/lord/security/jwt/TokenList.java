package sn.lord.security.jwt;

import java.util.HashMap;

public class TokenList {
    private static final HashMap<String, String> blacklist = new HashMap<>();

    public static void addToList(String username, String token) {
        blacklist.put(username, token);
    }

    public static boolean isContainsTokenOf(String username, String token) {
        String _token = blacklist.get(username);

        return token != null && token.equals(_token);
    }
}
