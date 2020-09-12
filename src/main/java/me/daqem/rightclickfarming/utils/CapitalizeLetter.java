package me.daqem.rightclickfarming.utils;

public class CapitalizeLetter {

    public String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

}
