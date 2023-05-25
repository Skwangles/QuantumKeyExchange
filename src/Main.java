public class Main {

    // Public for testability - not ideal
    public static int[][] transmitterInfos;
    public static String agreedKeyTransmitter;
    public static int[][] receiverInfos;
    public static String agreedKeyReceiver;

    private static final int VALUE = 1;
    private static final int POLARIZATION = 0;

    public static void main(String[] args){

        // Stream of Qubits from one to the other
        generateReceiverValues(generateTransmitterValues(1024));

        //Share polarizations with one another and infer key
        agreedKeyTransmitter = findSharedKey(transmitterInfos, getOnlyPolarizations(receiverInfos));
        agreedKeyReceiver = findSharedKey(receiverInfos, getOnlyPolarizations(transmitterInfos));

        String secretMsg = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";

        // Encrypt using transmitter's 'key'
        String encryptedMsg = XORciphering.xor(secretMsg, agreedKeyTransmitter);

        // Decrypt using receiver's 'key'
        String decryptedMessage = XORciphering.xor(encryptedMsg, agreedKeyReceiver);

        System.out.printf("Original: %s, PSK Encrypted: %s, Unencrypted: %s", secretMsg, encryptedMsg, decryptedMessage);
        System.out.println();
        System.out.println("Shared keys are: " + (agreedKeyReceiver.equals(agreedKeyTransmitter) ? "Equal!" : "Incorrect!"));
        System.out.printf("Transmitter Key: %s, Receiver Key: %s", agreedKeyTransmitter, agreedKeyReceiver);
    }


    public static Qubit[] generateTransmitterValues(int qubits){
        if (qubits <= 0) throw new IllegalArgumentException("Input must be atleast 1 qubit");
        transmitterInfos = new int[qubits][2];
        Qubit[] qubitStream = new Qubit[qubits];
        for (int i = 0; i < qubits; i++){
               qubitStream[i] = new Qubit(0, 0);
               int polarization = Math.random() > 0.5 ? 1 : 0;
               transmitterInfos[i][POLARIZATION] = polarization;
               transmitterInfos[i][VALUE] = qubitStream[i].measure(polarization);
        }
        return qubitStream;
    }

    public static void generateReceiverValues(Qubit[] qubitStream){
        receiverInfos = new int[qubitStream.length][2];
        for (int i = 0; i < qubitStream.length; i++){
            int polarization = Math.random() > 0.5 ? 1 : 0;
            receiverInfos[i][POLARIZATION] = polarization;
            receiverInfos[i][VALUE] = qubitStream[i].measure(polarization);
        }
    }

    public static String findSharedKey(int[][] comparer, int[] exchangedPolarisation){
        if(comparer.length != exchangedPolarisation.length) return null;

        String sharedKey = "";

        for (int i = 0; i < comparer.length; i++){
            if(comparer[i][POLARIZATION] == exchangedPolarisation[i]){
                sharedKey += String.valueOf(comparer[i][VALUE]);
            }
        }

        return sharedKey;
    }

    public static int[] getOnlyPolarizations(int[][] privateInfos){
        int[] output = new int[privateInfos.length];

        // Strip away value position
        for (int i = 0; i < privateInfos.length; i++){
            output[i] = privateInfos[i][POLARIZATION];
        }

        return output;
    }

}
