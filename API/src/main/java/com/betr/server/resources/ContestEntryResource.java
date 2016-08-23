package com.betr.server.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.betr.server.domain.User;
import com.betr.server.exception.PaymentException;
import com.betr.server.game.domain.Contest;
import com.betr.server.game.domain.NFLContestEntry;
import com.betr.server.game.domain.NFLPlayer;
import com.betr.server.payment.PaymentExternalAPI;
import com.betr.server.repo.ContestEntryRepository;
import com.betr.server.repo.ContestRepository;
import com.betr.server.util.UserUtil;

@Controller
public class ContestEntryResource {

	@RequestMapping(value = "api/contest/entry/nfl", method = RequestMethod.GET) 
	public @ResponseBody List<NFLContestEntry> retrieveAllNFLContestEntries() {
		ContestEntryRepository contestEntryRepo = new ContestEntryRepository();
		User user = UserUtil.getUser();
		return contestEntryRepo.getNFLContestsForUser(user.getId());
	}
	
	@RequestMapping(value = "api/contest/entry/nfl/{contestId}", method = RequestMethod.GET) 
	public @ResponseBody List<NFLContestEntry> retrieveCurrentContestStatistics() {
		ContestEntryRepository contestEntryRepo = new ContestEntryRepository();
		User user = UserUtil.getUser();
		return contestEntryRepo.getNFLContestsForUser(user.getId());
	}
	
	@RequestMapping(value = "/api/contest/entry/nfl", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> submitNFLContest(@RequestBody NFLContestEntry contestEntry) {
		ContestRepository contestRepo = new ContestRepository();
		ContestEntryRepository contestEntryRepo = new ContestEntryRepository();
		
		Contest contest = contestRepo.retrieveContestById(contestEntry.getContestId());
		if(contest == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contest not found!");
		}
		long salary = 0L;
		for(NFLPlayer player : contestEntry.getPlayers()) {
			salary =+ player.getValue();
		}
		if(salary > contest.getSalaryCap()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contest entry over salary cap for contest!");
		}
		User user = UserUtil.getUser();
		PaymentExternalAPI paymentProcessor = new PaymentExternalAPI(user.getId());
		
		try {
			if(paymentProcessor.userHasFunds(contest.getBuyIn())) {
				contestEntryRepo.addContestEntry(contestEntry);
				paymentProcessor.processContestEntry(contestEntry);
			}
		} catch (PaymentException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}
