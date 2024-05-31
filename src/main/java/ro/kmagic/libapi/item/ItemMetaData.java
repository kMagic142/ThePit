package ro.kmagic.libapi.item;

public interface ItemMetaData
{
    ItemBuilder add(final String p0, final String p1);
    
    String get(final String p0);
    
    boolean has(final String p0);
    
    ItemBuilder remove(final String p0);
    
    ItemBuilder add(final String p0);
    
    String get();
    
    boolean has();
    
    ItemBuilder remove();
}
