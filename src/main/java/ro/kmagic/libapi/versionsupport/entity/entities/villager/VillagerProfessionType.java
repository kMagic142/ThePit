package ro.kmagic.libapi.versionsupport.entity.entities.villager;

public enum VillagerProfessionType
{
    FARMER(0), 
    LIBRARIAN(1), 
    PRIEST(2), 
    BLACKSMITH(3), 
    BUTCHER(4), 
    NITWIT(5);
    
    private final int professionId;
    
    VillagerProfessionType(final int professionId) {
        this.professionId = professionId;
    }
    
    public int getProfessionId() {
        return this.professionId;
    }
}
