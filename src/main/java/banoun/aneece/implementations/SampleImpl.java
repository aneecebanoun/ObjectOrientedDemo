package banoun.aneece.implementations;

import banoun.aneece.blueprints.Sample;
import banoun.aneece.blueprints.Tag;

public class SampleImpl extends Sample{

    private Tag tag;
    private String content;
    @Override
    public Tag getTag() {
        return tag;
    }

    @Override
    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
    
}
