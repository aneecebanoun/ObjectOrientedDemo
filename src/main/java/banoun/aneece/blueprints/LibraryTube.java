package banoun.aneece.blueprints;

public abstract class LibraryTube extends Container implements Labware{
    
    //constant to ensure type of sample is Tagged ie Tag field of Sample not null
    final boolean IS_TAGGED_SAMPLE = true;
    

    public LibraryTube(String containerId, String location) {
        super(containerId, location, 1, -1);
    }
    
    @Override
    final public void setBarcode(String barcode) throws NoBarcodeException {
        super.setBarcode("NT"+barcode);
    }
    
}
