package banoun.aneece.blueprints;

public abstract class Flowcell implements Labware{

    //constraints on number of Lanes we might use 2 as per-requirments
    final public int NUMBER_OF_LANE = 8;
    
    
    public abstract Lane[] getLanes();

    //id is the array index of the Lane element
    public abstract void setLanes(Lane lane, int id);

    @Override
    final public String getBarcode() throws NoBarcodeException {
        throw new NoBarcodeException();
    }
    
    @Override
    final public void setBarcode(String barcode) throws NoBarcodeException{
        throw new NoBarcodeException();
    }

}
