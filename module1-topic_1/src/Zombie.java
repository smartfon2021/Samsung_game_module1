public class Zombie extends Monster {
    private static final String[] ATTACK_NAMES = {
        "Гнилой укус", "Удар гнилой рукой", "Плевок кислотой", "Зомби-захват"
    };

    public Zombie(int difficulty) {
        super(
            "Зомби",
            "\uD83E\uDDDF\u200D",
            15 + difficulty * 5,
            3 + difficulty,
            1 + difficulty / 2,
            5 * difficulty
        );
    }

    @Override
    public int attack(Dice dice) {
        int roll = dice.roll();
        String attackName = ATTACK_NAMES[roll % ATTACK_NAMES.length];
        int damage = attackPower + (roll / 2);

        System.out.println("  " + emoji + " Зомби использует \"" + attackName + "\"!");

        if (dice.isCritical(roll)) {
            damage += 4;
            System.out.println("  ⚠️ КРИТИЧЕСКИЙ УКУС! Яд проникает в кровь! ⚠️");
            return damage;
        }

        if (dice.isFail(roll)) {
            System.out.println("  Зомби споткнулся и промахнулся...");
            return 0;
        }

        System.out.println("  Зомби наносит " + damage + " урона");
        return damage;
    }

    @Override
    public String getSpecialAbilityName() {
        return "Ядовитый укус 🧪";
    }
}
