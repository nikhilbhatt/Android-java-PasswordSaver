package com.example.passwordsaver;
import android.provider.BaseColumns;
public class emailcontract {
    public emailcontract(){}
    public final static class emailpasswordentry implements BaseColumns
    {
         public final static String TABLE_NAME="passwordsaver";
         public final static String COLOUMN_1="Email";
         public final static String COLOUMN_2="Password";
         public final static String COLOUMN_3="Name";
    }
}
