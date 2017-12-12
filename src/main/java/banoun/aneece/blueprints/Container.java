package banoun.aneece.blueprints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//very likely candidate to implement Labware interface
public abstract class Container{

    private Map<String, Sample> samples;
    private String barcode;
    private String containerId;
    private String location;

    final int MIN_SAMPLE;
    final int MAX_SAMPLE ;
    
    //this to be replaced by primary key, so it is DB simulation
    private static int containerIdsCounter;
    public static String getNextContainerId(){
        return ""+containerIdsCounter++;
    }
    
    
    //constants to be used in the implementor 
    //to make sure addSample(..), setSamples(..) and copySamples(...) conform with them 
    //-1 signal no upper limit

    public boolean equals(Container container){
        
        boolean sameObjects = false;        
            List<String> thisKeys = new ArrayList<>( this.getSamples().keySet());
            List<String> containerKeys = new ArrayList<>(container.getSamples().keySet() );
            thisKeys.removeAll(containerKeys);
            if(thisKeys.isEmpty()){
                sameObjects = true;
            }
        return sameObjects;
    }
    
    
    public Container(String containerId, String location, int min, int max){
        MIN_SAMPLE = min;
        MAX_SAMPLE = max;
        this.containerId = containerId;
        this.location = location;
        samples = new HashMap<>();
    }
    public String getContainerId(){
        return containerId;
    }
    
    public void setBarcode(String barcode) throws NoBarcodeException{
        this.barcode = barcode;
    }
    
    public String getBarcode() throws NoBarcodeException{
        return barcode;
    }
    
    public String getLocation(){
        return this.location;
    }

    final public Map<String, Sample> getSamples(){
        return samples;
    }
    
    final public void setSamples(Map<String, Sample> newSamples){
        if(this.MAX_SAMPLE != -1 && samples.size() == this.MAX_SAMPLE){
            return;
        }
        if( !(this instanceof SampleTube) ){
            for(String key : newSamples.keySet()){
               Sample sample = newSamples.get(key);
               if(sample.getTag() != null && !sample.getTag().getDnaSequence().equals(key)){
                  return;
                }
            }
         }
        if(this.MAX_SAMPLE == -1 ){
            samples = newSamples;
        }else{
            for(String key : newSamples.keySet()){
                Sample sample = newSamples.get(key);
                if(this.MAX_SAMPLE != -1 && samples.size() < this.MAX_SAMPLE){
                    if(this instanceof SampleTube && sample.getTag() == null){
                        samples.put(null, sample);
                        break;
                    }else{
                        if(sample.getTag() != null){
                           samples.put(sample.getTag().getDnaSequence(), sample);
                        }
                    }                    
                }else{
                    break;
                }                
            }
        }
    }
    
    final public void addSample(Sample sample){
        if(this.MAX_SAMPLE == -1 || samples.size() < this.MAX_SAMPLE){
           if(this instanceof SampleTube && sample.getTag() == null){
               samples.put(null, sample);
           }else{
               if(sample.getTag() != null){
                   samples.put(sample.getTag().getDnaSequence(), sample);
               }
           }
        }
    }
    
    //forcing the rule of no 2 samples with same tag
    //this is an example we might elaborate in the implementation to meet requirements
    final public void applyTag(Tag tag){
        Sample sample;
        sample = samples.get(tag.getDnaSequence());
        if(sample == null){
            for(String key : samples.keySet() ){
                sample = samples.get(key);
                samples.remove(key);
                sample.setTag(tag);
                samples.put(tag.getDnaSequence(), sample);
                break;
            }
        }
    }
    
    final public void copySamples(Container container){
        for(String key : container.getSamples().keySet()){
            Sample sample = container.getSamples().get(key);
            if(samples.size() < this.MAX_SAMPLE){            
               if(this instanceof SampleTube && sample.getTag() == null){
                    samples.put(null, sample);
                    break;
                }else{
                    samples.put(sample.getTag().getDnaSequence(), sample);
                }
            

            }

        }
    }
}
