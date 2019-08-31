package controller.editor;

public class FileNameBuilder {

    private String text = "";
    private String suffix;
    private int maxLength;

    public FileNameBuilder( String fileSuffix, int maxLength ){
        suffix = fileSuffix;
        this.maxLength = maxLength;
    }

    public String getName(){
        return text + suffix;
    }

    public void removeLastCharacter(){
        if(text.length() >  0)
            text = text.substring(0,text.length()-1);
    }

    public void removeCharacter(int index){
        if(text.length() > 0 && index < text.length() && index >= 0 ){
            if( index == 0){
                text = text.substring(1);
            }else if(index == text.length()-1){
                text = text.substring(0, text.length()-2);
            }else{
                text = text.substring(0, index) + text.substring(index+1);
            }
        }
    }

    public int getNameLength(){
        return text.length();
    }

    public boolean addCharacter(char character){
        if( text.length() < maxLength ){
            if( validateCharacter(character) ){
                text += character;
                return true;
            }
        }
        return false;
    }

    public void clearName(){
        text = "";
    }

    private boolean validateCharacter(char character){
        if( character < 48) return false;
        if( character > 57 && character < 65) return false;
        if( character > 90 && character < 97) return false;
        if( character > 122 ) return false;
        return true;
    }





}
