package com.example.enigmamachinechat;

import android.util.Pair;

import java.util.HashMap;

public class Rotor {
    private static final HashMap<Integer, Pair<String, Character>> possibleWheels =
            new HashMap<Integer, Pair<String, Character>>() {{
                put(1, new Pair<>("EKMFLGDQVZNTOWYHXUSPAIBRCJ", 'Q'));
                put(2, new Pair<>("AJDKSIRUXBLHWTMCQGZNPYFVOE", 'E'));
                put(3, new Pair<>("BDFHJLCPRTXVZNYEIWGAKMUSQO", 'V'));
                put(4, new Pair<>("ESOVPZJAYQUIRHXLNFTGKDCMWB", 'J'));
                put(5, new Pair<>("VZBRGITYUPSDNHLXAWMJQOFECK", 'Z'));
            }};
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String wheel;
    private int ringSetting;
    private int turned;
    private char turnover;

    public Rotor(int wheelNumber, int ringSetting) throws Exception {
        if (wheelNumber < 1 || wheelNumber > 5)
            throw new Exception("invalid wheel number");
        this.wheel = possibleWheels.get(wheelNumber).first;
        this.ringSetting = ringSetting % 26;
        this.turned = -(ringSetting % 26);
        this.turnover = Utility.chr(
                Utility.modulus((Utility.ord(possibleWheels.get(wheelNumber).second) - 65 + this.ringSetting), 26) + 65);
    }

    public char getTurnover() {
        return this.turnover;
    }

    public char turn() {
        char initial = alphabet.charAt((26 + this.turned) % 26);
        this.turned++;
        this.turned %= 26;
        return initial;
    }

    public char use(char letter) throws Exception {
        if (letter < 'A' || letter > 'Z') {
            throw new Exception("not an uppercase letter");
        }
        return this.wheel.charAt(Utility.modulus(Utility.ord(letter) - 65 + this.turned, 26));
    }

    public char useBackwards(char letter) throws Exception {
        if (letter < 'A' || letter > 'Z') {
            throw new Exception("not an uppercase letter");
        }
        return alphabet.charAt(Utility.modulus(this.wheel.indexOf(letter) + this.turned, 26));
    }

}
