package com.example.enigmamachinechat;

import android.util.Log;

public class EnigmaMachine {

    private Rotor[] rotors;
    private Reflector reflector;
    private Plugboard plugboard;

    /**
     * @param rotorSettings the settings for each rotor, each array represents a single rotor - the first value represents the wheel number, the second
     *                      represents the ring settings, and the third represents the rotor setting
     * @param plugboard     the plugboard
     * @param reflector     the reflector
     */
    public EnigmaMachine(int[][] rotorSettings, Plugboard plugboard, Reflector reflector) {
        this.rotors = new Rotor[3];
        for (int i = 0; i < 3; i++) {
            this.rotors[i] = new Rotor(rotorSettings[i][0], rotorSettings[i][1] - 1);
            for (int j = 0; j < rotorSettings[i][2] - 1; j++)
                this.rotors[i].turn();
        }
        this.reflector = reflector;
        this.plugboard = plugboard;
    }

    public char use(char letter) {
        if (this.rotors[0].turn() == this.rotors[0].getTurnover())
            if (this.rotors[1].turn() == this.rotors[1].getTurnover())
                this.rotors[2].turn();
        char modifiedLetter = this.plugboard.use(letter);
        Log.d("TAG", "use: 1:"+modifiedLetter);
        for (int i = 0; i < 3; i++) {
            modifiedLetter = this.rotors[i].use(modifiedLetter);
            Log.d("TAG", "use: 2:" + modifiedLetter);
        }
        modifiedLetter = this.reflector.use(modifiedLetter);
        Log.d("TAG", "use: 3:"+modifiedLetter);
        for (int i = 2; i > -1; i--) {
            modifiedLetter = this.rotors[i].useBackwards(modifiedLetter);
            Log.d("TAG", "use: 4:" + modifiedLetter);
        }
        modifiedLetter = this.plugboard.use(modifiedLetter);
        Log.d("TAG", "use: 5:"+modifiedLetter);
        return modifiedLetter;
    }
}
