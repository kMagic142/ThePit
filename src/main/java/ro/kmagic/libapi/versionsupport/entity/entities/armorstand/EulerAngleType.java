package ro.kmagic.libapi.versionsupport.entity.entities.armorstand;

public enum EulerAngleType
{
    BODY("BodyPose"), 
    LEFT_ARM("LeftArmPose"), 
    RIGHT_ARM("RightArmPose"), 
    LEFT_LEG("LeftLegPose"), 
    RIGHT_LEG("RightLegPose"), 
    HEAD("HeadPose");
    
    private final String poseName;
    
    EulerAngleType(final String poseName) {
        this.poseName = poseName;
    }
    
    public String getPoseName() {
        return this.poseName;
    }
}
