public class Player {
    private final String name;
    private final String emoji;
    private int health;
    private final int maxHealth;
    private int attackPower;
    private int defense;
    private int healUses;
    private int poisonTurns;

    public Player(String name, String emoji) {
        this.name = name;
        this.emoji = emoji;
        this.maxHealth = 25;
        this.health = 25;
        this.attackPower = 4;
        this.defense = 1;
        this.healUses = 3;
        this.poisonTurns = 0;
    }

    public int attack(Dice dice) {
        int roll = dice.roll();
        int damage = attackPower + (roll / 2);

        if (dice.isCritical(roll)) {
            damage += 5;
            System.out.println("  " + emoji + " КРИТИЧЕСКИЙ УДАР! Молния поражает зомби! ⚡");
            return damage;
        }

        if (dice.isFail(roll)) {
            System.out.println("  " + emoji + " Ты споткнулся и промахнулся...");
            return 0;
        }

        System.out.println("  " + emoji + " Ты атакуешь и наносишь " + damage + " урона");
        return damage;
    }

    public void defend() {
        defense += 2;
        System.out.println("  " + emoji + " Ты встал в защитную стойку! Защита +2 🛡️");
    }

    public void resetDefense() {
        defense = 1;
    }

    public int heal() {
        if (healUses <= 0) {
            System.out.println("  " + emoji + " У тебя больше нет зелий! 💊");
            return 0;
        }
        healUses--;
        int healAmount = 4 + new Dice(4).roll();
        health = Math.min(maxHealth, health + healAmount);
        System.out.println("  " + emoji + " Ты выпил зелье восстановления! +" + healAmount + " HP (осталось " + healUses + " зелий) 💊");
        return healAmount;
    }

    public void takeDamage(int damage) {
        int reducedDamage = Math.max(1, damage - defense);
        health = Math.max(0, health - reducedDamage);
        System.out.println("  " + emoji + " Ты получаешь " + reducedDamage + " урона! (защита: " + defense + ")");
    }

    public void applyPoison() {
        if (poisonTurns > 0) {
            health = Math.max(0, health - 2);
            System.out.println("  ☠️ Яд действует! -2 HP (осталось " + poisonTurns + " ходов)");
            poisonTurns--;
        }
    }

    public void setPoisoned(int turns) {
        if (turns > poisonTurns) {
            poisonTurns = turns;
        }
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean isPoisoned() {
        return poisonTurns > 0;
    }

    public void displayStats() {
        String bar = "█".repeat(Math.max(0, health * 10 / maxHealth))
                   + "░".repeat(Math.max(0, 10 - health * 10 / maxHealth));
        String poison = isPoisoned() ? " ☠️" : "";
        System.out.println("  " + emoji + " " + name + poison + ": ❤️ " + health + "/" + maxHealth + " [" + bar + "]");
    }

    public String getName() { return name; }
    public String getEmoji() { return emoji; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getHealUses() { return healUses; }
}
