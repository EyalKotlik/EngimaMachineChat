package com.example.enigmamachinechat;

public final class Utility {

    /**
     * standard mathematical modulus
     */
    public static int modulus(int x, int y) {
        while (x < 0)
            x += y;
        return x%y;
    }

    /**
     *converts ascii value (x) to char
     */
    public static char chr(int x){
        return (char)x;
    }

    /**
     * converts char into ascii value
     */
    public static int ord(char chr){
        return (int)chr;
    }
}

