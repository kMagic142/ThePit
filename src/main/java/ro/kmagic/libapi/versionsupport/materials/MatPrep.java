package ro.kmagic.libapi.versionsupport.materials;

public class MatPrep
{
    private final PrepType prepType;
    private String mat;
    private int id;
    private int data;
    private boolean placeholder;
    
    public MatPrep(final String mat) {
        this.placeholder = false;
        this.prepType = PrepType.MAT;
        this.mat = mat;
    }
    
    public MatPrep(final String mat, final boolean placeholder) {
        this.placeholder = false;
        this.prepType = PrepType.MAT;
        this.mat = mat;
        this.placeholder = placeholder;
    }
    
    public MatPrep(final int id) {
        this.placeholder = false;
        this.prepType = PrepType.ID;
        this.id = id;
    }
    
    public MatPrep(final int id, final boolean placeholder) {
        this.placeholder = false;
        this.prepType = PrepType.ID;
        this.id = id;
        this.placeholder = placeholder;
    }
    
    public MatPrep(final int id, final int data) {
        this.placeholder = false;
        this.prepType = PrepType.ID_DATA;
        this.id = id;
        this.data = data;
    }
    
    public MatPrep(final int id, final int data, final boolean placeholder) {
        this.placeholder = false;
        this.prepType = PrepType.ID_DATA;
        this.id = id;
        this.data = data;
        this.placeholder = placeholder;
    }
    
    public PrepType getPrepType() {
        return this.prepType;
    }
    
    public String getMaterial() {
        return this.mat;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int getData() {
        return this.data;
    }
    
    public boolean isPlaceholder() {
        return this.placeholder;
    }
    
    public enum PrepType
    {
        MAT, 
        ID, 
        ID_DATA
    }
}
