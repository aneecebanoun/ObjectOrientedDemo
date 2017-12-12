package banoun.aneece.blueprints;

public abstract class Lane extends Container{
    
    //constant to ensure type of sample is Tagged ie Tag field of Sample not null
    final boolean IS_TAGGED_SAMPLES = true;

    public Lane(String containerId, String location, Sample sample) throws Exception {
        super(containerId, location,1, -1);
        if(sample == null){
            throw new Exception("AT LEAST ONE SAMPLE MUST PROVIDED");
        }
        this.addSample(sample);
    }

    public abstract boolean isQc();

    public abstract void setQc(boolean qc);
    
    @Override
    final public String getBarcode() throws NoBarcodeException {
        throw new NoBarcodeException();
    }
    
    @Override
    final public void setBarcode(String barcode) throws NoBarcodeException{
        throw new NoBarcodeException();
    }
    
}
