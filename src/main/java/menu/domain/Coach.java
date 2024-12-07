package menu.domain;

public class Coach {
    private final CoachName coachName;
    private CantEatMenu cantEatMenu;

    public Coach(final CoachName coachName) {
        this.coachName = coachName;
    }

    public void putCantEatMenu(CantEatMenu cantEatMenu) {
        this.cantEatMenu = cantEatMenu;
    }

    public CoachName getCoachName() {
        return coachName;
    }

    public CantEatMenu getCantEatMenu() {
        return cantEatMenu;
    }
}
