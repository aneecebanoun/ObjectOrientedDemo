package banoun.aneece;

import banoun.aneece.blueprints.Container;
import banoun.aneece.blueprints.Flowcell;
import banoun.aneece.blueprints.Labware;
import banoun.aneece.blueprints.Lane;
import banoun.aneece.blueprints.LibraryTube;
import banoun.aneece.blueprints.Plate;
import banoun.aneece.blueprints.Sample;
import banoun.aneece.blueprints.SampleTube;
import banoun.aneece.blueprints.Tag;
import banoun.aneece.blueprints.Well;
import banoun.aneece.implementations.FlowcellImpl;
import banoun.aneece.implementations.LaneImpl;
import banoun.aneece.implementations.LibraryTubeImpl;
import banoun.aneece.implementations.PlateImpl;
import banoun.aneece.implementations.SampleImpl;
import banoun.aneece.implementations.SampleTubeImpl;
import banoun.aneece.implementations.WellImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainerSearch {
    
    public static void main(String[] a) throws Exception{

        List<Labware> labwares = new ArrayList<>();
        
        //Labware
        Plate plate;
        SampleTube sampleTube;//Containers too
        LibraryTube libTube;//Containers too
        Flowcell flowcell;
        final String id1 = Container.getNextContainerId();
        final String id2 = Container.getNextContainerId();
        final String id3 = Container.getNextContainerId();
        final String id4 = Container.getNextContainerId();
        //Containers
        Well well = new WellImpl(id1, "B");
        Sample sample = new SampleImpl();
        Lane lane = new LaneImpl(id2, "C", sample);
        well.addSample(sample);
        plate = new PlateImpl("A", well);
        labwares.add(plate);
        
        sample.setTag(Tag.getTag(getRandomDNASequence()));
        sample = new SampleImpl();
        sample.setTag(Tag.getTag(getRandomDNASequence()));
        
        well.addSample(sample);
        sampleTube = new SampleTubeImpl(id3, "D", well);
        labwares.add(sampleTube);
        libTube = new LibraryTubeImpl(id4, "D", lane);
        labwares.add(libTube);
        plate = new PlateImpl("A", libTube);
        labwares.add(plate);
        flowcell = new FlowcellImpl(id1, "D", sampleTube);
        labwares.add(flowcell);
        for(Labware labware : labwares){
            ContainerSearch containerSearch = new ContainerSearch(labware);
            Container container = containerSearch.search(id1);
            System.out.println("id1: " +id1 + " containerID: "+(container != null ? container.getContainerId():"NO MATCH") );
            samplesLookup(container, containerSearch);
            container = containerSearch.search(id2);
            System.out.println("id2: " +id2 + " containerID: "+ (container != null ? container.getContainerId():"NO MATCH") );
            samplesLookup(container, containerSearch);
            container = containerSearch.search(id3);
            System.out.println("id3: " +id3 + " containerID: "+(container != null ? container.getContainerId():"NO MATCH") );
            samplesLookup(container, containerSearch);
            container = containerSearch.search(id4);
            System.out.println("id4: " +id4 + " containerID: "+(container != null ? container.getContainerId():"NO MATCH") );
            samplesLookup(container, containerSearch);
        }
    }

    private static String getRandomDNASequence() {
        String randomDNASequence = "";
        Map<String, String> bases = new HashMap<>();
        bases.put("A", "T");
        bases.put("T", "A");
        bases.put("C", "G");
        bases.put("G", "C");
        String[] theBases = {"A","C","G","T"};
        
        for(int n = 0; n < 8; n++){
            int basesInex = (int)(Math.random()*4);
            randomDNASequence += theBases[basesInex] + bases.get(theBases[basesInex]);
        }
        
        return randomDNASequence;
    }
    private static void samplesLookup(Container container, ContainerSearch containerSearch) {
        if(container != null){
            Container container1 = containerSearch.search(container.getSamples());
            Map<String, Sample> samples = container.getSamples();
            System.out.println("container1 == container : " +(container1 == container) + " containerID: "+container.getContainerId()+" container1ID: "+container1.getContainerId());
            for(String key : samples.keySet()){
                System.out.println("key: "+key+" DnaSequence: "+samples.get(key).getTag().getDnaSequence());
            }
        }
    }
    
    private final Labware labware;
    
    public ContainerSearch(Labware labware){
        this.labware = labware;
    }
    //we might search by samples?
    public Container search(Map<String, Sample> samples){
        Container container = null;
        List<Container> containers = labware.getContainers();
        for(Container containerInLab : containers){
            List<String> labContainerKeys = new ArrayList<>( containerInLab.getSamples().keySet());
            List<String> samplesKeys = new ArrayList<>( samples.keySet());
            labContainerKeys.removeAll(samplesKeys);
            if(labContainerKeys.isEmpty()){
                container = containerInLab;
                break;
            }
        }
        return container;
    }
    
    public Container search(String containerId){
        Container container = null;
        List<Container> containers = labware.getContainers();
        for(Container containerInLab : containers){
            if(containerInLab.getContainerId().equals(containerId)){
                container =containerInLab;
                break;
            }
        }
        return container;
    }
    
}
