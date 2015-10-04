package es.danirod.rectball.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import es.danirod.rectball.RectballGame;
import es.danirod.rectball.actors.StatsTable;
import es.danirod.rectball.listeners.ScreenJumper;

/**
 * Statistics screen.
 */
public class StatisticsScreen extends MenuScreen {

    public StatisticsScreen(RectballGame game) {
        super(game);
    }

    @Override
    public void setUpInterface(Table table) {
        final TextButton backButton = newButton("Back");
        final Label stats = newLabel("Stats");

        table.pad(20);
        table.add(backButton).align(Align.left).expandX();
        table.add(stats).align(Align.center).expandX().row();

        StatsTable statsTable = new StatsTable(game.statistics, stats.getStyle(), stats.getStyle());

        ScrollPane.ScrollPaneStyle style = new ScrollPane.ScrollPaneStyle();
        ScrollPane pane = new ScrollPane(statsTable, style);
        table.add(pane).colspan(2).align(Align.topLeft).expand().fill();

        backButton.addCaptureListener(new ScreenJumper(game, Screens.MAIN_MENU));
    }

    @Override
    public int getID() {
        return Screens.STATISTICS;
    }

}
