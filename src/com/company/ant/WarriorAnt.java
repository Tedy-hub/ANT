package com.company.ant;

import java.util.Random;

public class WarriorAnt extends ant{
   WarriorAnt(){
       Random rand = new Random();
       setName("Warrior Ant");
       // случайный размер от 2 до 5 ед.
       int size = rand.nextInt(3);
       setSize(size + 2);
       setSpawnTime(1000);
   }
}
