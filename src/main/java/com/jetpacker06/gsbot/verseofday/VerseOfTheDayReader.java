package com.jetpacker06.gsbot.verseofday;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VerseOfTheDayReader {

    public static final HashMap<Date, Verse> versesMap = new HashMap<>();

    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat wordFormat = new SimpleDateFormat("MMMM d, yyyy");

    public static void init() throws FileNotFoundException, ParseException {
        Scanner reader = new Scanner(new File("Upcoming YouVersion Verse of the Day .csv"));
        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split(",", 4);

            Date date = format.parse(clean(line[0]));
            Verse verse = new Verse(clean(line[1]), clean(line[3]));
            versesMap.put(date, verse);
        }
    }

    private static String clean(String data) {
        String cleaned = data.trim().replaceAll("[^\\x20-\\x7E\\n\\r\\t-]", "");
        return cleaned.replaceAll("[^\\x20-\\x7E]", "");
    }
}
