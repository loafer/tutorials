package com.github.loafer.mybatis.other;


import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaojh
 */
public class Model {
    private String id;
    private String namespace;
    private Map<String, Object> replacedAtrributes = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void addReplacedAttribute(String key, Object value){
        replacedAtrributes.put(key, value);
    }

    public void removeReplacedAttribute(String key){
        if(replacedAtrributes.containsKey(key)){
            replacedAtrributes.remove(key);
        }
    }

    public void clearReplacedAttributes(){
        replacedAtrributes.clear();
    }

    public Map<String, Object> getReplacedAtrributes(){
        return replacedAtrributes;
    }
}
