package io.github.nosequel.katakuna.menu.paginated;

import io.github.nosequel.katakuna.button.Button;
import io.github.nosequel.katakuna.button.impl.ButtonBuilder;
import io.github.nosequel.katakuna.button.impl.pagination.PaginationButton;
import io.github.nosequel.katakuna.button.impl.pagination.PaginationType;
import io.github.nosequel.katakuna.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PaginatedMenu extends Menu {

    private int page;

    /**
     * Constructor for creating a new PaginatedMenu {@link Menu}
     *
     * @param title the title
     * @param size  the size
     */
    public PaginatedMenu(Player player, String title, int size) {
        super(player, title, size + 9);
    }

    @Override
    public void updateMenu() {
        final List<Button> buttons = new ArrayList<>(Arrays.asList(
                new PaginationButton(0, PaginationType.PREVIOUS, this),
                new PaginationButton(8, PaginationType.NEXT, this))
        );

        buttons.addAll(this.getButtonsInRange());


        this.updateMenu(buttons);
    }

    /**
     * Get all the buttons in the current range
     *
     * @return the buttons
     */
    public List<Button> getButtonsInRange() {
        return this.getButtons().stream()
                .filter(button -> button.getIndex() >= getSize() && button.getIndex() <= getSize() * page)
                .map(button -> new ButtonBuilder(button).setIndex(button.getIndex() + 9))
                .collect(Collectors.toList());
    }
}