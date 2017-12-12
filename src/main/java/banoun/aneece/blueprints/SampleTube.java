package banoun.aneece.blueprints;

public abstract class SampleTube extends Container implements Labware{

    //constant to flag type of sample is unTagged ie Tag field of Sample is null
    final boolean IS_TAGGED_SAMPLE = false;

    public SampleTube(String containerId, String location) {
        super(containerId, location, 1, 1);
    }
    
    @Override
    final public void setBarcode(String barcode) throws NoBarcodeException {
        super.setBarcode("NT"+barcode);
    }

}
