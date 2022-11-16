package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;

public class DrawNumberStandardOutputView implements DrawNumberView {

    @Override
    public void setController(DrawNumberController observer) {
        // Output only
        
    }

    @Override
    public void start() {
        // Not needed
        
    }

    @Override
    public void result(DrawResult res) {
        System.out.println(res.getDescription());
        
    }
    
}
