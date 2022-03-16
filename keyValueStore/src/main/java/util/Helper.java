package util;

public class Helper {

    public static Object parseString(String value){
        if(isInteger(value)){
            return Integer.parseInt(value);
        }else if (isDouble(value)){
            return Double.parseDouble(value);
        }else if(isBoolean(value)){
            return Boolean.parseBoolean(value);
        }
        return value;
    }

    private static Boolean isInteger(String value){
        try {
            Integer.parseInt(value);
            return Boolean.TRUE;
        }catch (NumberFormatException e){
            return Boolean.FALSE;
        }
    }

    private static Boolean isDouble(String value){
        try {
            Double.parseDouble(value);
            return Boolean.TRUE;
        }catch (NumberFormatException e){
            return Boolean.FALSE;
        }
    }

    private static Boolean isBoolean(String value){
        return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

}
