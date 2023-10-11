package com.example.game;


public interface CreatureInterface {
    String hit(Creature enemy);
    boolean isAlive();
    String getName();
    int getAttackPoint();
    int getHealthPoint();
    int getHealthPointMax();
    int getDamagePointMin();
    int getDamagePointMax();
    void setName(String name);
    void setAttackPoint(int attackPoint);
    void setProtectPoint(int protectPoint);
    void setHealthPoint(int healthPoint);
    void setHealthPointMax(int healthPointMax);
    void setDamagePointRange(int damagePointMin, int damagePointMax);
}
