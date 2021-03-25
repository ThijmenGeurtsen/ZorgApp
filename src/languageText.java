public class languageText {
    private Language lan = new Language();

    public void goBack(){
        String language = lan.getLanguage();
        String text = language.equals("NE") ? "Voer 0 in terug te gaan" : "Press 0 to go back";
        System.out.println(text);
    }
}
