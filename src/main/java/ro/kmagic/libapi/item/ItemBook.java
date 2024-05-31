package ro.kmagic.libapi.item;

import java.util.List;

public interface ItemBook
{
    boolean hasTitle();
    
    String getTitle();
    
    ItemBuilder setTitle(final String p0);
    
    boolean hasAuthor();
    
    String getAuthor();
    
    ItemBuilder setAuthor(final String p0);
    
    boolean hasPages();
    
    String getPage(final int p0);
    
    ItemBuilder setPage(final int p0, final String p1);
    
    List<String> getPages();
    
    ItemBuilder setPages(final List<String> p0);
    
    ItemBuilder addPages(final String... p0);
    
    int getPageCount();
}
