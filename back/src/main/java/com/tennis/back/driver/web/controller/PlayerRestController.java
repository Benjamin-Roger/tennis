package com.tennis.back.driver.web.controller;

import com.tennis.back.driver.repository.LocalMemoryPlayerRepository;
import com.tennis.back.interfaceAdapter.controller.PlayerController;
import com.tennis.back.interfaceAdapter.gateway.PlayerGateway;
import com.tennis.back.interfaceAdapter.presenter.GetPlayerDetailResponse;
import com.tennis.back.interfaceAdapter.presenter.GetPlayersSummariesResponse;
import com.tennis.back.interfaceAdapter.presenter.PlayerPresenter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/player")
public class PlayerRestController {

    private PlayerController controller;

    public PlayerRestController(LocalMemoryPlayerRepository repository) {
        this.controller = new PlayerController(new PlayerGateway(repository), new PlayerPresenter());
    }

    @CrossOrigin
    @GetMapping("/summaries")
    public @ResponseBody GetPlayersSummariesResponse getPlayersSummaries() {
        this.controller.getPlayerSummaries();
        return this.controller.getPresenter().getPlayerSummariesResponse();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public @ResponseBody GetPlayerDetailResponse getPlayerById(@PathVariable String id) {
        this.controller.getPlayerDetail(id);
        return this.controller.getPresenter().getPlayerDetailResponse();
    }

}
