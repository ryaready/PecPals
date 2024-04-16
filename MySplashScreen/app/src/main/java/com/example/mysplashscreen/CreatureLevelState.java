package com.example.mysplashscreen;

import com.example.mysplashscreen.home.models.Creature;

import java.util.List;

public class CreatureLevelState {

    private LevelState state;
    List<Creature> creatureArrayList;
    User user;

    int current_xp = user.getXp();
    int currentUserState = user.getCurrentUserState();

    public CreatureLevelState() {
        this.state = new Egg1State1();
    }
    public void setState(LevelState state) {
        this.state = state;
    }
    public void levelUp() {
        user.upgradeCurrentUserState();
    }

    private class Egg1State1 implements LevelState {
        @Override
        public void levelUp() {
            setState(new Egg1State2());
        }

        @Override
        public int getImageId() {
            return R.drawable.animation_splash;
        }

    }

    private class Egg1State2 implements LevelState {
        @Override
        public void levelUp() {
            setState(new Egg2State1());
        }

        @Override
        public int getImageId() {
            return R.drawable.egg1;
        }

    }
    private class Egg2State1 implements LevelState {
        @Override
        public void levelUp() {
            setState(new Egg2State2());
        }

        @Override
        public int getImageId() {
            return R.drawable.animation_splash;
        }

    }
    private class Egg2State2 implements LevelState {
        @Override
        public void levelUp() {
            setState(new Egg3State1());
        }

        @Override
        public int getImageId() {
            return R.drawable.pos13;
        }

    }
    private class Egg3State1 implements LevelState {
        @Override
        public void levelUp() {
            setState(new Egg3State2());
        }

        @Override
        public int getImageId() {
            return R.drawable.animation_splash;
        }

    }
    private class Egg3State2 implements LevelState {
        @Override
        public void levelUp() {
            setState(new Egg4State1());
        }

        @Override
        public int getImageId() {
            return R.drawable.egg3;
        }

    }
    private class Egg4State1 implements LevelState {
        @Override
        public void levelUp() {
            setState(new Egg4State2());
        }

        @Override
        public int getImageId() {
            return R.drawable.animation_splash;
        }

    }
    private class Egg4State2 implements LevelState {
        @Override
        public void levelUp() {
            System.out.println("Max Level Reached!");
        }

        @Override
        public int getImageId() {
            return R.drawable.egg4;
        }

    }

}
