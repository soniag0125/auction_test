/**
 * 
 */
package trio.auction.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.stereotype.Service;

import trio.auction.dto.BidderAuctionDto;
import trio.auction.dto.BidderDto;
import trio.auction.dto.WinnerAuctionDto;

/**
 * @author sonia.guama
 *
 */
@Service
public class AuctionService {

	private List<BidderDto> bidders = new ArrayList<>();

	public WinnerAuctionDto getWinner() {
		WinnerAuctionDto winnerAuction;
		BidderDto startBidWinner = validateStartBidWinner(bidders);
		if (startBidWinner.getBidderName() == null) {
			winnerAuction = calculateWinner().get(0);
		} else {
			winnerAuction = new WinnerAuctionDto(startBidWinner);
		}
		return winnerAuction;
	}

	private List<WinnerAuctionDto> calculateWinner() {
		List<WinnerAuctionDto> winner = new ArrayList<>();
		List<BidderAuctionDto> bidderAuctions = generateBidderAuctions();

		if (!bidderAuctions.isEmpty()) {
			BidderAuctionDto maxtBid = getMaxBid(bidderAuctions);
			BigDecimal bidValueWithLastIncrement = getLastBidWithLastIncrement(maxtBid);

			boolean isExistsAnotherMax = contains(bidderAuctions, bidValueWithLastIncrement);
			if (isExistsAnotherMax) {
				WinnerAuctionDto winnerBid = new WinnerAuctionDto(maxtBid.getBidder(), maxtBid.getMaxCounter(),
						maxtBid.getMaxBidAmount());
				winner.add(winnerBid);
			} else if (winner.isEmpty()) {
				Integer winnerCounter = 0;
				do {
					winnerCounter = winnerCounter + 1;
					BigDecimal bidValueInEachIncrement = getLastBidWithLastIncrement(maxtBid);
					BidderAuctionDto bidderAuctionReview = new BidderAuctionDto(maxtBid.getBidder(), winnerCounter,
							bidValueInEachIncrement);
					List<BidderAuctionDto> bidderAuctionReviews = new ArrayList<>();
					bidderAuctionReviews.addAll(bidderAuctions);
					bidderAuctionReviews.remove(maxtBid);
					bidderAuctionReviews.add(bidderAuctionReview);
					BidderAuctionDto maxtBidReview = getMaxBid(bidderAuctionReviews);
					if (!maxtBidReview.equals(maxtBid)) {
						WinnerAuctionDto winnerBid = new WinnerAuctionDto(maxtBid.getBidder(),
								Math.subtractExact(maxtBid.getMaxCounter(), (winnerCounter - 1)),
								bidValueInEachIncrement.add(maxtBid.getBidder().getAutoIncrementAmount()));
						winner.add(winnerBid);
					}
				} while (maxtBid.getMaxCounter() >= 1 && winner.isEmpty());
			}

		}
		return winner;
	}

	private BigDecimal getLastBidWithLastIncrement(BidderAuctionDto maxtBid) {
		return maxtBid.getMaxBidAmount().subtract(maxtBid.getBidder().getAutoIncrementAmount());
	}

	private BidderAuctionDto getMaxBid(List<BidderAuctionDto> bidderAuctions) {
		return bidderAuctions.stream().max(Comparator.comparing(BidderAuctionDto::getMaxBidAmount))
				.orElseThrow(NoSuchElementException::new);
	}

	private List<BidderAuctionDto> generateBidderAuctions() {
		List<BidderAuctionDto> bidderAuctions = new ArrayList<>();
		bidders.forEach(bidder -> {
			Integer maxCounter = (bidder.getMaxBid().subtract(bidder.getStartBid()))
					.divide(bidder.getAutoIncrementAmount(), 0, RoundingMode.DOWN).intValue();
			BigDecimal maxAmount = bidder.getAutoIncrementAmount().multiply(BigDecimal.valueOf(maxCounter));
			BidderAuctionDto bidderAuction = new BidderAuctionDto(bidder, maxCounter,
					bidder.getStartBid().add(maxAmount));
			bidderAuctions.add(bidderAuction);
		});
		return bidderAuctions;
	}

	private static boolean contains(List<BidderAuctionDto> list, BigDecimal value) {
		return list.stream().anyMatch(e -> Objects.equals(e.getMaxBidAmount(), value));
	}

	private boolean verifyAllEqualUsingStream() {
		List<BigDecimal> amounts = new ArrayList<>();
		bidders.forEach(bidder -> amounts.add(bidder.getAutoIncrementAmount()));

		return amounts.stream().distinct().count() <= 1;
	}

	private BidderDto validateStartBidWinner(List<BidderDto> bidders) {
		BidderDto bidderWinner = new BidderDto();
		BidderDto maxStartBid = bidders.stream().max(Comparator.comparing(BidderDto::getStartBid))
				.orElseThrow(NoSuchElementException::new);
		BidderDto maxBid = bidders.stream().max(Comparator.comparing(BidderDto::getMaxBid))
				.orElseThrow(NoSuchElementException::new);
		BidderDto maxAmount = bidders.stream().max(Comparator.comparing(BidderDto::getAutoIncrementAmount))
				.orElseThrow(NoSuchElementException::new);

		if (maxStartBid.equals(maxBid) && verifyAllEqualUsingStream()
				|| (maxStartBid.equals(maxBid) && maxStartBid.equals(maxAmount))) {
			bidderWinner = maxStartBid;
		}
		return bidderWinner;

	}

	public List<BidderDto> getBidders() {
		return bidders;
	}

	public void setBidders(List<BidderDto> bidders) {
		this.bidders = bidders;
	}

}
