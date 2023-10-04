public abstract class Creature {

    private final int attack;
    private final int protection;
    private volatile int health;
    private final int damage;
    private final String name;

    public Creature(int health, String name) {
        this.attack = RandomUtil.getAttackOrProtection();
        this.protection = RandomUtil.getAttackOrProtection();
        this.health = health;
        this.damage = RandomUtil.getDamage(health);
        this.name = name;
    }

    public boolean assault(Creature creature) {
        int protectionOfEnemy = creature.getProtection();
        int modifier = this.attack - protectionOfEnemy + 1;
        if (modifier <= 0) {
            modifier = 1;
        }
        for (int i = 0; i < modifier; i++) {
            int score = 1 + (int) (Math.random() * 7);
            if (score >= 5) {
                return true;
            }
        }
        return false;
    }

    public void successfulAttack(Creature creature) {
        creature.setHealth(creature.getHealth() - RandomUtil.getValueOfSuccessfulAttack(this.damage));
    }

    public int getProtection() {
        return protection;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }
}
