package banoun.aneece.blueprints;

public abstract class Plate implements Labware{

    final private Well[] wells = new Well[384];
    
    private String barcode;
    
    @Override
    final public void setBarcode(String barcode) {
        this.barcode = "DN"+barcode;
    }
    
}
