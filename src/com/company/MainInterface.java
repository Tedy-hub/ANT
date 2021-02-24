package com.company;

public interface MainInterface {
    interface View {
        void showText();
    }
    interface Presenter {
        void onButtonWasClicked();
        void onDestroy();
    }
    interface Repository {
        void loadAnts() throws InterruptedException;
    }
}
