package banoun.aneece.implementations;

import banoun.aneece.blueprints.Container;
import banoun.aneece.blueprints.Flowcell;
import banoun.aneece.blueprints.Lane;
import java.util.ArrayList;
import java.util.List;

public class FlowcellImpl extends Flowcell{

    
    private String location;
    private List<Container> containers;
    private String state;
    private String[] validStates;
    private int currentState;
    Lane[] lanes;

    public FlowcellImpl(String containerId, String location, Container container) throws Exception{
        if(container == null){
            throw new Exception("AT LEAST ONE CONTAINER NEEDED");
        }
        
        lanes = new Lane[8];
        
        this.containers = new ArrayList<>();
        this.containers.add(container);
        this.location = location;
        this.state = "pending";
        this.currentState = 0;
        this.validStates = new String[4];
        this.validStates[0] = "pending";
        this.validStates[1] = "started";
        this.validStates[2] = "passed";
        this.validStates[3] = "failed";        
    }
    
    @Override
    public Lane[] getLanes() {
        return lanes;
    }

    @Override
    public void setLanes(Lane lane, int id) {
        if(id < lanes.length){
            lanes[id] = lane;
        }
    }

    @Override
    public String getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(String location) {
         this.location = location;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        if(currentState == 0 && state.equals("started")){
            currentState = 1;
            this.state = state;
            return;
        }
        if(currentState == 1 ){
            if(state.equals("passed")){
                currentState = 2;
                this.state = state;
                return;                
            }
            if(state.equals("passed")){
                currentState = 3;
                this.state = state;
            }
        }
    }

    @Override
    public List<Container> getContainers() {
        return containers;
    }

    @Override
    public void addContainers(Container container) {
        containers.add(container);
    }
    
}
