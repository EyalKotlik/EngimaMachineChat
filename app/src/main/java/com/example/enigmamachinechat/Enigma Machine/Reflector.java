package com.example.enigmamachinechat;

import android.util.Pair;

import java.util.HashMap;

public class Reflector {
    private static final HashMap<Character, String> possibleReflectors =
            new HashMap<Character, String>() {{
                put('A', "EJMZALYXVBWFCRQUONTSPIKHGD");
                put('B', "YRUHQSLDPXNGOKMIEBFZCWVJAT");
                put('C', "FVPJIAOYEDRZXWGCTKUQSBNMHL");
            }};
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String reflector;

    /**
     * @param reflector the character denoting the reflector version
     */
    public Reflector(Character reflector){
        this.reflector = possibleReflectors.get(reflector);
    }

    /**
     * sends a letter through the reflector, encrypting it
     * @param letter the letter sent into the reflector
     * @return the letter after encryption
     */
    public char use(char letter){
        return this.reflector.charAt(Utility.ord(letter)-65);
    }
}
