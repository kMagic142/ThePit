package ro.kmagic.libapi.item;

import java.util.UUID;

public interface ItemSkull
{
    UUID getOwner();
    
    boolean hasOwner();
    
    ItemBuilder setOwner(final String p0);
    
    ItemBuilder setSkin(final String p0);
}
