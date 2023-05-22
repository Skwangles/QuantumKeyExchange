public class Qubits {
    private int value;
    private int polarization;

    public Qubits(int value, int polarization){
        this.value = value;
        this.polarization = polarization;
    }

    public void set(int value, int polarization){
        this.value = value;
        this.polarization = polarization;
    }

    public int measure(int polarization) {
        if (polarization == this.polarization)
            return value;

        this.set(Math.random() > 0.5 ? 1 : 0, polarization);
        return this.value;
    }


}
