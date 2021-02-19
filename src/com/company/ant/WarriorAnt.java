package com.company.ant;

import java.util.Random;

public class WarriorAnt extends ant{
   WarriorAnt(){
       Random rand = new Random();
       setName("Warrior Ant");
       setSize(rand.nextInt(5));
       setSpawnTime(1000);
   }
}
