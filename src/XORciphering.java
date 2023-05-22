public class XORciphering {
    static public String xor(String text, String key){
        char[] textChars = text.toCharArray();
        char[] keyChars = key.toCharArray();

        // XOR individual chars 1/0
        for (int i = 0; i < textChars.length; i++){
            char keyChar = keyChars[i % keyChars.length];
            char textChar = textChars[i];

            if ((textChar != '0' && textChar != '1') || (keyChar != '0' && keyChar != '1')){
                throw new IllegalArgumentException("Input must be a binary string!");
            }

            textChars[i] = keyChar == textChar ? '0' : '1';
        }

        return String.valueOf(textChars);
    }
}
