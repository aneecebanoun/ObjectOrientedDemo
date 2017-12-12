package banoun.aneece.implementations;

import banoun.aneece.blueprints.Lane;
import banoun.aneece.blueprints.Sample;

public class LaneImpl extends Lane{

    private boolean isQc;

    public LaneImpl(String containerId, String location, Sample sample) throws Exception {
        super(containerId, location, sample);
    }
    @Override
    public boolean isQc() {
        return this.isQc;
    }

    @Override
    public void setQc(boolean isQc) {
        this.isQc = isQc;
    }
    
    
    
}
