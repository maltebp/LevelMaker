package controller.scenes;

import java.util.LinkedList;


/**
 *  A datastructure, which add functionality useful for creating menus.
 *  The datastructure has a "pointer", which points to the "hovered"
 *  (selected) option. The hovered option may be switched by using either of
 *  the methods: nextOption(), previousOption() and setHoveredOption().
 *
 *  Actions may be added to menu options as lambdaexpressions, and an option
 *  is chosen, running the action, by using the 'optionIsChosen' method.
 */
public class MenuList {

    private LinkedList<Option> options = new LinkedList<>();
    private int hoveredOption = -1; // -1 means that there are no options in the menu yet

    /**
     * Adds an option to the menu. If its the first option, this will automatically
     * be set as the currently hovered option.
     *
     * @param title The title/label of the option
     * @param action A lambdaexpression which will be run if optionIsChosen() is called,
     *               and the the option is the currently 'hovered option' in the menu.
     */
    public void addOption( String title, OptionAction action ) {
        Option option = new Option(title, options.size()+1, action);
        options.add(option);
        if(hoveredOption == -1) setHoveredOption(0);
    }

    /**
     * Hovers the next option in the menu. If current option is the last
     * it will go back to the first.
     */
    public void nextOption(){
        if( hoveredOption == -1 ) throw new Error("No options");
        setHoveredOption( hoveredOption >= options.size()-1 ? 0 : hoveredOption + 1);
    }

    /**
     * Hovers the preivious option in the menu. If current option is the first
     * it will go to the last.
     */
    public void previousOption(){
        if( hoveredOption == -1 ) throw new Error("No options");
        setHoveredOption( hoveredOption == 0 ? options.size()-1 : hoveredOption - 1);
    }

    /**
     * Sets the hovered option to the option with certain index.
     * @param id Index of the option you want to hover
     */
    public void setHoveredOption(int id) {

        if(id >= options.size() || id < 0){
            throw new IndexOutOfBoundsException("Was "+id+" but size is "+options.size());
        }

        if(hoveredOption > -1){
            options.get(hoveredOption).setHovered(false);
        }

        hoveredOption = id;
        options.get(hoveredOption).setHovered(true);
    }

    /** Choses the currently hovered option, running the options
     * given lambda expression.
     */
    public void optionIsChosen(){
        options.get(hoveredOption).runAction();
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

    public int getHoveredOption(){
        return hoveredOption;
    }


    public interface OptionAction{
        void run();
    }

    /**
     * Object which contains information about the menu option.
     */
    public class Option{

        private String title;
        private int id;
        private OptionAction action;

        private boolean hovered = false; // Is the option the currently hovered in its menu

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
