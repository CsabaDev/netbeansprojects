package view;

class PropertyChangeEvent {

    Object sender;
    String propertyName;

    public PropertyChangeEvent(Object sender, String propertyName) {
        this.sender = sender;
        this.propertyName = propertyName;
    }
    
    public Object getSender() {
        return sender;
    }

    public String getPropertyName() {
        return propertyName;
    }
    
    
    
}
