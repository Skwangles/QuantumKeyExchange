public class Qubit {
    private int value;
    private int polarization; // 0 - circular, 1 - linear

    public Qubit(int value, int polarization){
        if(polarization > 1 || polarization < 0) throw new IllegalArgumentException("Polarization must be 1 or 0");
        if(value > 1 || value < 0) throw new IllegalArgumentException("Value must be 1 or 0");

        this.value = value;
        this.polarization = polarization;
    }

    public void set(int value, int polarization){
        if(polarization > 1 || polarization < 0) throw new IllegalArgumentException("Polarization must be 1 or 0");
        if(value > 1 || value < 0) throw new IllegalArgumentException("Value must be 1 or 0");

        this.value = value;
        this.polarization = polarization;
    }

    public int measure(int polarization) {
        if(polarization > 1 || polarization < 0) throw new IllegalArgumentException("Polarization must be 1 or 0");

        if (polarization == this.polarization)
            return value;

        this.set(Math.random() > 0.5 ? 1 : 0, polarization);
        return this.value;
    }


}
