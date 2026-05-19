import java.util.Random;

public class Dice {
    private final int sides;
    private final Random random;

    public Dice() {
        this(6);
    }

    public Dice(int sides) {
        this.sides = sides;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(sides) + 1;
    }

    public boolean isCritical(int roll) {
        return roll == sides;
    }

    public boolean isFail(int roll) {
        return roll == 1;
    }
}
