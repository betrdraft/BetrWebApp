package com.betr.server.resources;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betr.server.game.domain.Contest;
import com.betr.server.repo.ContestRepository;

@Controller
public class ContestResource {

	@RequestMapping(value = "/api/retrieve/contests/all", method = RequestMethod.GET)
	public @ResponseBody List<Contest> retrieveAllContests() {
		ContestRepository repo = new ContestRepository();

		return repo.retrieveAllContests();
	}

	@RequestMapping(value = "/api/insert/contest", method = RequestMethod.POST)
	public void insertContest(@RequestBody Contest contest) {
		ContestRepository repo = new ContestRepository();

		repo.createContest(contest);
	}

}
