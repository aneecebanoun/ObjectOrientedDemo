package banoun.aneece.blueprints;

import java.util.HashMap;
import java.util.Map;

public class Tag {

    public String getDnaSequence() {
        return dnaSequence;
    }

//    public void setDnaSequence(String dnaSequence) {
//        this.dnaSequence = dnaSequence;
//    }
    
    private Tag(String dnaSequence){
        this.dnaSequence = dnaSequence;
    }
    
    private String dnaSequence;
    
    //simulate DB
    private static Map<String, Tag> uniqueTags;
    
    //using factory to ensure unique tags generated...
    //if to use DB primary key will enforce such requirements
    public static Tag getTag(String dnaSeqence){
        if(uniqueTags == null){
            uniqueTags = new HashMap<>();
            Tag tag = new Tag(dnaSeqence);
            uniqueTags.put(dnaSeqence, tag);
            return tag;
        }else{
            Tag tag = uniqueTags.get(dnaSeqence);
            if(tag == null){
                tag = new Tag(dnaSeqence);
                uniqueTags.put(dnaSeqence, tag);
            }
            return tag;
        }
    } 
}
