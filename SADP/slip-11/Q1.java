import java.util.*;

interface BeatObserver {
    void updateBeat();
}

interface BPMObserver {
    void updateBPM();
}

// --- BEAT MODEL INTERFACE ---
interface BeatModelInterface {
    void on();
    void off();
    void setBPM(int bpm);
    int getBPM();
    void registerObserver(BeatObserver o);
    void registerObserver(BPMObserver o);
}

// --- HEART MODEL INTERFACE (Adaptee) ---
interface HeartModelInterface {
    int getHeartRate();
    void registerObserver(BeatObserver o);
    void registerObserver(BPMObserver o);
}

// --- HEART MODEL (Adaptee) ---
class HeartModel implements HeartModelInterface {
    private int heartRate = 75;
    private List<BeatObserver> beatObservers = new ArrayList<>();
    private List<BPMObserver> bpmObservers = new ArrayList<>();

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int rate) {
        this.heartRate = rate;
        for (BPMObserver o : bpmObservers) o.updateBPM();
    }

    public void registerObserver(BeatObserver o) {
        beatObservers.add(o);
    }

    public void registerObserver(BPMObserver o) {
        bpmObservers.add(o);
    }

    public void heartBeat() {
        System.out.println("Heart beat!");
        for (BeatObserver o : beatObservers) o.updateBeat();
    }
}

// --- ADAPTER (Heart to Beat) ---
class HeartAdapter implements BeatModelInterface {
    private HeartModelInterface heart;

    public HeartAdapter(HeartModelInterface heart) {
        this.heart = heart;
    }

    public void on() {
        System.out.println("Heart monitoring started");
    }

    public void off() {
        System.out.println("Heart monitoring stopped");
    }

    public void setBPM(int bpm) {
        System.out.println("Cannot set heart rate!");
    }

    public int getBPM() {
        return heart.getHeartRate();
    }

    public void registerObserver(BeatObserver o) {
        heart.registerObserver(o);
    }

    public void registerObserver(BPMObserver o) {
        heart.registerObserver(o);
    }
}

// --- BEAT MODEL ---
class BeatModel implements BeatModelInterface {
    private int bpm = 90;
    private List<BeatObserver> beatObservers = new ArrayList<>();
    private List<BPMObserver> bpmObservers = new ArrayList<>();

    public void on() {
        System.out.println("Music started");
        setBPM(90);
    }

    public void off() {
        System.out.println("Music stopped");
        setBPM(0);
    }

    public void setBPM(int bpm) {
        this.bpm = bpm;
        for (BPMObserver o : bpmObservers) o.updateBPM();
    }

    public int getBPM() {
        return bpm;
    }

    public void registerObserver(BeatObserver o) {
        beatObservers.add(o);
    }

    public void registerObserver(BPMObserver o) {
        bpmObservers.add(o);
    }
}

// --- VIEW ---
class DJView implements BeatObserver, BPMObserver {
    private BeatModelInterface model;

    public DJView(BeatModelInterface model) {
        this.model = model;
        model.registerObserver((BeatObserver) this);
        model.registerObserver((BPMObserver) this);
    }

    public void updateBPM() {
        System.out.println("DJ View: BPM = " + model.getBPM());
    }

    public void updateBeat() {
        System.out.println("DJ View: Beat!");
    }
}

// --- MAIN ---
public class Q1 {
    public static void main(String[] args) {
        System.out.println("=== Beat Model ===");
        BeatModelInterface beatModel = new BeatModel();
        DJView view1 = new DJView(beatModel);
        beatModel.on();
        beatModel.setBPM(120);

        System.out.println("\n=== Heart Model with Adapter ===");
        HeartModel heartModel = new HeartModel();
        BeatModelInterface adaptedHeart = new HeartAdapter(heartModel);
        DJView view2 = new DJView(adaptedHeart);
        adaptedHeart.on();
        
        heartModel.setHeartRate(80);
        heartModel.heartBeat();
    }
}