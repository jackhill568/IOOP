import java.util.Scanner;
import java.math.BigInteger;

public class IBANValidator {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the IBAN");
    String ibanInput = scanner.nextLine().replaceAll("\\s", "");

    // Write your solution to Task 2 here
    if (ibanInput.length() < 4 || !ibanInput.matches("^[\\dA-Z]+$")) {
      System.out.println("Invalid IBAN");
    } else {
      ibanInput = ibanInput.substring(4, ibanInput.length()) + ibanInput.substring(0, 4);
      String ibanStr = "";
      for (int i = 0; i < ibanInput.length(); i++) {
        char chr;
        ibanStr += (Character.isDigit(chr = ibanInput.charAt(i)) ? "" + chr : "" + (chr - 'A' + 10));
      }
      BigInteger bigInt = new BigInteger(ibanStr);
      System.out.println(bigInt.mod(BigInteger.valueOf(89)).intValue() == 1 ? "Valid IBAN" : "Invalid IBAN");
    }
    scanner.close();
  }
}
