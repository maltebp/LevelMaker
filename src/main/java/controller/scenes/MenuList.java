package controller.scenes;

import java.util.LinkedList;

public class MenuList {

    private LinkedList<Option> options = new LinkedList<>();
    private int hoveredOption = 0;


    public void addOption( String title, OptionAction action ) {
        Option option = new Option(title, options.size()+1, action);
        options.add(option);
    }

    public void nextOption(){
        hoveredOption++;
        if(hoveredOption >= options.size()) hoveredOption = 0;
    }

    public Option getOption(int id){
        return options.get(id);
    }

    public LinkedList<Option> getOptions(){
        return new LinkedList<>(options);
    }

    public int getNumberOfOptions(){
        return options.size();
    }

    public void optionIsChosen(){
        options.get(hoveredOption).runAction();
    }

    public int getHoveredOption(){
        return hoveredOption;
    }

    interface OptionAction{
        void run();
    }

    class Option{

        private String title;
        private int id;
        private OptionAction action;

        private boolean hovered = false;

        Option( String title, int id, OptionAction action ){
            this.title = title;
            this.id = id;
            this.action = action;
        }


        void setHovered(boolean hovered){
            this.hovered = hovered;
        }

        void runAction(){
            action.run();
        }

        public boolean isHovered() {
            return hovered;
        }

        public int getId(){
            return id;
        }

        public String getTitle(){
            return title;
        }
    }
}
