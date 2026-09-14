package quizgame.model;

public class Question{
    private final String abbreviation;
    private final String fullTerm;

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getFullTerm() {
        return fullTerm;
    }

    public boolean checkAnswer(String inp){
        if(inp == null) return false;
        return fullTerm.equalsIgnoreCase(inp.trim());
    }

    public Question(String abbreviation, String fullTerm){
        this.abbreviation = abbreviation;
        this.fullTerm = fullTerm;
    }
}
