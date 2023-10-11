package com.example.game;

public class Monster extends Creature{

    public Monster(){
        super();
        setName("Monster_"+ formatForDateNow.format(dateNow));
    }
    public Monster(String name, int attackPoint, int protectPoint, int healthPoint, int damagePointMin, int damagePointMax){
        super(name,attackPoint,protectPoint,healthPoint,damagePointMin,damagePointMax);
    }

}
