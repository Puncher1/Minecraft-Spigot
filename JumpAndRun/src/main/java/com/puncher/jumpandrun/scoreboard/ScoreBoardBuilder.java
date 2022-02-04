package com.puncher.jumpandrun.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.*;

public class ScoreBoardBuilder {

    ScoreboardManager manager = Bukkit.getScoreboardManager();
    Scoreboard board = manager.getNewScoreboard();
    Objective obj = board.registerNewObjective("test", "dummy", "test");
    obj.setDisplaySlot(DisplaySlot.SIDEBAR);
}
