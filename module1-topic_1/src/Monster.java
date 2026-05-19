public abstract class Monster {
    protected String name;
    protected String emoji;
    protected int health;
    protected int maxHealth;
    protected int attackPower;
    protected int defense;
    protected int expReward;

    public Monster(String name, String emoji, int health, int attackPower, int defense, int expReward) {
        this.name = name;
        this.emoji = emoji;
        this.health = health;
        this.maxHealth = health;
        this.attackPower = attackPower;
        this.defense = defense;
        this.expReward = expReward;
    }

    public abstract int attack(Dice dice);

    public abstract String getSpecialAbilityName();

    public void takeDamage(int damage) {
        int reducedDamage = Math.max(1, damage - defense);
        health = Math.max(0, health - reducedDamage);
        System.out.println("  " + emoji + " " + name + " получает " + reducedDamage + " урона! (защита: " + defense + ")");
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void displayStats() {
        String bar = "█".repeat(Math.max(0, health * 10 / maxHealth))
                   + "░".repeat(Math.max(0, 10 - health * 10 / maxHealth));
        System.out.println("  " + emoji + " " + name + ": ❤️ " + health + "/" + maxHealth + " [" + bar + "]");
    }

    public String getName() { return name; }
    public String getEmoji() { return emoji; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getAttackPower() { return attackPower; }
    public int getExpReward() { return expReward; }
}
