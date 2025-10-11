import java.util.Scanner;

public class EANBarcodeCompleter {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the barcode prefix");
    String barcodePrefix = scanner.nextLine();

    // Write your solution to Task 1 here

    if (barcodePrefix.length() != 12 || !barcodePrefix.matches("^\\d+$")) {
      System.out.println("barcode is not valid");
    } else {
      int total = 0;
      for (int i = 0; i < barcodePrefix.length(); i++)
        total += (i % 2 == 0) ? 1 * (barcodePrefix.charAt(i) - '0') : 4 * (barcodePrefix.charAt(i) - '0');
      System.out.println("Complete barcode: " + barcodePrefix + (10 - (total % 10)));
    }
    scanner.close();
  }
}
