import java.util.Scanner;

public class BattleGame {
    private final Player player;
    private final Monster monster;
    private final Dice dice;
    private final Scanner scanner;
    private final int difficulty;
    private int round;

    public BattleGame(int difficulty) {
        this.player = new Player("Маг", "\uD83E\uDDD9\u200D");
        this.monster = new Zombie(difficulty);
        this.dice = new Dice();
        this.scanner = new Scanner(System.in);
        this.difficulty = difficulty;
        this.round = 0;
    }

    public void start() {
        printHeader();

        while (player.isAlive() && monster.isAlive()) {
            round++;
            System.out.println("\n═══════════════════ РАУНД " + round + " ═══════════════════");
            displayStatus();

            playerTurn();
            if (!monster.isAlive()) break;

            if (player.isPoisoned()) {
                player.applyPoison();
                if (!player.isAlive()) break;
            }

            monsterTurn();
            player.resetDefense();

            waitForInput();
        }

        printResult();
    }

    private void playerTurn() {
        System.out.println("\n🔥 Твой ход! Выбери действие:");
        System.out.println("  1. ⚔️ Атаковать");
        System.out.println("  2. 🛡️ Защищаться");
        System.out.println("  3. 💊 Лечиться (осталось: " + player.getHealUses() + " зелий)");
        System.out.print("  Твой выбор: ");

        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= 3) break;
                System.out.print("  Выбери 1, 2 или 3: ");
            } catch (NumberFormatException e) {
                System.out.print("  Введи число 1, 2 или 3: ");
            }
        }

        System.out.println();

        switch (choice) {
            case 1:
                int damage = player.attack(dice);
                if (damage > 0) {
                    monster.takeDamage(damage);
                }
                break;
            case 2:
                player.defend();
                break;
            case 3:
                player.heal();
                break;
        }

        sleep(800);
    }

    private void monsterTurn() {
        System.out.println("\n💀 Ход зомби!");

        int decision = dice.roll() + difficulty;

        if (monster.getHealth() < monster.getMaxHealth() * 0.3 && decision > 3) {
            int healAmount = 3 + dice.roll();
            System.out.println("  " + monster.getEmoji() + " Зомби регенерирует! +" + healAmount + " HP");
            monster.takeDamage(-healAmount);
            return;
        }

        if (decision <= 2 && player.getHealth() < player.getMaxHealth()) {
            System.out.println("  " + monster.getEmoji() + " Зомби готовится к атаке... (защита +1)");
            return;
        }

        int damage = monster.attack(dice);
        if (damage > 0) {
            if (dice.roll() == 6) {
                player.setPoisoned(3);
                System.out.println("  ☠️ Ты отравлен на 3 хода!");
            }
            player.takeDamage(damage);
        }

        sleep(800);
    }

    private void displayStatus() {
        System.out.println();
        player.displayStats();
        monster.displayStats();
        System.out.println();
    }

    private void printHeader() {
        System.out.println("\n" + "╔".repeat(40));
        System.out.println("  🎮 БИТВА С МОНСТРОМ! 🎮");
        System.out.println("  Сложность: " + "⭐".repeat(difficulty));
        System.out.println("  Противник: " + monster.getEmoji() + " " + monster.getName());
        System.out.println("  Способность: " + monster.getSpecialAbilityName());
        System.out.println("  Награда: " + monster.getExpReward() + " XP");
        System.out.println("╚" + "═".repeat(40));
        System.out.println("\n  Ты встретил " + monster.getEmoji() + " " + monster.getName() + "!");
        System.out.println("  Битва начинается...\n");
        sleep(1000);
    }

    private void printResult() {
        System.out.println("\n" + "=".repeat(40));
        if (player.isAlive()) {
            System.out.println("\n  🎉 ПОБЕДА! 🎉");
            System.out.println("  " + player.getEmoji() + " Ты одолел " + monster.getEmoji() + " " + monster.getName() + "!");
            System.out.println("  Получено опыта: " + monster.getExpReward() + " XP");
            System.out.println("  Осталось HP: " + player.getHealth() + "/" + player.getMaxHealth());
            System.out.println("  Ходов потребовалось: " + round);
        } else {
            System.out.println("\n  💀 ПОРАЖЕНИЕ... 💀");
            System.out.println("  " + monster.getEmoji() + " " + monster.getName() + " одолел тебя...");
            System.out.println("  Попробуй выбрать меньшую сложность в следующий раз!");
        }
        System.out.println("=".repeat(40));
        System.out.println();
    }

    private void waitForInput() {
        System.out.print("\n  Нажми Enter чтобы продолжить...");
        scanner.nextLine();
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
