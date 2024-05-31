package ro.kmagic.libapi.utils.spiral;

import org.bukkit.Location;

public class SpiralLocation
{
    public static int nextOddSquareRoot(final int n) {
        final int n2 = (int)Math.sqrt(n);
        if (n2 % 2 != 0 && n2 * n2 == n) {
            return n2;
        }
        return n2 + ((n2 % 2 == 0) ? 1 : 2);
    }
    
    public static int getLowerPerfectSquare(final int n) {
        final int n2 = (int)Math.sqrt(n);
        return n2 * n2;
    }
    
    public static int getHigherPerfectSquare(final int n) {
        final int n2 = (int)Math.sqrt(n) + 1;
        return n2 * n2;
    }
    
    public static boolean isPerfectSquare(final int n) {
        final int n2 = (int)Math.sqrt(n);
        return n2 * n2 == n;
    }
    
    public static SpiralPosition getPositionInMatrix(final int n) {
        final int lowerPerfectSquare = getLowerPerfectSquare(n);
        final int n2 = (lowerPerfectSquare + getHigherPerfectSquare(n)) / 2 + 1;
        if (isPerfectSquare(n)) {
            if (n % 2 == 0) {
                return new SpiralPosition((int)Math.sqrt(n), 1);
            }
            return new SpiralPosition(0, (int)Math.sqrt(n) - 1);
        }
        else {
            final int n3 = (int)Math.sqrt(lowerPerfectSquare);
            if (n < n2) {
                return (lowerPerfectSquare % 2 == 0) ? new SpiralPosition(n2 - n, 0) : new SpiralPosition(n3 + 1 - n2 + n, n3 + 1);
            }
            if (n > n2) {
                return (lowerPerfectSquare % 2 == 0) ? new SpiralPosition(0, n - n2) : new SpiralPosition(n3 + 1, n3 + 1 - n + n2);
            }
            return (lowerPerfectSquare % 2 == 0) ? new SpiralPosition(0, 0) : new SpiralPosition(n3 + 1, n3 + 1);
        }
    }
    
    public static SpiralPosition relativeToMiddle(final int n) {
        if (n == 1) {
            return new SpiralPosition(0, 0);
        }
        final int nextOddSquareRoot = nextOddSquareRoot(n);
        return getPositionInMatrix(n).subtract(new SpiralPosition(nextOddSquareRoot / 2, nextOddSquareRoot / 2));
    }
    
    public static Location getLocationRelative(final Location location, final int n, final int n2) {
        final SpiralPosition relativeToMiddle = relativeToMiddle(n2);
        return new Location(location.getWorld(), location.getX() + relativeToMiddle.getX() * n, location.getY(), location.getZ() + relativeToMiddle.getY() * n);
    }
}
