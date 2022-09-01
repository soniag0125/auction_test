/**
 * 
 */
package trio.auction.dto;

import java.math.BigDecimal;

/**
 * @author sonia.guama
 *
 */
public class WinnerAuctionDto {

	private BidderDto bidder;
	private Integer winnerCounter;
	private BigDecimal winnerBidAmount;

	public WinnerAuctionDto(BidderDto bidder, Integer winnerCounter, BigDecimal winnerBidAmount) {
		this.bidder = bidder;
		this.winnerCounter = winnerCounter;
		this.winnerBidAmount = winnerBidAmount;
	}

	public WinnerAuctionDto(BidderDto bidder) {
		this.bidder = bidder;
	}

	public WinnerAuctionDto() {
	}

	public BidderDto getBidder() {
		return bidder;
	}

	public void setBidder(BidderDto bidder) {
		this.bidder = bidder;
	}

	public Integer getWinnerCounter() {
		return winnerCounter;
	}

	public void setWinnerCounter(Integer winnerCounter) {
		this.winnerCounter = winnerCounter;
	}

	public BigDecimal getWinnerBidAmount() {
		return winnerBidAmount;
	}

	public void setWinnerBidAmount(BigDecimal winnerBidAmount) {
		this.winnerBidAmount = winnerBidAmount;
	}

	public String buildResponse() {
		return "Winner: ".concat(getBidder().getBidderName()).concat(", ").concat("Winning Amt: ")
				.concat(getWinnerBidAmount().toString());
	}

}
