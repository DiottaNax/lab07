package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    private enum Month {
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);
        
        private final int days;

        Month(final int days) {
            this.days = days;
        }
        
        static Month fromString(final String month) {
            Objects.requireNonNull(month);
            Month match = null;
            try{
                return Month.valueOf(month);
            } catch (IllegalArgumentException e) {
                for (final Month m : Month.values()) {
                    if (m.toString().contains(month.toUpperCase(Locale.ROOT))) {
                        if (match != null) {
                            throw new IllegalArgumentException(
                                    "Both " + m + "and " + match + "could refer to " + month + " " + e);
                        }
                        match = m;
                    }
                }

                if (match == null) {
                    throw new IllegalArgumentException("No month matching with " + month + " found");
                }
                return match;
            }
        }
    }

    private static class SortByDate implements Comparator<String> {

        @Override
        public int compare(String arg0, String arg1) {
            return Integer.compare(Month.fromString(arg0).days, Month.fromString(arg1).days);
        }
    }

    private static class SortByMonthOrder implements Comparator<String> {

        @Override
        public int compare(String arg0, String arg1) {
            return Month.fromString(arg0).compareTo(Month.fromString(arg1));
        }
    }

    @Override
    public Comparator<String> sortByDays() {
        final Comparator<String> comparator = new SortByDate();
        return comparator;
    }

    @Override
    public Comparator<String> sortByOrder() {
        final Comparator<String> comparator = new SortByMonthOrder();
        return comparator;
    }
}
