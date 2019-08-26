package controller.scenes;

import java.util.LinkedList;

public class MenuList {

    private LinkedList<Option> options = new LinkedList<>();
    private int hoveredOption = -1;


    public void addOption( String title, OptionAction action ) {
        Option option = new Option(title, options.size()+1, action);
        options.add(option);
        if(hoveredOption == -1) setHoveredOption(0);
    }

    public void nextOption(){
        if( hoveredOption == -1 ) throw new Error("No options");
        setHoveredOption( hoveredOption >= options.size()-1 ? 0 : hoveredOption + 1);
    }

    public void previousOption(){
        if( hoveredOption == -1 ) throw new Error("No options");
        setHoveredOption( hoveredOption == 0 ? options.size()-1 : hoveredOption - 1);
    }

    public void setHoveredOption(int id) {

        if(id >= options.size() || id < 0){
            throw new IndexOutOfBoundsException();
        }

        if(hoveredOption > -1){
            options.get(hoveredOption).setHovered(false);
        }

        hoveredOption = id;
        options.get(hoveredOption).setHovered(true);
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

    public class Option{

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
