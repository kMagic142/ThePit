package ro.kmagic.libapi.item;

import org.bukkit.entity.EntityType;

public interface ItemSpawnEgg
{
    EntityType getEntityType();
    
    ItemBuilder setEntityType(final EntityType p0);
}
