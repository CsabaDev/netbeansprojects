package view;

import java.util.EventListener;

public interface PropertyChangeListener extends EventListener {

    void propertyChanged(PropertyChangeEvent event);
}
