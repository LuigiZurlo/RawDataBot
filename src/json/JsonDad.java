/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package json;

/**
 *
 * @author LuigiZ
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"channel",
"feeds"
})
public class JsonDad {

    @JsonProperty("channel")
    private Channel channel;
    @JsonProperty("feeds")
    private List<Feed> feeds = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public JsonDad() {
    }

    public JsonDad(Channel channel, List<Feed> feeds) {
        this.channel = channel;
        this.feeds = feeds;
    }
    
    

    @JsonProperty("channel")
    public Channel getChannel() {
    return channel;
    }

    @JsonProperty("channel")
    public void setChannel(Channel channel) {
    this.channel = channel;
    }

    @JsonProperty("feeds")
    public List<Feed> getFeeds() {
    return feeds;
    }

    @JsonProperty("feeds")
    public void setFeeds(List<Feed> feeds) {
    this.feeds = feeds;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "JsonDad{" + "channel=" + channel + ", feeds=" + feeds + ", additionalProperties=" + additionalProperties + '}';
    }

}