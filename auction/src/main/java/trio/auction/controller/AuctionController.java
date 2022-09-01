/**
 * 
 */
package trio.auction.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trio.auction.dto.BidderDto;
import trio.auction.dto.WinnerAuctionDto;
import trio.auction.services.AuctionService;

/**
 * @author sonia.guama
 *
 */

@RestController
@RequestMapping("api/virtualAuction")
public class AuctionController {

	private List<BidderDto> bidders = new ArrayList<>();

	@Autowired
	private AuctionService auctionService;

	@PostMapping("/virtualBid/v1")
	public String addBid(@Valid @RequestBody BidderDto bidder) {
		Optional<BidderDto> findBidder = Optional.ofNullable(bidders.stream()
				.filter(bidderN -> bidder.getBidderName().equals(bidderN.getBidderName())).findAny().orElse(null));
		if (findBidder.isPresent()) {
			return "This bidder is already in the auction.";
		} else if (dataValidation(bidder) != null) {
			return dataValidation(bidder);
		} else {
			this.setBidders(bidder);

			return "Bidder added";
		}
	}

	@GetMapping("/getBidders/v1")
	public List<BidderDto> getBidders() {
		return bidders;
	}

	@GetMapping("/startAuction/v1")
	public String getWinner() {
		auctionService.setBidders(bidders);
		this.bidders = new ArrayList<>();
		WinnerAuctionDto winner = auctionService.getWinner();

		if (winner != null) {
			return winner.buildResponse();
		}
		return "";
	}

	public void setBidders(BidderDto bidder) {
		this.bidders.add(bidder);
	}

	private String dataValidation(BidderDto bidder) {
		String wrongMessage = "Wrong value.";
		if (bidder.getBidderName().isEmpty()) {
			return "Name is required.";
		}
		if (bidder.getAutoIncrementAmount() == null || bidder.getStartBid().compareTo(BigDecimal.ZERO) <= 0) {
			return wrongMessage;
		}
		if (bidder.getMaxBid() == null || bidder.getMaxBid().compareTo(BigDecimal.ZERO) <= 0) {
			return wrongMessage;
		}
		if (bidder.getAutoIncrementAmount() == null
				|| bidder.getAutoIncrementAmount().compareTo(BigDecimal.ZERO) <= 0) {
			return wrongMessage;
		}
		return null;
	}

}
