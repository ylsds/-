package com.Util;

import static java.util.UUID.randomUUID;

public class UUIDUtil {
    public static String getUUID(){
        return randomUUID().toString().replace("-","");
    }
}
