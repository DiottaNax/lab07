package it.unibo.mvc;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberStandardOutputView;
import it.unibo.mvc.view.DrawNumberSwingView;

import java.lang.reflect.*;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws SecurityException
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        String[] typeOfView = { "StandardOutput", "Swing" };

        for (int i = 0; i < 3; i++) {
            for(String type : typeOfView){
                final Class<?> clas = Class.forName("it.unibo.mvc.view.DrawNumber" + type + "View");
                Constructor<?> builder = clas.getConstructor();
                Object newView = builder.newInstance();
                try{
                    app.addView((DrawNumberView) newView);
                } catch (Exception e) {
                    throw new IllegalStateException(
                            newView.getClass() + "is not a subClass of: " + DrawNumberView.class.getClass(), e); 
                }
            }
                
        }
        
        
    }
}
