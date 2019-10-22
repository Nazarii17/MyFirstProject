package nazarii.tkachuk.com.utils;

public final class ValidatorUtil {
    private ValidatorUtil() {throw new UnsupportedOperationException(); }

    public static boolean validatePhoneNumber(String phoneNumber) {
        //validate phone numbers of format "1234567890"
        if (phoneNumber.matches("\\d{9}")) return true;
            //validating phone number with -, . or spaces
        else if(phoneNumber.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;
            //validating phone number with extension length from 3 to 5
        else if(phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) return true;
            //validating phone number where area code is in braces ()
        else if(phoneNumber.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) return true;
            //return false if nothing matches the input
        else return false;
    }
}
