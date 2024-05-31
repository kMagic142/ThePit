package ro.kmagic.libapi.exceptions;

public class SlotOccupiedException extends RuntimeException
{
    public SlotOccupiedException() {
        super("You cannot set that item here, the slot is already occupied");
    }
}
