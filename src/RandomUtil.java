public final class RandomUtil {

    private static final int ATTACK_OR_PROTECTION_LIMIT = 30;
    private static final int MAX_AMOUNT_OF_HEALING = 4;

    private RandomUtil() {
    }

    public static int getDamage(int damageLimit) {
        return 1 + (int) (Math.random() * (damageLimit + 1));
    }

    public static int getAmountOfHealing() {
        return (int) (Math.random() * (MAX_AMOUNT_OF_HEALING + 1));
    }

    public static int getAttackOrProtection() {
        return 1 + (int) (Math.random() * (ATTACK_OR_PROTECTION_LIMIT + 1));
    }

    public static int getValueOfSuccessfulAttack(int damageLimit) {
        return 1 + (int) (Math.random() * (damageLimit + 1));
    }

    public static int getIndexOfMonster(int amountOfMonsters) {
        return (int) (Math.random() * (amountOfMonsters));
    }
}
