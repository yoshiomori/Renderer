package com.example.mori.mainmenu;

import android.util.Log;

/**
 * Método para emitir e gerar exceção error extraído do código.
 * Created by mori on 06/07/16.
 */
public class ErrorCondition {
    public static void e(boolean condition, String tag, String message){
        if(condition){
            Log.e(tag, message);
            throw new RuntimeException(message);
        }
    }
}
