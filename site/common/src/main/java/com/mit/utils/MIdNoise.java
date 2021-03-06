/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mit.utils;

/**
 *
 * @author nghiatc
 * @since Jul 22, 2015
 */
public class MIdNoise {

    private final static String MSALT = "dknHOwshjakwnla@7861";
    private final static int HASH_LENGTH = 11;
    private final static Hashids hashId = new Hashids(MSALT, HASH_LENGTH);
    
    public static MIdNoise instance = new MIdNoise();
    private MIdNoise(){
        
    }
    public static MIdNoise getInstance(){
        return instance;
    }
    
    public static String enNoiseIId(int id){
        if(id < 0){
            throw new IllegalArgumentException("id must more than or equal 0.");
        }
        return hashId.encode(id);
    }
    
    public static int deNoiseIId(String eid){
        if(eid == null || eid.isEmpty()){
            throw new IllegalArgumentException("eid is not null or empty.");
        }
        Integer ret = new Integer(String.valueOf(hashId.decode(eid)[0]));
        return ret;
    }
    
    public static String enNoiseLId(long id){
        if(id < 0){
            throw new IllegalArgumentException("id must more than or equal 0.");
        }
        return hashId.encode(id);
    }
    
    public static long deNoiseLId(String eid){
        if(eid == null || eid.isEmpty()){
            throw new IllegalArgumentException("eid is not null or empty.");
        }
        return hashId.decode(eid)[0];
    }
    
    
    public static void main(String[] args) {
    	//http://115.79.45.86/bq/load/face/photo/8WBdx3g9ywv
    	System.out.println(MIdNoise.deNoiseLId("nXDd23NgmQk"));
    	System.out.println(MIdNoise.enNoiseLId(3800));
    }
    
}
