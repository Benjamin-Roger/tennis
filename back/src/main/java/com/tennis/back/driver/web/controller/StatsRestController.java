package com.tennis.back.driver.web.controller;

import com.tennis.back.driver.repository.LocalMemoryPlayerRepository;
import com.tennis.back.interfaceAdapter.controller.StatsController;
import com.tennis.back.interfaceAdapter.gateway.PlayerGateway;
import com.tennis.back.interfaceAdapter.presenter.GeneralStatsDTOModel;
import com.tennis.back.interfaceAdapter.presenter.StatsPresenter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/stats")
public class StatsRestController {

    private StatsController controller;

    public StatsRestController(LocalMemoryPlayerRepository repository) {
        this.controller = new StatsController(new PlayerGateway(repository));
    }

    @CrossOrigin
    @GetMapping
    public @ResponseBody GeneralStatsDTOModel getPlayersSummaries() {
        StatsPresenter presenter = new StatsPresenter();
        this.controller.getGeneralStats(presenter);
        return presenter.getDTOModel();
    }

}
