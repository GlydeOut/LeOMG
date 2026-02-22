public class Translation {
    String english;
    String german;
    public int pos; // 0 = subst, 1 = verb, 2 = adjadv, 3 = praep, 4 = definition, 5 = phrase, 6 = example

    public Translation(String english, String german, int pos) {
        this.english = english;
        this.german = german;
        this.pos = pos;
        formatText();
    }

    void formatText() {
        // Noun formatting
        english = english.replaceAll("\\\\xa0Pl\\.:", "<i>pl.:</i>");
        german = german.replaceAll("\\\\xa0Pl\\.:", "<i>pl.:</i>");
        english = english.replaceAll("\\\\xa0\\\\xa0auch:", "<i>or:</i>");
        german = german.replaceAll("\\\\xa0\\\\xa0auch:", "<i>or.:</i>");
        english = english.replaceAll("\\\\xa0\\\\xa0-", "-");
        german = german.replaceAll("\\\\xa0\\\\xa0-", "-");
        english = english.replaceAll("\\\\xa0- Pl\\.:", "<i>-pl.:</i>");
        german = german.replaceAll("\\\\xa0- Pl\\.:", "<i>-pl.:</i>");
        english = english.replaceAll("\\\\xa0Pl\\.", "<i>pl.</i>->");
        german = german.replaceAll("\\\\xa0Pl\\.", "<i>pl.</i>->");
        english = english.replaceAll("\\\\xa0\\\\xa0wiss\\.:", "<i>Lat.:</i>");
        german = german.replaceAll("\\\\xa0\\\\xa0wiss\\.:", "<i>Lat.:</i>");
        english = english.replaceAll("\\\\xa0\\\\xa0\\[", "[");
        german = german.replaceAll("\\\\xa0\\\\xa0\\[", "[");
        english = english.replaceAll("\\\\xa0\\\\xa0veraltend", "<i>dated</i>");
        german = german.replaceAll("\\\\xa0\\\\xa0veraltend\\[", "<i>dated</i>");
        english = english.replaceAll("\\\\xa0kein Pl.", "<i>no plural</i>");
        german = german.replaceAll("\\\\xa0kein Pl.", "<i>no plural</i>");
        english = english.replaceAll("\\\\xa0\\\\xa0auch\\\\xa0", "<i>also</i>");
        german = german.replaceAll("\\\\xa0\\\\xa0auch\\\\xa0", "<i>also</i>");
        english = english.replaceAll("\\\\xa0\\\\xa0veraltet", "<i>archaic</i>");
        german = german.replaceAll("\\\\xa0\\\\xa0veraltet\\[", "<i>archaic</i>");

        // Verb formatting
        english = english.replaceAll("\\\\xa0\\|\\\\xa0", "| ");
        german = german.replaceAll("\\\\xa0\\|\\\\xa0", "| ");
        english = english.replaceAll("\\\\xa0\\|", " |");
        german = german.replaceAll("\\\\xa0\\|", " |");
        english = english.replaceAll("\\\\xa0⇔\\\\xa0", " ⇔ ");
        german = german.replaceAll("\\\\xa0⇔\\\\xa0", " ⇔ ");

    }

    public String toString() {
        String posText;
        switch (pos) {
            case 0 -> posText = "N";
            case 1 -> posText = "V";
            case 2 -> posText = "A";
            case 3 -> posText = "P";
            case 4 -> posText = "Definitions";
            case 5 -> posText = "Phrases / Collocations";
            case 6 -> posText = "Examples";
            default -> posText = "Error: pos = " + pos;


        }
        return posText + ": " + english + "; " + german;
    }
}
