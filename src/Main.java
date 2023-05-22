public class Main {

    private static int[][] transmitterInfos;
    private static String agreedKeyTransmitter;
    private static int[][] receiverInfos;
    private static String agreedKeyReceiver;

    public static void main(String[] args){

        // Stream of Qubits from one to the other
        generateReceiverValues(generateTransmitterValues(256));

        //Share polarizations with one another and infer key
        agreedKeyTransmitter = findSharedKey(transmitterInfos, getOnlyPolarizations(receiverInfos));
        agreedKeyReceiver = findSharedKey(receiverInfos, getOnlyPolarizations(transmitterInfos));

        String secretMsg = "111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";

        // Encrypt using transmitter's 'key'
        String encryptedMsg = XORciphering.xor(secretMsg, agreedKeyTransmitter);

        // Decrypt using receiver's 'key'
        String unencodedMessage = XORciphering.xor(encryptedMsg, agreedKeyReceiver);

        System.out.printf("Original: %s, Symmetrically Encrypted: %s, Unencrypted: %s", secretMsg, encryptedMsg, unencodedMessage);
        System.out.println();
        System.out.printf("Transmitter Key: %s, Receiver Key: %s", agreedKeyTransmitter, agreedKeyReceiver);
    }


    public static Qubit[] generateTransmitterValues(int qubits){
        transmitterInfos = new int[qubits][2];
        Qubit[] qubitStream = new Qubit[qubits];
        for (int i = 0; i < qubits; i++){
               qubitStream[i] = new Qubit(0, 0);
               int polarization = Math.random() > 0.5 ? 1 : 0;
               transmitterInfos[i][0] = polarization;
               transmitterInfos[i][1] = qubitStream[i].measure(polarization);
        }
        return qubitStream;
    }

    public static void generateReceiverValues(Qubit[] qubitStream){
        receiverInfos = new int[qubitStream.length][2];
        for (int i = 0; i < qubitStream.length; i++){
            int polarization = Math.random() > 0.5 ? 1 : 0;
            receiverInfos[i][0] = polarization;
            receiverInfos[i][1] = qubitStream[i].measure(polarization);
        }
    }

    public static String findSharedKey(int[][] comparer, int[] exchangedPolarisation){
        if(comparer.length != exchangedPolarisation.length) return null;

        String sharedKey = "";

        for (int i = 0; i < comparer.length; i++){
            if(comparer[i][0] == exchangedPolarisation[i]){
                sharedKey += String.valueOf(comparer[i][1]);
            }
        }

        return sharedKey;
    }

    public static int[] getOnlyPolarizations(int[][] privateInfos){
        int[] output = new int[privateInfos.length];

        // Strip away value position
        for (int i = 0; i < privateInfos.length; i++){
            output[i] = privateInfos[i][0];
        }

        return output;
    }

}
