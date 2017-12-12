package banoun.aneece.blueprints;

public abstract class Well extends Container{

    public Well(String containerId, String location) {
        super(containerId, location, 0, -1);
    }
    
    @Override
    final public String getBarcode() throws NoBarcodeException {
        throw new NoBarcodeException();
    }
    
    @Override
    final public void setBarcode(String barcode) throws NoBarcodeException{
        throw new NoBarcodeException();
    }
    
}
