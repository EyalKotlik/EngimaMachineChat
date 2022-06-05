package com.example.enigmamachinechat;

import androidx.core.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Plugboard {
    private HashMap<Character, Character> plugs;

    /**
     * @param plugs pairs of connected letters
     */
    public Plugboard(HashSet<Pair<Character, Character>> plugs) {
        this.plugs = new HashMap<Character, Character>() {{
            for (char letter = 'A'; letter <= 'Z'; letter++)
                put(letter, letter);
        }};
        for (Pair<Character, Character> connection : plugs) {
            this.plugs.put(connection.first, connection.second);
            this.plugs.put(connection.second, connection.first);
        }
    }

    /**
     * sends a letter through the plugboard, encrypting it
     * @param letter letter before encryption
     * @return letter after encryption
     */
    public char use(char letter){
        return this.plugs.get(letter);
    }
}
