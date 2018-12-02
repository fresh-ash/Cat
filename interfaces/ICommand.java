package com.mygdx.game.interfaces;

import com.mygdx.game.model.Board;
import com.mygdx.game.model.entity.Pill;

public interface ICommand {

    void execute(Controllable controllable);
}
