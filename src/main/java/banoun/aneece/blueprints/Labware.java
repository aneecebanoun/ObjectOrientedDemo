package banoun.aneece.blueprints;

import java.util.List;

public interface Labware {

    //constants to be used in the implemntor 
    //to make ensure one or many condition
    //-1 signal no upper limit
    final int MIN_CONTAINER = 1;
    final int MAX_CONTAINER = -1;

    
    String getLocation();
    void setLocation(String location);

    String getBarcode() throws NoBarcodeException;
    void setBarcode(String barcode)throws NoBarcodeException;
    
    String getState();
    void setState(String state);
    
    List<Container> getContainers();
    void addContainers(Container container);
    
    
    
}
