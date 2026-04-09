public class Practical {
    public static void main(String[] args) {
        String str = "Java Programming";

        // 1. length() - returns the length of the string
        int length = str.length();
        System.out.println("Length of the string: " + length);

        // 2. charAt() - returns the character at a specific index
        char charAt5 = str.charAt(5);
        System.out.println("Character at index 5: " + charAt5);
        // 3. substring() - returns a substring from the string
        String substring = str.substring(7, 12);
        System.out.println("Substring from index 7 to 12: " + substring);

        // 4. toUpperCase() - converts the string to uppercase
        String upperCaseStr = str.toUpperCase();
        System.out.println("String in uppercase: " + upperCaseStr);

        // 5. replace() - replaces occurrences of a specified character or substring
        String replacedStr = str.replace("Programming", "World");
        System.out.println("String after replacement: " + replacedStr);
    }
    
}