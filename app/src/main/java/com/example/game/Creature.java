package com.example.game;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

abstract public class Creature implements CreatureInterface, Serializable {
    private String name;
    private int attackPoint;
    private int protectPoint;
    protected int healthPoint;
    protected int healthPointMax;
    private int damagePointMin;
    private int damagePointMax;
    private final Random rand = new Random();
    protected Date dateNow = new Date();
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss:SS");


    public String getName() {
        return name;
    }

    public int getAttackPoint() {
        return attackPoint;
    }

    public int getProtectPoint() {
        return protectPoint;
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public int getHealthPointMax() {
        return healthPointMax;
    }

    public int getDamagePointMin() {
        return damagePointMin;
    }

    public int getDamagePointMax() {
        return damagePointMax;
    }

    public void setName(String name) {
        if(name != "") this.name = name;
    }

    public void setAttackPoint(int attackPoint) {
        if (attackPoint > 0 & attackPoint <= 30) this.attackPoint = attackPoint;

    }

    public void setProtectPoint(int protectPoint) {
        if (protectPoint > 0 & protectPoint <= 30) this.protectPoint = protectPoint;

    }

    public void setHealthPoint(int healthPoint) {
        if (healthPoint >= 0) {
            this.healthPoint = healthPoint;
        }
    }

    public void setHealthPointMax(int healthPointMax) {
        if (healthPointMax > 0) {
            this.healthPointMax = healthPointMax;
        }
    }

    public void setDamagePointRange(int damagePointMin, int damagePointMax) {
        if (damagePointMin >= 0 & damagePointMin <= damagePointMax) {
            this.damagePointMin = damagePointMin;
            this.damagePointMax = damagePointMax;
        }
    }

    public Creature() {
        name = "Creature_" + formatForDateNow.format(dateNow);
        attackPoint = rand.nextInt(30-1+1)+1;
        protectPoint = rand.nextInt(30-1+1)+1;
        healthPoint = 100;
        healthPointMax = healthPoint;
        damagePointMin = 1;
        damagePointMax = 40;
    }

    public Creature(String name, int attackPoint, int protectPoint, int healthPoint, int damagePointMin, int damagePointMax) {
        this.name = name;
        if (attackPoint > 0 & attackPoint <= 30) this.attackPoint = attackPoint;
        else {
            this.attackPoint = rand.nextInt(30-1+1)+1;
        }
        if (protectPoint > 0 & protectPoint <= 30) this.protectPoint = protectPoint;
        else {
            this.protectPoint = rand.nextInt(30-1+1)+1;
        }
        if (healthPoint >= 0) this.healthPoint = healthPoint;
        else {
            this.healthPoint = 100;
        }
        healthPointMax = healthPoint;
        if (damagePointMin >= 0 & damagePointMin <= damagePointMax) {
            this.damagePointMin = damagePointMin;
            this.damagePointMax = damagePointMax;
        } else {
        }
    }

    private int modifierCount(Creature enemy) {
        if (enemy.isAlive() & isAlive()) {
            int difference = attackPoint - (enemy.protectPoint + 1);
            if (difference <= 0) return 1;
            return difference;
        }
        return 0;
    }

    private boolean isAttackSuccess(Creature enemy) {
        int N = modifierCount(enemy);
        if (N > 0) {
            for (int i = 1; i <= N; i++) {
                int diceValue = rand.nextInt(6-1+1)+1;
                if (diceValue == 5 || diceValue == 6) {
                    return true;
                }
            }
        }
        return false;
    }

    public String hit(Creature enemy) {
        String result = "Атака существом "+ name + " по существу " + enemy.name + " не удалась. \n";
        if (isAttackSuccess(enemy)) {
            int damage = rand.nextInt(damagePointMax - damagePointMin+1 ) + damagePointMin;
            enemy.healthPoint = enemy.healthPoint - damage;
             result = "Существо " + enemy.name + " атаковано существом " + name + " и получило " + damage + " урона.\n";
            if (!enemy.isAlive()) {
                result += "Существо " + enemy.name + " погибло\n";
                return result;
            }
            return result;
        }
        return result;
    }

    public boolean isAlive() {
        return healthPoint > 0;
    }


}
