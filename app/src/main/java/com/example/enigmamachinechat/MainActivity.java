package com.example.enigmamachinechat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.os.Bundle;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "RESULT: " + Utility.ord('O'));
        int[][] rotor_settings = {{5, 12, 1}, {4, 21, 12}, {2, 2, 2}};
        HashSet<Pair<Character, Character>> plugs = new HashSet<Pair<Character, Character>>();
        plugs.add(new Pair<>('A', 'V'));
        plugs.add(new Pair<>('B', 'S'));
        plugs.add(new Pair<>('C', 'G'));
        plugs.add(new Pair<>('D', 'L'));
        plugs.add(new Pair<>('F', 'U'));
        plugs.add(new Pair<>('H', 'Z'));
        plugs.add(new Pair<>('I', 'N'));
        plugs.add(new Pair<>('K', 'M'));
        plugs.add(new Pair<>('O', 'W'));
        plugs.add(new Pair<>('R', 'X'));
        Plugboard plug = new Plugboard(plugs);
        Reflector reflect = new Reflector('B');
        EnigmaMachine enigma = new EnigmaMachine(rotor_settings, plug, reflect);
        String code = "EDPUD";
        code = code.replaceAll(" ", "");
        String result = "";
        char letter = 'A';
        for (int i = 0; i < code.length(); i++) {
            letter = enigma.use(code.charAt(i));
            Log.d("TAG", "Letter: "+letter);
            result = result + letter;
        }
        Log.d("TAG", "RESULT: " + result);

    }
}