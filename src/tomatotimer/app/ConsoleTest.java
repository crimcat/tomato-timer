/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tomatotimer.app;

public class ConsoleTest {

    public static void main(String[] args) {
        System.out.println("Starting: 2, 2, 1");
        TomatoEngine te = new TomatoEngine(2, 2, 1);
        te.setMinuteElapsedCallback(() -> { System.out.println("1 minute elapsed"); });
        te.setStateChangeCallback(
            () -> {
                System.out.println("State changed: " + te.getCurrentState() + ", Tomatoes: " + te.getRemainingTomatoes());
            }
        );
        te.start();
    }
}
