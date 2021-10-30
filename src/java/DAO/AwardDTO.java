/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author JohnnyMC
 */
public class AwardDTO {

    private int awardID;
    private String awardName;
    private int effectiveDay;

    public AwardDTO(int awardID, String awardName, int effectiveDay) {
        this.awardID = awardID;
        this.awardName = awardName;
        this.effectiveDay = effectiveDay;
    }

    public AwardDTO(String awardName, int effectiveDay) {
        this.awardName = awardName;
        this.effectiveDay = effectiveDay;
    }

    public AwardDTO() {
    }

    public int getAwardID() {
        return awardID;
    }

    public void setAwardID(int awardID) {
        this.awardID = awardID;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getEffectiveDay() {
        return effectiveDay;
    }

    public void setDayEffective(int effectiveDay) {
        this.effectiveDay = effectiveDay;
    }

    @Override
    public String toString() {
        return "AwardDTO{" + "awardID=" + awardID + ", awardName=" + awardName + ", effectiveDay=" + effectiveDay + '}';
    }

    
}
