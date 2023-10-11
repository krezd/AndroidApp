package com.example.game;

public class Player extends Creature {
    private int healIndex = 0;

    public Player() {
        super();
        setName("Player_" + formatForDateNow.format(dateNow));
    }

    public Player(String name, int attackPoint, int protectPoint, int healthPoint, int damagePointMin, int damagePointMax) {
        super(name, attackPoint, protectPoint, healthPoint, damagePointMin, damagePointMax);
    }

    public String healing() {
        String result = "Не удалось применить аптечку, т.к. существо " + getName() + " мертво\n";
        if (isAlive()) {
            if (healthPoint != healthPointMax) {
                if (healIndex < 4) {
                    int healthRestored = ((int) Math.round(healthPointMax * 0.3));
                    if (healthPoint + healthRestored > healthPointMax) {
                        result = "Существо " + getName() + " пополнило здоровье на " + (healthPointMax - healthPoint) + " единиц. Здоровье максимально\n";
                        healthPoint = healthPointMax;
                        healIndex++;
                        return result;
                    } else {
                        result = "Существо " + getName() + " пополнило здоровье на " + healthRestored + " единиц. Здоровья осталось " + healthPoint + "\n";
                        healIndex++;
                       return result;
                    }
                } else {
                    result ="У существа " + getName() + " закончились аптечки\n";
                    return result;
                }
            } else {
                result = "Здоровье у существа " + getName()+" максимально. Аптечки не использованы\n";
                return result;
            }
        }
        return result;
    }
}
