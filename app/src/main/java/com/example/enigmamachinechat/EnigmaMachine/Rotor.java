package com.example.enigmamachinechat.EnigmaMachine;

import android.util.Log;
import android.util.Pair;

import com.example.enigmamachinechat.Utility;

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

    /**
     * @param wheelNumber the specific wheel used in this rotor
     * @param ringSetting the setting of the ring in this rotor
     */
    public Rotor(int wheelNumber, int ringSetting) {
        this.wheel = possibleWheels.get(wheelNumber).first;
        this.ringSetting = Utility.modulus(ringSetting, 26);
        this.turned = -this.ringSetting;
        this.turnover = Utility.chr(
                Utility.modulus((Utility.ord(possibleWheels.get(wheelNumber).second) - 65 - this.ringSetting), 26) + 65);
    }

    public char getTurnover() {
        return this.turnover;
    }

    /**
     * turns the rotor by 1
     *
     * @return the position from which the rotor turned
     */
    public char turn() {
        char initial = alphabet.charAt(Utility.modulus(26 + this.turned, 26));
        this.turned++;
        this.turned = Utility.modulus(this.turned, 26);
        return initial;
    }

    /**
     * Sends a letter through the rotor, encrypting it
     *
     * @param letter the letter sent in
     * @return the letter after encryption
     */
    public char use(char letter) {
        return this.alphabet.charAt(Utility.modulus(Utility.ord(this.wheel.charAt(Utility.modulus(Utility.ord(letter) - 65 + this.turned, 26))) - 65 - this.turned, 26));
    }

    /**
     * Sends a letter through the rotor backwards, meaning sent through the other side
     *
     * @param letter letter sent it
     * @return encrypted letter
     */
    public char useBackwards(char letter) {
        int index = Utility.modulus(this.wheel.indexOf(Utility.chr((Utility.ord(letter)-65+this.turned)%26+65))-this.turned,26);
        return alphabet.charAt(index);
    }

}
